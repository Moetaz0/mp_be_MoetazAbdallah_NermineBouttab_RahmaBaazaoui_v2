package soa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import soa.entities.Clients;


import java.util.List;

@Repository
@Transactional

public class ClientDao implements IClientDao{
    @PersistenceContext
    private EntityManager em;
    public ClientDao(EntityManager entityManager) {
        this.em = entityManager;
    }
    public List<Clients> findAll() {
        Query query=
                em.createQuery("select c from Clients c order by c.ClientId");
        return query.getResultList();
    }


}
