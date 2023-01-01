package org.intellij.lang.batch.util;

import consulo.component.util.localize.AbstractBundle;
import org.jetbrains.annotations.PropertyKey;

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
