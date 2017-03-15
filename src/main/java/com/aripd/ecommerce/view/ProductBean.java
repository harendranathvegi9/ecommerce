package com.aripd.ecommerce.view;

import com.aripd.ecommerce.entity.BasketitemEntity;
import com.aripd.ecommerce.service.ProductService;
import com.aripd.ecommerce.service.UserService;
import com.aripd.ecommerce.entity.ProductEntity;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.service.BasketitemService;
import com.aripd.util.MessageUtil;
import com.aripd.util.currency.CurrencyBean;
import com.aripd.util.currency.PriceHelper;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ProductBean implements Serializable {

    @Inject
    private ProductService productService;
    private ProductEntity selectedRecord;

    private Long id;
    private String slug;

    @Inject
    private UserService userService;
    private UserEntity user;

    @Inject
    private BasketitemService basketitemService;

    @Inject
    PriceHelper priceHelper;

    @Inject
    CurrencyBean currencyBean;

    @Inject
    MessageUtil messageUtil;

    public ProductBean() {
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedRecord = productService.find(id);

        if (selectedRecord == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }

//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        ExternalContext externalContext = facesContext.getExternalContext();
//        Map<String, String> headers = externalContext.getRequestHeaderMap();
//        boolean ajax = "XMLHttpRequest".equals(headers.get("X-Requested-With"));
//        if (!ajax) {
//            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
//            productService.saveRating(user, selectedRecord, request);
//        }
    }

    public BigDecimal getPriceTaxedExchanged(ProductEntity product, Integer quantity) {
        return priceHelper.getPriceTaxedExchanged(product, quantity, currencyBean.getCurrency().getCurrencyCode());
    }

    public void addBasketitem(ActionEvent actionEvent) {
        if (user == null) {
            messageUtil.addGlobalInfoFlashMessage("Please login first");
        } else {
            Integer quantity = 1;
            BasketitemEntity entity = basketitemService.findOneByUserAndProduct(user, selectedRecord);
            if (entity != null) {
                Integer old = entity.getQuantity();
                quantity += old;
                entity.setQuantity(quantity);
                basketitemService.update(entity);
            } else {
                entity = new BasketitemEntity();
                entity.setCreatedBy(user);
                entity.setProduct(selectedRecord);
                entity.setQuantity(quantity);
                entity.setNote(null);
                basketitemService.create(entity);
            }

            messageUtil.addGlobalInfoFlashMessage("Product has been added to your shopping basket");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public ProductEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(ProductEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
