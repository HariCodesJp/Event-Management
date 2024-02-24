package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import daoclasses.AdminDao;
import daoclasses.ClientDao;
import daoclasses.ClientEventDao;
import daoclasses.ClientServiceDao;
import daoclasses.ServiceDao;
import dtoclasses.AdminDto;
import dtoclasses.ClientDto;
import dtoclasses.ClientEventDto;
import dtoclasses.ClientServiceDto;
import dtoclasses.EventType;
import dtoclasses.ServiceDto;


public class EventManagement {

	AdminDao aDao = new AdminDao();
	ServiceDao sDao = new ServiceDao();
	ClientDao cDao = new ClientDao();
	ClientEventDao ceDao = new ClientEventDao();
	ClientServiceDao csDao = new ClientServiceDao();
	
	
	public static void main(String[] args) 
	
	{
				EventManagement em = new EventManagement();
//				System.out.println(em.loginAdmin());
//				System.out.println(em.saveService());
//				System.out.println(em.saveAdmin());
//				System.out.println(em.getALlServices());
//				System.out.println(em.deleteService());
//				System.out.println(em.saveClient());
//				System.out.println(em.loginClient());
				System.out.println(em.createClientEvent());
//				System.out.println(em.deleteEventService());
//				em.addEventServices();
	}
	
	public AdminDto saveAdmin()
	{
		AdminDto admin = new AdminDto();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Admin Name");
		admin.setAdminName(sc.next());
		System.out.println("Enter Admin Mail");
		admin.setAdminEmail(sc.next());
		System.out.println("Enter Admin Password");
		admin.setAdminPassword(sc.next());
		System.out.println("Enter Admin Number");
		admin.setAdminContact(sc.nextLong());
		
		return aDao.saveAdmin(admin);
		
	}
	
	public AdminDto loginAdmin() {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter Admin Email");
	    String adminMail = sc.next();

	    System.out.println("Enter Admin Password");
	    String adminPassword = sc.next();

	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hari");
	    EntityManager em = emf.createEntityManager();

	    String q = "select a from AdminDto a where a.adminEmail=?1";
	    javax.persistence.Query query = em.createQuery(q);
	    query.setParameter(1, adminMail);
	    
	    AdminDto a = null;
	    try {
	        a = (AdminDto) query.getSingleResult();
	    	} 
	    catch (NoResultException e) 
	    {
	        System.out.println("Admin not found!");
	        return null;
	    }
	    
	    if (a.getAdminEmail().equals(adminMail) && a.getAdminPassword().equals(adminPassword)) {
	        return a;
	    } else {
	        System.out.println("Invalid credentials!");
	        return null;
	    }
	}
		
	public ServiceDto saveService()
	{
		System.out.println("Enter Admin Login Credentials");
		AdminDto admin = loginAdmin();
		if(admin!=null)
		{
		ServiceDto service = new ServiceDto();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Service Name");
		service.setServiceName(sc.next());
		System.out.println("Enter Cost Per Day");
		service.setServiceCostPerDay(sc.nextDouble());
		System.out.println("Enter Cost Per Persons");
		service.setServiceCostPerPerson(sc.nextDouble());
		ServiceDto savedservice = sDao.saveService(service);
		admin.getServices().add(savedservice);
		aDao.updateAdmin(admin, admin.getAdminId());
		return service;
		
		}
			return null;
	}
	
	public List<ServiceDto> getALlServices() {
		System.out.println("Enter Admin Credentials To Proceed");
		AdminDto exAdmin = loginAdmin();
		if (exAdmin != null) {
			return exAdmin.getServices();
		}
		return null;
	}
	
	
	public String updateService()
	{
		System.out.println("Enter Admin Login Credentials");
		AdminDto admin = loginAdmin();
		if(admin!=null)
		{
			List<ServiceDto> service = getALlServices();
			for(ServiceDto s : service)
			{
				System.out.println("serviceId    " + "serviceName   " + "cost_per_person   " + "cost_per_day");
				System.out.println("     " + s.getServiceId() + "        " + s.getServiceName() + "          "
						+ s.getServiceCostPerPerson() + "           " + s.getServiceCostPerDay());
			}
			Scanner sc = new Scanner(System.in);
			System.out.println("%%%%%%%%%%%%%%%%%%% choose service id from above to update %%%%%%%%%%%%%%%%%%%%");
			int id = sc.nextInt();
			ServiceDto tobeUpdated = sDao.findService(id);
			System.out.println("enter updated cost per person");
			double costperPerson = sc.nextDouble();
			System.out.println("enter updated cost per day");
			double costperday = sc.nextDouble();
			tobeUpdated.setServiceCostPerDay(costperday);
			tobeUpdated.setServiceCostPerPerson(costperPerson);

			ServiceDto updated = sDao.updateService(tobeUpdated, id);
			if (updated != null) 
			{
				return "service update success";
			}
			return "invalid service id";
		}
		return "Email Id Invalid";
	}
	
	public void deleteService() 
	{
		Scanner sc = new Scanner(System.in);
		AdminDto exAdmin = loginAdmin();

		if (exAdmin != null) {
			List<ServiceDto> services = exAdmin.getServices();
			System.out.println("%%%%%%%%%%%%%%%%%%% choose service id from below to delete %%%%%%%%%%%%%%%%%%%%");

			for (ServiceDto s : services) {
				System.out.println("serviceId    " + "serviceName   " + "cost_per_person   " + "cost_per_day");
				System.out.println("     " + s.getServiceId() + "        " + s.getServiceName() + "          "
						+ s.getServiceCostPerPerson() + "           " + s.getServiceCostPerDay());
			}
			int id = sc.nextInt();

			List<ServiceDto> newList = new ArrayList<ServiceDto>();

			for (ServiceDto s : services) {
				if (s.getServiceId() != id) {
					newList.add(s);
				}
			}

			exAdmin.setServices(newList);
			aDao.updateAdmin(exAdmin, exAdmin.getAdminId());
			sDao.deleteService(id);
		}
	}
	
	public ClientDto saveClient()
	{
		
		ClientDto client = new ClientDto();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Client Name");
		client.setClientName(sc.next());
		System.out.println("Enter Client Mail");
		 client.setClientMail(sc.next());
		System.out.println("Enter Client Contact");
		client.setClientContact(sc.nextLong());
		System.out.println("Enter Client Password");
		client.setClientPassword(sc.next());
		cDao.saveClient(client);
		return client;
	
	}
	
	public ClientDto loginClient() {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Enter Client Email");
	    String clientMail = sc.next();

	    System.out.println("Enter Client Password");
	    String clientPassword = sc.next();

	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hari");
	    EntityManager em = emf.createEntityManager();

	    String q = "select c from ClientDto c where c.clientMail=?1";
	    javax.persistence.Query query = em.createQuery(q);
	    query.setParameter(1, clientMail);
	    
	    ClientDto c = null;
	    try {
	        c = (ClientDto) query.getSingleResult();
	    	} 
	    catch (NoResultException e) 
	    {
	        System.out.println("Client not found!");
	        return null;
	    }
	    
	    if (c.getClientMail().equals(clientMail) && c.getClientPassword().equals(clientPassword)) {
	        return c;
	    } else {
	        System.out.println("Invalid credentials!");
	        return null;
	    }
	}
	
	public ClientDto createClientEvent() {
		ClientDto client = loginClient();
		ClientEventDto event = new ClientEventDto();
		if (client != null) {

			System.out.println("enter event type");
			int i = 1;
			for (EventType e : EventType.values()) {
				System.out.println(i + " " + e);
				i++;
			}
			Scanner sc = new Scanner(System.in);
			int eventType = sc.nextInt();
			switch (eventType) {
			case 1:
				event.setEvent(EventType.Anniversary);
				break;
			case 2:
				event.setEvent(EventType.Babyshower);
				break;
			case 3:
				event.setEvent(EventType.Bachelorparty);
				break;
			case 4:
				event.setEvent(EventType.Birthday);
				break;
			case 5:
				event.setEvent(EventType.Engagement);
				break;
			case 6:
				event.setEvent(EventType.Marriage);
				break;
			case 7:
				event.setEvent(EventType.Namingceremony);
				break;
			case 8:
				event.setEvent(EventType.Reunion);
				break;
			default:
				event.setEvent(EventType.Birthday);
				break;
			}
			System.out.println("enter the event location");
			String eventLocation = sc.next();
			event.setClientEventLocation(eventLocation);

			System.out.println("enter the " + event.getEvent() + " year");
			int year = sc.nextInt();
			System.out.println("enter the " + event.getEvent() + " month");
			int month = sc.nextInt();
			System.out.println("enter the " + event.getEvent() + " date");
			int day = sc.nextInt();
			event.setStartdate(LocalDate.of(year, month, day));

			System.out.println("enter number of days for the event");
			event.setClientEventNoOfDays(sc.nextInt());

			System.out.println("enter the number of people that will be attending the event");
			event.setClientEventNoOfPeople(sc.nextInt());
			
			event.setClient(client);
			client.getClient().add(event);

			
			cDao.updateClient(client, client.getClientId());
			System.out.println("Enter User Credential To Add Event Service");
			addEventServices();
			
			
			return client;

		}
		return null;
	}
	
	public void addEventServices() 
	{
		
		ClientDto client = loginClient();
		if (client != null) 
		{
			for (ClientEventDto event : client.getClient())
			{
				System.out.println("Event Id   " + "Event type   " + "Event location");
				System.out.println(
						event.getClientEventId() + " " + event.getEvent() + " " + event.getClientEventLocation());
			}
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the Event Id you want to add Services");
			int eventid = sc.nextInt();
			ClientEventDto event = ceDao.findClientEvent(eventid);
			if (event != null) 
			{
				System.out.println("Enter Admin Credentials");
				AdminDto admin = loginAdmin();
				if (admin != null) 
				{
					System.out.println("Enter the number of services you want to add to the event");
					int count = sc.nextInt();
					while (count > 0) 
					{

						for (ServiceDto cs : admin.getServices()) 
						{
							System.out.println(cs.getServiceId() + " " + cs.getServiceName());
						}
						System.out.println("Enter the service id you want to choose");
						int serviceId = sc.nextInt();

						ServiceDto exService = sDao.findService(serviceId);
						ClientServiceDto cs = new ClientServiceDto();
 						cs.setClientServiceName(exService.getServiceName());
						cs.setClientServiceNoOfDays(event.getClientEventNoOfDays());
						cs.setClientServiceCostPerPerson(exService.getServiceCostPerPerson());

						if (exService.getServiceCostPerPerson() == 0) 
						{
							cs.setClientServiceCost(exService.getServiceCostPerDay() * event.getClientEventNoOfDays());
						} else 
						{
							cs.setClientServiceCost(exService.getServiceCostPerDay() * event.getClientEventNoOfDays()
									* exService.getServiceCostPerPerson());

						}
						event.setClientEventCost(event.getClientEventCost() + cs.getClientServiceCost());
						event.getClientService().add(cs);
						ceDao.updateClientEvent(event, event.getClientEventId());
						count--;
						

					}
				}
			}
		} 
	}
	  
	  public ClientEventDto deleteEventService() {
			ClientDto client = loginClient();
			if (client != null) 
			{
				for (ClientEventDto event : client.getClient())
				{
					System.out.println(event.getClientEventId()+" "+event.getEvent());
				}
				
				Scanner sc = new Scanner(System.in);
				System.out.println("choose the  event from which you want to remove a service");
				int eventId = sc.nextInt();
				ClientEventDto event = ceDao.findClientEvent(eventId);
				List<ClientServiceDto> services = event.getClientService();
				for(ClientServiceDto service: services) {
					System.out.println(service.getClientServiceId()+" "+service.getClientServiceName());
				}
				System.out.println("enter the service id you want to delete from event");
				int sid = sc.nextInt();
				for(ClientServiceDto service: services) {
					if(service.getClientServiceId()==sid) {
						services.remove(service);
					}
				}
				event.setClientService(services);
				
				ceDao.updateClientEvent(event,event.getClientEventId());
				return event;
			}
			return null;
		}
}