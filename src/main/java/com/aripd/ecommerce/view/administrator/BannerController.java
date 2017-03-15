package com.aripd.ecommerce.view.administrator;

import com.aripd.ecommerce.service.BannerService;
import com.aripd.ecommerce.entity.BannerEntity;
import com.aripd.ecommerce.entity.BannerType;
import com.aripd.ecommerce.model.data.LazyBannerDataModel;
import com.aripd.util.MessageUtil;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.UploadedFile;

@Named
@ViewScoped
public class BannerController implements Serializable {

    @Inject
    private BannerService bannerService;
    private BannerEntity newRecord;
    private BannerEntity selectedRecord;
    private List<BannerEntity> selectedRecords;
    private LazyDataModel<BannerEntity> lazyModel;

    private UploadedFile file;

    @Inject
    MessageUtil messageUtil;

    public BannerController() {
        newRecord = new BannerEntity();
        selectedRecord = new BannerEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyBannerDataModel(bannerService);
    }

    public List<BannerType> getBannerTypes() {
        return Arrays.asList(BannerType.values());
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        if (file != null) {
            newRecord.setBytes(file.getContents());
        }
        bannerService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        if (file != null) {
            selectedRecord.setBytes(file.getContents());
        }
        bannerService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        bannerService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        if (selectedRecords.isEmpty()) {
            messageUtil.addGlobalErrorFlashMessage("Please select at least one item");
        } else {
            bannerService.deleteItems(selectedRecords);
            messageUtil.addGlobalInfoFlashMessage("Deleted");
        }
    }

    public BannerEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(BannerEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<BannerEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<BannerEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public BannerEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(BannerEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<BannerEntity> getLazyModel() {
        return lazyModel;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
