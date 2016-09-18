/*
 * Copyright 2013-2016 consulo.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package consulo.batch.psi;

import org.intellij.lang.batch.BatchLanguage;
import org.intellij.lang.batch.BatchTokenTypes;
import org.intellij.lang.batch.lexer._BatchLexer;
import org.jetbrains.annotations.NotNull;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import consulo.lang.LanguageVersion;

/**
 * @author VISTALL
 * @since 09.01.15
 */
public class BatchParsingDefinition implements ParserDefinition
{
	private static final IFileElementType FILE_ELEMENT_TYPE = new IFileElementType("BATCH_FILE", BatchLanguage.INSTANCE);

	@NotNull
	@Override
	public Lexer createLexer(@NotNull LanguageVersion languageVersion)
	{
		return new FlexAdapter(new _BatchLexer((java.io.Reader) null));
	}

	@NotNull
	@Override
	public PsiParser createParser(@NotNull LanguageVersion languageVersion)
	{
		return new PsiParser()
		{
			@NotNull
			@Override
			public ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder, @NotNull LanguageVersion languageVersion)
			{
				PsiBuilder.Marker mark = builder.mark();
				while(!builder.eof())
				{
					builder.advanceLexer();
				}
				mark.done(root);
				return builder.getTreeBuilt();
			}
		};
	}

	@NotNull
	@Override
	public IFileElementType getFileNodeType()
	{
		return FILE_ELEMENT_TYPE;
	}

	@NotNull
	@Override
	public TokenSet getWhitespaceTokens(@NotNull LanguageVersion languageVersion)
	{
		return BatchTokenTypes.WHITE_SPACES;
	}

	@NotNull
	@Override
	public TokenSet getCommentTokens(@NotNull LanguageVersion languageVersion)
	{
		return BatchTokenTypes.COMMENTS;
	}

	@NotNull
	@Override
	public TokenSet getStringLiteralElements(@NotNull LanguageVersion languageVersion)
	{
		return BatchTokenTypes.STRINGS;
	}

	@NotNull
	@Override
	public PsiElement createElement(ASTNode node)
	{
		return new ASTWrapperPsiElement(node);
	}

	@Override
	public PsiFile createFile(FileViewProvider viewProvider)
	{
		return new BatchFile(viewProvider);
	}

	@NotNull
	@Override
	public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right)
	{
		return SpaceRequirements.MAY;
	}
}
