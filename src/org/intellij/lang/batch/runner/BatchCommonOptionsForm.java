package org.intellij.lang.batch.runner;

import com.intellij.execution.configuration.EnvironmentVariablesComponent;
import com.intellij.ide.util.BrowseFilesListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.ui.RawCommandLineEditor;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;

import java.awt.*;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author wibotwi
 */
public class BatchCommonOptionsForm implements CommonBatchRunConfigurationParams
{
	private JPanel rootPanel;
	private EnvironmentVariablesComponent envsField;
	private TextFieldWithBrowseButton workingDirectoryField;
	private RawCommandLineEditor interpreterOptionsField;

	public BatchCommonOptionsForm(BatchRunConfiguration runConfiguration)
	{
		workingDirectoryField.addBrowseFolderListener("Select Working Directory", "", runConfiguration.getProject(), BrowseFilesListener.SINGLE_DIRECTORY_DESCRIPTOR);
	}

	public JPanel getRootPanel()
	{
		return rootPanel;
	}

	public String getInterpreterOptions()
	{
		return interpreterOptionsField.getText().trim();
	}

	public void setInterpreterOptions(String options)
	{
		interpreterOptionsField.setText(options);
	}

	public String getWorkingDirectory()
	{
		return FileUtil.toSystemIndependentName(workingDirectoryField.getText().trim());
	}

	public void setWorkingDirectory(String workingDirectory)
	{
		workingDirectoryField.setText(FileUtil.toSystemDependentName(workingDirectory));
	}

	public boolean isPassParentEnvs()
	{
		return envsField.isPassParentEnvs();
	}

	public void setPassParentEnvs(boolean passParentEnvs)
	{
		envsField.setPassParentEnvs(passParentEnvs);
	}

	public Map<String, String> getEnvs()
	{
		return envsField.getEnvs();
	}

	public void setEnvs(Map<String, String> envs)
	{
		envsField.setEnvs(envs);
	}

	public TextFieldWithBrowseButton getWorkingDirectoryField()
	{
		return workingDirectoryField;
	}

}
