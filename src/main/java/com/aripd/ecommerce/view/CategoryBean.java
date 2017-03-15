package com.aripd.ecommerce.view;

import com.aripd.ecommerce.service.ProductService;
import com.aripd.ecommerce.entity.ProductEntity;
import com.aripd.ecommerce.entity.CategoryEntity;
import com.aripd.ecommerce.model.data.LazyProductDataModelByCategory;
import com.aripd.util.currency.PriceHelper;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.TreeNode;
import com.aripd.ecommerce.service.CategoryService;
import com.aripd.util.currency.CurrencyBean;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

@Named
@ViewScoped
public class CategoryBean implements Serializable {

    @Inject
    private CategoryService categoryService;
    private CategoryEntity selectedCategory;

    private Long id;
    private String slug;

    private MenuModel model;

    @Inject
    private ProductService productService;
    private LazyDataModel<ProductEntity> lazyModel;

    private TreeNode root3;
    private TreeNode[] selectedNodes2;

    @Inject
    CurrencyBean currencyBean;

    @Inject
    PriceHelper priceHelper;

    public CategoryBean() {
    }

    @PostConstruct
    public void init() {
    }

    public void onLoad() {
        if (id != null) {
            selectedCategory = categoryService.find(id);
            lazyModel = new LazyProductDataModelByCategory(productService, selectedCategory);
            System.out.println(String.format("Filtered by selectedCategory: %s", selectedCategory));

            model = createMenuModel(selectedCategory);
        }
    }

    public TreeNode getRoot() {
        TreeNode treeNode1 = new DefaultTreeNode("Root", null);
        List<CategoryEntity> list1 = categoryService.findByParent(null);
        getTreeChildren(treeNode1, list1);
//        for (CategoryEntity entity1 : list1) {
//            TreeNode treeNode2 = new DefaultTreeNode(entity1, treeNode1);
//            List<CategoryEntity> list2 = categoryService.findByParent(entity1);
//            for (CategoryEntity entity2 : list2) {
//                TreeNode treeNode3 = new DefaultTreeNode(entity2, treeNode2);
//                List<CategoryEntity> list3 = categoryService.findByParent(entity2);
//                for (CategoryEntity entity3 : list3) {
//                    TreeNode treeNode4 = new DefaultTreeNode(entity3, treeNode3);
//                    List<CategoryEntity> list4 = categoryService.findByParent(entity3);
//                    for (CategoryEntity entity4 : list4) {
//                        TreeNode treeNode5 = new DefaultTreeNode(entity4, treeNode4);
//                    }
//                }
//            }
//        }
        return treeNode1;
    }

    private void getTreeChildren(TreeNode treeNode1, List<CategoryEntity> list1) {
        for (CategoryEntity entity1 : list1) {
            TreeNode treeNode2 = new DefaultTreeNode(entity1, treeNode1);
            List<CategoryEntity> list2 = categoryService.findByParent(entity1);
            getTreeChildren(treeNode2, list2);
        }
    }

    public BigDecimal getPriceTaxedExchanged(ProductEntity product) {
        return priceHelper.getPriceTaxedExchanged(product, 1, currencyBean.getCurrency().getCurrencyCode());
    }

    public MenuModel createMenuModel(CategoryEntity category) {
        MenuModel menuModel = new DefaultMenuModel();

        DefaultMenuItem home = new DefaultMenuItem();
        home.setValue("Categories");
        home.setOutcome("/categories");
        menuModel.addElement(home);

        for (CategoryEntity c : category.getBreadcrumb()) {
            DefaultMenuItem item = new DefaultMenuItem();
            item.setValue(c.getCode());
            item.setOutcome("/category");
            item.setParam("id", c.getId());
            item.setParam("slug", c.getSlug());
            menuModel.addElement(item);
        }

        return menuModel;
    }

    public LazyDataModel<ProductEntity> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<ProductEntity> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public CategoryEntity getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(CategoryEntity selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public TreeNode getRoot3() {
        return root3;
    }

    public TreeNode[] getSelectedNodes2() {
        return selectedNodes2;
    }

    public void setSelectedNodes2(TreeNode[] selectedNodes2) {
        this.selectedNodes2 = selectedNodes2;
    }

    public MenuModel getModel() {
        return model;
    }

}
