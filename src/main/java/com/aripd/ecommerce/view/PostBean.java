package com.aripd.ecommerce.view;

import com.aripd.ecommerce.service.PostService;
import com.aripd.ecommerce.entity.PostEntity;
import com.aripd.ecommerce.model.data.LazyPostDataModel;
import com.aripd.util.MessageUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@ViewScoped
public class PostBean implements Serializable {

    @Inject
    private PostService postService;
    private PostEntity selectedRecord;
    private LazyDataModel<PostEntity> lazyModel;

    private Long id;

    @Inject
    MessageUtil messageUtil;

    public PostBean() {
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyPostDataModel(postService);
    }

    public void onLoad() {
        if (id == null) {
            selectedRecord = postService.findOneByLatest();
        } else {
            selectedRecord = postService.find(id);
        }

        if (selectedRecord == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
