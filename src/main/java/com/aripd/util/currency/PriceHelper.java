package com.aripd.util.currency;

import com.aripd.ecommerce.entity.PriceEntity;
import com.aripd.ecommerce.entity.ProductEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRateProvider;
import javax.money.convert.MonetaryConversions;

public class PriceHelper implements Serializable {

    public PriceHelper() {
    }

    private MonetaryAmount priceExchange(ProductEntity product, Integer quantity, String currencyCode) {
        List<PriceEntity> prices = product.getPrices();
        Collections.sort(prices, new Comparator<PriceEntity>() {

            @Override
            public int compare(PriceEntity o1, PriceEntity o2) {

                int rollno1 = o1.getQuantity();
                int rollno2 = o2.getQuantity();

                return rollno1 - rollno2;//For ascending order
                //return rollno2-rollno1;//For descending order
            }
        });

        PriceEntity actualPrice = Collections.min(prices, new Comparator<PriceEntity>() {

            @Override
            public int compare(PriceEntity o1, PriceEntity o2) {
                return o1.getQuantity().compareTo(o2.getQuantity());
            }

        });

        for (PriceEntity price : prices) {
            if (quantity >= price.getQuantity()) {
                actualPrice = price;
            }
        }

        CurrencyUnit coinTo = Monetary.getCurrency(currencyCode);
        MonetaryAmount monetaryAmount = Monetary.getDefaultAmountFactory()
                .setNumber(actualPrice.getAmount())
                .setCurrency(actualPrice.getCurrency())
                .create();

        ExchangeRateProvider exchangeRateProvider = MonetaryConversions.getExchangeRateProvider();

        CurrencyConversion currencyConversion = exchangeRateProvider.getCurrencyConversion(coinTo);
        //MonetaryAmount result = currencyConversion.apply(product.getPriceAsMonetaryAmount());
        MonetaryAmount result = currencyConversion.apply(monetaryAmount);
        return result;
    }

    public BigDecimal getPriceExchanged(ProductEntity product, Integer quantity, String currencyCode) {
        MonetaryAmount result = this.priceExchange(product, quantity, currencyCode);
        return result.getNumber().numberValue(BigDecimal.class);
    }

    public BigDecimal getTaxExchanged(ProductEntity product, Integer quantity, String currencyCode) {
        return this.getPriceExchanged(product, quantity, currencyCode).multiply(product.getTaxRate());
    }

    public BigDecimal getPriceTaxedExchanged(ProductEntity product, Integer quantity, String currencyCode) {
        return this.getPriceExchanged(product, quantity, currencyCode).multiply(product.getTaxRate().add(BigDecimal.ONE));
    }

}
