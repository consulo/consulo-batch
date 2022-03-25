package org.intellij.lang.batch.editor;

import consulo.language.CodeDocumentationAwareCommenter;
import consulo.language.ast.IElementType;
import consulo.language.psi.PsiComment;
import org.intellij.lang.batch.BatchTokenTypes;

import javax.annotation.Nullable;

/**
 * @author wibotwi
 */
public class BatchCommenter implements CodeDocumentationAwareCommenter
{

	@Override
	public IElementType getLineCommentTokenType()
	{
		return BatchTokenTypes.COMMENT;
	}

	@Override
	public IElementType getBlockCommentTokenType()
	{
		return null;
	}

	@Override
	public IElementType getDocumentationCommentTokenType()
	{
		return null;
	}

	@Override
	public String getDocumentationCommentPrefix()
	{
		return null;
	}

	@Override
	public String getDocumentationCommentLinePrefix()
	{
		return null;
	}

	@Override
	public String getDocumentationCommentSuffix()
	{
		return null;
	}

	@Override
	public boolean isDocumentationComment(PsiComment element)
	{
		return false;
	}

	@Override
	public String getLineCommentPrefix()
	{
		return "rem ";
	}

	@Override
	public String getBlockCommentPrefix()
	{
		return null;
	}

	@Override
	public String getBlockCommentSuffix()
	{
		return null;
	}

	@Nullable
	@Override
	public String getCommentedBlockCommentPrefix()
	{
		return null;
	}

	@Nullable
	@Override
	public String getCommentedBlockCommentSuffix()
	{
		return null;
	}
}
