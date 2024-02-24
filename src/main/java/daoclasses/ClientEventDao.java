package daoclasses;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dtoclasses.ClientEventDto;

public class ClientEventDao 

{
EntityManagerFactory emf = Persistence.createEntityManagerFactory("hari");
EntityManager em = emf.createEntityManager();
EntityTransaction et = em.getTransaction();


public ClientEventDto saveClientEvent(ClientEventDto clientEvent) 
{
	et.begin();
	em.persist(clientEvent);
	et.commit();
	return clientEvent;
}

public ClientEventDto findClientEvent(int clientEventId) 
{
	ClientEventDto clientEvent = em.find(ClientEventDto.class, clientEventId);
	if(clientEvent!=null) 
	{
		return clientEvent;
	}
	return null;
}


public ClientEventDto updateClientEvent(ClientEventDto clientEvent,int id)
	
	{
		ClientEventDto exClient = em.find(ClientEventDto.class, id);
		if(exClient!=null)
		{
			clientEvent.setClientEventId(id);
			et.begin();
			em.merge(clientEvent);
			et.commit();
			return clientEvent;
		}
		return null;
	}


public ClientEventDto deleteClientEvent(int id) 
{
	ClientEventDto exClientEvent = em.find(ClientEventDto.class, id);
	if(exClientEvent!=null) 
	{
		et.begin();
		em.remove(exClientEvent);
		et.commit();
		return exClientEvent;
	}
	return null;
}

}
