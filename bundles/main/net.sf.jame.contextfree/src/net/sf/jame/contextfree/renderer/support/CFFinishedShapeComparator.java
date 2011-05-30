package net.sf.jame.contextfree.renderer.support;

import java.util.Comparator;


public class CFFinishedShapeComparator implements Comparator<CFFinishedShape> {
	public int compare(CFFinishedShape o1, CFFinishedShape o2) {
		return o1.compareTo(o2);
	}
}
