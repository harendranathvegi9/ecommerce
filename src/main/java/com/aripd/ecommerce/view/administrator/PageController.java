package com.aripd.ecommerce.view.administrator;

import com.aripd.ecommerce.model.data.LazyPageDataModel;
import com.aripd.ecommerce.service.PageService;
import com.aripd.ecommerce.entity.PageEntity;
import com.aripd.util.MessageUtil;
import com.aripd.util.RequestUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@ViewScoped
public class PageController implements Serializable {

    @Inject
    private PageService pageService;
    private PageEntity newRecord;
    private PageEntity selectedRecord;
    private List<PageEntity> selectedRecords;
    private LazyDataModel<PageEntity> lazyModel;

    private Long id;

    @Inject
    MessageUtil messageUtil;

    public PageController() {
        newRecord = new PageEntity();
        selectedRecord = new PageEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyPageDataModel(pageService);
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedRecord = pageService.find(id);

        if (selectedRecord == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        PageEntity page = pageService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");

        String navigation = "/administrator/page/form?id=" + page.getId() + "&amp;faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        pageService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDuplicateRecord(ActionEvent actionEvent) {
        PageEntity e = pageService.create(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Duplicated");

        String navigation = "/administrator/page/form?faces-redirect=true&id=" + e.getId();
        RequestUtil.doNavigate(navigation);
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        pageService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        if (selectedRecords.isEmpty()) {
            messageUtil.addGlobalErrorFlashMessage("Please select at least one item");
        } else {
            pageService.deleteItems(selectedRecords);
            messageUtil.addGlobalInfoFlashMessage("Deleted");
        }
    }

    public PageEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(PageEntity newRecord) {
        this.newRecord = newRecord;
    }

    public PageEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(PageEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public LazyDataModel<PageEntity> getLazyModel() {
        return lazyModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PageEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<PageEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

}
