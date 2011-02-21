package net.sf.jame.contextfree.renderer.support;

import java.util.Comparator;


public class CFShapeComparator implements Comparator<CFShape> {
	public int compare(CFShape o1, CFShape o2) {
		if (o1 == o2) {
			return 0;
		}
		float d = o1.getWorldState().getZ() - o2.getWorldState().getZ();
		return d < 0 ? -1 : 1;
	}
}
