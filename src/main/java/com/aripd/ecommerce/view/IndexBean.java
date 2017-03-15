package com.aripd.ecommerce.view;

import com.aripd.ecommerce.entity.ProductEntity;
import com.aripd.ecommerce.model.data.LazyProductDataModelByStatus;
import com.aripd.ecommerce.service.ProductService;
import com.aripd.util.currency.CurrencyBean;
import com.aripd.util.currency.PriceHelper;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@ViewScoped
public class IndexBean implements Serializable {

    @Inject
    private ProductService productService;
    private LazyDataModel<ProductEntity> lazyModel;

    @Inject
    CurrencyBean currencyBean;

    @Inject
    PriceHelper priceHelper;

    public IndexBean() {
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyProductDataModelByStatus(productService, true);
    }

    public BigDecimal getPriceTaxedExchanged(ProductEntity product) {
        return priceHelper.getPriceTaxedExchanged(product, 1, currencyBean.getCurrency().getCurrencyCode());
    }

    public List<ProductEntity> getBanners() {
        return productService.findByBannerStatusTrue();
    }

    public LazyDataModel<ProductEntity> getLazyModel() {
        return lazyModel;
    }

}
