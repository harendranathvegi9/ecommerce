package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.WishitemEntity;
import com.aripd.ecommerce.entity.ProductEntity;
import com.aripd.ecommerce.entity.UserEntity;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

@Local
public interface WishitemService extends CrudService<WishitemEntity, Long> {

    public List<WishitemEntity> findAllByUser(UserEntity user);

    public WishitemEntity findOneByUserAndProduct(UserEntity user, ProductEntity product);

    public List<WishitemEntity> getResultList(UserEntity user, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);

    public List<WishitemEntity> getResultList(UserEntity user, int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters);

    public int count(UserEntity user, Map<String, Object> filters);

}
