package org.intellij.lang.batch.runner;

import consulo.batch.localize.BatchLocalize;
import consulo.disposer.Disposable;
import consulo.execution.ui.awt.EnvironmentVariablesTextFieldWithBrowseButton;
import consulo.fileChooser.FileChooserDescriptorFactory;
import consulo.fileChooser.FileChooserTextBoxBuilder;
import consulo.platform.base.icon.PlatformIconGroup;
import consulo.process.cmd.ParametersListUtil;
import consulo.project.Project;
import consulo.ui.TextBoxWithExpandAction;
import consulo.ui.annotation.RequiredUIAccess;
import consulo.ui.util.FormBuilder;
import consulo.util.io.FileUtil;
import consulo.util.lang.StringUtil;

import java.util.Map;

/**
 * @author wibotwi
 */
public class BatchCommonOptionsForm implements CommonBatchRunConfigurationParams {
    private final EnvironmentVariablesTextFieldWithBrowseButton myEnvsField;
    private final TextBoxWithExpandAction myInterpreterOptionsField;
    private final FileChooserTextBoxBuilder.Controller myWorkingDirectoryField;

    @RequiredUIAccess
    public BatchCommonOptionsForm(Project project, Disposable uiDisposable) {
        myEnvsField = new EnvironmentVariablesTextFieldWithBrowseButton();

        myInterpreterOptionsField = TextBoxWithExpandAction.create(
            PlatformIconGroup.actionsShow(),
            BatchLocalize.runcfgCaptionsInterpreter_options_dialog().get(),
            ParametersListUtil.DEFAULT_LINE_PARSER,
            ParametersListUtil.DEFAULT_LINE_JOINER
        );

        myWorkingDirectoryField = FileChooserTextBoxBuilder.create(project)
            .dialogTitle(BatchLocalize.runcfgCaptionsSelect_working_directory())
            .fileChooserDescriptor(FileChooserDescriptorFactory.createSingleFolderDescriptor())
            .uiDisposable(uiDisposable)
            .build();
    }

    @RequiredUIAccess
    public void addTo(FormBuilder builder) {
        builder.addLabeled(BatchLocalize.runcfgLabelsEnvironment_variables(), myEnvsField.getComponent());
        builder.addLabeled(BatchLocalize.runcfgLabelsInterpreter_options(), myInterpreterOptionsField);
        builder.addLabeled(BatchLocalize.runcfgLabelsWorking_directory(), myWorkingDirectoryField.getComponent());
    }

    @Override
    @RequiredUIAccess
    public String getInterpreterOptions() {
        return StringUtil.notNullize(myInterpreterOptionsField.getValue()).trim();
    }

    @Override
    @RequiredUIAccess
    public void setInterpreterOptions(String options) {
        myInterpreterOptionsField.setValue(StringUtil.notNullize(options));
    }

    @Override
    @RequiredUIAccess
    public String getWorkingDirectory() {
        return FileUtil.toSystemIndependentName(myWorkingDirectoryField.getValue().trim());
    }

    @Override
    @RequiredUIAccess
    public void setWorkingDirectory(String workingDirectory) {
        myWorkingDirectoryField.setValue(FileUtil.toSystemDependentName(StringUtil.notNullize(workingDirectory)));
    }

    @Override
    public boolean isPassParentEnvs() {
        return myEnvsField.isPassParentEnvs();
    }

    @Override
    @RequiredUIAccess
    public void setPassParentEnvs(boolean passParentEnvs) {
        myEnvsField.setPassParentEnvs(passParentEnvs);
    }

    @Override
    public Map<String, String> getEnvs() {
        return myEnvsField.getEnvs();
    }

    @Override
    @RequiredUIAccess
    public void setEnvs(Map<String, String> envs) {
        myEnvsField.setEnvs(envs);
    }
}
