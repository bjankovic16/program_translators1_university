
package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}
	
%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" "		{ }
"\b"	{ }
"\t" 	{ }
"\r\n"	{ }
"\f"	{ }

"program"	{ return new_symbol(sym.PROGRAM, yytext()); }
"break"		{ return new_symbol(sym.BREAK, yytext()); }
"class"		{ return new_symbol(sym.CLASS, yytext()); }
"enum"		{ return new_symbol(sym.ENUM, yytext()); }
"else"		{ return new_symbol(sym.ELSE, yytext()); }
"const"		{ return new_symbol(sym.CONST, yytext()); }
"if"		{ return new_symbol(sym.IF, yytext()); }
"do"		{ return new_symbol(sym.DO, yytext()); }
"while"		{ return new_symbol(sym.WHILE, yytext()); }
"new"		{ return new_symbol(sym.NEW, yytext()); }
"print"		{ return new_symbol(sym.PRINT, yytext()); }
"read"		{ return new_symbol(sym.READ, yytext()); }
"return"	{ return new_symbol(sym.RETURN, yytext()); }
"void"		{ return new_symbol(sym.VOID, yytext()); }
"extends"	{ return new_symbol(sym.EXTENDS, yytext()); }
"continue"	{ return new_symbol(sym.CONTINUE, yytext()); }
"foreach"	{ return new_symbol(sym.FOREACH, yytext()); }

"+"			{ return new_symbol(sym.PLUS, yytext()); }
"-"			{ return new_symbol(sym.MINUS, yytext()); }
"*"			{ return new_symbol(sym.MUL, yytext()); }
"/"			{ return new_symbol(sym.DIV, yytext()); }
"%"			{ return new_symbol(sym.REM, yytext()); }
"=="		{ return new_symbol(sym.EQUAL, yytext()); }
"!="		{ return new_symbol(sym.NOTEQUAL, yytext()); }
">"			{ return new_symbol(sym.GREAT, yytext()); }
">="		{ return new_symbol(sym.GREATEQUAL, yytext()); }
"<"			{ return new_symbol(sym.LESS, yytext()); }
"<="		{ return new_symbol(sym.LESSEQUAL, yytext()); }
"&&"		{ return new_symbol(sym.AND, yytext()); }
"||"		{ return new_symbol(sym.OR, yytext()); }
"="			{ return new_symbol(sym.EQUALS, yytext()); }
"++"		{ return new_symbol(sym.INC, yytext()); }	
"--"		{ return new_symbol(sym.DEC, yytext()); }
";"			{ return new_symbol(sym.SEMI, yytext()); }
":"			{ return new_symbol(sym.TWOTACKS, yytext());}
","			{ return new_symbol(sym.COMMA, yytext()); }	
"."			{ return new_symbol(sym.DOT, yytext()); }	
"("			{ return new_symbol(sym.LPAREN, yytext()); }	
")"			{ return new_symbol(sym.RPAREN, yytext()); }
"["			{ return new_symbol(sym.LBRACK, yytext()); }
"]"			{ return new_symbol(sym.RBRACK, yytext()); }	
"{"			{ return new_symbol(sym.LBRACE, yytext()); }	
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"=>"		{ return new_symbol(sym.IMPLICATION, yytext()); }

"//"	{ yybegin(COMMENT); }
<COMMENT> . { yybegin(COMMENT); }
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

("true"|"false")	{ return new_symbol(sym.BOOL, yytext()); }
("'"."'")	{ return new_symbol(sym.CHAR, new Character(yytext().charAt(1))); }
[0-9]+	{ return new_symbol(sym.NUMBER, new Integer(yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]*	{ return new_symbol(sym.IDENT, yytext()); }

.	{System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)+" i u koloni "+yycolumn);}

