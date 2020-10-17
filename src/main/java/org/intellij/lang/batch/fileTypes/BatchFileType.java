package org.intellij.lang.batch.fileTypes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import consulo.batch.icon.BatchIconGroup;
import org.intellij.lang.batch.BatchLanguage;
import org.intellij.lang.batch.util.BatchBundle;
import org.jetbrains.annotations.NonNls;
import com.intellij.openapi.fileTypes.LanguageFileType;
import consulo.ui.image.Image;

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
	public Image getIcon()
	{
		return BatchIconGroup.batch();
	}

	@Override
	@Nonnull
	@NonNls
	public String getId()
	{
		return "Batch";
	}
}
