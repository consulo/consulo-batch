package org.intellij.lang.batch;

import java.text.MessageFormat;

import org.intellij.lang.batch.fileTypes.BatchFileType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;

public class BatchElementType extends IElementType
{
	public BatchElementType(@NotNull @NonNls String debugName)
	{
		super(debugName, BatchFileType.BASH_LANGUAGE);
	}

	@Override
	@SuppressWarnings({"HardCodedStringLiteral"})
	public String toString()
	{
		return MessageFormat.format("Batch:{0}", super.toString());
	}
}
