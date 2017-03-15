package com.aripd.ecommerce.view;

import com.aripd.ecommerce.service.UserService;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.entity.UserStatus;
import com.aripd.util.MessageUtil;
import com.aripd.util.RequestUtil;
import com.aripd.util.helper.CookieHelper;
import com.aripd.util.locale.LocaleBean;
import com.aripd.util.validator.EmailAddress;
import java.io.Serializable;
import java.util.Locale;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.LocaleUtils;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    public static final String COOKIE_NAME = "remember";
    public static final int COOKIE_AGE = 2592000;// 30 days

    @Inject
    private UserService userService;
    private UserEntity user;

    private String token;

    @EmailAddress
    private String username;
    private String password;
    private boolean remember;

    @Inject
    private LocaleBean localeBean;

    @Inject
    MessageUtil messageUtil;

    public LoginBean() {
    }

    @PostConstruct
    public void init() {
    }

    public void onLoad() {
        if (token != null) {
            user = userService.findOneByToken(token);

            if (user != null) {
                username = user.getEmail();
                password = user.getPassword();
                login();
            }
        }
    }

    /**
     * TODO 403 substatus integration REF: http://en.wikipedia.org/wiki/HTTP_403
     *
     * @param actionEvent ActionEvent
     */
    public void doLogin(ActionEvent actionEvent) {
        user = userService.findOneByEmail(username);
        if (user == null) {
            messageUtil.addGlobalErrorFlashMessage("User is not exist");
        } else if (user.getUserStatus().equals(UserStatus.Unconfirmed)) {
            messageUtil.addGlobalErrorFlashMessage("Your account is not confirmed yet");
        } else if (user.getUserStatus().equals(UserStatus.Confirmed)) {
            messageUtil.addGlobalErrorFlashMessage("Your account is not approved yet");
        } else if (user.getUserStatus().equals(UserStatus.Approved)) {
            login();
        }
    }

    private void login() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

            request.login(username, password);

            Locale locale = LocaleUtils.toLocale(user.getLocale());
            localeBean.doChange(locale);

            if (remember) {
                String uuid = UUID.randomUUID().toString();
                user.setUuid(uuid);
                userService.update(user);
                CookieHelper.addCookie(response, COOKIE_NAME, uuid, COOKIE_AGE);
            } else {
                user.setUuid(null);
                userService.update(user);
                CookieHelper.removeCookie(response, COOKIE_NAME);
            }

            String navigation = "/login?faces-redirect=true";
            RequestUtil.doNavigate(navigation);
        } catch (ServletException ex) {
            messageUtil.addGlobalErrorFlashMessage("The username or password you provided does not match our records");
        }
    }

    public String doLogout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        try {
            user = userService.findOneByEmail(request.getUserPrincipal().getName());
            user.setUuid(null);
            userService.update(user);
            CookieHelper.removeCookie(response, COOKIE_NAME);

            request.logout();
        } catch (ServletException ex) {
            messageUtil.addGlobalErrorFlashMessage("Logout failed");
        }
        return "/index?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
