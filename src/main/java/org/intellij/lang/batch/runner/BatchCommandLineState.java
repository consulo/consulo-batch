package org.intellij.lang.batch.runner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandlerFactory;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

import javax.annotation.Nonnull;
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
	protected OSProcessHandler startProcess() throws ExecutionException
	{
		OSProcessHandler osProcessHandler = ProcessHandlerFactory.getInstance().createProcessHandler(generateCommandLine());
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
