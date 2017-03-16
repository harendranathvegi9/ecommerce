package com.aripd.ecommerce.view.administrator;

import com.aripd.ecommerce.entity.TicketEntity;
import com.aripd.ecommerce.model.data.LazyTicketDataModel;
import com.aripd.util.MessageUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import com.aripd.ecommerce.service.TicketService;

@Named
@ViewScoped
public class TicketController implements Serializable {

    @Inject
    private TicketService ticketService;
    private LazyDataModel<TicketEntity> lazyModel;

    @Inject
    MessageUtil messageUtil;

    public TicketController() {
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyTicketDataModel(ticketService);
    }

    public LazyDataModel<TicketEntity> getLazyModel() {
        return lazyModel;
    }

}
