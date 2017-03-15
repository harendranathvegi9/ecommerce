package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.view.SignupModel;
import javax.ejb.Local;

@Local
public interface UserService extends CrudService<UserEntity, Long> {

    public UserEntity signup(SignupModel model);

    public UserEntity getCurrentUser();

    public boolean isExistByEmailExceptEmail(String emailNew, String email);

    public UserEntity findOneByToken(String token);

    public UserEntity findOneByEmail(String email);

    public UserEntity findOneByUuid(String uuid);

}
