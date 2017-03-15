package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.PriceEntity;
import javax.ejb.Local;

@Local
public interface PriceService extends CrudService<PriceEntity, Long> {

}
