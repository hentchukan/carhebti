package prv.carhebti.business.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import prv.carhebti.business.entities.Type;

public class TypeManager extends AbstractManager<Type> {

	private static final Logger log = Logger.getLogger(Type.class);
	public static final TypeManager SINGLETON = new TypeManager();
	
	private TypeManager() {
	}
	
	@Override
	public Type create(Type type) {

		log.info("Insert new Type record");
		EntityManager em = getEntityManager();

		if (em == null) {
			log.error("No EntityManager");
			return null;
		}

		try {
			em.getTransaction().begin();
			em.persist(type);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

		log.info("Insert Type id=" + type.getId() + " succeded");
		return type;
	}

	@Override
	public Type update(Type type) {

		log.info("Update a Type record");
		EntityManager em = getEntityManager();

		if (em == null) {
			log.error("No EntityManager");
			return null;
		}

		try {
			em.getTransaction().begin();
			em.merge(type);
			em.getTransaction().commit();
		} finally {
			em.close();
		}

		log.info("Update Type id=" + type.getId() + " succeded");
		return type;
	}

	@Override
	public List<Type> retrieve() {
		log.info("Retrieve Type items list");
		EntityManager em = getEntityManager();

		if (em == null) {
			log.error("No EntityManager");
			return null;
		}

		return em.createNamedQuery("Type.findAll", Type.class).getResultList();
	}

	@Override
	public Type retrieve(Integer id) {
		log.info("Retrieve Type item whose id is "+id);
		EntityManager em = getEntityManager();

		if (em == null) {
			log.error("No EntityManager");
			return null;
		}

		return em.find(Type.class, id);
	}
	
	@Override
	public boolean delete(Integer[] ids) {
		log.info("Delete Type items list");

		boolean response = true;
		for (Integer id : ids) {
			response = response | delete(id);
		}

		return response;
	}

	@Override
	public boolean delete(Integer id) {
		log.info("Delete Type " + id);
		EntityManager em = getEntityManager();

		if (em == null) {
			log.error("No EntityManager");
			return false;
		}

		try {
			Type type = em.find(Type.class, id);

			em.getTransaction().begin();
			em.remove(type);
			em.getTransaction().commit();

			log.info("Delete Entity " + type.getId() + " success");
		} finally {
			em.close();
		}

		return true;
	}

}
