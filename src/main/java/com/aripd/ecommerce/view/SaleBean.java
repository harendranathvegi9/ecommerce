package com.aripd.ecommerce.view;

import com.aripd.ecommerce.entity.SaleEntity;
import com.aripd.util.MessageUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.aripd.ecommerce.service.SaleService;

@Named
@ViewScoped
public class SaleBean implements Serializable {

    @Inject
    private SaleService saleService;
    private SaleEntity selectedRecord;

    private String orderRef;

    @Inject
    MessageUtil messageUtil;

    public SaleBean() {
    }

    @PostConstruct
    public void init() {
    }

    public void onLoad() {
        if (orderRef == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedRecord = saleService.findOneByOrderRef(orderRef);

        if (selectedRecord == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }

    }

//    public BigDecimal doCalculateSaleTotal() {
//        BigDecimal total = BigDecimal.ZERO;
//        for (SalelineEntity saleline : selectedRecord.getSalelines()) {
//            total = total.add(saleline.getPriceTotalBeforeTax());
//        }
//        return total;
//    }
//
//    public BigDecimal doCalculateTaxTotal() {
//        BigDecimal total = BigDecimal.ZERO;
//        for (SalelineEntity saleline : selectedRecord.getSalelines()) {
//            total = total.add(saleline.getTaxTotal());
//        }
//        return total;
//    }

    public SaleEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(SaleEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

}
