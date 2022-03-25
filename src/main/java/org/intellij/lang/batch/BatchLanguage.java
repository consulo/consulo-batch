package org.intellij.lang.batch;

import consulo.language.Language;

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
	}
}
