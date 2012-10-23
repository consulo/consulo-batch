package org.intellij.lang.batch.runner;

import org.intellij.lang.batch.fileTypes.BatchFileType;
import com.intellij.execution.Location;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.junit.RuntimeConfigurationProducer;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

/**
 * @author wibotwi
 */
public class BatchRunConfigurationProducer extends RuntimeConfigurationProducer implements Cloneable
{
	private PsiFile sourceFile;

	public BatchRunConfigurationProducer()
	{
		super(BatchConfigurationType.getInstance());
	}

	@Override
	public PsiElement getSourceElement()
	{
		return sourceFile;
	}

	@Override
	protected RunnerAndConfigurationSettings createConfigurationByElement(Location location, ConfigurationContext configurationContext)
	{
		sourceFile = location.getPsiElement().getContainingFile();
		if(sourceFile != null && sourceFile.getFileType() == BatchFileType.BATCH_FILE_TYPE)
		{
			Project project = sourceFile.getProject();
			RunnerAndConfigurationSettings settings = cloneTemplateConfiguration(project, configurationContext);
			BatchRunConfiguration runConfiguration = (BatchRunConfiguration) settings.getConfiguration();
			runConfiguration.setScriptName(sourceFile.getVirtualFile().getPath());
			runConfiguration.setName(runConfiguration.suggestedName());
			Module module = ModuleUtil.findModuleForPsiElement(location.getPsiElement());
			if(module != null)
			{
				runConfiguration.setModule(module);
			}

			// that is it? copyStepsBeforeRun(project, runConfiguration);
			return settings;
		}
		else
		{
			return null;
		}
	}

	public int compareTo(Object o)
	{
		return 0;
	}
}
