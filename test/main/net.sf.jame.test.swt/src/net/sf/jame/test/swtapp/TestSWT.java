package net.sf.jame.test.swtapp;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TestSWT {
	public static final int RESTART = 1;
	private volatile int result = 0;
	private Shell shell;

	public void run() throws Exception {
		Display display = new Display();
		shell = new Shell(display);
		shell.setSize(400, 300);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.close();
	}

	public void stop() {
		shell.close();
		shell.dispose();
	}

	public int getResult() {
		return result;
	}
}
