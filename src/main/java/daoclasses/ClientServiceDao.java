package daoclasses;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dtoclasses.ClientServiceDto;

public class ClientServiceDao 
{

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("hari");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
	
	public ClientServiceDto saveClientService(ClientServiceDto clientService) 
	{
		et.begin();
		em.persist(clientService);
		et.commit();
		return clientService;
	}
	
	public ClientServiceDto findClientService(int ClientServiceId) 
	{
		ClientServiceDto clientService = em.find(ClientServiceDto.class, ClientServiceId);
		if(clientService!=null) 
		{
			return clientService;
		}
		return null;
	}
	
	public ClientServiceDto updateClientService(ClientServiceDto clientService, int id) 
	{
		ClientServiceDto exClientService = em.find(ClientServiceDto.class, id);
		if(exClientService!=null) 
		{
			clientService.setClientServiceId(id);
			et.begin();
			em.merge(clientService);
			et.commit();
			return clientService;
		}
		return null;
	}
	
	public ClientServiceDto deleteClientService(int id) 
	{
		ClientServiceDto exClientService = em.find(ClientServiceDto.class, id);
		if(exClientService!=null)
		{
			et.begin();
			em.remove(exClientService);
			et.commit();
			return exClientService;
		}
		return null;
	}
	
	public List<ClientServiceDto> getallClientServices(){
		Query query = em.createQuery("select c from ClientServiceDto c");
		return query.getResultList();
	}
	
}
