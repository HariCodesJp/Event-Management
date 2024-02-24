package daoclasses;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dtoclasses.AdminDto;
import dtoclasses.ServiceDto;

public class ServiceDao 

{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("hari");
	EntityManager em = emf.createEntityManager();
	EntityTransaction et = em.getTransaction();
		
	public ServiceDto saveService(ServiceDto service)
	{
		et.begin();
		em.persist(service);
		et.commit();
		
		return service;
	}
	
	public ServiceDto findService(int id)
	{
		ServiceDto service = em.find(ServiceDto.class, id);
		if(service!=null)
		{
			return service;
		}
		else
		{
			return null;
		}
	}
	
	public ServiceDto deleteService(int id)
	{
		ServiceDto service = em.find(ServiceDto.class, id);
		if(service!=null)
		{
			ServiceDto services = em.find(ServiceDto.class, id);
			AdminDto admin  = em.find(AdminDto.class, id);
			admin.setServices(null);
			
			services.setServiceId(id);
			
			et.begin();
			em.remove(services);
			et.commit();
		}
		return service;
	}
	
	public ServiceDto updateService(ServiceDto service, int id) 
	{
		ServiceDto exService = em.find(ServiceDto.class, id);
		if(exService!=null) 
		{
			service.setServiceId(id);
			et.begin();
			em.merge(service);
			et.commit();
			return service;
		}
		return null;
	}
	
}
