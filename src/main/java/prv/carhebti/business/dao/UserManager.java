package prv.carhebti.business.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import prv.carhebti.business.entities.User;

public class UserManager extends AbstractManager<User> {

	private static final Logger log = Logger.getLogger(User.class);
	public static final UserManager SINGLETON = new UserManager();
	
	private UserManager() {}
	
	@Override
	public User create(User item) {
		log.error("creation not available");
		return null;
	}

	@Override
	public List<User> retrieve() {
		log.error("user listing not available");
		return null;
	}

	@Override
	public List<User> retrieve(User owner) {
		log.warn("This method has no meaning at all <UserManager.retrieve(User)>");
		return Arrays.asList(owner);
	}

	@Override
	public User retrieve(Integer id) {
		log.info("Retrieve User item whose id is "+id);
		EntityManager em = getEntityManager();

		if (em == null) {
			log.error("No EntityManager");
			return null;
		}

		return em.find(User.class, id);
	}
	
	public User retrieve(String username) {
		log.info("Retrieve User item whose username is "+username);
		EntityManager em = getEntityManager();

		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		List<User> result = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username).getResultList();
		
		if (result != null && result.size() == 1)
			return result.get(0);
		else return null;
	}
	

	@Override
	public User update(User item) {
		log.error("update not available");
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		log.error("delete not available");
		return false;
	}

	@Override
	public boolean delete(Integer[] ids) {
		log.error("delete not available");
		return false;
	}

}
