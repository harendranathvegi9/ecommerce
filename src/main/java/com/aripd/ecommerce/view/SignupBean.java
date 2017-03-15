package com.aripd.ecommerce.view;

import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.entity.UserGroup;
import com.aripd.ecommerce.entity.UserStatus;
import com.aripd.ecommerce.service.UserService;
import com.aripd.util.MessageUtil;
import com.aripd.util.locale.LocaleProvider;
import com.aripd.util.mail.IMAPMessageSendResponseModel;
import com.aripd.util.mail.MailUtil;
import com.aripd.util.validator.EmailAddress;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class SignupBean implements Serializable {

    @EmailAddress
    private String email;

    private String firstname;
    private String lastname;

    private String company;

    private String line1;
    private String line2;
    private String zipcode;
    private String county;

    @Inject
    private UserService userService;

    @Inject
    MessageUtil messageUtil;

    @Inject
    MailUtil mailUtil;

    public SignupBean() {
    }

    @PostConstruct
    public void init() {
    }

    public String getReturnValue() {
        return "/index";
    }

    public void doSubmitMember(ActionEvent actionEvent) {
        UserEntity user = userService.findOneByEmail(email);
        if (user != null) {
            messageUtil.addGlobalInfoFlashMessage("User is already signed up");
        } else {
            SignupModel model = new SignupModel.Builder()
                    .setGroup(UserGroup.Members)
                    .setStatus(UserStatus.Unconfirmed)
                    .setEmail(email)
                    .setFirstname(firstname)
                    .setLastname(lastname)
                    .build();

            user = userService.signup(model);

            String subject = messageUtil.getMailMessage(LocaleProvider.getLocale(), "confirmation.subject");
            String msg = messageUtil.getMailMessage(LocaleProvider.getLocale(), "confirmation.msg", new Object[]{
                user.getEmail(),
                user.getEmail(),
                user.getPassword(),
                user.getValidationUrl()
            });
            IMAPMessageSendResponseModel imapModel = mailUtil.sendEmail(user.getEmail(), subject, msg);
            if (imapModel.isSuccess()) {
                messageUtil.addGlobalInfoFlashMessage("Your confirmation message is sent to {0}", new Object[]{user.getEmail()});
            } else {
                messageUtil.addGlobalCustomFlashMessage(imapModel.getMessage());
            }
        }
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
