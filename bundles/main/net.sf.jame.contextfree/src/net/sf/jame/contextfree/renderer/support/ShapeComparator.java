package net.sf.jame.contextfree.renderer.support;

import java.util.Comparator;


public class ShapeComparator implements Comparator<FinishedShape> {
	public int compare(FinishedShape o1, FinishedShape o2) {
		if (o1 == o2) {
			return 0;
		}
		float d = o1.getZ() - o2.getZ();
		return d < 0 ? -1 : 1;
	}
}
