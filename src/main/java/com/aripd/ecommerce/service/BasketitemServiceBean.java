package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.BasketitemEntity;
import com.aripd.ecommerce.entity.BasketitemEntity_;
import com.aripd.ecommerce.entity.ProductEntity;
import com.aripd.ecommerce.entity.UserEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class BasketitemServiceBean extends CrudServiceBean<BasketitemEntity, Long> implements BasketitemService {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BasketitemServiceBean() {
        super(BasketitemEntity.class);
    }

    @Override
    public List<BasketitemEntity> findAllByUser(UserEntity user) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BasketitemEntity> cq = cb.createQuery(BasketitemEntity.class);
        Root<BasketitemEntity> root = cq.from(BasketitemEntity.class);

        Predicate predicate = cb.equal(root.get(BasketitemEntity_.createdBy), user);
        cq.where(predicate);

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public BasketitemEntity findOneByUserAndProduct(UserEntity user, ProductEntity product) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<BasketitemEntity> cq = cb.createQuery(BasketitemEntity.class);
            Root<BasketitemEntity> root = cq.from(BasketitemEntity.class);

            Predicate predicate1 = cb.equal(root.get(BasketitemEntity_.createdBy), user);
            Predicate predicate2 = cb.equal(root.get(BasketitemEntity_.product), product);
            cq.where(cb.and(predicate1, predicate2));

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void deleteByUser(UserEntity user) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaDelete<BasketitemEntity> cd = cb.createCriteriaDelete(BasketitemEntity.class);
        Root<BasketitemEntity> root = cd.from(BasketitemEntity.class);

        Predicate predicate = cb.equal(root.get(BasketitemEntity_.createdBy), user);
        cd.where(predicate);

        getEntityManager().createQuery(cd).executeUpdate();
    }

    @Override
    public void deleteByUserAndProduct(UserEntity user, ProductEntity product) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaDelete<BasketitemEntity> cd = cb.createCriteriaDelete(BasketitemEntity.class);
        Root<BasketitemEntity> root = cd.from(BasketitemEntity.class);

        Predicate predicate1 = cb.equal(root.get(BasketitemEntity_.createdBy), user);
        Predicate predicate2 = cb.equal(root.get(BasketitemEntity_.product), product);
        cd.where(cb.and(predicate1, predicate2));

        getEntityManager().createQuery(cd).executeUpdate();
    }

}
