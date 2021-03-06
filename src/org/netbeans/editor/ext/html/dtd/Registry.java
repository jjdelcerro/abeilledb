/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2000 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.editor.ext.html.dtd;

import java.util.*;
import java.io.Reader;
import java.lang.ref.WeakReference;

/**
 * This class stores references to DTDReaderProviders. It also acts as a cache
 * for parsed DTDs and as the only factory for creating DTDs.
 * 
 * @author Petr Nejedly
 * @version 1.0
 */
public class Registry {

	/**
	 * The map [DTD public identifier -> Weak reference(DTD)] Works as a DTD
	 * cache.
	 */
	private static Map dtdMap = new HashMap();

	/** List of all registered providers we use for parsing DTDs with */
	private static List providers = new ArrayList();

	/** The list of all listeners for DTD invalidate events. */
	private static LinkedList listeners = new LinkedList();

	/**
	 * The map [DTDReaderProvider -> Set[String]] representing the public
	 * identifiers of the DTDs parsed by this provider] Used when the provider
	 * is invalidated/removed.
	 */
	private static Map provider2dtds = new HashMap();

	/**
	 * Add a DTD.InvalidateListener to the listener list. The listener will be
	 * notified when any DTD is changed. The listeners are referenced weakly, so
	 * they are removed automatically when they are no longer in use.
	 * 
	 * @param listener
	 *            The DTD.InvalidateListener to be added.
	 */
	public void addInvalidateListener(InvalidateListener listener) {
		synchronized (listeners) {
			listeners.add(new WeakReference(listener));
		}
	}

	/**
	 * Remove a DTD.InvalidateListener from teh listener list. The listeners are
	 * referenced weakly, so they are removed automatically when they are no
	 * longer in use.
	 * 
	 * @param listener
	 *            The DTD.InvalidateListener to be removed.
	 */
	public void removeInvalidateListener(InvalidateListener listener) {
		synchronized (listeners) {
			// Iterator on LinkedList allows remove()
			for (Iterator it = listeners.iterator(); it.hasNext();) {
				WeakReference ref = (WeakReference) it.next();
				InvalidateListener obj = (InvalidateListener) ref.get();
				// remove required or gc()ed references
				if (obj == null || obj == listener)
					it.remove();
			}
		}
	}

	/**
	 * Report an invalidation event to all registered listeners.
	 * 
	 * @param identifiers
	 *            The set of Strings representing the public identifiers of
	 *            invalidated DTDs.
	 */
	public static void fireInvalidateEvent(Set identifiers) {
		// 1. clean up our cache
		for (Iterator it = identifiers.iterator(); it.hasNext();) {
			dtdMap.remove(it.next());
		}

		// 2. gather all valid listeners, throw away those already dead.
		java.util.List targets = new ArrayList();
		synchronized (listeners) {
			for (Iterator it = listeners.iterator(); it.hasNext();) {
				WeakReference ref = (WeakReference) it.next();
				InvalidateListener obj = (InvalidateListener) ref.get();
				if (obj == null) {
					it.remove();
				} else {
					targets.add(obj);
				}
			}
		}

		// 3. distribute the event
		InvalidateEvent evt = new InvalidateEvent(identifiers);
		for (Iterator it = targets.iterator(); it.hasNext();) {
			InvalidateListener l = (InvalidateListener) it.next();
			l.dtdInvalidated(evt);
		}
	}

	/**
	 * Add DTDReaderProvider. It can be then used for parsing the DTD.
	 * 
	 * @param provider
	 *            The ReaderProvider capable of providing any number of streams
	 *            for any public identifier. It shall be able to provide streams
	 *            for all public identifiers referenced from its streams.
	 */
	public static void registerReaderProvider(ReaderProvider provider) {
		providers.add(provider);
	}

	/**
	 * Destroy all DTDs parsed from Readers provided by this provider and notify
	 * all registered users of such DTDs they are invalid now
	 */
	public static void invalidateReaderProvider(ReaderProvider provider) {
		Set identifiers = (Set) provider2dtds.get(provider);

		if (identifiers != null) {
			// 2. clean up our cache and notify all registered users
			// of affected DTDs.
			fireInvalidateEvent(identifiers);

			// 3. free the provider from the parsed refistry
			provider2dtds.remove(provider);
		}
	}

	/**
	 * Remove given ReaderProvider from usage, destroy all DTDs parsed from
	 * Readers provided by this provider and notify all registered users of such
	 * DTDs they are invalid now
	 */
	public static void unregisterReaderProvider(ReaderProvider provider) {
		// remove it from our provider list to not use it any more
		providers.remove(provider);

		invalidateReaderProvider(provider);
	}

	/**
	 * The "smart" method for accessing the items in the table, cares of the
	 * weak indirection and cleaning up the gc()ed cells.
	 */
	private static DTD getWeak(String identifier) {
		WeakReference ref = (WeakReference) dtdMap.get(identifier);
		if (ref == null) // don't know even the key
			return null;

		DTD dtd = (DTD) ref.get();
		if (dtd == null) // gc()ed in the mean time, clean up the table
			dtdMap.remove(identifier);

		return dtd;
	}

	/** The method for storing an item into the map as a weak reference */
	private static void putWeak(String identifier, DTD dtd) {
		dtdMap.put(identifier, new WeakReference(dtd));
	}

	private static ReaderProvider getProvider(String identifier, String fileName) {
		for (Iterator i = providers.iterator(); i.hasNext();) {
			ReaderProvider prov = (ReaderProvider) i.next();
			Reader reader = prov.getReaderForIdentifier(identifier, fileName);
			if (reader != null)
				return prov;
		}
		return null;
	}

	private static DTD parseDTD(String identifier, String fileName) {
		ReaderProvider prov = getProvider(identifier, fileName);
		if (prov == null)
			return null;
		try {
			DTD dtd = new DTDParser().createDTD(prov, identifier, fileName);
			if (dtd != null) {
				Set dtdSet = (Set) provider2dtds.get(prov);
				if (dtdSet == null) {
					dtdSet = new HashSet();
					provider2dtds.put(prov, dtdSet);
				}

				dtdSet.add(identifier);
				putWeak(identifier, dtd);
			}
			return dtd;
		} catch (DTDParser.WrongDTDException exc) {
			// System.err.println("TODO (DTD.java:90): Notify the error during parsing."
			// );
			return null;
		}
	}

	/**
	 * Get the DTD identified by its public identifier, exact match of
	 * identifier is required.
	 * 
	 * @param identifier
	 *            public identifier of required DTD, e.g.
	 *            "-//W3C//DTD HTML 4.01//EN"
	 * @param fileName
	 *            the name of file for this DTD, is used only as a helper for
	 *            lookup, could be <CODE>null</CODE>, or could be e.g. URL to
	 *            the DTD on the internet, in which case proper
	 *            DTDReaderProvider could try to fetch it from there.
	 * @return implementation of DTD interface for given public identifier, or
	 *         null, if no such DTD is cached and could not be created from
	 *         registered DTDReaderProviders.
	 */
	public static DTD getDTD(String identifier, String fileName) {
		DTD dtd = getWeak(identifier);

		if (dtd == null)
			dtd = parseDTD(identifier, fileName);

		return dtd;
	}
}
