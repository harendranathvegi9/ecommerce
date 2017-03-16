package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.TicketEntity;
import com.aripd.ecommerce.entity.TicketmessageEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface TicketmessageService extends CrudService<TicketmessageEntity, Long> {

    public List<TicketmessageEntity> findByTicket(TicketEntity ticket);

    public void sendTicketmessage(TicketmessageEntity ticketmessage);

}
