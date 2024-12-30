package org.intellij.lang.batch.runner;

import consulo.batch.localize.BatchLocalize;
import consulo.compiler.execution.CompileStepBeforeRun;
import consulo.execution.RuntimeConfigurationException;
import consulo.execution.configuration.*;
import consulo.execution.configuration.ui.SettingsEditor;
import consulo.execution.executor.Executor;
import consulo.execution.runner.ExecutionEnvironment;
import consulo.execution.ui.awt.EnvironmentVariablesComponent;
import consulo.execution.ui.console.TextConsoleBuilderFactory;
import consulo.module.Module;
import consulo.module.ModuleManager;
import consulo.process.ExecutionException;
import consulo.util.lang.Comparing;
import consulo.util.lang.StringUtil;
import consulo.util.xml.serializer.InvalidDataException;
import consulo.util.xml.serializer.JDOMExternalizerUtil;
import consulo.util.xml.serializer.WriteExternalException;
import org.jdom.Element;

import jakarta.annotation.Nonnull;
import java.io.File;
import java.util.*;

/**
 * @author wibotwi
 */
public class BatchRunConfiguration extends ModuleBasedConfiguration<RunConfigurationModule> implements CommonBatchRunConfigurationParams,
		BatchRunConfigurationParams, CompileStepBeforeRun.Suppressor
{

	// common config
	private String interpreterOptions = "";
	private String workingDirectory = "";
	private boolean passParentEnvs = true;
	private Map<String, String> envs = new HashMap<String, String>();

	// run config
	private String scriptName;
	private String scriptParameters;

	public BatchRunConfiguration(RunConfigurationModule runConfigurationModule, ConfigurationFactory batchConfigurationFactory, String name)
	{
		super(name, runConfigurationModule, batchConfigurationFactory);
	}

	@Override
	public Collection<Module> getValidModules()
	{
		List<Module> modules = new ArrayList<Module>();
		Module[] allModules = ModuleManager.getInstance(getProject()).getModules();
		modules.addAll(Arrays.asList(allModules));
		return modules;
	}

	@Override
	protected ModuleBasedConfiguration createInstance()
	{
		return new BatchRunConfiguration(getConfigurationModule(), getFactory(), getName());
	}

	@Override
	public SettingsEditor<? extends RunConfiguration> getConfigurationEditor()
	{
		return new BatchRunConfigurationEditor(this);
	}

	@Override
	public RunProfileState getState(@Nonnull Executor executor, @Nonnull ExecutionEnvironment env) throws ExecutionException
	{
		BatchCommandLineState state = new BatchCommandLineState(this, env);
		state.setConsoleBuilder(TextConsoleBuilderFactory.getInstance().createBuilder(getProject()));
		return state;
	}

	public String getInterpreterPath()
	{
		return "cmd.exe";
	}

	@Override
	public void checkConfiguration() throws RuntimeConfigurationException
	{
		super.checkConfiguration();
		if(StringUtil.isEmptyOrSpaces(scriptName))
		{
			throw new RuntimeConfigurationException(BatchLocalize.runcfgNo_script_name().get());
		}
	}

	@Override
	public boolean isGeneratedName()
	{
		return Comparing.equal(getName(), suggestedName());
	}

	@Override
	public String suggestedName()
	{
		String name = (new File(scriptName)).getName();
		int ind = name.lastIndexOf('.');
		if(ind != -1)
		{
			return name.substring(0, ind);
		}
		return name;
	}

	@Override
	public void readExternal(Element element) throws InvalidDataException
	{
		super.readExternal(element);

		// common config
		interpreterOptions = JDOMExternalizerUtil.readField(element, "INTERPRETER_OPTIONS");
		workingDirectory = JDOMExternalizerUtil.readField(element, "WORKING_DIRECTORY");
		String str = JDOMExternalizerUtil.readField(element, "PARENT_ENVS");
		if(str != null)
		{
			passParentEnvs = Boolean.parseBoolean(str);
		}
		EnvironmentVariablesComponent.readExternal(element, envs);

		// ???
		getConfigurationModule().readExternal(element);

		// run config
		scriptName = JDOMExternalizerUtil.readField(element, "SCRIPT_NAME");
		scriptParameters = JDOMExternalizerUtil.readField(element, "PARAMETERS");
	}

	@Override
	public void writeExternal(Element element) throws WriteExternalException
	{
		super.writeExternal(element);

		// common config
		JDOMExternalizerUtil.writeField(element, "INTERPRETER_OPTIONS", interpreterOptions);
		JDOMExternalizerUtil.writeField(element, "WORKING_DIRECTORY", workingDirectory);
		JDOMExternalizerUtil.writeField(element, "PARENT_ENVS", Boolean.toString(passParentEnvs));
		EnvironmentVariablesComponent.writeExternal(element, envs);

		// ???
		getConfigurationModule().writeExternal(element);

		// run config
		JDOMExternalizerUtil.writeField(element, "SCRIPT_NAME", scriptName);
		JDOMExternalizerUtil.writeField(element, "PARAMETERS", scriptParameters);
	}

	public static void copyParams(CommonBatchRunConfigurationParams from, CommonBatchRunConfigurationParams to)
	{
		to.setEnvs(new HashMap<String, String>(from.getEnvs()));
		to.setInterpreterOptions(from.getInterpreterOptions());
		to.setWorkingDirectory(from.getWorkingDirectory());
		to.setPassParentEnvs(from.isPassParentEnvs());
	}

	public static void copyParams(BatchRunConfigurationParams from, BatchRunConfigurationParams to)
	{
		copyParams(from.getCommonParams(), to.getCommonParams());

		to.setScriptName(from.getScriptName());
		to.setScriptParameters(from.getScriptParameters());
	}

	@Override
	public String getInterpreterOptions()
	{
		return interpreterOptions;
	}

	@Override
	public void setInterpreterOptions(String interpreterOptions)
	{
		this.interpreterOptions = interpreterOptions;
	}

	@Override
	public String getWorkingDirectory()
	{
		return workingDirectory;
	}

	@Override
	public void setWorkingDirectory(String workingDirectory)
	{
		this.workingDirectory = workingDirectory;
	}

	@Override
	public boolean isPassParentEnvs()
	{
		return passParentEnvs;
	}

	@Override
	public void setPassParentEnvs(boolean passParentEnvs)
	{
		this.passParentEnvs = passParentEnvs;
	}

	@Override
	public Map<String, String> getEnvs()
	{
		return envs;
	}

	@Override
	public void setEnvs(Map<String, String> envs)
	{
		this.envs = envs;
	}

	@Override
	public CommonBatchRunConfigurationParams getCommonParams()
	{
		return this;
	}

	@Override
	public String getScriptName()
	{
		return scriptName;
	}

	@Override
	public void setScriptName(String scriptName)
	{
		this.scriptName = scriptName;
	}

	@Override
	public String getScriptParameters()
	{
		return scriptParameters;
	}

	@Override
	public void setScriptParameters(String scriptParameters)
	{
		this.scriptParameters = scriptParameters;
	}
}
