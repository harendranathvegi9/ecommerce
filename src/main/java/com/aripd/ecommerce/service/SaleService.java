package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.SaleEntity;
import com.aripd.ecommerce.entity.SaleStatus;
import com.aripd.ecommerce.entity.UserEntity;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

@Local
public interface SaleService extends CrudService<SaleEntity, Long> {

    public SaleEntity findOneByOrderRef(String orderRef);

    public SaleEntity findOneByUserEmailAndOrderRef(String email, String orderRef);

    public SaleEntity findOneByUserEmailAndId(String email, Long id);

    public SaleEntity findOneByUserAndOrderRef(UserEntity user, String orderRef);

    public SaleEntity findOneByUserAndId(UserEntity user, Long id);

    public List<SaleEntity> findByUser(UserEntity user);

    public boolean sendToMember(SaleEntity sale);

    public boolean sendToAdministrator(SaleEntity sale);

    public List<SaleEntity> getResultList(List<SaleStatus> list, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);

    public int count(List<SaleStatus> list, Map<String, Object> filters);

    public List<SaleEntity> getResultList(UserEntity user, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);

    public List<SaleEntity> getResultList(UserEntity user, int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters);

    public int count(UserEntity user, Map<String, Object> filters);

}
