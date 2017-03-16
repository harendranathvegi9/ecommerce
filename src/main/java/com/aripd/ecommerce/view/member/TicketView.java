package com.aripd.ecommerce.view.member;

import com.aripd.ecommerce.entity.TicketEntity;
import com.aripd.ecommerce.entity.TicketType;
import com.aripd.ecommerce.entity.TicketmessageEntity;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.model.data.LazyTicketDataModelByUser;
import com.aripd.ecommerce.service.UserService;
import com.aripd.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import com.aripd.ecommerce.service.TicketService;
import com.aripd.ecommerce.service.TicketmessageService;
import com.aripd.util.RequestUtil;
import java.util.Arrays;

@Named
@ViewScoped
public class TicketView implements Serializable {

    @Inject
    private TicketService ticketService;
    private LazyDataModel<TicketEntity> lazyModel;

    private TicketType ticketType;
    private String content;

    @Inject
    private UserService userService;
    private UserEntity user;

    @Inject
    private TicketmessageService ticketmessageService;

    @Inject
    MessageUtil messageUtil;

    public TicketView() {
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();
        lazyModel = new LazyTicketDataModelByUser(ticketService, user);
    }

    public List<TicketType> getTicketTypes() {
        return Arrays.asList(TicketType.values());
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        TicketEntity newRecord = new TicketEntity();
        newRecord.setCreatedBy(user);
        newRecord.setTicketType(ticketType);
        TicketEntity ticket = ticketService.create(newRecord);

        TicketmessageEntity ticketmessage = new TicketmessageEntity();
        ticketmessage.setContent(content);
        ticketmessage.setCreatedBy(user);
        ticketmessage.setTicket(ticket);
        ticketmessageService.create(ticketmessage);
//        ticketmessageService.sendTicketmessage(ticketmessage);
        messageUtil.addGlobalInfoFlashMessage("Created");

        String navigation = "/member/ticket/show.xhtml?id=" + ticket.getId() + "&amp;faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LazyDataModel<TicketEntity> getLazyModel() {
        return lazyModel;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
