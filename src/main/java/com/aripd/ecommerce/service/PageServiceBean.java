package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.PageEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PageServiceBean extends CrudServiceBean<PageEntity, Long> implements PageService {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PageServiceBean() {
        super(PageEntity.class);
    }
}
