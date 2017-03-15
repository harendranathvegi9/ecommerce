package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.CategoryEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CategoryService extends CrudService<CategoryEntity, Long> {

    public List<CategoryEntity> findByParent(CategoryEntity category);

}
