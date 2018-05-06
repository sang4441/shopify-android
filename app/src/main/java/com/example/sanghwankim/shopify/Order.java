package com.example.sanghwankim.shopify;

/**
 * Created by kimsanghwan on 2014-12-29.
 */
public class Order {

    private long id;
    private String createdAt;
    private String province;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}


//
//{
//        "id":5324014979,
//        "email":"napoleon.batz@gmail.com",
//        "closed_at":null,
//        "created_at":"2017-07-11T14:05:00-04:00",
//        "updated_at":"2017-07-11T14:05:00-04:00",
//        "number":141,
//        "note":null,
//        "token":"4d25a87bdc091c93647e7a5e0eb94796",
//        "gateway":"manual",
//        "test":false,
//        "total_price":"116.20",
//        "subtotal_price":"116.20",
//        "total_weight":24709,
//        "total_tax":"0.00",
//        "taxes_included":false,
//        "currency":"CAD",
//        "financial_status":"paid",
//        "confirmed":true,
//        "total_discounts":"0.00",
//        "total_line_items_price":"116.20",
//        "cart_token":null,
//        "buyer_accepts_marketing":false,
//        "name":"#1141",
//        "referring_site":null,
//        "landing_site":null,
//        "cancelled_at":null,
//        "cancel_reason":null,
//        "total_price_usd":"90.12",
//        "checkout_token":null,
//        "reference":null,
//        "user_id":41793859,
//        "location_id":null,
//        "source_identifier":null,
//        "source_url":null,
//        "processed_at":"2017-07-11T14:05:00-04:00",
//        "device_id":null,
//        "phone":null,
//        "customer_locale":null,
//        "app_id":1354745,
//        "browser_ip":null,
//        "landing_site_ref":null,
//        "order_number":1141,
//        "discount_codes":[],
//        "note_attributes":[],
//        "payment_gateway_names":["manual"],
//        "processing_method":"manual",
//        "checkout_id":null,
//        "source_name":"shopify_draft_order",
//        "fulfillment_status":null,
//        "tax_lines":[],
//        "tags":"",
//        "contact_email":"napoleon.batz@gmail.com",
//        "order_status_url":"https:\/\/checkout.shopify.com\/10007970\/orders\/4d25a87bdc091c93647e7a5e0eb94796\/authenticate?key=3c54a5952e4e237a2a78d1db63b0fe60",
//        "line_items":[{"id":10381885123,"variant_id":8041741251,"title":"Aerodynamic Concrete Clock","quantity":1,"price":"76.67","sku":"","variant_title":"Violet","vendor":"Jenkins, Orn and Blick","fulfillment_service":"manual","product_id":2759137027,"requires_shipping":true,"taxable":true,"gift_card":false,"name":"Aerodynamic Concrete Clock - Violet","variant_inventory_management":null,"properties":[],"product_exists":true,"fulfillable_quantity":1,"grams":8081,"total_discount":"0.00","fulfillment_status":null,"tax_lines":[]}
//                        {"id":10381885187,"variant_id":8041766147,"title":"Aerodynamic Granite Plate","quantity":1,"price":"19.45","sku":"","variant_title":"Azure","vendor":"Schneider and Sons","fulfillment_service":"manual","product_id":2759143811,"requires_shipping":true,"taxable":true,"gift_card":false,"name":"Aerodynamic Granite Plate - Azure","variant_inventory_management":null,"properties":[],"product_exists":true,"fulfillable_quantity":1,"grams":7304,"total_discount":"0.00","fulfillment_status":null,"tax_lines":[]},
//                        {"id":10381885251,"variant_id":8041766083,"title":"Aerodynamic Granite Plate","quantity":1,"price":"20.08","sku":"","variant_title":"Ivory","vendor":"Schneider and Sons","fulfillment_service":"manual","product_id":2759143811,"requires_shipping":true,"taxable":true,"gift_card":false,"name":"Aerodynamic Granite Plate - Ivory","variant_inventory_management":null,"properties":[],"product_exists":true,"fulfillable_quantity":1,"grams":9324,"total_discount":"0.00","fulfillment_status":null,"tax_lines":[]}],
//        "shipping_lines":[],
//        "billing_address":{"first_name":"Napoleon","address1":"464 Dixie Mission","phone":"(573) 502-6949 x722","city":"Bessemer","zip":"35023","province":"Alabama","country":"United States","last_name":"Batz","address2":null,"company":null,"latitude":33.4704596,"longitude":-87.0424393,"name":"Napoleon Batz","country_code":"US","province_code":"AL"},
//        "shipping_address":{"first_name":"Napoleon","address1":"464 Dixie Mission","phone":"(573) 502-6949 x722","city":"Bessemer","zip":"35023","province":"Alabama","country":"United States","last_name":"Batz","address2":null,"company":null,"latitude":33.4704596,"longitude":-87.0424393,"name":"Napoleon Batz","country_code":"US","province_code":"AL"},
//        "fulfillments":[],
//        "refunds":[],
//        "customer":{"id":4953626051,"email":"napoleon.batz@gmail.com","accepts_marketing":false,"created_at":"2016-12-05T23:16:40-05:00","updated_at":"2017-07-11T14:05:00-04:00","first_name":"Napoleon","last_name":"Batz","orders_count":3,"state":"disabled","total_spent":"185.44","last_order_id":5324014979,"note":null,"verified_email":true,"multipass_identifier":null,"tax_exempt":false,"phone":null,"tags":"","last_order_name":"#1141","default_address":{"id":5056822019,"customer_id":4953626051,"first_name":"Napoleon","last_name":"Batz","company":null,"address1":"464 Dixie Mission","address2":null,"city":"Bessemer","province":"Alabama","country":"United States","zip":"35023","phone":"(573) 502-6949 x722","name":"Napoleon Batz","province_code":"AL","country_code":"US","country_name":"United States","default":true}}},