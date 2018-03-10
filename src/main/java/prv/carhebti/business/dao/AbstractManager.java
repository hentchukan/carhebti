package prv.carhebti.business.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

import prv.carhebti.business.entities.ICarhebtiEntity;
import prv.carhebti.business.entities.User;

public abstract class AbstractManager<E extends ICarhebtiEntity> {
	
	protected static Logger log = Logger.getLogger(AbstractManager.class);
	private EntityManagerFactory emf;

	protected EntityManager getEntityManager() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("carhebti");
		}
		return emf.createEntityManager();
	}
	
	public E create(E item) {
		log.info("Insert new "+item.getClass().getName()+" record");
		EntityManager em = getEntityManager();
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		
		try {
			em.getTransaction().begin();
			em.persist(item);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
		
		log.info("Insert "+item.getClass().getName()+" id="+item.getId()+" succeded");
		return item;
	}
	
	public abstract List<E> retrieve();
	public abstract List<E> retrieve(User owner);
	public abstract E retrieve(Integer id);
	public abstract E update(E item);
	public abstract boolean delete(Integer id);
	public abstract boolean delete(Integer[] ids);
}
