package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.PostEntity;
import com.aripd.ecommerce.entity.PostEntity_;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

@Stateless
public class PostServiceBean extends CrudServiceBean<PostEntity, Long> implements PostService {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostServiceBean() {
        super(PostEntity.class);
    }

    @Override
    public PostEntity findOneByLatest() {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<PostEntity> cq = cb.createQuery(PostEntity.class);
            Root<PostEntity> root = cq.from(PostEntity.class);

            Subquery<Date> maxSubQuery = cq.subquery(Date.class);
            Root<PostEntity> fromEntityX = maxSubQuery.from(PostEntity.class);
            maxSubQuery.select(cb.greatest(fromEntityX.get(PostEntity_.createdAt)));
            cq.where(cb.equal(root.get(PostEntity_.createdAt), maxSubQuery));

            return getEntityManager().createQuery(cq).setMaxResults(1).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
