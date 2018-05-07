package org.intellij.lang.batch.fileTypes;

import javax.annotation.Nonnull;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;

/**
 * @author wibotwi
 */
public class BatchFileTypeFactory extends FileTypeFactory
{

	@Override
	public void createFileTypes(@Nonnull FileTypeConsumer consumer)
	{
		consumer.consume(BatchFileType.INSTANCE, "bat;cmd");
	}
}
