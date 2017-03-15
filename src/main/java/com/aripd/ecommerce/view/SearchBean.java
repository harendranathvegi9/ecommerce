package com.aripd.ecommerce.view;

import com.aripd.ecommerce.service.ProductService;
import com.aripd.ecommerce.entity.ProductEntity;
import com.aripd.ecommerce.model.data.LazyProductDataModelBySearchTerm;
import com.aripd.util.currency.CurrencyBean;
import com.aripd.util.currency.PriceHelper;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@ViewScoped
public class SearchBean implements Serializable {

    @Inject
    private ProductService productService;
    private LazyDataModel<ProductEntity> lazyModel;

    private String searchTerm;

    @Inject
    CurrencyBean currencyBean;

    @Inject
    PriceHelper priceHelper;

    public SearchBean() {
    }

    @PostConstruct
    public void init() {
    }

    public void onLoad() {
        if (searchTerm != null) {
            lazyModel = new LazyProductDataModelBySearchTerm(productService, searchTerm);
        }
    }

    public String doSearch() {
        if (searchTerm.length() > 0) {
            return "/search?searchTerm=" + searchTerm + "&amp;faces-redirect=true";
        }
        return "";
    }

    public BigDecimal getPriceTaxedExchanged(ProductEntity product) {
        return priceHelper.getPriceTaxedExchanged(product, 1, currencyBean.getCurrency().getCurrencyCode());
    }

    public LazyDataModel<ProductEntity> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<ProductEntity> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

}
