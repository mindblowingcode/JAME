package net.sf.jame.contextfree.renderer.support;

import java.util.Comparator;


public class CFFinishedShapeComparator implements Comparator<CFFinishedShape> {
	public int compare(CFFinishedShape o1, CFFinishedShape o2) {
		if (o1 == o2) {
			return 0;
		}
		float d = o1.getWorldState().getZ() - o2.getWorldState().getZ();
		return d < 0 ? -1 : 1;
	}
}
