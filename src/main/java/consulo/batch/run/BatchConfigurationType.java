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

import consulo.annotation.component.ExtensionImpl;
import consulo.application.Application;
import consulo.batch.icon.BatchIconGroup;
import consulo.execution.configuration.ConfigurationFactory;
import consulo.execution.configuration.ConfigurationTypeBase;
import consulo.execution.configuration.RunConfiguration;
import consulo.execution.configuration.RunConfigurationModule;
import consulo.project.Project;
import org.intellij.lang.batch.runner.BatchRunConfiguration;

import javax.annotation.Nonnull;
/**
 * @author VISTALL
 */
@ExtensionImpl
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

	@Nonnull
	public static BatchConfigurationType getInstance()
	{
		return Application.get().getExtensionPoint(BatchConfigurationType.class).findExtensionOrFail(BatchConfigurationType.class);
	}

	public BatchConfigurationType()
	{
		super("BatchConfigurationType", "Batch", "Batch run configuration", BatchIconGroup.batch());
		addFactory(new BatchConfigurationFactory(this));
	}
}
