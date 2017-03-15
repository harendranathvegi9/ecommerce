package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.ImageEntity;
import javax.ejb.Local;

@Local
public interface ImageService extends CrudService<ImageEntity, Long> {

}
