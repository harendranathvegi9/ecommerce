package com.aripd.ecommerce.entity;

public enum SalelineStatus {

    CANCELLED,//cancelled by member
    WAITING_FOR_PAYMENT,//ödeme bekleniyor
    SENT_FOR_PROVISION,//kart ödemesine provizyon bekleniyor
    PREPARING,
    SENT,
    PAYMENT_AUTHORIZED,//kart ödemesine provizyon verildi
    PAYMENT_RECEIVED,//havaleyle yapılan ödeme alındı
    TEST,//test siparişi
    CASH,//nakit ödemeli sipariş
    COMPLETE,//tamamlanan sipariş (provizyon verilmiş/teslim edilmiş)
    REVERSED,//iptal edilen sipariş (geri çevrilen sipariş, reject)
    REFUND//ödemesi iade edilen sipariş (iade edilen ödeme)
}
