package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.SaleEntity;
import com.aripd.ecommerce.entity.SaleEntity_;
import com.aripd.ecommerce.entity.SaleStatus;
import com.aripd.ecommerce.entity.SalelineEntity;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.entity.UserEntity_;
import com.aripd.util.locale.LocaleProvider;
import com.aripd.util.MessageUtil;
import com.aripd.util.RequestUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.FacesException;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

@Stateless
public class SaleServiceBean extends CrudServiceBean<SaleEntity, Long> implements SaleService {

    @Inject
    MessageUtil messageUtil;

    @PersistenceContext
    private EntityManager em;

    @Resource(name = "mail/ecommerce")
    private Session mailSession;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SaleServiceBean() {
        super(SaleEntity.class);
    }

    @Override
    public SaleEntity findOneByOrderRef(String orderRef) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<SaleEntity> cq = cb.createQuery(SaleEntity.class);
            Root<SaleEntity> root = cq.from(SaleEntity.class);

            Predicate predicate = cb.equal(root.get(SaleEntity_.REFNOEXT), orderRef);
            cq.where(predicate);

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public SaleEntity findOneByUserEmailAndOrderRef(String email, String orderRef) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<SaleEntity> cq = cb.createQuery(SaleEntity.class);
            Root<SaleEntity> root = cq.from(SaleEntity.class);
            Join<SaleEntity, UserEntity> createdBy = root.join(SaleEntity_.createdBy);

            Predicate predicate1 = cb.equal(createdBy.get(UserEntity_.email), email);
            Predicate predicate2 = cb.equal(root.get(SaleEntity_.REFNOEXT), orderRef);
            Predicate predicate = cb.and(predicate1, predicate2);
            cq.where(predicate);

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public SaleEntity findOneByUserEmailAndId(String email, Long id) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<SaleEntity> cq = cb.createQuery(SaleEntity.class);
            Root<SaleEntity> root = cq.from(SaleEntity.class);
            Join<SaleEntity, UserEntity> createdBy = root.join(SaleEntity_.createdBy);

            Predicate predicate1 = cb.equal(createdBy.get(UserEntity_.email), email);
            Predicate predicate2 = cb.equal(root.get(SaleEntity_.id), id);
            Predicate predicate = cb.and(predicate1, predicate2);
            cq.where(predicate);

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public SaleEntity findOneByUserAndOrderRef(UserEntity user, String orderRef) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<SaleEntity> cq = cb.createQuery(SaleEntity.class);
            Root<SaleEntity> root = cq.from(SaleEntity.class);

            Predicate predicate1 = cb.equal(root.get(SaleEntity_.createdBy), user);
            Predicate predicate2 = cb.equal(root.get(SaleEntity_.REFNOEXT), orderRef);
            Predicate predicate = cb.and(predicate1, predicate2);
            cq.where(predicate);

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public SaleEntity findOneByUserAndId(UserEntity user, Long id) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<SaleEntity> cq = cb.createQuery(SaleEntity.class);
            Root<SaleEntity> root = cq.from(SaleEntity.class);

            Predicate predicate1 = cb.equal(root.get(SaleEntity_.createdBy), user);
            Predicate predicate2 = cb.equal(root.get(SaleEntity_.id), id);
            Predicate predicate = cb.and(predicate1, predicate2);
            cq.where(predicate);

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<SaleEntity> findByUser(UserEntity user) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<SaleEntity> cq = cb.createQuery(SaleEntity.class);
        Root<SaleEntity> root = cq.from(SaleEntity.class);

        Predicate predicate = cb.equal(root.get(SaleEntity_.createdBy), user);
        cq.where(predicate);

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public boolean sendToMember(SaleEntity sale) {
        String subject = messageUtil.getMailMessage(LocaleProvider.getLocale(), "sale.subject");
        String msg = messageUtil.getMailMessage(LocaleProvider.getLocale(), "sale.msg",
                new Object[]{
                    sale.getCreatedBy().getFullname(),
                    sale.getREFNOEXT(),
                    messageUtil.getI18nResource(String.valueOf(sale.getSaleStatus())),
                    RequestUtil.getFullAddress("/sale.jsf?orderRef=" + sale.getREFNOEXT())
                }
        );

        try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(mailSession.getProperty("mail.from"), mailSession.getProperty("mail.from"), "UTF-8"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sale.getCreatedBy().getEmail(), false));
            message.setSubject(subject, "UTF-8");
            message.setText(msg, "UTF-8");
            //message.setContent(msg, "text/html; charset=utf-8");
            message.setHeader("X-Mailer", "My Mailer");
            message.setSentDate(new Date());
            Transport.send(message);
            return true;
        } catch (MessagingException | UnsupportedEncodingException ex) {
            throw new FacesException(ex);
        }
    }

    @Override
    public boolean sendToAdministrator(SaleEntity sale) {
        List<SalelineEntity> salelines = sale.getSalelines();
        StringBuilder sb = new StringBuilder();
        for (SalelineEntity saleline : salelines) {
            sb.append(saleline.getIPN_PCODE());
            sb.append("\t");
            sb.append(saleline.getIPN_PNAME());
            sb.append("\t");
            sb.append(saleline.getIPN_QTY());
            sb.append("\n");
        }

        String subject = messageUtil.getMailMessage(LocaleProvider.getLocale(), "order.subject",
                new Object[]{
                    sale.getREFNOEXT()
                }
        );
        String msg = messageUtil.getMailMessage(LocaleProvider.getLocale(), "order.msg",
                new Object[]{
                    RequestUtil.getFullAddress("/administrator/sale/show.jsf?id=" + sale.getId()),
                    sale.getREFNOEXT(),
                    sale.getCreatedBy().getFullname(),
                    sale.getCreatedBy().getEmail(),
                    sale.getCreatedAt(),
                    messageUtil.getI18nResource(String.valueOf(sale.getSaleStatus())),
                    sale.getDeliveryAddress(),
                    sale.getBillAddress(),
                    sb
                }
        );

        try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(mailSession.getProperty("mail.from"), mailSession.getProperty("mail.from"), "UTF-8"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailSession.getProperty("mail.from"), false));
            message.setSubject(subject, "UTF-8");
            message.setText(msg, "UTF-8");
            //message.setContent(msg, "text/html; charset=utf-8");
            message.setHeader("X-Mailer", "My Mailer");
            message.setSentDate(new Date());
            Transport.send(message);
            return true;
        } catch (MessagingException | UnsupportedEncodingException ex) {
            throw new FacesException(ex);
        }
    }

    @Override
    public List<SaleEntity> getResultList(List<SaleStatus> list, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<SaleEntity> root = cq.from(SaleEntity.class);

        List<Predicate> orPredicates = new ArrayList<>();
        for (SaleStatus saleStatus : list) {
            orPredicates.add(cb.equal(root.get(SaleEntity_.saleStatus), saleStatus));
        }
        Predicate predicate1 = cb.or(orPredicates.toArray(new Predicate[orPredicates.size()]));
        Predicate predicate2 = this.getFilterCondition(cb, root, filters);

        Predicate predicate = cb.and(predicate1, predicate2);
        cq.where(predicate);

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                cq.orderBy(cb.asc(root.get(sortField)));
            } else if (sortOrder == SortOrder.DESCENDING) {
                cq.orderBy(cb.desc(root.get(sortField)));
            }
        }

        cq.orderBy(cb.desc(root.get(SaleEntity_.id)));

        Query q = getEntityManager().createQuery(cq);
        return q.setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    @Override
    public int count(List<SaleStatus> list, Map<String, Object> filters) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<SaleEntity> root = cq.from(SaleEntity.class);

            List<Predicate> orPredicates = new ArrayList<>();
            for (SaleStatus saleStatus : list) {
                orPredicates.add(cb.equal(root.get(SaleEntity_.saleStatus), saleStatus));
            }
            Predicate predicate1 = cb.or(orPredicates.toArray(new Predicate[orPredicates.size()]));
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
    public List<SaleEntity> getResultList(UserEntity user, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<SaleEntity> root = cq.from(SaleEntity.class);

        Predicate predicate1 = cb.equal(root.get(SaleEntity_.createdBy), user);
        Predicate predicate2 = this.getFilterCondition(cb, root, filters);
        Predicate predicate = cb.and(predicate1, predicate2);
        cq.where(predicate);

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                cq.orderBy(cb.asc(root.get(sortField)));
            } else if (sortOrder == SortOrder.DESCENDING) {
                cq.orderBy(cb.desc(root.get(sortField)));
            }
        }

        Query q = getEntityManager().createQuery(cq);
        return q.setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    @Override
    public List<SaleEntity> getResultList(UserEntity user, int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<SaleEntity> root = cq.from(SaleEntity.class);

        Predicate predicate1 = cb.equal(root.get(SaleEntity_.createdBy), user);
        Predicate predicate2 = this.getFilterCondition(cb, root, filters);
        Predicate predicate = cb.and(predicate1, predicate2);
        cq.where(predicate);

        if (multiSortMeta != null) {
            List<Order> orders = new ArrayList<>();
            for (SortMeta sortMeta : multiSortMeta) {
                String sortField = sortMeta.getSortField();
                SortOrder sortOrder = sortMeta.getSortOrder();
                if (sortField != null) {
                    Order order = null;
                    if (sortOrder == SortOrder.ASCENDING) {
                        order = cb.asc(root.get(sortField));
                    } else if (sortOrder == SortOrder.DESCENDING) {
                        order = cb.desc(root.get(sortField));
                    }
                    orders.add(order);
                }
            }
            cq.orderBy(orders);
        }

        Query q = getEntityManager().createQuery(cq);
        return q.setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    @Override
    public int count(UserEntity user, Map<String, Object> filters) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<SaleEntity> root = cq.from(SaleEntity.class);

            Predicate predicate1 = cb.equal(root.get(SaleEntity_.createdBy), user);
            Predicate predicate2 = this.getFilterCondition(cb, root, filters);
            Predicate predicate = cb.and(predicate1, predicate2);
            cq.where(predicate);

            cq.select(cb.count(root));

            return getEntityManager().createQuery(cq).getSingleResult().intValue();
        } catch (NoResultException ex) {
            return 0;
        }
    }

}
