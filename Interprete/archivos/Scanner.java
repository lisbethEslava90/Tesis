/* The following code was generated by JFlex 1.4.1 on 22/07/13 19:54 */

package interprete;

import java_cup.runtime.SymbolFactory;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 22/07/13 19:54 from the specification file
 * <tt>C:/Users/Julio/Dropbox/Tesis/Tesis/Interprete/archivos/lexico.flex</tt>
 */
class Scanner implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0,  8,  6,  0,  0,  7,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     8, 25, 31,  5,  5,  5,  0,  0, 28, 29, 34, 32, 30, 33,  2,  3, 
     1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0,  0, 27, 24, 26,  0, 
     0, 23,  4, 20, 18, 10, 19,  4,  4, 17,  4,  4, 11,  4, 16, 14, 
    12,  4, 13,  9, 21, 15, 22,  4,  4,  4,  4,  0,  0,  0,  0,  5, 
     0,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4, 
     4,  4,  4,  4,  4,  4,  4,  4,  4,  4,  4,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\0\1\5\11\3"+
    "\1\6\1\0\1\7\1\10\1\11\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\2\0\1\4\3\3\1\20\5\3"+
    "\1\21\1\22\1\23\1\1\1\0\1\24\1\25\1\3"+
    "\1\26\1\27\1\30\1\31\1\32\1\33\1\0\1\34"+
    "\1\35\1\36";

  private static int [] zzUnpackAction() {
    int [] result = new int[57];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\43\0\106\0\151\0\214\0\257\0\322\0\365"+
    "\0\u0118\0\u013b\0\u015e\0\u0181\0\u01a4\0\u01c7\0\u01ea\0\u020d"+
    "\0\106\0\u0230\0\u0253\0\u0276\0\106\0\106\0\106\0\106"+
    "\0\106\0\106\0\106\0\u0299\0\u02bc\0\106\0\u02df\0\u0302"+
    "\0\u0325\0\151\0\u0348\0\u036b\0\u038e\0\u03b1\0\u03d4\0\106"+
    "\0\106\0\106\0\u0299\0\u03f7\0\151\0\u041a\0\u043d\0\151"+
    "\0\151\0\151\0\151\0\151\0\151\0\u0460\0\151\0\151"+
    "\0\u0460";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[57];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\0\1\2\1\0\1\3\1\4\1\0\1\5\1\6"+
    "\1\7\1\10\2\4\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\4\4\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\0"+
    "\1\2\1\34\1\35\103\0\2\4\1\0\2\4\3\0"+
    "\17\4\22\0\1\36\41\0\1\36\44\0\1\7\33\0"+
    "\2\4\1\0\2\4\3\0\1\4\1\37\15\4\14\0"+
    "\2\4\1\0\2\4\3\0\4\4\1\40\12\4\14\0"+
    "\2\4\1\0\2\4\3\0\1\4\1\41\15\4\14\0"+
    "\2\4\1\0\2\4\3\0\4\4\1\42\12\4\14\0"+
    "\2\4\1\0\2\4\3\0\7\4\1\43\7\4\14\0"+
    "\2\4\1\0\2\4\3\0\5\4\1\44\11\4\14\0"+
    "\2\4\1\0\2\4\3\0\7\4\1\45\7\4\14\0"+
    "\2\4\1\0\2\4\3\0\10\4\1\46\6\4\14\0"+
    "\2\4\1\0\2\4\3\0\7\4\1\47\7\4\43\0"+
    "\1\50\42\0\1\51\42\0\1\52\13\0\1\53\42\0"+
    "\1\54\42\0\2\4\1\0\2\4\3\0\2\4\1\55"+
    "\14\4\14\0\2\4\1\0\2\4\3\0\5\4\1\56"+
    "\11\4\14\0\2\4\1\0\2\4\3\0\6\4\1\57"+
    "\10\4\14\0\2\4\1\0\2\4\3\0\10\4\1\60"+
    "\6\4\14\0\2\4\1\0\2\4\3\0\14\4\1\61"+
    "\2\4\14\0\2\4\1\0\2\4\3\0\14\4\1\62"+
    "\2\4\14\0\2\4\1\0\2\4\3\0\12\4\1\63"+
    "\2\4\1\64\1\4\14\0\2\4\1\0\2\4\3\0"+
    "\11\4\1\65\5\4\14\0\1\54\1\0\1\66\40\0"+
    "\2\4\1\0\2\4\3\0\13\4\1\67\3\4\14\0"+
    "\2\4\1\0\2\4\3\0\7\4\1\70\7\4\14\0"+
    "\1\71\41\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1155];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\1\1\11\2\1\1\0\12\1\1\11\1\0"+
    "\2\1\7\11\2\0\1\11\11\1\3\11\1\1\1\0"+
    "\11\1\1\0\3\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[57];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
	private SymbolFactory sf;
	private int lineanum;
	private boolean debug;

	public Scanner(java.io.InputStream r, SymbolFactory sf){
		this(r);
		this.sf=sf;
		lineanum=0;
		debug=true;
	}



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Scanner(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Scanner(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzPushbackPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead < 0) {
      return true;
    }
    else {
      zzEndRead+= numRead;
      return false;
    }
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 20: 
          { if(debug) System.out.println("token SEL");
			return sf.newSymbol("SEL",sym.SEL);
          }
        case 31: break;
        case 9: 
          { if(debug) System.out.println("token IPAREN");
			return sf.newSymbol("IPAREN",sym.IPAREN);
          }
        case 32: break;
        case 23: 
          { if(debug) System.out.println("token NOT");
			return sf.newSymbol("NOT",sym.NOT);
          }
        case 33: break;
        case 6: 
          { if(debug) System.out.println("token EQ");
			return sf.newSymbol("EQ",sym.EQ);
          }
        case 34: break;
        case 10: 
          { if(debug) System.out.println("token DPAREN");
			return sf.newSymbol("DPAREN",sym.DPAREN);
          }
        case 35: break;
        case 21: 
          { if(debug) System.out.println("token PRO");
			return sf.newSymbol("PRO",sym.PRO);
          }
        case 36: break;
        case 16: 
          { if(debug) System.out.println("token OR");
			return sf.newSymbol("OR",sym.OR);
          }
        case 37: break;
        case 27: 
          { if(debug) System.out.println("token AND");
			return sf.newSymbol("AND",sym.AND);
          }
        case 38: break;
        case 18: 
          { if(debug) System.out.println("token MAYOR EQ");
			return sf.newSymbol("MAYOREQ",sym.MAYOREQ);
          }
        case 39: break;
        case 11: 
          { if(debug) System.out.println("token COMA");
			return sf.newSymbol("COMA",sym.COMA);
          }
        case 40: break;
        case 1: 
          { if(debug) System.out.println("token NUM");
			return sf.newSymbol("NUM",sym.NUM,new Integer(yytext()));
          }
        case 41: break;
        case 5: 
          { /* saltos espacios en blanco*/
          }
        case 42: break;
        case 12: 
          { if(debug) System.out.println("token COMILLAS");
				return sf.newSymbol("COMI",sym.COMI);
          }
        case 43: break;
        case 24: 
          { if(debug) System.out.println("token INT");
			return sf.newSymbol("INT",sym.INT);
          }
        case 44: break;
        case 26: 
          { if(debug) System.out.println("token DIV");
			return sf.newSymbol("DIV",sym.DIV);
          }
        case 45: break;
        case 25: 
          { if(debug) System.out.println("token DIF");
			return sf.newSymbol("DIF",sym.DIF);
          }
        case 46: break;
        case 28: 
          { if(debug) System.out.println("token PROC");
			return sf.newSymbol("PROC",sym.PROC);
          }
        case 47: break;
        case 14: 
          { if(debug) System.out.println("token RESTA");
				return sf.newSymbol("RESTA",sym.RESTA);
          }
        case 48: break;
        case 13: 
          { if(debug) System.out.println("token SUMA");
				return sf.newSymbol("SUMA",sym.SUMA);
          }
        case 49: break;
        case 15: 
          { if(debug) System.out.println("token MULTIPLICACION");
				return sf.newSymbol("MULTI",sym.MULTI);
          }
        case 50: break;
        case 3: 
          { if(debug) System.out.println("token ID");
				return sf.newSymbol("ID",sym.ID,new String(yytext()));
          }
        case 51: break;
        case 19: 
          { if(debug) System.out.println("token MENOREQ");
			return sf.newSymbol("MENOREQ",sym.MENOREQ);
          }
        case 52: break;
        case 30: 
          { if(debug) System.out.println("token FECHA");
				return sf.newSymbol("FECHA",sym.FECHA,new String(yytext()));
          }
        case 53: break;
        case 2: 
          { if(debug) System.out.println("token DIVISION");
				return sf.newSymbol("DIVI",sym.DIVI);
          }
        case 54: break;
        case 22: 
          { if(debug) System.out.println("token UNI");
			return sf.newSymbol("UNI",sym.UNI);
          }
        case 55: break;
        case 29: 
          { if(debug)System.out.println("token REUN");
			return sf.newSymbol("REUN",sym.REUN);
          }
        case 56: break;
        case 17: 
          { if(debug) System.out.println("token DIFERENTE");
			return sf.newSymbol("DIFERENTE",sym.DIFERENTE);
          }
        case 57: break;
        case 7: 
          { if(debug) System.out.println("token MAYOR");
			return sf.newSymbol("MAYOR",sym.MAYOR);
          }
        case 58: break;
        case 4: 
          { /*if(debug) System.out.println(" ");
			return sf.newSymbol("NUEVA", sym.NUEVA);*/
          }
        case 59: break;
        case 8: 
          { if(debug) System.out.println("token MENOR");
			return sf.newSymbol("MENOR",sym.MENOR);
          }
        case 60: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              {     return sf.newSymbol("EOF",sym.EOF);
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
