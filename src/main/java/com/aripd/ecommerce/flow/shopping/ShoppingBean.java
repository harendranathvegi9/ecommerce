package com.aripd.ecommerce.flow.shopping;

import com.aripd.ecommerce.service.UserService;
import com.aripd.ecommerce.entity.AddressEntity;
import com.aripd.ecommerce.entity.BasketitemEntity;
import com.aripd.ecommerce.entity.SaleEntity;
import com.aripd.ecommerce.entity.ProductEntity;
import com.aripd.ecommerce.entity.SalelineEntity;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.service.BasketitemService;
import com.aripd.util.MessageUtil;
import com.aripd.ecommerce.entity.SaleStatus;
import com.aripd.ecommerce.entity.PaymentMethod;
import com.aripd.ecommerce.entity.SalelineStatus;
import com.aripd.ecommerce.service.AddressService;
import com.aripd.util.RequestUtil;
import com.aripd.util.currency.PriceHelper;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.faces.event.ActionEvent;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.collections4.Predicate;
import com.aripd.ecommerce.service.SaleService;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import org.apache.commons.collections4.IterableUtils;

@Named
@FlowScoped("shopping")
public class ShoppingBean implements Serializable {

    @Inject
    private UserService userService;
    private UserEntity user;
    private List<BasketitemEntity> basketitems = new ArrayList<>();
    private BasketitemEntity selectedBasketitem;
    private ProductEntity selectedProduct;

    @Inject
    private AddressService addressService;
    private AddressEntity deliveryAddress;
    private AddressEntity billAddress;

    private PaymentMethod paymentMethod;

    @Inject
    private SaleService saleService;

    private BigDecimal amount;

    @Inject
    private BasketitemService basketitemService;

    private String orderRef;
    private String orderDate;

    @Inject
    PriceHelper priceHelper;

    @Inject
    MessageUtil messageUtil;

    public ShoppingBean() {
    }

    @PostConstruct
    public void init() {
    }

    public void onLoad() {
        user = userService.getCurrentUser();
        if (user != null) {
            basketitems = basketitemService.findAllByUser(user);
        }
    }

    public List<AddressEntity> getAddresses() {
        return addressService.findByUser(user);
    }

    public List<PaymentMethod> getPaymentMethods() {
        return Arrays.asList(PaymentMethod.values());
    }

    public String goToHome() {
        return "/index?faces-redirect=true";
    }

    public BigDecimal getPriceExchanged(ProductEntity product, Integer quantity, String currencyCode) {
        return priceHelper.getPriceExchanged(product, quantity, currencyCode);
    }

    public BigDecimal getTaxExchanged(ProductEntity product, Integer quantity, String currencyCode) {
        return priceHelper.getTaxExchanged(product, quantity, currencyCode);
    }

    public BigDecimal getDiscountExchanged(ProductEntity product, String currencyCode) {
        BasketitemEntity entity = (BasketitemEntity) IterableUtils.find(basketitems, new Predicate() {

            @Override
            public boolean evaluate(Object o) {
                BasketitemEntity element = (BasketitemEntity) o;
                return element.getProduct().equals(product);
            }

        });

        Integer quantity = entity.getQuantity();

        BigDecimal priceTaxed = priceHelper.getPriceTaxedExchanged(product, quantity, currencyCode);
        return priceTaxed;
    }

    public BigDecimal getAmountExchanged(ProductEntity product, String currencyCode) {
        BasketitemEntity entity = (BasketitemEntity) IterableUtils.find(basketitems, new Predicate() {

            @Override
            public boolean evaluate(Object o) {
                BasketitemEntity element = (BasketitemEntity) o;
                return element.getProduct().equals(product);
            }

        });

        Integer quantity = entity.getQuantity();

        BigDecimal priceTaxed = priceHelper.getPriceTaxedExchanged(product, quantity, currencyCode);
        return priceTaxed.multiply(new BigDecimal(quantity));
    }

    public BigDecimal doCalculateTaxSubtotal(ProductEntity product, String currencyCode) {
        BasketitemEntity entity = (BasketitemEntity) IterableUtils.find(basketitems, new Predicate() {

            @Override
            public boolean evaluate(Object o) {
                BasketitemEntity element = (BasketitemEntity) o;
                return element.getProduct().equals(product);
            }

        });

        Integer quantity = entity.getQuantity();

        return priceHelper.getTaxExchanged(product, quantity, currencyCode).multiply(new BigDecimal(quantity));
    }

    public BigDecimal doCalculateDiscountSubtotal(ProductEntity product, String currencyCode) {
        BasketitemEntity entity = (BasketitemEntity) IterableUtils.find(basketitems, new Predicate() {

            @Override
            public boolean evaluate(Object o) {
                BasketitemEntity element = (BasketitemEntity) o;
                return element.getProduct().equals(product);
            }

        });

        Integer quantity = entity.getQuantity();

        BigDecimal priceTaxed = priceHelper.getPriceTaxedExchanged(product, quantity, currencyCode).multiply(new BigDecimal(quantity));
        return priceTaxed;
    }

    public BigDecimal doCalculateTaxTotal(String currencyCode) {
        BigDecimal total = BigDecimal.ZERO;
        for (BasketitemEntity entry : basketitems) {
            ProductEntity p = entry.getProduct();
            Integer q = entry.getQuantity();
            total = total.add(priceHelper.getTaxExchanged(p, q, currencyCode).multiply(new BigDecimal(q)));
        }
        return total;
    }

    public BigDecimal doCalculateBasketTotal(String currencyCode) {
        BigDecimal total = BigDecimal.ZERO;
        for (BasketitemEntity entry : basketitems) {
            ProductEntity p = entry.getProduct();
            Integer q = entry.getQuantity();
            total = total.add(priceHelper.getPriceExchanged(p, q, currencyCode).multiply(new BigDecimal(q)));
        }
        return total;
    }

    public BigDecimal doCalculateGeneralTotal(String currencyCode) {
        return doCalculateBasketTotal(currencyCode).add(doCalculateTaxTotal(currencyCode));
    }

    public void ajaxUpdateBasketitem() {
        for (BasketitemEntity basketitem : basketitems) {
            basketitemService.update(basketitem);
        }
        user.setBasketitems(basketitems);
    }

    public void doUpdateBasketitem(ActionEvent actionEvent) {
        basketitemService.update(selectedBasketitem);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public String removeBasketitem() {
        basketitemService.deleteByUserAndProduct(user, selectedProduct);
        messageUtil.addGlobalInfoFlashMessage("Removed");
        return "shopping";
    }

    private static String getOrderNumber() {
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
        f.setTimeZone(TimeZone.getTimeZone("UTC"));
        return (f.format(new Date()));
    }

    private static String getDate() {
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            f.setTimeZone(TimeZone.getTimeZone("UTC"));
            return f.format(new Date());
        } catch (Exception ex) {
            throw new FacesException(ex);
        }
    }

    public void doProcessOrderForIyzipay(ActionEvent actionEvent) {
        doCreateSale(orderRef, orderDate, PaymentMethod.CCVISAMC);
    }

    public void doProcessOrderForPayPal(ActionEvent actionEvent) {
        doCreateSale(orderRef, orderDate, PaymentMethod.CCVISAMC);
    }

    public void doProcessOrderForPayU(ActionEvent actionEvent) {
        doCreateSale(orderRef, orderDate, PaymentMethod.CCVISAMC);
    }

    public void doProcessOrderForMT(ActionEvent actionEvent) {
        orderRef = getOrderNumber();
        orderDate = getDate();
        SaleEntity sale = doCreateSale(orderRef, orderDate, PaymentMethod.WIRE);

        saleService.sendToAdministrator(sale);
        saleService.sendToMember(sale);
        messageUtil.addGlobalInfoFlashMessage("Order information has been sent to {0}", new Object[]{sale.getCreatedBy().getEmail()});

        String navigation = "/sale.xhtml?orderRef=" + sale.getREFNOEXT() + "&amp;faces-redirect=true";
        RequestUtil.doNavigate(navigation);
    }

    public String doProcessPayment() {
        switch (paymentMethod) {
            case WIRE:
            default:
                return "shopping_wire";
            case Payment_On_Delivery:
                return "shopping_pod";
            case CCVISAMC:
                return "shopping_0062";
        }
    }

    private SaleEntity doCreateSale(String orderRef, String orderDate, PaymentMethod paymentMethod) {
        SaleEntity e = new SaleEntity();
        e.setCreatedBy(user);

        e.setDeliveryFirstname(deliveryAddress.getFirstname());
        e.setDeliveryLastname(deliveryAddress.getLastname());
        e.setDeliveryLine(deliveryAddress.getLine());
        e.setDeliveryCity(deliveryAddress.getCity());
        e.setDeliveryZipcode(deliveryAddress.getZipcode());
        e.setDeliveryCountry(deliveryAddress.getCountry());
        e.setDeliveryPhone(deliveryAddress.getPhone());

        e.setBillFirstname(billAddress.getFirstname());
        e.setBillLastname(billAddress.getLastname());
        e.setBillLine(billAddress.getLine());
        e.setBillCity(billAddress.getCity());
        e.setBillZipcode(billAddress.getZipcode());
        e.setBillCountry(billAddress.getCountry());
        e.setBillPhone(billAddress.getPhone());

        List<SalelineEntity> salelines = new ArrayList<>();
        for (BasketitemEntity entry : basketitems) {
            ProductEntity basketProduct = entry.getProduct();
            Integer basketAmount = entry.getQuantity();
            String basketNote = entry.getNote();

            SalelineEntity saleline = new SalelineEntity();
            saleline.setSale(e);
            saleline.setProduct(basketProduct);
            saleline.setSalelineStatus(SalelineStatus.WAITING_FOR_PAYMENT);
            saleline.setNote(basketNote);
            saleline.setIPN_PID(String.valueOf(basketProduct.getId()));
            saleline.setIPN_PNAME(basketProduct.getName());
            saleline.setIPN_PCODE(basketProduct.getCode());
            saleline.setIPN_INFO(basketProduct.getDescription());
            saleline.setIPN_QTY(basketAmount);
            saleline.setIPN_PRICE(priceHelper.getPriceExchanged(basketProduct, basketAmount, "TRY"));
            saleline.setIPN_VAT(priceHelper.getTaxExchanged(basketProduct, basketAmount, "TRY"));
            saleline.setIPN_VER("");
            saleline.setIPN_PROMONAME("");
            saleline.setIPN_DELIVEREDCODES("");
            saleline.setIPN_TOTAL(saleline.getIPN_PRICE().add(saleline.getIPN_VAT()));
            saleline.setIPN_DISCOUNT(BigDecimal.ZERO);

            salelines.add(saleline);
        }
        e.setSalelines(salelines);

        e.setSALEDATE(orderDate);
        e.setPAYMENTDATE("");
        e.setCOMPLETE_DATE("");
        e.setREFNO("");
        e.setREFNOEXT(orderRef);
        e.setORDERNO("");
        e.setSaleStatus(SaleStatus.WAITING_FOR_PAYMENT);
        e.setPAYMETHOD("");
        e.setPAYMETHOD_CODE(paymentMethod);

        e.setIPN_PAID_AMOUNT(BigDecimal.ZERO);
        e.setIPN_INSTALLMENTS_PROGRAM("");
        e.setIPN_INSTALLMENTS_NUMBER("");
        e.setIPN_INSTALLMENTS_PROFIT(BigDecimal.ZERO);

        e.setCurrency("TRY");
        e.setIPN_COMMISSION(BigDecimal.ZERO);
        e.setIPN_SHIPPING(BigDecimal.ZERO);
        e.setIPN_TOTALGENERAL(e.getPriceTotalAfterTax().add(e.getIPN_COMMISSION().add(e.getIPN_SHIPPING())));
        e.setIPN_GLOBALDISCOUNT(BigDecimal.ZERO);
        e.setIPN_DATE("");
        e.setHASH("");

        SaleEntity sale = saleService.create(e);

        basketitemService.deleteByUser(user);

        return sale;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public ProductEntity getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductEntity selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public AddressEntity getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(AddressEntity deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public AddressEntity getBillAddress() {
        return billAddress;
    }

    public void setBillAddress(AddressEntity billAddress) {
        this.billAddress = billAddress;
    }

    public List<BasketitemEntity> getBasketitems() {
        return basketitems;
    }

    public void setBasketitems(List<BasketitemEntity> basketitems) {
        this.basketitems = basketitems;
    }

    public BasketitemEntity getSelectedBasketitem() {
        return selectedBasketitem;
    }

    public void setSelectedBasketitem(BasketitemEntity selectedBasketitem) {
        this.selectedBasketitem = selectedBasketitem;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}
