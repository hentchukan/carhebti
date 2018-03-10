package prv.carhebti.business.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import prv.carhebti.business.dto.BudgetByTypeItem;
import prv.carhebti.business.entities.Service;
import prv.carhebti.business.entities.User;

public class ServiceManager extends AbstractManager<Service> {
	
	private static final Logger log = Logger.getLogger(Service.class);
	public static final ServiceManager SINGLETON = new ServiceManager();
	
	private ServiceManager() {
	}
	
	@Override
	public Service create(Service service) {
		
		log.info("Insert new Operation record");
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		
		try {
			em.getTransaction().begin();
			em.persist(service);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
		log.info("Insert Operation id="+service.getId()+" succeded");
		return service;
	}
	
	public List<Service> retrieve() {
		log.info("Retrieve Operation items list");
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		
		return em.createNamedQuery("Service.findAll", Service.class).getResultList();
	}
	
	@Override
	public List<Service> retrieve(User owner) {
		log.info("Retrieve "+owner.getUsername()+"'s Operation items list");
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		
		return em.createNamedQuery("Service.findByUser", Service.class).setParameter("owner", owner).getResultList();
	}
	
	
	@Override
	public Service retrieve(Integer id) {
		log.info("Retrieve Operation item whose id is "+id);
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		
		return em.find(Service.class, id);
	}
	
	public Service update(Service service) {
		
		log.info("Update an Operation record");
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		
		try {
			em.getTransaction().begin();
			em.merge(service);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
		log.info("Update Operation id="+service.getId()+" succeded");
		return service;
	}
	
	public boolean delete(Integer[] ids) {
		log.info("Delete Operation items list");
		
		boolean response = true;
		for (Integer id : ids) {
			response = response | delete(id);
		}
		
		return response;
	}

	public boolean delete(Integer id) {
		
		log.info("Delete Operation "+id);
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return false;
		}

		try {
			Service service = em.find(Service.class, id);
			em.getTransaction().begin();
			em.remove(service);
			em.getTransaction().commit();
			
			log.info("Delete Entity " + service.getId() + " success");
		} finally {
			em.close();
		}
		
		return true;
	}

	public List<BudgetByTypeItem> findTotalCostByType(Date startDate, Date endDate) {
		log.info("Fetch cost by operation type between "+startDate+" and "+endDate);
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		
		List<BudgetByTypeItem> result = null;
		try {
			TypedQuery<BudgetByTypeItem> query = em.createQuery("SELECT new prv.carhebti.business.dto.BudgetByTypeItem(s.type.name, SUM(s.cost)) FROM Service s WHERE s.dateService BETWEEN :startDate AND :endDate GROUP BY s.type", BudgetByTypeItem.class)
					.setParameter("startDate", startDate)
					.setParameter("endDate", endDate);
			
			result = query.getResultList();
			
			log.info("Fetch cost by operation type between "+startDate+" and "+endDate);
		} catch (Exception e) {
			log.error("Error : ", e);
		} finally {
			em.close();
		}
		
		return result;
	}
	
}
