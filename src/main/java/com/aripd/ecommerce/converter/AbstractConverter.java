package com.aripd.ecommerce.converter;

import com.aripd.ecommerce.entity.AbstractEntity;
import java.util.HashMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * @param <T> Entity
 */
public abstract class AbstractConverter<T extends AbstractEntity> implements Converter {

    private final HashMap<String, T> map = new HashMap<>();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return map.get(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value.equals("")) {
            return "";
        }
        T e = (T) value;
        map.put(e.getId().toString(), e);
        return e.getId().toString();
    }
}
