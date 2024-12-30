package org.intellij.lang.batch.runner;

import consulo.annotation.component.ExtensionImpl;
import consulo.execution.configuration.RunProfile;
import consulo.execution.executor.DefaultRunExecutor;
import consulo.execution.runner.DefaultProgramRunner;

import jakarta.annotation.Nonnull;
/**
 * @author wibotwi
 */
@ExtensionImpl
public class BatchRunner extends DefaultProgramRunner
{
	@Nonnull
	public String getRunnerId()
	{
		return "BatchRunner";
	}

	public boolean canRun(@Nonnull String executorId, @Nonnull RunProfile profile)
	{
		return executorId.equals(DefaultRunExecutor.EXECUTOR_ID) && profile instanceof BatchRunConfiguration;
	}
}
