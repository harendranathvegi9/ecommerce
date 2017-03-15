package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.ImageEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
