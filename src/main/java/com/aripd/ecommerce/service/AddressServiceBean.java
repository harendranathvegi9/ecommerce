package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.AddressEntity;
import com.aripd.ecommerce.entity.AddressEntity_;
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
public class AddressServiceBean extends CrudServiceBean<AddressEntity, Long> implements AddressService {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AddressServiceBean() {
        super(AddressEntity.class);
    }

    @Override
    public AddressEntity findOneByUserAndId(UserEntity user, Long id) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<AddressEntity> cq = cb.createQuery(AddressEntity.class);
            Root<AddressEntity> root = cq.from(AddressEntity.class);

            Predicate predicate1 = cb.equal(root.get(AddressEntity_.createdBy), user);
            Predicate predicate2 = cb.equal(root.get(AddressEntity_.id), id);
            cq.where(cb.and(predicate1, predicate2));

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<AddressEntity> findByUser(UserEntity user) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AddressEntity> cq = cb.createQuery(AddressEntity.class);
        Root<AddressEntity> root = cq.from(AddressEntity.class);

        Predicate predicate = cb.equal(root.get(AddressEntity_.createdBy), user);
        cq.where(predicate);

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<AddressEntity> getResultList(UserEntity user, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<AddressEntity> root = cq.from(AddressEntity.class);

        Predicate predicate1 = cb.equal(root.get(AddressEntity_.createdBy), user);
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
    public List<AddressEntity> getResultList(UserEntity user, int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<AddressEntity> root = cq.from(AddressEntity.class);

        Predicate predicate1 = cb.equal(root.get(AddressEntity_.createdBy), user);
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
            Root<AddressEntity> root = cq.from(AddressEntity.class);

            Predicate predicate1 = cb.equal(root.get(AddressEntity_.createdBy), user);
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
