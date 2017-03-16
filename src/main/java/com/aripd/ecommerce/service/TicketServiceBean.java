package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.TicketEntity;
import com.aripd.ecommerce.entity.TicketEntity_;
import com.aripd.ecommerce.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

@Stateless
public class TicketServiceBean extends CrudServiceBean<TicketEntity, Long> implements TicketService {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TicketServiceBean() {
        super(TicketEntity.class);
    }

    @Override
    public TicketEntity findOneByUserAndId(UserEntity user, Long id) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<TicketEntity> cq = cb.createQuery(TicketEntity.class);
            Root<TicketEntity> root = cq.from(TicketEntity.class);

            Predicate predicate1 = cb.equal(root.get(TicketEntity_.createdBy), user);
            Predicate predicate2 = cb.equal(root.get(TicketEntity_.id), id);
            Predicate predicate = cb.and(predicate1, predicate2);
            cq.where(predicate);

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<TicketEntity> getResultList(UserEntity user, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<TicketEntity> root = cq.from(TicketEntity.class);

        Predicate predicate1 = cb.equal(root.get(TicketEntity_.createdBy), user);
        Predicate predicate2 = this.getFilterCondition(cb, root, filters);
        Predicate predicate = cb.and(predicate1, predicate2);
        cq.where(predicate);

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                cq.orderBy(cb.asc(root.get(sortField)));
            } else if (sortOrder == SortOrder.DESCENDING) {
                cq.orderBy(cb.desc(root.get(sortField)));
            }
        }

        Query q = getEntityManager().createQuery(cq);
        return q.setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    @Override
    public List<TicketEntity> getResultList(UserEntity user, int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<TicketEntity> root = cq.from(TicketEntity.class);

        Predicate predicate1 = cb.equal(root.get(TicketEntity_.createdBy), user);
        Predicate predicate2 = this.getFilterCondition(cb, root, filters);
        Predicate predicate = cb.and(predicate1, predicate2);
        cq.where(predicate);

        if (multiSortMeta != null) {
            List<Order> orders = new ArrayList<>();
            for (SortMeta sortMeta : multiSortMeta) {
                String sortField = sortMeta.getSortField();
                SortOrder sortOrder = sortMeta.getSortOrder();
                if (sortField != null) {
                    Order order = null;
                    if (sortOrder == SortOrder.ASCENDING) {
                        order = cb.asc(root.get(sortField));
                    } else if (sortOrder == SortOrder.DESCENDING) {
                        order = cb.desc(root.get(sortField));
                    }
                    orders.add(order);
                }
            }
            cq.orderBy(orders);
        }

        Query q = getEntityManager().createQuery(cq);
        return q.setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    @Override
    public int count(UserEntity user, Map<String, Object> filters) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<TicketEntity> root = cq.from(TicketEntity.class);

            Predicate predicate1 = cb.equal(root.get(TicketEntity_.createdBy), user);
            Predicate predicate2 = this.getFilterCondition(cb, root, filters);
            Predicate predicate = cb.and(predicate1, predicate2);
            cq.where(predicate);

            cq.select(cb.count(root));

            return getEntityManager().createQuery(cq).getSingleResult().intValue();
        } catch (NoResultException ex) {
            return 0;
        }
    }

}
