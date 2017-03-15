package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.AddressEntity;
import com.aripd.ecommerce.entity.UserEntity;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

@Local
public interface AddressService extends CrudService<AddressEntity, Long> {

    public AddressEntity findOneByUserAndId(UserEntity user, Long id);

    public List<AddressEntity> findByUser(UserEntity user);

    public List<AddressEntity> getResultList(UserEntity user, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);

    public List<AddressEntity> getResultList(UserEntity user, int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters);

    public int count(UserEntity user, Map<String, Object> filters);

}
