/* Generated By:JavaCC: Do not edit this line. MySQLForeignKeyParser.java */
package com.jeta.plugins.abeille.mysql.parsers;

import java.util.Collection;
import java.util.ArrayList;

public class MySQLForeignKeyParser implements MySQLForeignKeyParserConstants {
	private ArrayList m_localcols = new ArrayList();
	private ArrayList m_refcols = new ArrayList();
	private String m_reftable;
	private String m_refcatalog;
	private String m_updateaction;
	private String m_deleteaction;

	public String getUpdateAction() {
		return m_updateaction;
	}

	public String getDeleteAction() {
		return m_deleteaction;
	}

	public String[] getLocalColumns() {
		return (String[]) m_localcols.toArray(new String[0]);
	}

	public String[] getReferenceColumns() {
		return (String[]) m_refcols.toArray(new String[0]);
	}

	public String getReferenceTable() {
		return m_reftable;
	}

	public String getReferenceCatalog() {
		return m_refcatalog;
	}

	/**
	 * Invoke parse to start the parse operation
	 */
	final public void parse() throws ParseException {
		Token x = null;
		jj_consume_token(20);
		getColumns(true);
		jj_consume_token(REFER);

		x = jj_consume_token(ID);
		m_refcatalog = x.image;
		jj_consume_token(21);
		x = jj_consume_token(ID);
		m_reftable = x.image;
		jj_consume_token(20);
		getColumns(false);
		getActions();
	}

	final public void getColumns(boolean local) throws ParseException {
		Token x = null;
		if (jj_2_1(2)) {
			x = jj_consume_token(ID);
			if (local)
				m_localcols.add(x.image);
			else
				m_refcols.add(x.image);
			getColumns(local);
		} else if (jj_2_2(2)) {
			jj_consume_token(22);
		} else {
			jj_consume_token(-1);
			throw new ParseException();
		}
	}

	final public void getActions() throws ParseException {
		Token x = null;
		String action;
		if (jj_2_3(2)) {
			jj_consume_token(0);
		} else if (jj_2_4(2)) {
			jj_consume_token(ON_DELETE);
			action = getAction();
			m_deleteaction = action;
			getActions();
		} else if (jj_2_5(2)) {
			jj_consume_token(ON_UPDATE);
			action = getAction();
			m_updateaction = action;
			getActions();
		} else {
			jj_consume_token(-1);
			throw new ParseException();
		}
	}

	final public String getAction() throws ParseException {
		Token x = null;
		if (jj_2_6(2)) {
			x = jj_consume_token(CASCADE);
			{
				if (true)
					return x.image;
			}
		} else if (jj_2_7(2)) {
			x = jj_consume_token(SET_NULL);
			{
				if (true)
					return x.image;
			}
		} else if (jj_2_8(2)) {
			x = jj_consume_token(NO_ACTION);
			{
				if (true)
					return x.image;
			}
		} else if (jj_2_9(2)) {
			x = jj_consume_token(RESTRICT);
			{
				if (true)
					return x.image;
			}
		} else {
			jj_consume_token(-1);
			throw new ParseException();
		}
		throw new Error("Missing return statement in function");
	}

	final private boolean jj_2_1(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		boolean retval = !jj_3_1();
		jj_save(0, xla);
		return retval;
	}

	final private boolean jj_2_2(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		boolean retval = !jj_3_2();
		jj_save(1, xla);
		return retval;
	}

	final private boolean jj_2_3(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		boolean retval = !jj_3_3();
		jj_save(2, xla);
		return retval;
	}

	final private boolean jj_2_4(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		boolean retval = !jj_3_4();
		jj_save(3, xla);
		return retval;
	}

	final private boolean jj_2_5(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		boolean retval = !jj_3_5();
		jj_save(4, xla);
		return retval;
	}

	final private boolean jj_2_6(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		boolean retval = !jj_3_6();
		jj_save(5, xla);
		return retval;
	}

	final private boolean jj_2_7(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		boolean retval = !jj_3_7();
		jj_save(6, xla);
		return retval;
	}

	final private boolean jj_2_8(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		boolean retval = !jj_3_8();
		jj_save(7, xla);
		return retval;
	}

	final private boolean jj_2_9(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		boolean retval = !jj_3_9();
		jj_save(8, xla);
		return retval;
	}

	final private boolean jj_3_9() {
		if (jj_scan_token(RESTRICT))
			return true;
		if (jj_la == 0 && jj_scanpos == jj_lastpos)
			return false;
		return false;
	}

	final private boolean jj_3_8() {
		if (jj_scan_token(NO_ACTION))
			return true;
		if (jj_la == 0 && jj_scanpos == jj_lastpos)
			return false;
		return false;
	}

	final private boolean jj_3_2() {
		if (jj_scan_token(22))
			return true;
		if (jj_la == 0 && jj_scanpos == jj_lastpos)
			return false;
		return false;
	}

	final private boolean jj_3_7() {
		if (jj_scan_token(SET_NULL))
			return true;
		if (jj_la == 0 && jj_scanpos == jj_lastpos)
			return false;
		return false;
	}

	final private boolean jj_3R_2() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_6()) {
			jj_scanpos = xsp;
			if (jj_3_7()) {
				jj_scanpos = xsp;
				if (jj_3_8()) {
					jj_scanpos = xsp;
					if (jj_3_9())
						return true;
					if (jj_la == 0 && jj_scanpos == jj_lastpos)
						return false;
				} else if (jj_la == 0 && jj_scanpos == jj_lastpos)
					return false;
			} else if (jj_la == 0 && jj_scanpos == jj_lastpos)
				return false;
		} else if (jj_la == 0 && jj_scanpos == jj_lastpos)
			return false;
		return false;
	}

	final private boolean jj_3_6() {
		if (jj_scan_token(CASCADE))
			return true;
		if (jj_la == 0 && jj_scanpos == jj_lastpos)
			return false;
		return false;
	}

	final private boolean jj_3_5() {
		if (jj_scan_token(ON_UPDATE))
			return true;
		if (jj_la == 0 && jj_scanpos == jj_lastpos)
			return false;
		if (jj_3R_2())
			return true;
		if (jj_la == 0 && jj_scanpos == jj_lastpos)
			return false;
		return false;
	}

	final private boolean jj_3R_1() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3_1()) {
			jj_scanpos = xsp;
			if (jj_3_2())
				return true;
			if (jj_la == 0 && jj_scanpos == jj_lastpos)
				return false;
		} else if (jj_la == 0 && jj_scanpos == jj_lastpos)
			return false;
		return false;
	}

	final private boolean jj_3_1() {
		if (jj_scan_token(ID))
			return true;
		if (jj_la == 0 && jj_scanpos == jj_lastpos)
			return false;
		if (jj_3R_1())
			return true;
		if (jj_la == 0 && jj_scanpos == jj_lastpos)
			return false;
		return false;
	}

	final private boolean jj_3_4() {
		if (jj_scan_token(ON_DELETE))
			return true;
		if (jj_la == 0 && jj_scanpos == jj_lastpos)
			return false;
		if (jj_3R_2())
			return true;
		if (jj_la == 0 && jj_scanpos == jj_lastpos)
			return false;
		return false;
	}

	final private boolean jj_3_3() {
		if (jj_scan_token(0))
			return true;
		if (jj_la == 0 && jj_scanpos == jj_lastpos)
			return false;
		return false;
	}

	public MySQLForeignKeyParserTokenManager token_source;
	SimpleCharStream jj_input_stream;
	public Token token, jj_nt;
	private int jj_ntk;
	private Token jj_scanpos, jj_lastpos;
	private int jj_la;
	public boolean lookingAhead = false;
	private boolean jj_semLA;
	private int jj_gen;
	final private int[] jj_la1 = new int[0];
	final private int[] jj_la1_0 = {};
	final private JJCalls[] jj_2_rtns = new JJCalls[9];
	private boolean jj_rescan = false;
	private int jj_gc = 0;

	public MySQLForeignKeyParser(java.io.InputStream stream) {
		jj_input_stream = new SimpleCharStream(stream, 1, 1);
		token_source = new MySQLForeignKeyParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 0; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	public void ReInit(java.io.InputStream stream) {
		jj_input_stream.ReInit(stream, 1, 1);
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 0; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	public MySQLForeignKeyParser(java.io.Reader stream) {
		jj_input_stream = new SimpleCharStream(stream, 1, 1);
		token_source = new MySQLForeignKeyParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 0; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	public void ReInit(java.io.Reader stream) {
		jj_input_stream.ReInit(stream, 1, 1);
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 0; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	public MySQLForeignKeyParser(MySQLForeignKeyParserTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 0; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	public void ReInit(MySQLForeignKeyParserTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 0; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	final private Token jj_consume_token(int kind) throws ParseException {
		Token oldToken;
		if ((oldToken = token).next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		if (token.kind == kind) {
			jj_gen++;
			if (++jj_gc > 100) {
				jj_gc = 0;
				for (int i = 0; i < jj_2_rtns.length; i++) {
					JJCalls c = jj_2_rtns[i];
					while (c != null) {
						if (c.gen < jj_gen)
							c.first = null;
						c = c.next;
					}
				}
			}
			return token;
		}
		token = oldToken;
		jj_kind = kind;
		throw generateParseException();
	}

	final private boolean jj_scan_token(int kind) {
		if (jj_scanpos == jj_lastpos) {
			jj_la--;
			if (jj_scanpos.next == null) {
				jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
			} else {
				jj_lastpos = jj_scanpos = jj_scanpos.next;
			}
		} else {
			jj_scanpos = jj_scanpos.next;
		}
		if (jj_rescan) {
			int i = 0;
			Token tok = token;
			while (tok != null && tok != jj_scanpos) {
				i++;
				tok = tok.next;
			}
			if (tok != null)
				jj_add_error_token(kind, i);
		}
		return (jj_scanpos.kind != kind);
	}

	final public Token getNextToken() {
		if (token.next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		jj_gen++;
		return token;
	}

	final public Token getToken(int index) {
		Token t = lookingAhead ? jj_scanpos : token;
		for (int i = 0; i < index; i++) {
			if (t.next != null)
				t = t.next;
			else
				t = t.next = token_source.getNextToken();
		}
		return t;
	}

	final private int jj_ntk() {
		if ((jj_nt = token.next) == null)
			return (jj_ntk = (token.next = token_source.getNextToken()).kind);
		else
			return (jj_ntk = jj_nt.kind);
	}

	private java.util.Vector jj_expentries = new java.util.Vector();
	private int[] jj_expentry;
	private int jj_kind = -1;
	private int[] jj_lasttokens = new int[100];
	private int jj_endpos;

	private void jj_add_error_token(int kind, int pos) {
		if (pos >= 100)
			return;
		if (pos == jj_endpos + 1) {
			jj_lasttokens[jj_endpos++] = kind;
		} else if (jj_endpos != 0) {
			jj_expentry = new int[jj_endpos];
			for (int i = 0; i < jj_endpos; i++) {
				jj_expentry[i] = jj_lasttokens[i];
			}
			boolean exists = false;
			for (java.util.Enumeration enum1 = jj_expentries.elements(); enum1.hasMoreElements();) {
				int[] oldentry = (int[]) (enum1.nextElement());
				if (oldentry.length == jj_expentry.length) {
					exists = true;
					for (int i = 0; i < jj_expentry.length; i++) {
						if (oldentry[i] != jj_expentry[i]) {
							exists = false;
							break;
						}
					}
					if (exists)
						break;
				}
			}
			if (!exists)
				jj_expentries.addElement(jj_expentry);
			if (pos != 0)
				jj_lasttokens[(jj_endpos = pos) - 1] = kind;
		}
	}

	final public ParseException generateParseException() {
		jj_expentries.removeAllElements();
		boolean[] la1tokens = new boolean[23];
		for (int i = 0; i < 23; i++) {
			la1tokens[i] = false;
		}
		if (jj_kind >= 0) {
			la1tokens[jj_kind] = true;
			jj_kind = -1;
		}
		for (int i = 0; i < 0; i++) {
			if (jj_la1[i] == jj_gen) {
				for (int j = 0; j < 32; j++) {
					if ((jj_la1_0[i] & (1 << j)) != 0) {
						la1tokens[j] = true;
					}
				}
			}
		}
		for (int i = 0; i < 23; i++) {
			if (la1tokens[i]) {
				jj_expentry = new int[1];
				jj_expentry[0] = i;
				jj_expentries.addElement(jj_expentry);
			}
		}
		jj_endpos = 0;
		jj_rescan_token();
		jj_add_error_token(0, 0);
		int[][] exptokseq = new int[jj_expentries.size()][];
		for (int i = 0; i < jj_expentries.size(); i++) {
			exptokseq[i] = (int[]) jj_expentries.elementAt(i);
		}
		return new ParseException(token, exptokseq, tokenImage);
	}

	final public void enable_tracing() {
	}

	final public void disable_tracing() {
	}

	final private void jj_rescan_token() {
		jj_rescan = true;
		for (int i = 0; i < 9; i++) {
			JJCalls p = jj_2_rtns[i];
			do {
				if (p.gen > jj_gen) {
					jj_la = p.arg;
					jj_lastpos = jj_scanpos = p.first;
					switch (i) {
					case 0:
						jj_3_1();
						break;
					case 1:
						jj_3_2();
						break;
					case 2:
						jj_3_3();
						break;
					case 3:
						jj_3_4();
						break;
					case 4:
						jj_3_5();
						break;
					case 5:
						jj_3_6();
						break;
					case 6:
						jj_3_7();
						break;
					case 7:
						jj_3_8();
						break;
					case 8:
						jj_3_9();
						break;
					}
				}
				p = p.next;
			} while (p != null);
		}
		jj_rescan = false;
	}

	final private void jj_save(int index, int xla) {
		JJCalls p = jj_2_rtns[index];
		while (p.gen > jj_gen) {
			if (p.next == null) {
				p = p.next = new JJCalls();
				break;
			}
			p = p.next;
		}
		p.gen = jj_gen + xla - jj_la;
		p.first = token;
		p.arg = xla;
	}

	static final class JJCalls {
		int gen;
		Token first;
		int arg;
		JJCalls next;
	}

}
