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

package consulo.batch.run;

import consulo.annotation.component.ExtensionImpl;
import consulo.execution.action.ConfigurationContext;
import consulo.execution.action.RunConfigurationProducer;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiManager;
import consulo.util.lang.Comparing;
import consulo.util.lang.ref.Ref;
import consulo.virtualFileSystem.LocalFileSystem;
import consulo.virtualFileSystem.VirtualFile;
import consulo.virtualFileSystem.fileType.FileType;
import org.intellij.lang.batch.fileTypes.BatchFileType;
import org.intellij.lang.batch.runner.BatchRunConfiguration;

import javax.annotation.Nullable;

/**
 * @author VISTALL
 * @since 09.01.15
 */
@ExtensionImpl
public class BatchRunConfigurationProducer extends RunConfigurationProducer<BatchRunConfiguration>
{
	public BatchRunConfigurationProducer()
	{
		super(BatchConfigurationType.getInstance());
	}

	@Override
	protected boolean setupConfigurationFromContext(BatchRunConfiguration configuration, ConfigurationContext context, Ref<PsiElement> sourceElement)
	{
		PsiFile psiFile = getPsiFile(context);
		if(psiFile != null)
		{
			configuration.setScriptName(psiFile.getVirtualFile().getPath());
			configuration.setName(configuration.suggestedName());
			configuration.setModule(context.getModule());

			sourceElement.set(psiFile);
			return true;
		}
		return false;
	}

	@Override
	public boolean isConfigurationFromContext(BatchRunConfiguration configuration, ConfigurationContext context)
	{
		String scriptName = configuration.getScriptName();
		if(scriptName == null)
		{
			return false;
		}

		VirtualFile fileByPath = LocalFileSystem.getInstance().findFileByPath(configuration.getScriptName());
		if(fileByPath == null)
		{
			return false;
		}

		PsiManager psiManager = PsiManager.getInstance(context.getProject());
		PsiFile file = psiManager.findFile(fileByPath);
		if(file == null || file.getFileType() != BatchFileType.INSTANCE)
		{
			return false;
		}

		if(!Comparing.equal(configuration.getConfigurationModule().getModule(), context.getModule()))
		{
			return false;
		}

		PsiFile psiFile = getPsiFile(context);
		if(psiFile == null || !psiFile.isEquivalentTo(file))
		{
			return false;
		}
		return true;
	}

	@Nullable
	private static PsiFile getPsiFile(ConfigurationContext configurationContext)
	{
		PsiElement psiLocation = configurationContext.getPsiLocation();
		if(psiLocation == null)
		{
			return null;
		}
		PsiFile containingFile = psiLocation.getContainingFile();
		if(containingFile == null)
		{
			return null;
		}
		FileType fileType = containingFile.getFileType();
		if(fileType != BatchFileType.INSTANCE)
		{
			return null;
		}
		return containingFile;
	}
}
