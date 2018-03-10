package prv.carhebti.business.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import prv.carhebti.business.entities.Car;
import prv.carhebti.business.entities.Settings;
import prv.carhebti.business.entities.User;

public class SettingsManager extends AbstractManager<Settings> {

	private static final Logger log = Logger.getLogger(Car.class);
	public static final SettingsManager SINGLETON = new SettingsManager();
	
	private SettingsManager() {
		log.info("Singleton Manager created");
	}

	@Override
	public List<Settings> retrieve() {
		log.info("Retrieve Settings items list");
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		
		return em.createNamedQuery("Settings.findAll", Settings.class).getResultList();
	}

	@Override
	public List<Settings> retrieve(User owner) {
		log.info("Retrieve "+owner.getUsername()+"'s Settings items ");
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		
		return em.createNamedQuery("Settings.findByUser", Settings.class).setParameter("user", owner).getResultList();
	}

	public Settings retrieveSingle(User owner) {
		log.info("Retrieve "+owner.getUsername()+"'s Settings items ");
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		
		List<Settings> settings = em.createNamedQuery("Settings.findByUser", Settings.class).setParameter("user", owner).getResultList();
		if (settings != null && !settings.isEmpty())
			return settings.get(0);
		else return null;
	}
	
	@Override
	public Settings retrieve(Integer id) {
		log.info("Retrieve Settings item whose id is "+id);
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		
		return em.find(Settings.class, id);
	}

	@Override
	public Settings update(Settings settings) {
		log.info("Update an Settings record");
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		
		try {
			em.getTransaction().begin();
			em.merge(settings);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
		log.info("Update Car id="+settings.getId()+" succeded");
		return settings;
	}

	@Override
	public boolean delete(Integer id) {
		log.info("Delete Settings "+id);
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return false;
		}

		try {
			Settings settings = em.find(Settings.class, id);
			em.getTransaction().begin();
			em.remove(settings);
			em.getTransaction().commit();
			
			log.info("Delete Entity " + settings.getId() + " success");
		} finally {
			em.close();
		}
		
		return true;
	}

	@Override
	public boolean delete(Integer[] ids) {
		log.info("Delete Settings items list");
		
		boolean response = true;
		for (Integer id : ids) {
			response = response | delete(id);
		}
		
		return response;
	}
}
