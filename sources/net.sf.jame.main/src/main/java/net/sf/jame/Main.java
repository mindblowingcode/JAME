package net.sf.jame;

import net.sf.jame.core.ApplicationContext;
import net.sf.jame.twister.swing.JAME;

public class Main implements ApplicationContext {
	public static void main(String[] args) {
		JAME application = new JAME();
		try {
			application.start(new Main());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	@Override
	public void applicationRunning() {
	}
}
