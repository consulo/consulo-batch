package org.intellij.lang.batch.runner;

import consulo.configurable.ConfigurationException;
import consulo.execution.configuration.ui.SettingsEditor;
import consulo.project.Project;
import consulo.ui.Component;
import consulo.ui.annotation.RequiredUIAccess;
import jakarta.annotation.Nullable;

/**
 * @author wibotwi
 */
public class BatchRunConfigurationEditor extends SettingsEditor<BatchRunConfiguration> {
    private final Project myProject;

    @Nullable
    private BatchRunConfigurationForm myForm;

    public BatchRunConfigurationEditor(BatchRunConfiguration batchRunConfiguration) {
        myProject = batchRunConfiguration.getProject();
    }

    @Override
    @RequiredUIAccess
    protected void resetEditorFrom(BatchRunConfiguration runConfiguration) {
        BatchRunConfigurationForm form = myForm;
        if (form == null) {
            return;
        }

        BatchRunConfiguration.copyParams(runConfiguration, form);
    }

    @Override
    @RequiredUIAccess
    protected void applyEditorTo(BatchRunConfiguration runConfiguration) throws ConfigurationException {
        BatchRunConfigurationForm form = myForm;
        if (form == null) {
            return;
        }

        BatchRunConfiguration.copyParams(form, runConfiguration);
    }

    @Override
    @RequiredUIAccess
    protected Component createUIComponent() {
        BatchRunConfigurationForm form = new BatchRunConfigurationForm(myProject, this);
        myForm = form;
        return form.getComponent();
    }

    @Override
    protected void disposeEditor() {
        myForm = null;
    }
}
