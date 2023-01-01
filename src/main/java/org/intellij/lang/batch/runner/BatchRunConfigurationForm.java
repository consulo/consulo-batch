package org.intellij.lang.batch.runner;

import consulo.execution.ui.awt.RawCommandLineEditor;
import consulo.ui.ex.awt.TextFieldWithBrowseButton;
import consulo.ui.ex.awt.util.BrowseFilesListener;
import consulo.util.io.FileUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author wibotwi
 */
public class BatchRunConfigurationForm implements BatchRunConfigurationParams
{
	private TextFieldWithBrowseButton scriptNameField;
	private RawCommandLineEditor scriptParametersField;
	private JPanel commonOptionsPlaceholder;
	private JPanel rootPanel;
	private BatchCommonOptionsForm commonOptionsForm;

	public BatchRunConfigurationForm(BatchRunConfiguration runConfiguration)
	{
		commonOptionsForm = new BatchCommonOptionsForm(runConfiguration);
		commonOptionsPlaceholder.add(commonOptionsForm.getRootPanel(), BorderLayout.CENTER);

		scriptNameField.addBrowseFolderListener("Select Script", "", runConfiguration.getProject(), BrowseFilesListener.SINGLE_FILE_DESCRIPTOR);
	}

	public JPanel getRootPanel()
	{
		return rootPanel;
	}

	public CommonBatchRunConfigurationParams getCommonParams()
	{
		return commonOptionsForm;
	}

	public String getScriptName()
	{
		return FileUtil.toSystemIndependentName(scriptNameField.getText().trim());
	}

	public void setScriptName(String scriptName)
	{
		scriptNameField.setText(scriptName);
	}

	public String getScriptParameters()
	{
		return scriptParametersField.getText().trim();
	}

	public void setScriptParameters(String scriptParameters)
	{
		scriptParametersField.setText(scriptParameters);
	}

}
