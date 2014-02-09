package org.intellij.lang.batch.fileTypes;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;

/**
 * @author wibotwi
 */
public class BatchFileTypeFactory extends FileTypeFactory
{

	@Override
	public void createFileTypes(@NotNull FileTypeConsumer consumer)
	{
		for(String ext : BatchFileType.DEFAULT_ASSOCIATED_EXTENSIONS)
			consumer.consume(BatchFileType.BATCH_FILE_TYPE, ext);
	}
}
