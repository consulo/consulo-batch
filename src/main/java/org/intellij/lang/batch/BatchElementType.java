package org.intellij.lang.batch;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;

public class BatchElementType extends IElementType
{
	public BatchElementType(@NotNull @NonNls String debugName)
	{
		super(debugName, BatchLanguage.INSTANCE);
	}
}
