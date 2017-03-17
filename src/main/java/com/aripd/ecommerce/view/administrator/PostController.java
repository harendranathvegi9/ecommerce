package com.aripd.ecommerce.view.administrator;

import com.aripd.ecommerce.model.data.LazyPostDataModel;
import com.aripd.ecommerce.service.PostService;
import com.aripd.ecommerce.entity.PostEntity;
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
public class PostController implements Serializable {

    @Inject
    private PostService postService;
    private PostEntity newRecord;
    private PostEntity selectedRecord;
    private List<PostEntity> selectedRecords;
    private LazyDataModel<PostEntity> lazyModel;

    private Long id;

    @Inject
    MessageUtil messageUtil;

    public PostController() {
        newRecord = new PostEntity();
        selectedRecord = new PostEntity();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyPostDataModel(postService);
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedRecord = postService.find(id);

        if (selectedRecord == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        PostEntity post = postService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");

        String navigation = "/administrator/post/form?id=" + post.getId() + "&amp;faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        postService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDuplicateRecord(ActionEvent actionEvent) {
        PostEntity e = postService.create(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Duplicated");

        String navigation = "/administrator/post/form?faces-redirect=true&id=" + e.getId();
        RequestUtil.doNavigate(navigation);
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        postService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        if (selectedRecords.isEmpty()) {
            messageUtil.addGlobalErrorFlashMessage("Please select at least one item");
        } else {
            postService.deleteItems(selectedRecords);
            messageUtil.addGlobalInfoFlashMessage("Deleted");
        }
    }

    public PostEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(PostEntity newRecord) {
        this.newRecord = newRecord;
    }

    public PostEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(PostEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public LazyDataModel<PostEntity> getLazyModel() {
        return lazyModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PostEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<PostEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

}
