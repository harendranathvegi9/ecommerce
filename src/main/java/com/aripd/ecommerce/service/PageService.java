package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.PageEntity;
import javax.ejb.Local;

@Local
public interface PageService extends CrudService<PageEntity, Long> {

}
