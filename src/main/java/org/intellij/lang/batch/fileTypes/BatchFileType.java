package org.intellij.lang.batch.fileTypes;

import com.intellij.openapi.fileTypes.LanguageFileType;
import consulo.batch.icon.BatchIconGroup;
import consulo.batch.localize.BatchLocalize;
import consulo.localize.LocalizeValue;
import consulo.ui.image.Image;
import org.intellij.lang.batch.BatchLanguage;

import javax.annotation.Nonnull;

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
	public String getDefaultExtension()
	{
		return "bat";
	}

	@Override
	@Nonnull
	public LocalizeValue getDescription()
	{
		return BatchLocalize.batchFiletypeDescription();
	}

	@Override
	@Nonnull
	public Image getIcon()
	{
		return BatchIconGroup.batch();
	}

	@Override
	@Nonnull
	public String getId()
	{
		return "Batch";
	}
}
