package prv.carhebti.business.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import prv.carhebti.business.entities.Type;

public class TypeManager {

	private static final Logger log = Logger.getLogger(Type.class);
	private static EntityManagerFactory emf = Persistence .createEntityManagerFactory("carhebti");
	private static EntityManager em = emf.createEntityManager();
	
	public Type addType(Type type) {
		
		log.info("Insert new Type record");
		
		if (em == null) {
			log.error("No EntityManager");
			return null;
		}
		em.getTransaction().begin();
		em.persist(type);
		em.getTransaction().commit();
		
		log.info("Insert Type id="+type.getId()+" succeded");
		return type;
	}
}
