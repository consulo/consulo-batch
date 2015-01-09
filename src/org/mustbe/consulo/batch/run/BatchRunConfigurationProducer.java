package org.mustbe.consulo.batch.run;

import org.intellij.lang.batch.fileTypes.BatchFileType;
import org.intellij.lang.batch.runner.BatchRunConfiguration;
import org.jetbrains.annotations.Nullable;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;

/**
 * @author VISTALL
 * @since 09.01.15
 */
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
