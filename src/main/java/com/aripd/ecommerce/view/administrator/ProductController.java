package com.aripd.ecommerce.view.administrator;

import com.aripd.ecommerce.entity.CategoryEntity;
import com.aripd.util.MessageUtil;
import com.aripd.ecommerce.service.ProductService;
import com.aripd.ecommerce.entity.ProductEntity;
import com.aripd.ecommerce.entity.PriceEntity;
import com.aripd.ecommerce.model.data.LazyProductDataModel;
import com.aripd.ecommerce.service.CategoryService;
import com.aripd.ecommerce.service.PriceService;
import com.aripd.util.RequestUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@ViewScoped
public class ProductController implements Serializable {

    @Inject
    private ProductService productService;
    private ProductEntity newRecord;
    private ProductEntity selectedRecord;
    private List<ProductEntity> selectedRecords;
    private LazyDataModel<ProductEntity> lazyModel;

    private Long id;

    @Inject
    private PriceService priceService;
    private PriceEntity newPrice = new PriceEntity();
    private PriceEntity selectedPrice;

    @Inject
    private CategoryService categoryService;

    @Inject
    MessageUtil messageUtil;

    public ProductController() {
        newRecord = new ProductEntity();
        selectedRecord = new ProductEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyProductDataModel(productService);
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedRecord = productService.find(id);

        if (selectedRecord == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }

    }

    public List<CategoryEntity> getCategories() {
        return categoryService.findAll();
    }

    public void doCreatePrice(ActionEvent actionEvent) {
        newPrice.setProduct(selectedRecord);
        newPrice = priceService.create(newPrice);

        selectedRecord.getPrices().add(newPrice);
        productService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
        newPrice = new PriceEntity();
    }

    public String onDeletePrice() {
        selectedRecord.getPrices().remove(selectedPrice);
        productService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");

        return "/administrator/product/price?faces-redirect=true&id=" + selectedRecord.getId();
    }

    public void doDeletePrice(ActionEvent actionEvent) {
        priceService.delete(selectedPrice);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        ProductEntity product = productService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");

        String navigation = "/administrator/product/form?id=" + product.getId() + "&amp;faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        productService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord() {
        productService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        if (selectedRecords.isEmpty()) {
            messageUtil.addGlobalErrorFlashMessage("Please select at least one item");
        } else {
            productService.deleteItems(selectedRecords);
            messageUtil.addGlobalInfoFlashMessage("Deleted");
        }
    }

    public ProductEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(ProductEntity newRecord) {
        this.newRecord = newRecord;
    }

    public ProductEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(ProductEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<ProductEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<ProductEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public LazyDataModel<ProductEntity> getLazyModel() {
        return lazyModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PriceEntity getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(PriceEntity newPrice) {
        this.newPrice = newPrice;
    }

    public PriceEntity getSelectedPrice() {
        return selectedPrice;
    }

    public void setSelectedPrice(PriceEntity selectedPrice) {
        this.selectedPrice = selectedPrice;
    }

}
