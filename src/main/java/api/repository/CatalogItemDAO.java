package api.repository;

import api.entity.CatalogItemEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository

public class CatalogItemDAO {

    @PersistenceContext
    private EntityManager entityManager;


    public List<CatalogItemEntity> getAllItems() {
        try {
            TypedQuery<CatalogItemEntity> itemsQuery =
                    entityManager.createNamedQuery("findAllItems", CatalogItemEntity.class);
            return itemsQuery.getResultList();
        } catch (NoResultException nrex) {
            //throw custom api.exception is api.entity null
            return null;
        }
    }


}
