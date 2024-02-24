package daoclasses;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dtoclasses.AdminDto;

public class AdminDao 
{

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("hari");
			EntityManager em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
				
			public AdminDto saveAdmin(AdminDto admin)
			{
				et.begin();
				em.persist(admin);
				et.commit();
				
				return admin;
			}
			
			public AdminDto findAdmin(int id)
			{
				AdminDto admin = em.find(AdminDto.class, id);
				if(admin!=null)
				{
					return admin;
				}
				else
				{
					return null;
				}
			}
			
			public AdminDto deleteAdmin(int id)
			{
				AdminDto admin = em.find(AdminDto.class, id);
				if(admin!=null)
				{
					et.begin();
					em.remove(admin);
					et.commit();
				}
				return admin;
			}
			
			public AdminDto updateAdmin(AdminDto admin,int id)
			
			{
				AdminDto exAdmin = em.find(AdminDto.class, id);
				if(exAdmin!=null)
				{
					admin.setAdminId(id);
					et.begin();
					em.merge(admin);
					et.commit();
					return admin;
				}
				return null;
			}
}
