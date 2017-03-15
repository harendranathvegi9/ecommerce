package com.aripd.util.currency;

import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.money.CurrencyUnit;
import javax.money.Monetary;

public final class CurrencyProvider {

    private static final CurrencyProvider INSTANCE = new CurrencyProvider();

    private final FacesContext facesContext;

    private CurrencyUnit currency;
    private final List<CurrencyUnit> currencies;

    private CurrencyProvider() {
        facesContext = FacesContext.getCurrentInstance();

        currency = (currency == null) ? Monetary.getCurrency(facesContext.getApplication().getDefaultLocale()) : Monetary.getCurrency(facesContext.getViewRoot().getLocale());

        currencies = new ArrayList<>();
        //currencies = Monetary.getCurrencies();

        Currency[] pcs = Currency.values();
        for (Currency pc : pcs) {
            CurrencyUnit cu = pc.create(pc.name());
            currencies.add(cu);
        }

    }

    public static CurrencyUnit getCurrency() {
        return INSTANCE.currency;
    }

    public static List<CurrencyUnit> getCurrencies() {
        return INSTANCE.currencies;
    }

}
