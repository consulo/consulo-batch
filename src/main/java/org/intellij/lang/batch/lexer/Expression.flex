package org.intellij.lang.batch.lexer;

import consulo.language.lexer.LexerBase;
import consulo.language.ast.IElementType;
import org.intellij.lang.batch.BatchTokenTypes;

%%

%class _ExpressionLexer
%extends LexerBase
%final
%ignorecase
%unicode
%function advanceImpl
%type IElementType
%eof{ return;
%eof}
%{
    private IElementType defaultToken = null;
    public void setDefaultToken(IElementType defaultToken) {
       this.defaultToken = defaultToken;
    }
%}

Identifier = [^ \t\f\n\r\:\;\,\|\&\<\>\%]+
VariableName = [:digit:] | [A-Za-z]
Variable = "%""%"?({VariableName} | ("~"(([fdpnxsatz][fdpnxsatz]*("$"{Identifier}":")?)|("$"{Identifier}":")){VariableName}) | "*")
EnvVariable = "%" {Identifier} "%"

%%
<YYINITIAL> {
    {EnvVariable}       { yybegin(YYINITIAL); return BatchTokenTypes.ENVIRONMENT_VARIABLE; }
    {Variable}          { yybegin(YYINITIAL); return BatchTokenTypes.VARIABLE; }
    .                   { yybegin(YYINITIAL); return defaultToken;}
}

