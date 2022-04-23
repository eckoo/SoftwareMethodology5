package com.application.rucafe.Model;

/**
 * This Model class provides the functionality of Model of Order.
 * Every Thing of Order.
 * Like name, type, flavour, quantity, price and sizes of Coffee & Donut
 * @author Kiernan King and Ahmed Alghazwi
 *
 */
public class OrderModel {

    /**
     * Creates name Object of type String.
     * Creates type Object of type String.
     * Creates flavour Object of type String.
     * Creates qty Object of type String.
     * Creates price Object of type String.
     * Creates size Object of type String.
     */
    String name,type,flavour,qty,price,size;

    /**
     * OrderModel constructor method.
     * @param name Object of type String.
     * @param type Object of type String.
     * @param flavour Object of type String.
     * @param qty Object of type String.
     * @param price Object of type String.
     * @param size Object of type String.
     */
    public OrderModel(String name, String type, String flavour, String qty, String price, String size) {
        this.name = name;
        this.type = type;
        this.flavour = flavour;
        this.qty = qty;
        this.price = price;
        this.size = size;
    }

    /**
     * OrderModel
     */
    public OrderModel() {
    }

    /**
     * getName constructor method.
     * @return string representation of name.
     */
    public String getName() {
        return name;
    }

    /**
     * setName constructor method.
     * @param name Object of type String.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getSize constructor method.
     * @return string representation of size.
     */
    public String getSize() {
        return size;
    }

    /**
     * setSize constructor method.
     * @param size Object of type String.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * getType constructor method.
     * @return string representation of type.
     */
    public String getType() {
        return type;
    }

    /**
     * setType constructor method.
     * @param type Object of type String.
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * getFlavour constructor method.
     * @return string representation of flavour.
     */
    public String getFlavour() {
        return flavour;
    }

    /**
     * setFlavour constructor method.
     * @param flavour Object of type String.
     */
    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    /**
     * getQty constructor method.
     * @return string representation of qty.
     */
    public String getQty() {
        return qty;
    }

    /**
     * setQty constructor method.
     * @param qty Object of type String.
     */
    public void setQty(String qty) {
        this.qty = qty;
    }

    /**
     * getPrice constructor method.
     * @return string representation of price.
     */
    public String getPrice() {
        return price;
    }

    /**
     * setPrice constructor method.
     * @param price Object of type String.
     */
    public void setPrice(String price) {
        this.price = price;
    }
}
