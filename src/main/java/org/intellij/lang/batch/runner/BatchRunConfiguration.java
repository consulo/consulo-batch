package org.intellij.lang.batch.runner;

import consulo.batch.localize.BatchLocalize;
import consulo.compiler.execution.CompileStepBeforeRun;
import consulo.execution.CommonProgramRunConfigurationParameters;
import consulo.execution.RuntimeConfigurationException;
import consulo.execution.configuration.*;
import consulo.execution.configuration.fragment.EnvironmentFragmentState;
import consulo.execution.configuration.fragment.ProgramParametersFragmentState;
import consulo.execution.configuration.fragment.RunConfigurationFragment;
import consulo.execution.configuration.fragment.WorkingDirectoryFragmentState;
import consulo.execution.executor.Executor;
import consulo.execution.runner.ExecutionEnvironment;
import consulo.execution.ui.console.TextConsoleBuilderFactory;
import consulo.module.Module;
import consulo.module.ModuleManager;
import consulo.process.ExecutionException;
import consulo.util.lang.Comparing;
import consulo.util.lang.StringUtil;
import jakarta.annotation.Nonnull;
import org.jspecify.annotations.Nullable;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BatchRunConfiguration extends ModuleBasedConfiguration<RunConfigurationModule> implements CommonProgramRunConfigurationParameters,
    CompileStepBeforeRun.Suppressor {

    public BatchRunConfiguration(RunConfigurationModule runConfigurationModule, ConfigurationFactory batchConfigurationFactory, String name) {
        super(name, runConfigurationModule, batchConfigurationFactory);
    }

    @Override
    protected Class<BatchRunConfigurationOptions> getOptionsClass() {
        return BatchRunConfigurationOptions.class;
    }

    @Override
    public BatchRunConfigurationOptions getOptions() {
        return (BatchRunConfigurationOptions) super.getOptions();
    }

    @Override
    protected void convertLegacyState(LegacyRunConfigurationConverter converter) {
        super.convertLegacyState(converter);

        BatchRunConfigurationLegacyState old = converter.readXml(BatchRunConfigurationLegacyState.class);

        BatchRunConfigurationOptions options = new BatchRunConfigurationOptions();
        options.setScriptName(old.SCRIPT_NAME);
        options.setInterpreterOptions(old.INTERPRETER_OPTIONS);
        converter.setOptions(options);

        converter.addFragment(new ProgramParametersFragmentState(old.PARAMETERS));
        converter.addFragment(new WorkingDirectoryFragmentState(old.WORKING_DIRECTORY));
        converter.addFragment(new EnvironmentFragmentState(converter.readEnvs(), old.PARENT_ENVS));
    }

    @Override
    public List<RunConfigurationFragment<?>> createFragments() {
        return List.of(new BatchScriptFragment(), new BatchInterpreterOptionsFragment());
    }

    @Override
    public Collection<Module> getValidModules() {
        return Arrays.asList(ModuleManager.getInstance(getProject()).getModules());
    }

    @Override
    protected ModuleBasedConfiguration createInstance() {
        return new BatchRunConfiguration(getConfigurationModule(), getFactory(), getName());
    }

    @Override
    public RunProfileState getState(@Nonnull Executor executor, @Nonnull ExecutionEnvironment env) throws ExecutionException {
        BatchCommandLineState state = new BatchCommandLineState(this, env);
        state.setConsoleBuilder(TextConsoleBuilderFactory.getInstance().createBuilder(getProject()));
        return state;
    }

    public String getInterpreterPath() {
        return "cmd.exe";
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
        super.checkConfiguration();
        if (StringUtil.isEmptyOrSpaces(getScriptName())) {
            throw new RuntimeConfigurationException(BatchLocalize.runcfgNo_script_name().get());
        }
    }

    @Override
    public boolean isGeneratedName() {
        return Comparing.equal(getName(), suggestedName());
    }

    @Override
    public String suggestedName() {
        String name = new File(StringUtil.notNullize(getScriptName())).getName();
        int ind = name.lastIndexOf('.');
        if (ind != -1) {
            return name.substring(0, ind);
        }
        return name;
    }

    public @Nullable String getScriptName() {
        return getOptions().getScriptName();
    }

    public void setScriptName(@Nullable String scriptName) {
        getOptions().setScriptName(scriptName);
    }

    public @Nullable String getInterpreterOptions() {
        return getOptions().getInterpreterOptions();
    }

    public void setInterpreterOptions(@Nullable String interpreterOptions) {
        getOptions().setInterpreterOptions(interpreterOptions);
    }
}
