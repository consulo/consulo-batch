package org.intellij.lang.batch.lexer;

import org.intellij.lang.batch.BatchTokenTypes;
import com.intellij.lexer.LayeredLexer;

/**
 * Lexer adapter.
 *
 * @author Alexey Efimov
 */
public class BatchHighlighterLexer extends LayeredLexer
{
	public BatchHighlighterLexer()
	{
		super(new _BatchLexer());

		_ExpressionLexer stringLexer = new _ExpressionLexer();
		stringLexer.setDefaultToken(BatchTokenTypes.STRING_LITERAL);
		registerLayer(stringLexer, BatchTokenTypes.STRING_LITERAL);
		_ExpressionLexer extressionLexer = new _ExpressionLexer();
		extressionLexer.setDefaultToken(BatchTokenTypes.EXPRESSION);
		registerLayer(extressionLexer, BatchTokenTypes.EXPRESSION);
		_ExpressionLexer labelLexer = new _ExpressionLexer();
		labelLexer.setDefaultToken(BatchTokenTypes.LABEL_REFERENCE);
		registerLayer(labelLexer, BatchTokenTypes.LABEL_REFERENCE);
	}
}
