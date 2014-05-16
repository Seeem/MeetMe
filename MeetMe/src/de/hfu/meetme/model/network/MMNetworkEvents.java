/**
 * 
 */
package de.hfu.meetme.model.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;

import de.hfu.anybeam.networkCore.Client;
import de.hfu.anybeam.networkCore.NetworkEnvironment;
import de.hfu.anybeam.networkCore.NetworkEnvironmentListener;

/**
 * @author Simeon Sembach
 *
 */
public class MMNetworkEvents implements NetworkEnvironmentListener
{

	/** */
	private NetworkEnvironment networkEnvironment;
	
	/**
	 * 
	 */
	public MMNetworkEvents(final NetworkEnvironment aNetworkEnvironment)
	{
		if (aNetworkEnvironment == null)
			throw new IllegalArgumentException("network-environment is null");

		setNetworkEnvironment(aNetworkEnvironment);
		
		aNetworkEnvironment.addNetworkEnvironmentListener(this);
		
		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				try {
					aNetworkEnvironment.dispose();

				} catch (Exception e) {
					e.printStackTrace();

				}

			}
		});
	}

	// MM-API (Instance):
	
	@Override
	public void clientFound(Client aClient)
	{
	    ObjectOutputStream oos;
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);		
		    oos.writeObject(null);
		    oos.flush();
		    oos.close();
		    
		    InputStream is = new ByteArrayInputStream(baos.toByteArray());
		    
		    aClient.sendData(is, getNetworkEnvironment().getNetworkEnvironmentSettings());
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void clientUpdated(Client aC)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientLost(Client aC)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientListCleared()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientSearchStarted()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientSearchDone()
	{
		// TODO Auto-generated method stub
		
	}

	// Accessors (Instance):
	
	/**
	 * @return the networkEnvironment
	 */
	public NetworkEnvironment getNetworkEnvironment()
	{
		return networkEnvironment;
	}
	
	/**
	 * @param networkEnvironment the networkEnvironment to set
	 */
	public void setNetworkEnvironment(NetworkEnvironment networkEnvironment)
	{
		this.networkEnvironment = networkEnvironment;
	}
	
}
