package com.aripd.ecommerce.view.administrator;

import com.aripd.ecommerce.entity.ProductEntity;
import com.aripd.ecommerce.entity.SaleEntity;
import com.aripd.ecommerce.entity.SalelineEntity;
import com.aripd.ecommerce.service.ProductService;
import com.aripd.util.MessageUtil;
import com.aripd.ecommerce.entity.SaleStatus;
import com.aripd.ecommerce.model.data.LazySaleDataModel;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import com.aripd.ecommerce.service.SaleService;

@Named
@ViewScoped
public class SaleController implements Serializable {

    @Inject
    private SaleService saleService;
    private SaleEntity newRecord;
    private SaleEntity selectedRecord;
    private LazyDataModel<SaleEntity> lazyModel;

    private Long id;

    @Inject
    private ProductService productService;

    @Inject
    MessageUtil messageUtil;

    public SaleController() {
        newRecord = new SaleEntity();
        selectedRecord = new SaleEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazySaleDataModel(saleService);
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedRecord = saleService.find(id);

        if (selectedRecord == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }

    }

    public List<SaleStatus> getSaleStatuses() {
        return Arrays.asList(SaleStatus.values());
    }

    public ProductEntity getImageUrlByCode(String code) {
        return productService.findOneByCode(code);
    }

    public BigDecimal doCalculateSubtotal(SalelineEntity e) {
        return e.getIPN_TOTAL().multiply(new BigDecimal(e.getIPN_QTY()));
    }

    public BigDecimal doCalculatePriceTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (SalelineEntity e : selectedRecord.getSalelines()) {
            total = total.add(doCalculateSubtotal(e));
        }
        return total;
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        saleService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        saleService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doUpdateStatus(AjaxBehaviorEvent event) {
        saleService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public SaleEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(SaleEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public SaleEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(SaleEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<SaleEntity> getLazyModel() {
        return lazyModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
