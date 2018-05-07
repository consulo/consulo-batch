package org.intellij.lang.batch;

import org.jetbrains.annotations.NonNls;
import javax.annotation.Nonnull;
import com.intellij.psi.tree.IElementType;

public class BatchElementType extends IElementType
{
	public BatchElementType(@Nonnull @NonNls String debugName)
	{
		super(debugName, BatchLanguage.INSTANCE);
	}
}
