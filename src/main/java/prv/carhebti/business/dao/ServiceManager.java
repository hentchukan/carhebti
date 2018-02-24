package prv.carhebti.business.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import prv.carhebti.business.entities.Service;
import prv.carhebti.business.entities.Type;

public class ServiceManager extends AbstractManager<Service> {
	
	private static final Logger log = Logger.getLogger(Type.class);
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
		
		log.info("Insert Operation id="+service.getIdService()+" succeded");
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
		
		log.info("Update Operation id="+service.getIdService()+" succeded");
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
			
			log.info("Delete Entity " + service.getIdService() + " success");
		} finally {
			em.close();
		}
		
		return true;
	}
	
}
