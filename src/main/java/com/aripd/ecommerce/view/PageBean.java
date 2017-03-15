package com.aripd.ecommerce.view;

import com.aripd.ecommerce.service.PageService;
import com.aripd.ecommerce.entity.PageEntity;
import com.aripd.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class PageBean implements Serializable {

    @Inject
    private PageService pageService;
    private PageEntity selectedRecord;

    private Long id;

    @Inject
    MessageUtil messageUtil;

    public PageBean() {
    }

    @PostConstruct
    public void init() {
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

    public List<PageEntity> getPages() {
        return pageService.findAll();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PageEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(PageEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

}
