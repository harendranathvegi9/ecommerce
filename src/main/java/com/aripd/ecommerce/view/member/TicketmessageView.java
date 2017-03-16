package com.aripd.ecommerce.view.member;

import com.aripd.ecommerce.entity.TicketEntity;
import com.aripd.ecommerce.entity.TicketType;
import com.aripd.ecommerce.entity.TicketmessageEntity;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.service.TicketService;
import com.aripd.ecommerce.service.UserService;
import com.aripd.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.aripd.ecommerce.service.TicketmessageService;
import java.util.Arrays;

@Named
@ViewScoped
public class TicketmessageView implements Serializable {

    @Inject
    private TicketmessageService ticketmessageService;
    private TicketmessageEntity newRecord;
    private List<TicketmessageEntity> ticketmessages;

    private Long id;

    @Inject
    private TicketService ticketService;
    private TicketEntity selectedTicket;

    @Inject
    private UserService userService;
    private UserEntity user;

    @Inject
    MessageUtil messageUtil;

    public TicketmessageView() {
        newRecord = new TicketmessageEntity();
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedTicket = ticketService.find(id);

        ticketmessages = ticketmessageService.findByTicket(selectedTicket);

    }

    public List<TicketType> getTicketTypes() {
        return Arrays.asList(TicketType.values());
    }

    public void doReplyRecord(ActionEvent actionEvent) {
        newRecord.setCreatedBy(user);
        newRecord.setTicket(selectedTicket);
        ticketmessageService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Replied");

        newRecord = new TicketmessageEntity();
    }

    public TicketmessageEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(TicketmessageEntity newRecord) {
        this.newRecord = newRecord;
    }

    public List<TicketmessageEntity> getTicketmessages() {
        return ticketmessages;
    }

    public void setTicketmessages(List<TicketmessageEntity> ticketmessages) {
        this.ticketmessages = ticketmessages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TicketEntity getSelectedTicket() {
        return selectedTicket;
    }

    public void setSelectedTicket(TicketEntity selectedTicket) {
        this.selectedTicket = selectedTicket;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
