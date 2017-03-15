package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.BasketitemEntity;
import com.aripd.ecommerce.entity.ProductEntity;
import com.aripd.ecommerce.entity.UserEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface BasketitemService extends CrudService<BasketitemEntity, Long> {

    public List<BasketitemEntity> findAllByUser(UserEntity user);

    public BasketitemEntity findOneByUserAndProduct(UserEntity user, ProductEntity product);

    public void deleteByUser(UserEntity user);

    public void deleteByUserAndProduct(UserEntity user, ProductEntity selectedProduct);

}
