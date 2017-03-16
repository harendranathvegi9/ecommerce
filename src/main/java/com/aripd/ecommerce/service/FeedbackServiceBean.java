package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.FeedbackEntity;
import com.aripd.ecommerce.entity.FeedbackEntity_;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.util.MessageUtil;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
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
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

@Stateless
public class FeedbackServiceBean extends CrudServiceBean<FeedbackEntity, Long> implements FeedbackService {

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

    public FeedbackServiceBean() {
        super(FeedbackEntity.class);
    }

    @Override
    public List<FeedbackEntity> findByUuid(String uuid) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<FeedbackEntity> cq = cb.createQuery(FeedbackEntity.class);
        Root<FeedbackEntity> root = cq.from(FeedbackEntity.class);

        Predicate predicate = cb.equal(root.get(FeedbackEntity_.uuid), uuid);
        cq.where(predicate);

        List<Order> orderList = new ArrayList();
        orderList.add(cb.asc(root.get(FeedbackEntity_.createdAt)));
        cq.orderBy(orderList);

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public FeedbackEntity findOneByUserAndId(UserEntity user, Long id) {
        try {
            CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
            CriteriaQuery<FeedbackEntity> cq = cb.createQuery(FeedbackEntity.class);
            Root<FeedbackEntity> root = cq.from(FeedbackEntity.class);

            Predicate predicate1 = cb.equal(root.get(FeedbackEntity_.user), user);
            Predicate predicate2 = cb.equal(root.get(FeedbackEntity_.id), id);
            Predicate predicate = cb.and(predicate1, predicate2);
            cq.where(predicate);

            return getEntityManager().createQuery(cq).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void sendFeedback(FeedbackEntity feedback) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);

        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("Mail", locale);
        String subject = rb.getString("feedback.subject");
        String msg = MessageFormat.format(rb.getString("feedback.msg"),
                new Object[]{
                    feedback.getUser().getFullname(),
                    feedback.getUser().getEmail(),
                    feedback.getMessage()
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
            messageUtil.addGlobalInfoFlashMessage("Your message has been successfully submitted");
        } catch (MessagingException | UnsupportedEncodingException ex) {
            messageUtil.addGlobalErrorFlashMessage("An error occured");
            throw new FacesException(ex);
        }
    }

    @Override
    public List<FeedbackEntity> getResultList(UserEntity user, int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<FeedbackEntity> root = cq.from(FeedbackEntity.class);

        Predicate predicate1 = cb.equal(root.get(FeedbackEntity_.user), user);
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
    public List<FeedbackEntity> getResultList(UserEntity user, int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<FeedbackEntity> root = cq.from(FeedbackEntity.class);

        Predicate predicate1 = cb.equal(root.get(FeedbackEntity_.user), user);
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
            Root<FeedbackEntity> root = cq.from(FeedbackEntity.class);

            Predicate predicate1 = cb.equal(root.get(FeedbackEntity_.user), user);
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
