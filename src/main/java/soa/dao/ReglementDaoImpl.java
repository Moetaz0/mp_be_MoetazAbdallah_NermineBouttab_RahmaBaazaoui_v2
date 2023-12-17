package soa.dao;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import soa.entities.Reglement;

@Repository
@Transactional
public class ReglementDaoImpl implements IReglementDao{

	@PersistenceContext
	private EntityManager em;
	public ReglementDaoImpl(EntityManager entityManager) {
		this.em = entityManager;
	}
	public Reglement save(Reglement r) {
		em.persist(r);
		return r;
	}
	public List<Reglement> findAll() {
		Query query=  
      em.createQuery("select r from Reglement r order by r.id");
		return query.getResultList();
	}



	public void delete(Long id) {
		Reglement r = em.find(Reglement.class, id);
		em.remove(r);
		
	}




}
