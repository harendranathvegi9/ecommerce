package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.ImageEntity;
import com.aripd.ecommerce.entity.ProductEntity;
import javax.ejb.Local;

@Local
public interface ImageService extends CrudService<ImageEntity, Long> {

    public void updateAllImagesAsBannerFalseByProduct(ProductEntity product);

}
