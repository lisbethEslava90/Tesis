package Interprete;

import java_cup.runtime.SymbolFactory;

%%
%cup
%class Scanner
%{
	private SymbolFactory sf;
	private int lineanum;
	private boolean debug;

	public Scanner(java.io.InputStream r, SymbolFactory sf){
		this(r);
		this.sf=sf;
		lineanum=0;
		debug=true;
	}

%}
%eofval{
    return sf.newSymbol("EOF",sym.EOF);
%eofval}

digito		= [0-9]
numero		= {digito}+("."{digito}+)?
letra			= [a-zA-Z]
identificador	= {letra}([a-zA-Z0-9.%$_])*
nuevalinea		= \n | \n\r | \r\n
espacio		= [ \t]+

%%
"SEL"           { if(debug) System.out.println("token SEL");
			return sf.newSymbol("SEL",sym.SEL);
			}
"PRO"           { if(debug) System.out.println("token PRO");
			return sf.newSymbol("PRO",sym.PRO);
			}
"UNI"           { if(debug) System.out.println("token UNI");
			return sf.newSymbol("UNI",sym.UNI);
			}
"DIF"           { if(debug) System.out.println("token DIF");
			return sf.newSymbol("DIF",sym.DIF);
			}
"PROC"          { if(debug) System.out.println("token PROC");
			return sf.newSymbol("PROC",sym.PROC);
			}
"INT"           { if(debug) System.out.println("token INT");
			return sf.newSymbol("INT",sym.INT);
			}
"DIV"           { if(debug) System.out.println("token DIV");
			return sf.newSymbol("DIV",sym.DIV);
			}
"REUN"          { if(debug)System.out.println("token REUN");
			return sf.newSymbol("REUN",sym.REUN);
			}
"="             { if(debug) System.out.println("token EQ");
			return sf.newSymbol("EQ",sym.EQ);
			}	
"!="             { if(debug) System.out.println("token DIFERENTE");
			return sf.newSymbol("DIFERENTE",sym.DIFERENTE);
			}			
">"             { if(debug) System.out.println("token MAYOR");
			return sf.newSymbol("MAYOR",sym.MAYOR);
			}	
">="             { if(debug) System.out.println("token MAYOR EQ");
			return sf.newSymbol("MAYOREQ",sym.MAYOREQ);
			}	
"<"             { if(debug) System.out.println("token MENOR");
			return sf.newSymbol("MENOR",sym.MENOR);
			}	
"<="             { if(debug) System.out.println("token MENOREQ");
			return sf.newSymbol("MENOREQ",sym.MENOREQ);
			}			
"("             {	if(debug) System.out.println("token IPAREN");
			return sf.newSymbol("IPAREN",sym.IPAREN);
			}
")"             {	if(debug) System.out.println("token DPAREN");
			return sf.newSymbol("DPAREN",sym.DPAREN);
			}
","             {	if(debug) System.out.println("token COMA");
			return sf.newSymbol("COMA",sym.COMA);
			}
"\""			{	if(debug) System.out.println("token COMILLAS");
				return sf.newSymbol("COMI",sym.COMI);
			}
"."             {	if(debug) System.out.println("token PUNTO");
			return sf.newSymbol("PUNTO",sym.PUNTO);
			}
{numero}        {	if(debug) System.out.println("token NUM");
			return sf.newSymbol("NUM",sym.NUM,new Integer(yytext()));
			}
{identificador}	{	if(debug) System.out.println("token ID");
				return sf.newSymbol("ID",sym.ID,new String(yytext()));
			}
{nuevalinea}	{	if(debug) System.out.println(" ");
			return sf.newSymbol("NUEVA", sym.NUEVA);
			}			
{espacio}    	{ /* saltos espacios en blanco*/}
