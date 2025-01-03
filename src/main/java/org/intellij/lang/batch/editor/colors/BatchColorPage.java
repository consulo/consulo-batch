package org.intellij.lang.batch.editor.colors;

import consulo.annotation.component.ExtensionImpl;
import consulo.colorScheme.TextAttributesKey;
import consulo.colorScheme.setting.AttributesDescriptor;
import consulo.colorScheme.setting.ColorDescriptor;
import consulo.language.editor.colorScheme.setting.ColorSettingsPage;
import consulo.language.editor.highlight.SyntaxHighlighter;
import consulo.logging.Logger;
import consulo.util.io.FileUtil;
import consulo.util.lang.lazy.LazyValue;
import org.intellij.lang.batch.editor.BatchHighlighterColors;
import org.intellij.lang.batch.fileTypes.BatchSyntaxHighlighter;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@ExtensionImpl
final class BatchColorPage implements ColorSettingsPage
{
	private static final Supplier<String> SAMPLE = LazyValue.notNull(BatchColorPage::loadDemo);

	private final Set<AttributesDescriptor> attributeDescriptors = new HashSet<>();

	public BatchColorPage()
	{
		attributeDescriptors.add(new AttributesDescriptor("Brackets", BatchHighlighterColors.BRACKETS));
		attributeDescriptors.add(new AttributesDescriptor("Braces", BatchHighlighterColors.BRACES));
		attributeDescriptors.add(new AttributesDescriptor("Parenthesis", BatchHighlighterColors.PARENTHS));
		attributeDescriptors.add(new AttributesDescriptor("Comment", BatchHighlighterColors.COMMENT));
		attributeDescriptors.add(new AttributesDescriptor("Operator", BatchHighlighterColors.OPERATION_SIGN));
		attributeDescriptors.add(new AttributesDescriptor("Keyword", BatchHighlighterColors.KEYWORD));
		attributeDescriptors.add(new AttributesDescriptor("String", BatchHighlighterColors.STRING));
		attributeDescriptors.add(new AttributesDescriptor("Number", BatchHighlighterColors.NUMBER));
		attributeDescriptors.add(new AttributesDescriptor("Label", BatchHighlighterColors.LABEL));
		attributeDescriptors.add(new AttributesDescriptor("Label Reference", BatchHighlighterColors.LABEL_REFERENCE));
		attributeDescriptors.add(new AttributesDescriptor("Environment Variable", BatchHighlighterColors.ENVIRONMENT_VARIABLE));
		attributeDescriptors.add(new AttributesDescriptor("Environment Definition", BatchHighlighterColors.ENVIRONMENT_VARIABLE_DEFINITION));
		attributeDescriptors.add(new AttributesDescriptor("Variable", BatchHighlighterColors.VARIABLE));
		attributeDescriptors.add(new AttributesDescriptor("Expression", BatchHighlighterColors.EXPRESSION));
	}

	@Nullable
	public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap()
	{
		return null;
	}

	@Nonnull
	public AttributesDescriptor[] getAttributeDescriptors()
	{
		return attributeDescriptors.toArray(new AttributesDescriptor[attributeDescriptors.size()]);
	}

	@Nonnull
	public ColorDescriptor[] getColorDescriptors()
	{
		return ColorDescriptor.EMPTY_ARRAY;
	}

	@Nonnull
	public String getDemoText()
	{
		return SAMPLE.get();
	}

	private static String loadDemo()
	{
		try
		{
			return FileUtil.loadTextAndClose(BatchColorPage.class.getResourceAsStream("/examples/demo.bat")).replaceAll("\\r", "");
		}
		catch(IOException e)
		{
			Logger.getInstance(BatchColorPage.class).error(e);
		}
		return "";
	}

	@Nonnull
	public String getDisplayName()
	{
		return "Batch Script";
	}

	@Nonnull
	public SyntaxHighlighter getHighlighter()
	{
		return new BatchSyntaxHighlighter();
	}
}
