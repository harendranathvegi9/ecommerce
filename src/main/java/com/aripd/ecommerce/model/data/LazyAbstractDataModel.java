package com.aripd.ecommerce.model.data;

import com.aripd.ecommerce.entity.AbstractEntity;
import com.aripd.ecommerce.service.CrudService;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

public abstract class LazyAbstractDataModel<T extends AbstractEntity> extends LazyDataModel<T> implements Serializable {

    private final CrudService crudService;
    private List<T> datasource;
    private int pageSize;
    private int rowIndex;
    private int rowCount;

    /**
     *
     * @param crudService CrudService
     */
    public LazyAbstractDataModel(CrudService crudService) {
        this.crudService = crudService;
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
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        datasource = crudService.getResultList(first, pageSize, sortField, sortOrder, filters);
        setRowCount(crudService.count(filters));
        return datasource;
    }

    /**
     * Lazy loading list with multi-sorting ability
     *
     * @param first First Record
     * @param pageSize Page Size
     * @param multiSortMeta Multi Sort Meta
     * @param filters Filters
     * @return List
     */
    @Override
    public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        datasource = crudService.getResultList(first, pageSize, multiSortMeta, filters);
        setRowCount(crudService.count(filters));
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
    public Object getRowKey(T e) {
        return e.getId().toString();
    }

    /**
     * Returns object at the specified position in datasource.
     *
     * @return Entity
     */
    @Override
    public T getRowData() {
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
     * Returns object that has the row key.
     *
     * @param rowKey Row Key
     * @return Entity
     */
    @Override
    public T getRowData(String rowKey) {
        if (datasource == null) {
            return null;
        }
        for (T e : datasource) {
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
