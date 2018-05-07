package org.intellij.lang.batch;

import javax.annotation.Nonnull;

import org.intellij.lang.batch.fileTypes.BatchSyntaxHighlighter;
import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;

/**
 * Batch language.
 *
 * @author Alexey Efimov
 */
public class BatchLanguage extends Language
{
	public static final BatchLanguage INSTANCE = new BatchLanguage();

	public BatchLanguage()
	{
		super("Batch", "application/x-batch", "application/x-bat", "text/x-script.bat");
		SyntaxHighlighterFactory.LANGUAGE_FACTORY.addExplicitExtension(this, new BatchHighlighterFactory());
	}

	private static class BatchHighlighterFactory extends SingleLazyInstanceSyntaxHighlighterFactory
	{
		@Override
		@Nonnull
		protected SyntaxHighlighter createHighlighter()
		{
			return new BatchSyntaxHighlighter();
		}
	}
}