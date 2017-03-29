package com.aripd.ecommerce.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

public class ReportFormModel implements Serializable {

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    public ReportFormModel() {
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

}
