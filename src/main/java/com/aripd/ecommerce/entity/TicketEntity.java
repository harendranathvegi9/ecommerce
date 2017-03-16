package com.aripd.ecommerce.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class TicketEntity extends AbstractEntity {

    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private UserEntity createdBy;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketType ticketType;

    @OneToMany(mappedBy = "ticket", orphanRemoval = true)
    private List<TicketmessageEntity> ticketmessages = new ArrayList<>();

    public TicketEntity() {
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public List<TicketmessageEntity> getTicketmessages() {
        return ticketmessages;
    }

    public void setTicketmessages(List<TicketmessageEntity> ticketmessages) {
        this.ticketmessages = ticketmessages;
    }

}
