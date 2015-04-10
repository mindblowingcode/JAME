package net.sf.jame.test.swtapp;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * @author amedeghini
 */
public class TestSWTApplication implements IApplication {
	private TestSWT app;
	
	/**
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public Object start(IApplicationContext context) throws Exception {
		context.applicationRunning();
		app = new TestSWT();
		app.run();
		if (app.getResult() == TestSWT.RESTART) {
			return IApplication.EXIT_RESTART;
		}
		return IApplication.EXIT_OK;
	}

	/**
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		if (app != null) {
			app.stop();
		}
	}
}
