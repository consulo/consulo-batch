package org.intellij.lang.batch.editor;

import consulo.codeEditor.HighlighterColors;
import consulo.colorScheme.TextAttributes;
import consulo.colorScheme.TextAttributesKey;
import consulo.language.editor.DefaultLanguageHighlighterColors;
import consulo.ui.color.RGBColor;
import consulo.ui.style.StandardColors;

import java.awt.*;

/**
 * Batch highlighter colors.
 *
 * @author Alexey Efimov
 */
public interface BatchHighlighterColors
{
	TextAttributesKey COMMENT = TextAttributesKey.createTextAttributesKey("BATCH.COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
	TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey("BATCH.STRING", DefaultLanguageHighlighterColors.STRING);
	TextAttributesKey VARIABLE = TextAttributesKey.createTextAttributesKey("BATCH.VARIABLE", new TextAttributes(StandardColors.BLUE, null, null,
			null, Font.BOLD | Font.ITALIC));
	TextAttributesKey ENVIRONMENT_VARIABLE = TextAttributesKey.createTextAttributesKey("BATCH.ENVIRONMENT_VARIABLE",
			DefaultLanguageHighlighterColors.STATIC_FIELD);
	TextAttributesKey ENVIRONMENT_VARIABLE_DEFINITION = TextAttributesKey.createTextAttributesKey("BATCH.ENVIRONMENT_VARIABLE_DEFINITION",
			DefaultLanguageHighlighterColors.STATIC_FIELD);
	TextAttributesKey LABEL = TextAttributesKey.createTextAttributesKey("BATCH.LABEL", new TextAttributes(new RGBColor(0x80, 0x80, 0), null, null,
			null, Font.BOLD));
	TextAttributesKey LABEL_REFERENCE = TextAttributesKey.createTextAttributesKey("BATCH.LABEL_REFERENCE", new TextAttributes(new RGBColor(0x80, 0x80,
			0), null, null, null, Font.BOLD | Font.ITALIC));
	TextAttributesKey EXPRESSION = TextAttributesKey.createTextAttributesKey("BATCH.EXPRESSION", new TextAttributes(new RGBColor(0, 0, 0x80), null,
			null, null, 0));
	TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey("BATCH.NUMBER", DefaultLanguageHighlighterColors.NUMBER);
	TextAttributesKey OPERATION_SIGN = TextAttributesKey.createTextAttributesKey("BATCH.OPERATION_SIGN",
			DefaultLanguageHighlighterColors.OPERATION_SIGN);
	TextAttributesKey BRACES = TextAttributesKey.createTextAttributesKey("BATCH.BRACES", DefaultLanguageHighlighterColors.BRACES);
	TextAttributesKey BRACKETS = TextAttributesKey.createTextAttributesKey("BATCH.BRACES", DefaultLanguageHighlighterColors.BRACKETS);
	TextAttributesKey PARENTHS = TextAttributesKey.createTextAttributesKey("BATCH.PARENTHS", DefaultLanguageHighlighterColors.PARENTHESES);
	TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey("BATCH.KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);

	TextAttributesKey BAD_CHARACTER = HighlighterColors.BAD_CHARACTER;
}
