/*
 * Copyright 2013-2026 consulo.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.intellij.lang.batch.runner;

import consulo.batch.localize.BatchLocalize;
import consulo.disposer.Disposable;
import consulo.execution.configuration.fragment.RunConfigurationFragment;
import consulo.execution.configuration.fragment.RunConfigurationFragmentEditor;
import consulo.execution.configuration.fragment.RunConfigurationFragmentLayout;
import consulo.fileChooser.FileChooserDescriptorFactory;
import consulo.fileChooser.FileChooserTextBoxBuilder;
import consulo.localize.LocalizeValue;
import consulo.project.Project;
import consulo.ui.annotation.RequiredUIAccess;
import consulo.util.io.FileUtil;
import consulo.util.lang.StringUtil;

/**
 * The script a {@link BatchRunConfiguration} runs.
 *
 * @author VISTALL
 * @since 2026-10-24
 */
class BatchScriptFragment implements RunConfigurationFragment<BatchRunConfiguration> {
    @Override
    public String getId() {
        return "batchScript";
    }

    @Override
    public LocalizeValue getName() {
        return BatchLocalize.runcfgFragmentScript();
    }

    @Override
    public boolean isRemovable() {
        return false;
    }

    @RequiredUIAccess
    @Override
    public RunConfigurationFragmentEditor<BatchRunConfiguration> createEditor(Project project, Disposable parent) {
        FileChooserTextBoxBuilder.Controller scriptName = FileChooserTextBoxBuilder.create(project)
            .dialogTitle(BatchLocalize.runcfgCaptionsSelect_script())
            .fileChooserDescriptor(FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor())
            .uiDisposable(parent)
            .build();

        return new RunConfigurationFragmentEditor<>() {
            @RequiredUIAccess
            @Override
            public void addRows(RunConfigurationFragmentLayout layout) {
                layout.addLabeled(BatchLocalize.runcfgLabelsScript(), scriptName.getComponent());
            }

            @RequiredUIAccess
            @Override
            public void reset(BatchRunConfiguration target) {
                scriptName.setValue(FileUtil.toSystemDependentName(StringUtil.notNullize(target.getScriptName())), false);
            }

            @RequiredUIAccess
            @Override
            public void apply(BatchRunConfiguration target) {
                target.setScriptName(FileUtil.toSystemIndependentName(StringUtil.notNullize(scriptName.getValue()).trim()));
            }
        };
    }
}
