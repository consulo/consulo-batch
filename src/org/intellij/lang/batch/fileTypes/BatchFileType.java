package org.intellij.lang.batch.fileTypes;

import javax.swing.Icon;

import org.intellij.lang.batch.BatchLanguage;
import org.intellij.lang.batch.util.BatchBundle;
import org.intellij.lang.batch.util.BatchIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
	@NotNull
	@NonNls
	public String getDefaultExtension()
	{
		return "bat";
	}

	@Override
	@NotNull
	public String getDescription()
	{
		return BatchBundle.message("batch.filetype.description");
	}

	@Override
	@Nullable
	public Icon getIcon()
	{
		return BatchIcons.BATCH_FILE_ICON;
	}

	@Override
	@NotNull
	@NonNls
	public String getName()
	{
		return "Batch";
	}
}
