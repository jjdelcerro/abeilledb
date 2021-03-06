package com.jeta.abeille.database.model;

import java.util.Comparator;

public class CatalogComparator implements Comparator {

	public CatalogComparator() {

	}

	/**
    *
    */
	public int compare(Object o1, Object o2) {
		// @todo this is probably language specific code
		Catalog f1 = (Catalog) o1;
		Catalog f2 = (Catalog) o2;
		String s1 = f1.getName();
		String s2 = f2.getName();
		int n1 = s1.length(), n2 = s2.length();
		for (int i1 = 0, i2 = 0; i1 < n1 && i2 < n2; i1++, i2++) {
			char c1 = s1.charAt(i1);
			char c2 = s2.charAt(i2);
			if (c1 != c2) {
				c1 = Character.toUpperCase(c1);
				c2 = Character.toUpperCase(c2);
				if (c1 != c2) {
					c1 = Character.toLowerCase(c1);
					c2 = Character.toLowerCase(c2);
					if (c1 != c2)
						return c1 - c2;
				}
			}
		}
		return n1 - n2;
	}
}
