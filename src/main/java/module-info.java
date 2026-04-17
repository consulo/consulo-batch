/**
 * @author VISTALL
 * @since 25-Mar-22
 */
module consulo.batch
{
	// TODO remove this dependencies in future
	requires java.desktop;
	requires forms.rt;

	requires consulo.application.api;
	requires consulo.code.editor.api;
	requires consulo.color.scheme.api;
	requires consulo.compiler.api;
	requires consulo.component.api;
	requires consulo.configurable.api;
	requires consulo.execution.api;
	requires consulo.execution.impl;
	requires consulo.language.api;
	requires consulo.language.impl;
	requires consulo.language.editor.api;
	requires consulo.localize.api;
	requires consulo.logging.api;
	requires consulo.module.api;
	requires consulo.process.api;
	requires consulo.project.api;
	requires consulo.ui.api;
	requires consulo.ui.ex.awt.api;
	requires consulo.virtual.file.system.api;
	requires consulo.util.io;
	requires consulo.util.lang;
	requires consulo.util.xml.serializer;
}