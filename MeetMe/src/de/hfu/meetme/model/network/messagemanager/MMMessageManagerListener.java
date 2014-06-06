/**
 * 
 */
package de.hfu.meetme.model.network.messagemanager;

/**
 * @author Simeon Sembach
 *
 */
public interface MMMessageManagerListener
{

	/**
	 * 
	 * @param aMessageManagerEvent
	 */
	public void managerEventPerformed(MMMessageManagerEvent aMessageManagerEvent);
	
}
