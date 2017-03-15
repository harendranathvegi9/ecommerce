package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.BannerEntity;
import com.aripd.ecommerce.entity.BannerType;
import java.util.List;
import javax.ejb.Local;

@Local
public interface BannerService extends CrudService<BannerEntity, Long> {

    public List<BannerEntity> findAllByStatusTrueOrderBySortOrder();

    public List<BannerEntity> findAllByStatusTrueOrderBySortOrder(BannerType bannerType);

}
