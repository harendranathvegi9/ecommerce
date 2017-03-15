package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.CategoryEntity;
import com.aripd.ecommerce.entity.CategoryEntity_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class CategoryServiceBean extends CrudServiceBean<CategoryEntity, Long> implements CategoryService {

    @PersistenceContext
    private EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryServiceBean() {
        super(CategoryEntity.class);
    }

    @Override
    public List<CategoryEntity> findByParent(CategoryEntity category) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> cq = cb.createQuery(CategoryEntity.class);
        Root<CategoryEntity> root = cq.from(CategoryEntity.class);

        Predicate predicate;
        if (category == null) {
            predicate = cb.isNull(root.get(CategoryEntity_.parent));
        } else {
            predicate = cb.equal(root.get(CategoryEntity_.parent), category);
        }

        cq.where(predicate);

        List<Order> orderList = new ArrayList();
        orderList.add(cb.asc(root.get(CategoryEntity_.sortOrder)));
        orderList.add(cb.asc(root.get(CategoryEntity_.code)));
        cq.orderBy(orderList);

        return getEntityManager().createQuery(cq).getResultList();
    }

}
