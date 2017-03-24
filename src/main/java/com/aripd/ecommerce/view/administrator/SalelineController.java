package com.aripd.ecommerce.view.administrator;

import com.aripd.ecommerce.entity.SaleStatus;
import com.aripd.util.MessageUtil;
import com.aripd.ecommerce.entity.SalelineEntity;
import com.aripd.ecommerce.model.data.LazySalelineDataModel;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import com.aripd.ecommerce.service.SalelineService;
import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class SalelineController implements Serializable {

    @Inject
    private SalelineService salelineService;
    private SalelineEntity newRecord;
    private SalelineEntity selectedRecord;
    private LazyDataModel<SalelineEntity> lazyModel;

    private Long id;

    @Inject
    MessageUtil messageUtil;

    public SalelineController() {
        newRecord = new SalelineEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazySalelineDataModel(salelineService);
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedRecord = salelineService.find(id);

        if (selectedRecord == null) {
            messageUtil.addGlobalErrorFlashMessage("No such record");
            return;
        }

    }

    public List<SaleStatus> getSaleStatuses() {
        return Arrays.asList(SaleStatus.values());
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        salelineService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        salelineService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doUpdateStatus(AjaxBehaviorEvent event) {
        salelineService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public SalelineEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(SalelineEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public SalelineEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(SalelineEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<SalelineEntity> getLazyModel() {
        return lazyModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
