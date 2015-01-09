package org.mustbe.consulo.batch.psi;

import org.intellij.lang.batch.BatchLanguage;
import org.intellij.lang.batch.fileTypes.BatchFileType;
import org.jetbrains.annotations.NotNull;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;

/**
 * @author VISTALL
 * @since 09.01.15
 */
public class BatchFile extends PsiFileBase
{
	public BatchFile(@NotNull FileViewProvider viewProvider)
	{
		super(viewProvider, BatchLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public FileType getFileType()
	{
		return BatchFileType.INSTANCE;
	}
}
