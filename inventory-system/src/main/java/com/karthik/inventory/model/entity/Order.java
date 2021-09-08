package com.karthik.inventory.model.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name= "ORDER_DETAILS")
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @Column(name = "id")
    @NotNull
    private int id;

    @Column(name = "order_id")
    @NotNull
    private String orderId;

    @Column(name = "product_id")
    @NotNull
    private int productId;

    @Column(name = "quanity")
    @NotNull
    private Integer quantity;

    @Column(name = "amout_txn")
    @NotNull
    private Double amountTxn;

    @Column(name = "date_created")
    @NotNull
    private Date dateCreated;

    @Column(name = "order_status")
    @NotNull
    private String orderstatus;

    @Column(name = "terminal_id")
    @NotNull
    private long terminalId;

    @Column(name = "store_id")
    @NotNull
    private long storeId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmountTxn() {
        return amountTxn;
    }

    public void setAmount_Txn(Double amountTxn) {
        this.amountTxn = amountTxn;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(long terminalId) {
        this.terminalId = terminalId;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", quantity='" + quantity + '\'' +
                ", amountTxn=" + amountTxn +
                ", dateCreated=" + dateCreated +
                ", orderstatus=" + orderstatus +
                ", terminalId=" + terminalId +
                ", storeId=" + storeId +
                '}';
    }
}
