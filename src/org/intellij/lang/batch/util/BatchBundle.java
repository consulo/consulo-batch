package org.intellij.lang.batch.util;

import org.jetbrains.annotations.PropertyKey;
import com.intellij.AbstractBundle;

public final class BatchBundle extends AbstractBundle
{
	private static final BatchBundle ourInstance = new BatchBundle();

	private BatchBundle()
	{
		super("messages.BatchBundle");
	}

	public static String message(@PropertyKey(resourceBundle = "messages.BatchBundle") String key)
	{
		return ourInstance.getMessage(key);
	}

	public static String message(@PropertyKey(resourceBundle = "messages.BatchBundle") String key, Object... params)
	{
		return ourInstance.getMessage(key, params);
	}
}
