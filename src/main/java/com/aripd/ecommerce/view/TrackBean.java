package com.aripd.ecommerce.view;

import com.aripd.ecommerce.entity.SaleEntity;
import com.aripd.util.MessageUtil;
import com.aripd.util.RequestUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.aripd.ecommerce.service.SaleService;
import com.aripd.util.validator.EmailAddress;

@Named
@ViewScoped
public class TrackBean implements Serializable {

    @EmailAddress
    private String email;
    private String orderRef;

    @Inject
    private SaleService saleService;

    @Inject
    MessageUtil messageUtil;

    public TrackBean() {
    }

    @PostConstruct
    public void init() {
    }

    public void doSubmit(ActionEvent actionEvent) {
        SaleEntity sale = saleService.findOneByUserEmailAndOrderRef(email, orderRef);
        if (sale != null) {
            String navigation = String.format("/sale.jsf?orderRef=%s&amp;faces-redirect=true", sale.getREFNOEXT());
            RequestUtil.doNavigate(navigation);
        } else {
            messageUtil.addGlobalErrorFlashMessage("Not found");
        }
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
