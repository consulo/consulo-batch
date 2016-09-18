/*
 * Copyright 2013-2016 consulo.io
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

package consulo.batch.run;

import org.intellij.lang.batch.runner.BatchRunConfiguration;
import org.intellij.lang.batch.util.BatchIcons;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunConfigurationModule;
import com.intellij.openapi.project.Project;
import consulo.lombok.annotations.Lazy;

/**
 * @author VISTALL
 */
public class BatchConfigurationType extends ConfigurationTypeBase
{
	private static class BatchConfigurationFactory extends ConfigurationFactory
	{
		public BatchConfigurationFactory(BatchConfigurationType batchConfigurationType)
		{
			super(batchConfigurationType);
		}

		@Override
		public RunConfiguration createTemplateConfiguration(Project project)
		{
			return new BatchRunConfiguration(new RunConfigurationModule(project), this, "");
		}
	}

	@NotNull
	@Lazy
	public static BatchConfigurationType getInstance()
	{
		return CONFIGURATION_TYPE_EP.findExtension(BatchConfigurationType.class);
	}

	public BatchConfigurationType()
	{
		super("BatchConfigurationType", "Batch", "Batch run configuration", BatchIcons.Batch);
		addFactory(new BatchConfigurationFactory(this));
	}
}
