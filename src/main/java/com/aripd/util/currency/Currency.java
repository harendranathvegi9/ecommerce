package com.aripd.util.currency;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

public enum Currency {

    TRY {
        @Override
        public CurrencyUnit create(String currencyCode) {
            return Monetary.getCurrency(currencyCode);
        }
    },
    EUR {
        @Override
        public CurrencyUnit create(String currencyCode) {
            return Monetary.getCurrency(currencyCode);
        }
    },
    USD {
        @Override
        public CurrencyUnit create(String currencyCode) {
            return Monetary.getCurrency(currencyCode);
        }
    },
    GBP {
        @Override
        public CurrencyUnit create(String currencyCode) {
            return Monetary.getCurrency(currencyCode);
        }
    };

    public abstract CurrencyUnit create(String currencyCode);

}
