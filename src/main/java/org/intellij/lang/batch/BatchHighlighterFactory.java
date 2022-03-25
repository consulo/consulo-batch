package org.intellij.lang.batch;

import consulo.language.editor.highlight.SingleLazyInstanceSyntaxHighlighterFactory;
import consulo.language.editor.highlight.SyntaxHighlighter;
import org.intellij.lang.batch.fileTypes.BatchSyntaxHighlighter;

import javax.annotation.Nonnull;

public class BatchHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory
{
	@Override
	@Nonnull
	protected SyntaxHighlighter createHighlighter()
	{
		return new BatchSyntaxHighlighter();
	}
}
