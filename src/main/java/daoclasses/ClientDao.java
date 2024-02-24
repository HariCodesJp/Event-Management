package daoclasses;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dtoclasses.ClientDto;

public class ClientDao 

{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("hari");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
		
	public ClientDto saveClient(ClientDto client)
	{
		et.begin();
		em.persist(client);
		et.commit();
		
		return client;
	}
	
	public ClientDto findClient(int id)
	{
		ClientDto client = em.find(ClientDto.class, id);
		if(client!=null)
		{
			return client;
		}
		else
		{
			return null;
		}
	}
	
	public ClientDto deleteClient(int id)
	{
		ClientDto client = em.find(ClientDto.class, id);
		if(client!=null)
		{
			et.begin();
			em.remove(client);
			et.commit();
		}
		return client;
	}
	
	public ClientDto updateClient(ClientDto client,int id)
	
	{
		ClientDto exClient = em.find(ClientDto.class, id);
		if(exClient!=null)
		{
			client.setClientId(id);
			et.begin();
			em.merge(client);
			et.commit();
			return client;
		}
		return null;
	}
}
