package org.intellij.lang.batch;

import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.highlight.SingleLazyInstanceSyntaxHighlighterFactory;
import consulo.language.editor.highlight.SyntaxHighlighter;
import org.intellij.lang.batch.fileTypes.BatchSyntaxHighlighter;

import jakarta.annotation.Nonnull;

@ExtensionImpl
public class BatchHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory
{
	@Override
	@Nonnull
	protected SyntaxHighlighter createHighlighter()
	{
		return new BatchSyntaxHighlighter();
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return BatchLanguage.INSTANCE;
	}
}
