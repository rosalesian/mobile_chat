package com.example.bobster.discount.model;

/**
 * Created by bobster on 12/30/2015.
 */
public class Customer
{
    private String id;
    private String net_id;
    private String reference_id;
    private String name;

    public Customer(String id, String net_id, String reference_id, String name) {
        this.id = id;
        this.net_id = net_id;
        this.reference_id = reference_id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNet_id() {
        return net_id;
    }

    public void setNet_id(String net_id) {
        this.net_id = net_id;
    }

    public String getReference_id() {
        return reference_id;
    }

    public void setReference_id(String reference_id) {
        this.reference_id = reference_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
