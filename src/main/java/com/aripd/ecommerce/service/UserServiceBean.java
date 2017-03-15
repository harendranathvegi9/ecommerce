package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.AddressEntity;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.entity.UserEntity_;
import com.aripd.ecommerce.view.SignupModel;
import com.aripd.util.MessageUtil;
import java.security.Principal;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class UserServiceBean extends CrudServiceBean<UserEntity, Long> implements UserService {

    @Inject
    MessageUtil messageUtil;

    @PersistenceContext
    private EntityManager em;

    @Resource
    private SessionContext sessionContext;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserServiceBean() {
        super(UserEntity.class);
    }

    @Override
    public UserEntity signup(SignupModel model) {
        UserEntity u = new UserEntity();
        u.setUserGroup(model.getGroup());
        u.setUserStatus(model.getStatus());
        u.setEmail(model.getEmail());
        u.setPassword(model.getPassword());
        u.setLocale(model.getLocale());//
        u.setFirstname(model.getFirstname());
        u.setLastname(model.getLastname());

        if (model.getAddress() != null) {
            AddressEntity a = model.getAddress();
            a.setFirstname(model.getFirstname());
            a.setLastname(model.getLastname());
            a.setCreatedBy(u);
            u.getAddresses().add(a);
        }

        return this.create(u);
    }

    @Override
    public UserEntity getCurrentUser() {

        if (this.sessionContext == null) {
            throw new RuntimeException("initialization error, sessionContext must not be null!");
        }

        Principal callerPrincipal = this.sessionContext.getCallerPrincipal();
        if (callerPrincipal == null) {
            throw new RuntimeException("callerPrincipal must not be null, but it is");
        }

        String name = callerPrincipal.getName();
        if (name == null) {
            throw new RuntimeException("could not determine the current user id, because no prinicial in session context");
        }

        return this.findOneByEmail(name);
    }

    /**
     * Girilen e-posta adresinin kendisi dışındaki kayıtlarda olup olmadığına
     * bakar
     *
     * @param emailNew String
     * @param email String
     * @return boolean
     */
    @Override
    public boolean isExistByEmailExceptEmail(String emailNew, String email) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);

        Predicate predicate1 = cb.notEqual(root.get(UserEntity_.email), email);
        Predicate predicate2 = cb.equal(root.get(UserEntity_.email), emailNew);
        Predicate predicate = cb.and(predicate1, predicate2);

        cq.where(predicate);

        TypedQuery<UserEntity> typedQuery = getEntityManager().createQuery(cq);
        List<UserEntity> resultList = typedQuery.getResultList();
        return !resultList.isEmpty();
    }

    @Override
    public UserEntity findOneByToken(String token) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
            Root<UserEntity> root = cq.from(UserEntity.class);

            Expression<String> eConcat = cb.concat(root.get(UserEntity_.password), root.get(UserEntity_.email));
            Expression<String> eToken = cb.function("SHA2", String.class, eConcat, cb.literal("512"));

            Predicate predicate = cb.equal(eToken, token);
            cq.where(predicate);

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public UserEntity findOneByEmail(String email) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
            Root<UserEntity> root = cq.from(UserEntity.class);

            Predicate predicate = cb.equal(root.get(UserEntity_.email), email);
            cq.where(predicate);

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public UserEntity findOneByUuid(String uuid) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
            Root<UserEntity> root = cq.from(UserEntity.class);

            Predicate predicate = cb.equal(root.get(UserEntity_.uuid), uuid);
            cq.where(predicate);

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
