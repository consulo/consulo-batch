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
import consulo.localize.LocalizeValue;
import consulo.platform.base.icon.PlatformIconGroup;
import consulo.process.cmd.ParametersListUtil;
import consulo.project.Project;
import consulo.ui.TextBoxWithExpandAction;
import consulo.ui.annotation.RequiredUIAccess;
import consulo.util.lang.StringUtil;

/**
 * Options of {@code cmd.exe} for a {@link BatchRunConfiguration}, shown once set.
 *
 * @author VISTALL
 * @since 2026-10-24
 */
class BatchInterpreterOptionsFragment implements RunConfigurationFragment<BatchRunConfiguration> {
    @Override
    public String getId() {
        return "batchInterpreterOptions";
    }

    @Override
    public LocalizeValue getName() {
        return BatchLocalize.runcfgFragmentInterpreter_options();
    }

    @Override
    public boolean isVisibleByDefault(BatchRunConfiguration target) {
        return target.getInterpreterOptions() != null;
    }

    @Override
    public void applyHidden(BatchRunConfiguration target) {
        target.setInterpreterOptions(null);
    }

    @RequiredUIAccess
    @Override
    public RunConfigurationFragmentEditor<BatchRunConfiguration> createEditor(Project project, Disposable parent) {
        TextBoxWithExpandAction interpreterOptions = TextBoxWithExpandAction.create(
            PlatformIconGroup.actionsShow(),
            BatchLocalize.runcfgCaptionsInterpreter_options_dialog().get(),
            ParametersListUtil.DEFAULT_LINE_PARSER,
            ParametersListUtil.DEFAULT_LINE_JOINER
        );

        return new RunConfigurationFragmentEditor<>() {
            @RequiredUIAccess
            @Override
            public void addRows(RunConfigurationFragmentLayout layout) {
                layout.addLabeled(BatchLocalize.runcfgLabelsInterpreter_options(), interpreterOptions);
            }

            @RequiredUIAccess
            @Override
            public void reset(BatchRunConfiguration target) {
                interpreterOptions.setValue(StringUtil.notNullize(target.getInterpreterOptions()));
            }

            @RequiredUIAccess
            @Override
            public void apply(BatchRunConfiguration target) {
                target.setInterpreterOptions(interpreterOptions.getValue());
            }
        };
    }
}
