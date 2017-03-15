package com.aripd.ecommerce.view.administrator;

import com.aripd.ecommerce.entity.CategoryEntity;
import com.aripd.ecommerce.model.data.LazyCategoryDataModel;
import com.aripd.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import com.aripd.ecommerce.service.CategoryService;

@Named
@ViewScoped
public class CategoryController implements Serializable {

    @Inject
    private CategoryService categoryService;
    private CategoryEntity newRecord;
    private CategoryEntity selectedRecord;
    private List<CategoryEntity> selectedRecords;
    private LazyDataModel<CategoryEntity> lazyModel;

    private Long id;

    @Inject
    MessageUtil messageUtil;

    public CategoryController() {
        newRecord = new CategoryEntity();
        selectedRecord = new CategoryEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyCategoryDataModel(categoryService);
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedRecord = categoryService.find(id);

        if (selectedRecord == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }

    }

    public List<CategoryEntity> getCategories() {
        List<CategoryEntity> list = categoryService.findAll();
        list.remove(selectedRecord);
        return list;
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        categoryService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        categoryService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        categoryService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        if (selectedRecords.isEmpty()) {
            messageUtil.addGlobalErrorFlashMessage("Please select at least one item");
        } else {
            categoryService.deleteItems(selectedRecords);
            messageUtil.addGlobalInfoFlashMessage("Deleted");
        }
    }

    public CategoryEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(CategoryEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<CategoryEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<CategoryEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public CategoryEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(CategoryEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<CategoryEntity> getLazyModel() {
        return lazyModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
