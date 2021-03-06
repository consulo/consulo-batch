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

import javax.annotation.Nonnull;

import org.intellij.lang.batch.BatchLanguage;
import org.intellij.lang.batch.fileTypes.BatchFileType;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;

/**
 * @author VISTALL
 * @since 09.01.15
 */
public class BatchFile extends PsiFileBase
{
	public BatchFile(@Nonnull FileViewProvider viewProvider)
	{
		super(viewProvider, BatchLanguage.INSTANCE);
	}

	@Nonnull
	@Override
	public FileType getFileType()
	{
		return BatchFileType.INSTANCE;
	}
}
