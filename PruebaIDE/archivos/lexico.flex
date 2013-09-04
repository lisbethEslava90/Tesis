package interprete;

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
	
	public Scanner(String consulta, SymbolFactory sf){
		this(new java.io.StringReader(consulta));
		this.sf=sf;
		lineanum=0;
		debug=true;
	}

%}
%eofval{
    return sf.newSymbol("EOF",sym.EOF);
%eofval}

digito			= [0-9]
numero			= {digito}+("."{digito}+)?
fecha			= {digito}+("/"){digito}+("/"){digito}+	
letra			= [a-zA-Z]
identificador	= {letra}([a-zA-Z0-9.%$_#])*
nuevalinea		= \n | \n\r | \r\n
espacio			= [ \t]+

%%
"SEL"           { if(debug) 
			return sf.newSymbol("SEL",sym.SEL);
			}
"PRO"           { if(debug) 
			return sf.newSymbol("PRO",sym.PRO);
			}
"UNI"           { if(debug) 
			return sf.newSymbol("UNI",sym.UNI);
			}
"DIF"           { if(debug) 
			return sf.newSymbol("DIF",sym.DIF);
			}
"PROC"          { if(debug) 
			return sf.newSymbol("PROC",sym.PROC);
			}
"INT"           { if(debug) 
			return sf.newSymbol("INT",sym.INT);
			}
"DIV"           { if(debug) 
			return sf.newSymbol("DIV",sym.DIV);
			}
"REUN"          { if(debug)
			return sf.newSymbol("REUN",sym.REUN);
			}
"AND"             { if(debug) 
			return sf.newSymbol("AND",sym.AND);
			}						
"NOT"             { if(debug) 
			return sf.newSymbol("NOT",sym.NOT);
			}						
"OR"             { if(debug) 
			return sf.newSymbol("OR",sym.OR);
			}						
"="             { if(debug) 
			return sf.newSymbol("EQ",sym.EQ);
			}	
"!="             { if(debug) 
			return sf.newSymbol("DIFERENTE",sym.DIFERENTE);
			}			
">"             { if(debug) 
			return sf.newSymbol("MAYOR",sym.MAYOR);
			}	
">="             { if(debug) 
			return sf.newSymbol("MAYOREQ",sym.MAYOREQ);
			}	
"<"             { if(debug) 
			return sf.newSymbol("MENOR",sym.MENOR);
			}	
"<="             { if(debug) 
			return sf.newSymbol("MENOREQ",sym.MENOREQ);
			}			
"("             {	if(debug) 
			return sf.newSymbol("IPAREN",sym.IPAREN);
			}
")"             {	if(debug) 
			return sf.newSymbol("DPAREN",sym.DPAREN);
			}
","             {	if(debug) 
			return sf.newSymbol("COMA",sym.COMA);
			}
"\""			{	if(debug) 
				return sf.newSymbol("COMI",sym.COMI);
			}
"+"			{	if(debug) 
				return sf.newSymbol("SUMA",sym.SUMA);
			}			
"-"			{	if(debug) 
				return sf.newSymbol("RESTA",sym.RESTA);
			}			
"*"			{	if(debug) 
				return sf.newSymbol("MULTI",sym.MULTI);
			}			
"/"			{	if(debug) 
				return sf.newSymbol("DIVI",sym.DIVI);
			}			
{numero}        {	if(debug) 
			return sf.newSymbol("NUM",sym.NUM,new Integer(yytext()));
			}
{identificador}	{	if(debug) 
				return sf.newSymbol("ID",sym.ID,new String(yytext()));
			}
{fecha}			{	if(debug) 
				return sf.newSymbol("FECHA",sym.FECHA,new String(yytext()));
			}	
{nuevalinea}	{	/*if(debug) 
			return sf.newSymbol("NUEVA", sym.NUEVA);*/
			}			
{espacio}    	{ /* saltos espacios en blanco*/}
