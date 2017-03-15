package com.aripd.ecommerce.view;

import com.aripd.ecommerce.service.UserService;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.util.locale.LocaleBean;
import com.aripd.util.MessageUtil;
import com.aripd.util.RequestUtil;
import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.LocaleUtils;

@Named
@ViewScoped
public class ProfileBean implements Serializable {

    @Inject
    private UserService userService;
    private UserEntity selectedRecord;

    @Inject
    private LocaleBean localeBean;

    @Inject
    MessageUtil messageUtil;

    public ProfileBean() {
    }

    @PostConstruct
    public void init() {
        selectedRecord = userService.getCurrentUser();
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
        String navigation = "/index.xhtml?faces-redirect=true";
        UserEntity user = userService.getCurrentUser();
        if (selectedRecord.getEmail().equalsIgnoreCase(user.getEmail())) {
            userService.update(selectedRecord);
            messageUtil.addGlobalInfoFlashMessage("Updated");
        } else if (userService.isExistByEmailExceptEmail(selectedRecord.getEmail(), user.getEmail())) {
            messageUtil.addGlobalErrorFlashMessage("E-mail address {0} is available. Please try another one.", new Object[]{selectedRecord.getEmail()});
        } else {
            userService.update(selectedRecord);
            messageUtil.addGlobalInfoFlashMessage("Updated");

            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            try {
                request.logout();

                navigationHandler.handleNavigation(context, null, navigation);
            } catch (ServletException ex) {
                throw new FacesException(ex);
            }
        }
    }

    public void doUpdatePersonalSettings(ActionEvent actionEvent) {
        UserEntity user = userService.getCurrentUser();
        userService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");

        if (!selectedRecord.getLocale().equalsIgnoreCase(user.getLocale())) {
            Locale locale = LocaleUtils.toLocale(selectedRecord.getLocale());
            localeBean.doChange(locale);

            String navigation = "/member/personalsettings.xhtml?faces-redirect=true";
            RequestUtil.doNavigate(navigation);
        }
    }

    public UserEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(UserEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

}
