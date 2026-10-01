package org.intellij.lang.batch.runner;

import consulo.batch.localize.BatchLocalize;
import consulo.disposer.Disposable;
import consulo.fileChooser.FileChooserDescriptorFactory;
import consulo.fileChooser.FileChooserTextBoxBuilder;
import consulo.platform.base.icon.PlatformIconGroup;
import consulo.process.cmd.ParametersListUtil;
import consulo.project.Project;
import consulo.ui.Component;
import consulo.ui.TextBoxWithExpandAction;
import consulo.ui.annotation.RequiredUIAccess;
import consulo.ui.util.FormBuilder;
import consulo.util.io.FileUtil;
import consulo.util.lang.StringUtil;

/**
 * @author wibotwi
 */
public class BatchRunConfigurationForm implements BatchRunConfigurationParams {
    private final FileChooserTextBoxBuilder.Controller myScriptNameField;
    private final TextBoxWithExpandAction myScriptParametersField;
    private final BatchCommonOptionsForm myCommonOptionsForm;
    private final Component myComponent;

    @RequiredUIAccess
    public BatchRunConfigurationForm(Project project, Disposable uiDisposable) {
        myScriptNameField = FileChooserTextBoxBuilder.create(project)
            .dialogTitle(BatchLocalize.runcfgCaptionsSelect_script())
            .fileChooserDescriptor(FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor())
            .uiDisposable(uiDisposable)
            .build();

        myScriptParametersField = TextBoxWithExpandAction.create(
            PlatformIconGroup.actionsShow(),
            BatchLocalize.runcfgCaptionsScript_parameters_dialog().get(),
            ParametersListUtil.DEFAULT_LINE_PARSER,
            ParametersListUtil.DEFAULT_LINE_JOINER
        );

        myCommonOptionsForm = new BatchCommonOptionsForm(project, uiDisposable);

        FormBuilder builder = FormBuilder.create();
        builder.addLabeled(BatchLocalize.runcfgLabelsScript(), myScriptNameField.getComponent());
        builder.addLabeled(BatchLocalize.runcfgLabelsScript_parameters(), myScriptParametersField);
        myCommonOptionsForm.addTo(builder);
        myComponent = builder.build();
    }

    public Component getComponent() {
        return myComponent;
    }

    @Override
    public CommonBatchRunConfigurationParams getCommonParams() {
        return myCommonOptionsForm;
    }

    @Override
    @RequiredUIAccess
    public String getScriptName() {
        return FileUtil.toSystemIndependentName(myScriptNameField.getValue().trim());
    }

    @Override
    @RequiredUIAccess
    public void setScriptName(String scriptName) {
        myScriptNameField.setValue(StringUtil.notNullize(scriptName));
    }

    @Override
    @RequiredUIAccess
    public String getScriptParameters() {
        return StringUtil.notNullize(myScriptParametersField.getValue()).trim();
    }

    @Override
    @RequiredUIAccess
    public void setScriptParameters(String scriptParameters) {
        myScriptParametersField.setValue(StringUtil.notNullize(scriptParameters));
    }
}
