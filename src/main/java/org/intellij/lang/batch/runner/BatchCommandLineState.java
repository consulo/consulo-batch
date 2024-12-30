package org.intellij.lang.batch.runner;

import consulo.execution.configuration.CommandLineState;
import consulo.execution.process.ProcessTerminatedListener;
import consulo.execution.runner.ExecutionEnvironment;
import consulo.process.ExecutionException;
import consulo.process.ProcessHandler;
import consulo.process.cmd.GeneralCommandLine;
import consulo.process.local.ProcessHandlerFactory;
import consulo.util.lang.StringUtil;
import consulo.virtualFileSystem.LocalFileSystem;
import consulo.virtualFileSystem.VirtualFile;

import jakarta.annotation.Nonnull;
import java.nio.charset.Charset;

/**
 * @author wibotwi
 */
public class BatchCommandLineState extends CommandLineState
{
	private BatchRunConfiguration runConfiguration;

	public BatchCommandLineState(BatchRunConfiguration runConfiguration, ExecutionEnvironment env)
	{
		super(env);
		this.runConfiguration = runConfiguration;
	}

	@Nonnull
	@Override
	protected ProcessHandler startProcess() throws ExecutionException
	{
		ProcessHandler osProcessHandler = ProcessHandlerFactory.getInstance().createProcessHandler(generateCommandLine());
		ProcessTerminatedListener.attach(osProcessHandler);
		return osProcessHandler;
	}

	private GeneralCommandLine generateCommandLine()
	{
		GeneralCommandLine commandLine = new GeneralCommandLine();
		commandLine.setExePath(runConfiguration.getInterpreterPath());
		commandLine.getParametersList().addParametersString("/c");
		commandLine.getParametersList().addParametersString(runConfiguration.getInterpreterOptions());

		String parentPath = getEnvironment().getProject().getBasePath();
		if(!StringUtil.isEmptyOrSpaces(runConfiguration.getScriptName()))
		{
			commandLine.addParameter(runConfiguration.getScriptName());

			VirtualFile scriptFile = LocalFileSystem.getInstance().findFileByPath(runConfiguration.getScriptName());
			if (scriptFile != null)
			{
				Charset charset = scriptFile.getCharset();

				commandLine.setCharset(charset);

				VirtualFile parent = scriptFile.getParent();
				if(parent != null)
				{
					parentPath = parent.getPresentableUrl();
				}
			}
		}

		commandLine.getParametersList().addParametersString(runConfiguration.getScriptParameters());
		if(!StringUtil.isEmptyOrSpaces(runConfiguration.getWorkingDirectory()))
		{
			commandLine.setWorkDirectory(runConfiguration.getWorkingDirectory());
		}
		else
		{
			commandLine.setWorkDirectory(parentPath);
		}

		commandLine.getEnvironment().putAll(runConfiguration.getEnvs());
		commandLine.setPassParentEnvironment(runConfiguration.isPassParentEnvs());
		return commandLine;
	}

}
