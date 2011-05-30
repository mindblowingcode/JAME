package net.sf.jame.contextfree.renderer.support;

import java.util.ArrayList;
import java.util.List;

public class CFPathAttributes implements Cloneable {
	private List<CFPathAttribute> attributes = new ArrayList<CFPathAttribute>();
	
	public CFPathAttributes() {
	}
	
	public CFPathAttributes(List<CFPathAttribute> attributes) {
		this.attributes.addAll(attributes);
	}

	public void addAttribute(CFPathAttribute attribute) {
		attributes.add(attribute);
	}
	
	public void removeAttribute(CFPathAttribute attribute) {
		attributes.remove(attribute);
	}
	
	public CFPathAttribute getAttribute(int index) {
		return attributes.get(index);
	}
	
	public int getAttributeCount() {
		return attributes.size();
	}

	@Override
	public CFPathAttributes clone() {
		return new CFPathAttributes(attributes);
	}

	@Override
	public String toString() {
		return "CFPathAttributes [attributes=" + attributes + "]";
	}
}
