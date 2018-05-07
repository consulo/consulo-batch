package org.intellij.lang.batch.fileTypes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.Icon;

import org.intellij.lang.batch.BatchLanguage;
import org.intellij.lang.batch.util.BatchBundle;
import org.intellij.lang.batch.util.BatchIcons;
import org.jetbrains.annotations.NonNls;
import com.intellij.openapi.fileTypes.LanguageFileType;

/**
 * Batch file type.
 *
 * @author Alexey Efimov
 */
public final class BatchFileType extends LanguageFileType
{
	public static final BatchFileType INSTANCE = new BatchFileType();

	public BatchFileType()
	{
		super(BatchLanguage.INSTANCE);
	}

	@Override
	@Nonnull
	@NonNls
	public String getDefaultExtension()
	{
		return "bat";
	}

	@Override
	@Nonnull
	public String getDescription()
	{
		return BatchBundle.message("batch.filetype.description");
	}

	@Override
	@Nullable
	public Icon getIcon()
	{
		return BatchIcons.Batch;
	}

	@Override
	@Nonnull
	@NonNls
	public String getId()
	{
		return "Batch";
	}
}
