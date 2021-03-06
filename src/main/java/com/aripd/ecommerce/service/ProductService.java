package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.ProductEntity;
import com.aripd.ecommerce.entity.CategoryEntity;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.primefaces.model.SortOrder;

@Local
public interface ProductService extends CrudService<ProductEntity, Long> {

    public boolean isExistByCode(String code);

    public boolean isExistByCodeExceptItself(String newCode, String oldCode);

    public ProductEntity findOneByCode(String code);

    public List<ProductEntity> findByBannerStatusTrue();

    public List<ProductEntity> findByCategory(CategoryEntity category);

    public List<ProductEntity> getResultList(boolean status, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);

    public int count(boolean status, Map<String, Object> filters);

    public List<ProductEntity> getResultList(CategoryEntity category, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);

    public int count(CategoryEntity category, Map<String, Object> filters);

    public List<ProductEntity> getResultList(String searchTerms, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);

    public int count(String searchTerms, Map<String, Object> filters);

}
