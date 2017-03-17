package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.ProductEntity;
import com.aripd.ecommerce.entity.ProductEntity_;
import com.aripd.ecommerce.entity.SaleEntity;
import com.aripd.ecommerce.entity.SaleEntity_;
import com.aripd.ecommerce.entity.SalelineEntity;
import com.aripd.ecommerce.entity.SalelineEntity_;
import com.aripd.ecommerce.model.ReportFormModel;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class SalelineServiceBean extends CrudServiceBean<SalelineEntity, Long> implements SalelineService {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SalelineServiceBean() {
        super(SalelineEntity.class);
    }

    @Override
    public Map<String, Number> getSalesRevenueData(ReportFormModel reportFormModel) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<SalelineEntity> root = cq.from(SalelineEntity.class);
        Join<SalelineEntity, ProductEntity> product = root.join(SalelineEntity_.product);
        Join<SalelineEntity, SaleEntity> sale = root.join(SalelineEntity_.sale);

        Predicate predicate = cb.between(sale.get(SaleEntity_.createdAt), reportFormModel.getStart(), reportFormModel.getEnd());
        cq.where(predicate);

        Expression<String> eLabel = product.get(ProductEntity_.name);
        Expression<BigDecimal> eTotal = root.get(SalelineEntity_.IPN_TOTAL);
        Expression<BigDecimal> eQuantity = root.get(SalelineEntity_.IPN_QTY).as(BigDecimal.class);
        Expression<BigDecimal> eRevenue = cb.prod(eTotal, eQuantity);
        Expression<BigDecimal> eSum = cb.sum(eRevenue);

        cq.multiselect(eLabel.alias("LABEL"), eSum.alias("SUM"));
        cq.groupBy(eLabel);

        TypedQuery<Tuple> typedQuery = getEntityManager().createQuery(cq);
        List<Tuple> list = typedQuery.getResultList();
        Map<String, Number> map = new HashMap<>();
        for (Tuple tuple : list) {
            String tLabel = tuple.get("LABEL").toString();
            BigDecimal tSum = new BigDecimal(tuple.get("SUM").toString());
            map.put(tLabel, tSum);
        }
        return map;
    }

    @Override
    public Map<String, Number> getSalesVolumeData(ReportFormModel reportFormModel) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<SalelineEntity> root = cq.from(SalelineEntity.class);
        Join<SalelineEntity, ProductEntity> product = root.join(SalelineEntity_.product);
        Join<SalelineEntity, SaleEntity> sale = root.join(SalelineEntity_.sale);

        Predicate predicate = cb.between(sale.get(SaleEntity_.createdAt), reportFormModel.getStart(), reportFormModel.getEnd());
        cq.where(predicate);

        Expression<String> eLabel = product.get(ProductEntity_.name);
        Expression<Integer> eQuantity = root.get(SalelineEntity_.IPN_QTY);
        Expression<Integer> eSum = cb.sum(eQuantity);

        cq.multiselect(eLabel.alias("LABEL"), eSum.alias("SUM"));
        cq.groupBy(eLabel);

        TypedQuery<Tuple> typedQuery = getEntityManager().createQuery(cq);
        List<Tuple> list = typedQuery.getResultList();
        Map<String, Number> map = new HashMap<>();
        for (Tuple tuple : list) {
            String tLabel = tuple.get("LABEL").toString();
            Integer tSum = new Integer(tuple.get("SUM").toString());
            map.put(tLabel, tSum);
        }
        return map;
    }

}
