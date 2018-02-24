package prv.carhebti.business.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractManager<E> {

	private EntityManagerFactory emf;

	protected EntityManager getEntityManager() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("carhebti");
		}
		return emf.createEntityManager();
	}
	
	public abstract E create(E item);
	public abstract List<E> retrieve();
	public abstract E retrieve(Integer id);
	public abstract E update(E item);
	public abstract boolean delete(Integer id);
	public abstract boolean delete(Integer[] ids);
}
