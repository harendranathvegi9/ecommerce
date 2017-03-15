package com.aripd.util.currency;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.money.CurrencyUnit;

@Named
@SessionScoped
public class CurrencyBean implements Serializable {

    private CurrencyUnit currency;
    private List<CurrencyUnit> currencies;

    public CurrencyBean() {
        currencies = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        currency = CurrencyProvider.getCurrency();
        currencies = CurrencyProvider.getCurrencies();
    }

    public void onChange(AjaxBehaviorEvent abe) throws IOException {
        CurrencyUnit newCurrency = (CurrencyUnit) ((UIOutput) abe.getSource()).getValue();
        for (CurrencyUnit c : currencies) {
            if (c.equals(newCurrency)) {
                this.doChange(c);
                break;
            }
        }
    }

    public String onSet() {
        this.doChange(currency);

        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?includeViewParams=true&faces-redirect=true";
    }

    public void doChange(CurrencyUnit newCurrency) {
        currency = newCurrency;
    }

    public CurrencyUnit getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyUnit currency) {
        this.currency = currency;
    }

    public List<CurrencyUnit> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyUnit> currencies) {
        this.currencies = currencies;
    }

}
