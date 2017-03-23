package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.ImageEntity;
import com.aripd.ecommerce.entity.ImageEntity_;
import com.aripd.ecommerce.entity.ProductEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class ImageServiceBean extends CrudServiceBean<ImageEntity, Long> implements ImageService {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImageServiceBean() {
        super(ImageEntity.class);
    }

    @Override
    public void updateAllImagesAsBannerFalseByProduct(ProductEntity product) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<ImageEntity> cu = cb.createCriteriaUpdate(ImageEntity.class);
        Root<ImageEntity> root = cu.from(ImageEntity.class);

        cu.set(root.get(ImageEntity_.banner), false);

        Predicate predicate = cb.equal(root.get(ImageEntity_.product), product);
        cu.where(predicate);

        getEntityManager().createQuery(cu).executeUpdate();
    }

}
