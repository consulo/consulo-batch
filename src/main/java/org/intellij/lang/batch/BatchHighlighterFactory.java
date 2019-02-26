package org.intellij.lang.batch;

import javax.annotation.Nonnull;

import org.intellij.lang.batch.fileTypes.BatchSyntaxHighlighter;
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;

public class BatchHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory
{
	@Override
	@Nonnull
	protected SyntaxHighlighter createHighlighter()
	{
		return new BatchSyntaxHighlighter();
	}
}
