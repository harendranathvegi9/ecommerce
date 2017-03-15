package com.aripd.ecommerce.view;

import com.aripd.ecommerce.service.BannerService;
import com.aripd.ecommerce.entity.BannerEntity;
import com.aripd.ecommerce.entity.BannerType;
import com.aripd.ecommerce.entity.ProductEntity;
import com.aripd.ecommerce.entity.ProductStatus;
import com.aripd.ecommerce.model.data.LazyProductDataModelByProductStatus;
import com.aripd.ecommerce.service.ProductService;
import com.aripd.util.currency.CurrencyBean;
import com.aripd.util.currency.PriceHelper;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
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
    private BannerService bannerService;

    @Inject
    CurrencyBean currencyBean;

    @Inject
    PriceHelper priceHelper;

    public IndexBean() {
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyProductDataModelByProductStatus(productService, Arrays.asList(ProductStatus.ACTIVE));
    }

    public BigDecimal getPriceTaxedExchanged(ProductEntity product) {
        return priceHelper.getPriceTaxedExchanged(product, 1, currencyBean.getCurrency().getCurrencyCode());
    }

    public List<BannerEntity> getBannersTop() {
        return bannerService.findAllByStatusTrueOrderBySortOrder(BannerType.TOP);
    }

    public List<BannerEntity> getBannersBottom() {
        return bannerService.findAllByStatusTrueOrderBySortOrder(BannerType.BOTTOM);
    }

    public LazyDataModel<ProductEntity> getLazyModel() {
        return lazyModel;
    }

}
