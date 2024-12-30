package org.intellij.lang.batch.runner;

import consulo.configurable.ConfigurationException;
import consulo.execution.configuration.ui.SettingsEditor;

import jakarta.annotation.Nonnull;
import javax.swing.*;

/**
 * @author wibotwi
 */
public class BatchRunConfigurationEditor extends SettingsEditor<BatchRunConfiguration>
{
	private BatchRunConfigurationForm myForm;

	public BatchRunConfigurationEditor(BatchRunConfiguration batchRunConfiguration)
	{
		this.myForm = new BatchRunConfigurationForm(batchRunConfiguration);
	}

	@Override
	protected void resetEditorFrom(BatchRunConfiguration runConfiguration)
	{
		BatchRunConfiguration.copyParams(runConfiguration, myForm);
	}

	@Override
	protected void applyEditorTo(BatchRunConfiguration runConfiguration) throws ConfigurationException
	{
		BatchRunConfiguration.copyParams(myForm, runConfiguration);
	}

	@Override
	@Nonnull
	protected JComponent createEditor()
	{
		return myForm.getRootPanel();
	}

	@Override
	protected void disposeEditor()
	{
		myForm = null;
	}
}
