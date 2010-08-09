package net.sf.jame.contextfree.renderer;

import java.util.Comparator;

public class ShapeComparator implements Comparator<ContextFreeShape> {
	public int compare(ContextFreeShape o1, ContextFreeShape o2) {
		if (o1 == o2) {
			return 0;
		}
		float d = o1.getZ() - o2.getZ();
		return d < 0 ? -1 : 1;
	}
}
