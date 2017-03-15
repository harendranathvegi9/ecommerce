package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.BannerEntity;
import com.aripd.ecommerce.entity.BannerEntity_;
import com.aripd.ecommerce.entity.BannerType;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class BannerServiceBean extends CrudServiceBean<BannerEntity, Long> implements BannerService {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public BannerServiceBean() {
        super(BannerEntity.class);
    }
    
    @Override
    public List<BannerEntity> findAllByStatusTrueOrderBySortOrder() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BannerEntity> cq = cb.createQuery(BannerEntity.class);
        Root<BannerEntity> root = cq.from(BannerEntity.class);
        
        Predicate predicate = cb.equal(root.get(BannerEntity_.status), true);
        cq.where(predicate);
        
        cq.orderBy(cb.asc(root.get(BannerEntity_.sortOrder)));
        
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    @Override
    public List<BannerEntity> findAllByStatusTrueOrderBySortOrder(BannerType bannerType) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<BannerEntity> cq = cb.createQuery(BannerEntity.class);
        Root<BannerEntity> root = cq.from(BannerEntity.class);
        
        Predicate predicate1 = cb.equal(root.get(BannerEntity_.status), true);
        Predicate predicate2 = cb.equal(root.get(BannerEntity_.bannerType), bannerType);
        cq.where(cb.and(predicate1, predicate2));
        
        cq.orderBy(cb.asc(root.get(BannerEntity_.sortOrder)));
        
        return getEntityManager().createQuery(cq).getResultList();
    }
    
}
