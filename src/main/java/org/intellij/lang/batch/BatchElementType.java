package org.intellij.lang.batch;

import consulo.language.ast.IElementType;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;

public class BatchElementType extends IElementType
{
	public BatchElementType(@Nonnull @NonNls String debugName)
	{
		super(debugName, BatchLanguage.INSTANCE);
	}
}
