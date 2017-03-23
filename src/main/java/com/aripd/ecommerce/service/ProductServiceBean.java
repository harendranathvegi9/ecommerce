package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.ProductEntity;
import com.aripd.ecommerce.entity.ProductEntity_;
import com.aripd.ecommerce.entity.CategoryEntity;
import com.aripd.ecommerce.entity.ImageEntity;
import com.aripd.ecommerce.entity.ImageEntity_;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortOrder;

@Stateless
public class ProductServiceBean extends CrudServiceBean<ProductEntity, Long> implements ProductService {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductServiceBean() {
        super(ProductEntity.class);
    }

    @Override
    public boolean isExistByCode(String code) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ProductEntity> cq = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> root = cq.from(ProductEntity.class);

        Predicate predicate = cb.equal(root.get(ProductEntity_.code), code);

        cq.where(predicate);

        TypedQuery<ProductEntity> typedQuery = getEntityManager().createQuery(cq);
        List<ProductEntity> resultList = typedQuery.getResultList();
        return !resultList.isEmpty();
    }

    @Override
    public boolean isExistByCodeExceptItself(String newCode, String oldCode) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ProductEntity> cq = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> root = cq.from(ProductEntity.class);

        Predicate predicate1 = cb.equal(root.get(ProductEntity_.code), newCode);
        Predicate predicate2 = cb.notEqual(root.get(ProductEntity_.code), oldCode);
        Predicate predicate = cb.and(predicate1, predicate2);

        cq.where(predicate);

        TypedQuery<ProductEntity> typedQuery = getEntityManager().createQuery(cq);
        List<ProductEntity> resultList = typedQuery.getResultList();
        return !resultList.isEmpty();
    }

    @Override
    public ProductEntity findOneByCode(String code) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<ProductEntity> cq = cb.createQuery(ProductEntity.class);
            Root<ProductEntity> root = cq.from(ProductEntity.class);

            Predicate predicate = cb.equal(root.get(ProductEntity_.code), code);
            cq.where(predicate);

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<ProductEntity> findByBannerStatusTrue() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ProductEntity> cq = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> root = cq.from(ProductEntity.class);
        Join<ProductEntity, ImageEntity> images = root.join(ProductEntity_.images);

        Predicate predicate = cb.equal(images.get(ImageEntity_.banner), true);
        cq.where(predicate);

        cq.orderBy(cb.desc(root.get(ProductEntity_.id)));

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<ProductEntity> findByCategory(CategoryEntity category) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ProductEntity> cq = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> root = cq.from(ProductEntity.class);

        Predicate predicate = cb.equal(root.get(ProductEntity_.category), category);
        cq.where(predicate);

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<ProductEntity> getResultList(boolean status, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<ProductEntity> root = cq.from(ProductEntity.class);

        Predicate predicate1 = cb.equal(root.get(ProductEntity_.status), status);
        Predicate predicate2 = this.getFilterCondition(cb, root, filters);
        Predicate predicate4 = cb.isNotEmpty(root.get(ProductEntity_.prices));
        Predicate predicate5 = cb.isNotEmpty(root.get(ProductEntity_.images));

        Predicate predicate = cb.and(predicate1, predicate2, predicate4, predicate5);
        cq.where(predicate);

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                cq.orderBy(cb.asc(root.get(sortField)));
            } else if (sortOrder == SortOrder.DESCENDING) {
                cq.orderBy(cb.desc(root.get(sortField)));
            }
        }

        cq.orderBy(cb.desc(root.get(ProductEntity_.id)));

        Query q = getEntityManager().createQuery(cq);
        return q.setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    @Override
    public int count(boolean status, Map<String, Object> filters) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<ProductEntity> root = cq.from(ProductEntity.class);

            Predicate predicate1 = cb.equal(root.get(ProductEntity_.status), status);
            Predicate predicate2 = this.getFilterCondition(cb, root, filters);
            Predicate predicate4 = cb.isNotEmpty(root.get(ProductEntity_.prices));
            Predicate predicate5 = cb.isNotEmpty(root.get(ProductEntity_.images));

            Predicate predicate = cb.and(predicate1, predicate2, predicate4, predicate5);
            cq.where(predicate);

            cq.select(cb.count(root));

            return getEntityManager().createQuery(cq).getSingleResult().intValue();
        } catch (NoResultException ex) {
            return 0;
        }
    }

    @Override
    public List<ProductEntity> getResultList(CategoryEntity category, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<ProductEntity> root = cq.from(ProductEntity.class);

        Predicate predicate1 = cb.equal(root.get(ProductEntity_.category), category);
        Predicate predicate2 = this.getFilterCondition(cb, root, filters);
        Predicate predicate4 = cb.isNotEmpty(root.get(ProductEntity_.prices));

        Predicate predicate = cb.and(predicate1, predicate2, predicate4);
        cq.where(predicate);

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                cq.orderBy(cb.asc(root.get(sortField)));
            } else if (sortOrder == SortOrder.DESCENDING) {
                cq.orderBy(cb.desc(root.get(sortField)));
            }
        }

        cq.orderBy(cb.desc(root.get(ProductEntity_.id)));

        Query q = getEntityManager().createQuery(cq);
        return q.setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    @Override
    public int count(CategoryEntity category, Map<String, Object> filters) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<ProductEntity> root = cq.from(ProductEntity.class);

            Predicate predicate1 = cb.equal(root.get(ProductEntity_.category), category);
            Predicate predicate2 = this.getFilterCondition(cb, root, filters);

            Predicate predicate = cb.and(predicate1, predicate2);
            cq.where(predicate);

            cq.select(cb.count(root));

            return getEntityManager().createQuery(cq).getSingleResult().intValue();
        } catch (NoResultException ex) {
            return 0;
        }
    }

    @Override
    public List<ProductEntity> getResultList(String searchTerms, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<ProductEntity> root = cq.from(ProductEntity.class);

        List<Predicate> andPredicates2 = new ArrayList<>();
        List<Predicate> andPredicates3 = new ArrayList<>();

        if (searchTerms != null) {
            String[] searchTerm = searchTerms.split("\\s+");
            for (int i = 0; i < searchTerm.length; i++) {
                andPredicates2.add(cb.like(root.get(ProductEntity_.code), "%" + searchTerm[i] + "%"));
                andPredicates3.add(cb.like(root.get(ProductEntity_.name), "%" + searchTerm[i] + "%"));
            }
        }

        Predicate predicates2 = cb.and(andPredicates2.toArray(new Predicate[andPredicates2.size()]));
        Predicate predicates3 = cb.and(andPredicates3.toArray(new Predicate[andPredicates3.size()]));

        Predicate predicate1 = this.getFilterCondition(cb, root, filters);
        Predicate predicate2 = cb.equal(root.get(ProductEntity_.status), true);
        Predicate predicate4 = cb.isNotEmpty(root.get(ProductEntity_.prices));
        Predicate predicate5 = cb.or(predicates2, predicates3);

        Predicate predicate = cb.and(predicate1, predicate2, predicate4, predicate5);
        cq.where(predicate);

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                cq.orderBy(cb.asc(root.get(sortField)));
            } else if (sortOrder == SortOrder.DESCENDING) {
                cq.orderBy(cb.desc(root.get(sortField)));
            }
        }

        cq.orderBy(cb.desc(root.get(ProductEntity_.id)));

        Query q = getEntityManager().createQuery(cq);
        return q.setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    @Override
    public int count(String searchTerms, Map<String, Object> filters) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<ProductEntity> root = cq.from(ProductEntity.class);

            List<Predicate> andPredicates2 = new ArrayList<>();
            List<Predicate> andPredicates3 = new ArrayList<>();

            if (searchTerms != null) {
                String[] searchTerm = searchTerms.split("\\s+");
                for (int i = 0; i < searchTerm.length; i++) {
                    andPredicates2.add(cb.like(root.get(ProductEntity_.code), "%" + searchTerm[i] + "%"));
                    andPredicates3.add(cb.like(root.get(ProductEntity_.name), "%" + searchTerm[i] + "%"));
                }
            }

            Predicate predicates2 = cb.and(andPredicates2.toArray(new Predicate[andPredicates2.size()]));
            Predicate predicates3 = cb.and(andPredicates3.toArray(new Predicate[andPredicates3.size()]));

            Predicate predicate1 = this.getFilterCondition(cb, root, filters);
            Predicate predicate2 = cb.equal(root.get(ProductEntity_.status), true);
            Predicate predicate4 = cb.isNotEmpty(root.get(ProductEntity_.prices));
            Predicate predicate5 = cb.or(predicates2, predicates3);

            Predicate predicate = cb.and(predicate1, predicate2, predicate4, predicate5);
            cq.where(predicate);

            cq.select(cb.count(root));

            return getEntityManager().createQuery(cq).getSingleResult().intValue();
        } catch (NoResultException ex) {
            return 0;
        }
    }

}
