package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.TicketEntity;
import com.aripd.ecommerce.entity.TicketmessageEntity;
import com.aripd.ecommerce.entity.TicketmessageEntity_;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class TicketmessageServiceBean extends CrudServiceBean<TicketmessageEntity, Long> implements TicketmessageService {

    @Resource(name = "mail/ecommerce")
    private Session mailSession;

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TicketmessageServiceBean() {
        super(TicketmessageEntity.class);
    }

    @Override
    public List<TicketmessageEntity> findByTicket(TicketEntity ticket) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<TicketmessageEntity> cq = cb.createQuery(TicketmessageEntity.class);
        Root<TicketmessageEntity> root = cq.from(TicketmessageEntity.class);

        Predicate predicate = cb.equal(root.get(TicketmessageEntity_.ticket), ticket);
        cq.where(predicate);

        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public void sendTicketmessage(TicketmessageEntity ticketmessage) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);

        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("Mail", locale);
        String subject = rb.getString("ticketmessage.subject");
        String msg = MessageFormat.format(rb.getString("ticketmessage.msg"),
                new Object[]{
                    ticketmessage.getCreatedBy().getFullname(),
                    ticketmessage.getCreatedBy().getEmail(),
                    ticketmessage.getContent()
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
        } catch (MessagingException | UnsupportedEncodingException ex) {
            throw new FacesException(ex);
        }
    }

}
