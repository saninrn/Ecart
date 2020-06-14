package api.repository;

import api.entity.OrderEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository

public class OrderStatusDao {
    @PersistenceContext
    private EntityManager entityManager;

    public OrderEntity getOrder (int orderId) {
        try {
            TypedQuery<OrderEntity> orderQuery = entityManager.createNamedQuery("findOrder", OrderEntity.class);
            orderQuery.setParameter("orderId",orderId);
            return orderQuery.getSingleResult();
        } catch (NoResultException e) {
            //throw custom api.exception is api.entity null
            return null;
        }
    }
}
