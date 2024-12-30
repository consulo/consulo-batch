package org.intellij.lang.batch.fileTypes;

import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.highlight.SyntaxHighlighter;
import consulo.language.editor.highlight.SyntaxHighlighterProvider;
import consulo.project.Project;
import consulo.virtualFileSystem.VirtualFile;
import consulo.virtualFileSystem.fileType.FileType;

import jakarta.annotation.Nullable;

/**
 * @author VISTALL
 * @since 27-Jun-22
 */
@ExtensionImpl
public class BatchSyntaxHighlighterProvider implements SyntaxHighlighterProvider
{
	@Nullable
	@Override
	public SyntaxHighlighter create(FileType fileType, @Nullable Project project, @Nullable VirtualFile virtualFile)
	{
		if(fileType == BatchFileType.INSTANCE)
		{
			return new BatchSyntaxHighlighter();
		}
		return null;
	}
}
