package prv.carhebti.business.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import prv.carhebti.business.entities.Car;
import prv.carhebti.business.entities.User;

public class CarManager extends AbstractManager<Car> {

	private static final Logger log = Logger.getLogger(Car.class);
	public static final CarManager SINGLETON = new CarManager();
	
	private CarManager() {
		log.info("Singleton Manager created");
	}
	
	@Override
	public List<Car> retrieve() {
		log.info("Retrieve Car items list");
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		
		return em.createNamedQuery("Car.findAll", Car.class).getResultList();
	}

	@Override
	public List<Car> retrieve(User owner) {
		log.info("Retrieve "+owner.getUsername()+"'s Car items list ");
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		
		return em.createNamedQuery("Car.findByUser", Car.class).setParameter("owner", owner).getResultList();
	}
	
	@Override
	public Car retrieve(Integer id) {
		log.info("Retrieve Car item whose id is "+id);
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		
		return em.find(Car.class, id);
	}

	@Override
	public Car update(Car car) {
		log.info("Update an Car record");
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		
		try {
			em.getTransaction().begin();
			em.merge(car);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
		log.info("Update Car id="+car.getId()+" succeded");
		return car;
	}

	@Override
	public boolean delete(Integer[] ids) {
		log.info("Delete Car items list");
		
		boolean response = true;
		for (Integer id : ids) {
			response = response | delete(id);
		}
		
		return response;
	}

	public boolean delete(Integer id) {
		
		log.info("Delete Car "+id);
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return false;
		}

		try {
			Car car = em.find(Car.class, id);
			em.getTransaction().begin();
			em.remove(car);
			em.getTransaction().commit();
			
			log.info("Delete Entity " + car.getId() + " success");
		} finally {
			em.close();
		}
		
		return true;
	}

}
