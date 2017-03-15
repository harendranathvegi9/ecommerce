package com.aripd.ecommerce.model.data;

import com.aripd.ecommerce.entity.AddressEntity;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.service.AddressService;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

public class LazyAddressDataModelByUser extends LazyDataModel<AddressEntity> implements Serializable {

    private final AddressService addressService;
    private final UserEntity user;
    private List<AddressEntity> datasource;
    private int pageSize;
    private int rowIndex;
    private int rowCount;

    /**
     *
     * @param addressService AddressService
     * @param user UserEntity
     */
    public LazyAddressDataModelByUser(AddressService addressService, UserEntity user) {
        this.addressService = addressService;
        this.user = user;
    }

    /**
     * Lazy loading list with sorting ability
     *
     * @param first First Record
     * @param pageSize Page Size
     * @param sortField Sort Field
     * @param sortOrder Sort Order
     * @param filters Filters
     * @return List
     */
    @Override
    public List<AddressEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        datasource = addressService.getResultList(user, first, pageSize, sortField, sortOrder, filters);
        setRowCount(addressService.count(user, filters));
        return datasource;
    }

    /**
     * Lazy loading list with multi-sorting ability
     *
     * @param first First Record
     * @param pageSize Page Size
     * @param multiSortMeta Sort Meta
     * @param filters Filters
     * @return List
     */
    @Override
    public List<AddressEntity> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        datasource = addressService.getResultList(user, first, pageSize, multiSortMeta, filters);
        setRowCount(addressService.count(user, filters));
        return datasource;
    }

    /**
     * Checks if the row is available
     *
     * @return boolean
     */
    @Override
    public boolean isRowAvailable() {
        if (datasource == null) {
            return false;
        }
        int index = rowIndex % pageSize;
        return index >= 0 && index < datasource.size();
    }

    /**
     * Gets object's primary key
     *
     * @param e Entity
     * @return Object
     */
    @Override
    public Object getRowKey(AddressEntity e) {
        return e.getId().toString();
    }

    /**
     * Returns the object at the specified position in datasource.
     *
     * @return Entity
     */
    @Override
    public AddressEntity getRowData() {
        if (datasource == null) {
            return null;
        }
        int index = rowIndex % pageSize;
        if (index > datasource.size()) {
            return null;
        }
        return datasource.get(index);
    }

    /**
     * Returns the object that has the row key.
     *
     * @param rowKey Row Key
     * @return Entity
     */
    @Override
    public AddressEntity getRowData(String rowKey) {
        if (datasource == null) {
            return null;
        }
        for (AddressEntity e : datasource) {
            if (e.getId().toString().equals(rowKey)) {
                return e;
            }
        }
        return null;
    }

    /*
     * ===== Getters and Setters of fields
     */
    /**
     *
     * @param pageSize Page Size
     */
    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Returns page size
     *
     * @return int
     */
    @Override
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Returns current row index
     *
     * @return int
     */
    @Override
    public int getRowIndex() {
        return this.rowIndex;
    }

    /**
     * Sets row index
     *
     * @param rowIndex Row Index
     */
    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    /**
     * Sets row count
     *
     * @param rowCount Row Count
     */
    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * Returns row count
     *
     * @return int
     */
    @Override
    public int getRowCount() {
        return this.rowCount;
    }

}
