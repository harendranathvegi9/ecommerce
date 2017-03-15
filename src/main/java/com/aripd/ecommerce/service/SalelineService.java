package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.SalelineEntity;
import com.aripd.ecommerce.model.ReportFormModel;
import java.util.Map;
import javax.ejb.Local;

@Local
public interface SalelineService extends CrudService<SalelineEntity, Long> {

    public Map<String, Number> getSalesRevenueData(ReportFormModel reportFormModel);

    public Map<String, Number> getSalesVolumeData(ReportFormModel reportFormModel);

}
