package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.PriceEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PriceServiceBean extends CrudServiceBean<PriceEntity, Long> implements PriceService {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PriceServiceBean() {
        super(PriceEntity.class);
    }

}
