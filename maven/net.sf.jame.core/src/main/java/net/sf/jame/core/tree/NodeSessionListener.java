/**
 * 
 */
package net.sf.jame.core.tree;

/**
 * @author Andrea Medeghini
 */
public interface NodeSessionListener {
	public void fireSessionChanged();

	public void fireSessionAccepted();

	public void fireSessionCancelled();
}
