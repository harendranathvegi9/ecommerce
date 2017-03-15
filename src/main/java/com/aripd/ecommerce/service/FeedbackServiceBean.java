package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.FeedbackEntity;
import com.aripd.util.MessageUtil;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
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
import javax.persistence.PersistenceContext;

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
    public void sendFeedback(FeedbackEntity feedback) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);

        Locale locale = context.getViewRoot().getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("Mail", locale);
        String subject = rb.getString("feedback.subject");
        String msg = MessageFormat.format(rb.getString("feedback.msg"),
                new Object[]{
                    feedback.getName(),
                    feedback.getEmail(),
                    feedback.getPhone(),
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

}
