package com.aripd.ecommerce.view;

import com.aripd.ecommerce.entity.BasketitemEntity;
import com.aripd.ecommerce.service.UserService;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.service.BasketitemService;
import com.aripd.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
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

@Named
@ViewScoped
public class ProfileBean implements Serializable {

    @Inject
    private UserService userService;
    private UserEntity selectedRecord;

    private String passwordCurrent;
    private String passwordNew;

    @Inject
    private BasketitemService basketitemService;

    @Inject
    MessageUtil messageUtil;

    public ProfileBean() {
    }

    @PostConstruct
    public void init() {
        selectedRecord = userService.getCurrentUser();
    }

    public int getNumberOfItems() {
        List<BasketitemEntity> basketitems = basketitemService.findAllByUser(selectedRecord);
        return basketitems.size();
    }

    public void doUpdatePassword(ActionEvent actionEvent) {
        if (!selectedRecord.getPassword().equals(passwordCurrent)) {
            messageUtil.addGlobalErrorFlashMessage("Your current password is wrong");
        } else if (!passwordCurrent.equals(passwordNew)) {
            selectedRecord.setPassword(passwordNew);
            userService.update(selectedRecord);
            messageUtil.addGlobalInfoFlashMessage("Updated");
        }
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

    public UserEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(UserEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public String getPasswordCurrent() {
        return passwordCurrent;
    }

    public void setPasswordCurrent(String passwordCurrent) {
        this.passwordCurrent = passwordCurrent;
    }

    public String getPasswordNew() {
        return passwordNew;
    }

    public void setPasswordNew(String passwordNew) {
        this.passwordNew = passwordNew;
    }

}
