package com.aripd.ecommerce.view.member;

import com.aripd.ecommerce.entity.ProductEntity;
import com.aripd.ecommerce.service.UserService;
import com.aripd.ecommerce.entity.SaleEntity;
import com.aripd.ecommerce.entity.SalelineEntity;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.model.data.LazySaleDataModelByUser;
import com.aripd.ecommerce.service.ProductService;
import com.aripd.util.MessageUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import com.aripd.ecommerce.service.SaleService;
import com.aripd.ecommerce.service.SalelineService;
import com.aripd.util.RequestUtil;

@Named
@ViewScoped
public class SaleView implements Serializable {

    @Inject
    private SaleService saleService;
    private SaleEntity selectedRecord;
    private List<SaleEntity> selectedRecords;
    private LazyDataModel<SaleEntity> lazyModel;

    private Long id;

    @Inject
    private SalelineService salelineService;
    private SalelineEntity selectedSaleline;

    private String bankTransactionNumber;

    @Inject
    private UserService userService;
    private UserEntity user;

    @Inject
    private ProductService productService;

    @Inject
    MessageUtil messageUtil;

    public SaleView() {
        selectedRecords = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();
        if (user != null) {
            lazyModel = new LazySaleDataModelByUser(saleService, user);
        }
    }

    public void onLoad() {
        if (id == null) {
            RequestUtil.doNavigate("/member/index?faces-redirect=true");
        }

        selectedRecord = saleService.findOneByUserAndId(user, id);

        if (selectedRecord == null) {
            RequestUtil.doNavigate("/member/index?faces-redirect=true");
        }

    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        messageUtil.addGlobalInfoFlashMessage("Updated");
        saleService.update(selectedRecord);
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankTransactionNumber() {
        return bankTransactionNumber;
    }

    public void setBankTransactionNumber(String bankTransactionNumber) {
        this.bankTransactionNumber = bankTransactionNumber;
    }

    public SaleEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(SaleEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public LazyDataModel<SaleEntity> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<SaleEntity> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public List<SaleEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<SaleEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public SalelineEntity getSelectedSaleline() {
        return selectedSaleline;
    }

    public void setSelectedSaleline(SalelineEntity selectedSaleline) {
        this.selectedSaleline = selectedSaleline;
    }

}
