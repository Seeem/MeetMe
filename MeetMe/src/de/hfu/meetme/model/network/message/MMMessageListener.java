/**
 * 
 */
package de.hfu.meetme.model.network.message;


/**
 * @author Simeon Sembach
 *
 */
public interface MMMessageListener
{

	/**
	 * 
	 * @param aMessageEvent the {@link MMMessageEvent}
	 */
	public void messageReceived(MMMessageEvent aMessageEvent);
	
}
