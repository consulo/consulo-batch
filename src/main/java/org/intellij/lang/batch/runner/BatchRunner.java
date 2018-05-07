package org.intellij.lang.batch.runner;

import javax.annotation.Nonnull;

import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.runners.DefaultProgramRunner;

/**
 * @author wibotwi
 */
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
