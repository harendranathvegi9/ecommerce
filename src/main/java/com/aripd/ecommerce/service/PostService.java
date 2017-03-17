package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.PostEntity;
import javax.ejb.Local;

@Local
public interface PostService extends CrudService<PostEntity, Long> {

    public PostEntity findOneByLatest();

}
