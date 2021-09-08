package com.karthik.inventory.model.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name= "PRODUCT")
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @NotNull
    @Column(name = "productid")
    private int productid;

    @Column(name = "productname")
    private String productName;

    @Column(name = "upccode")
    @NotNull
    private String upcCode;

    @Column(name = "stockcount")
    @NotNull
    private Long stockCount;

    @Column(name = "storenumber")
    @NotNull
    private Long storeNumber;

    @Column(name = "price")
    @NotNull
    private Double price;

    public Product() {
    }

    public long getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUpcCode() {
        return upcCode;
    }

    public void setUpcCode(String upcCode) {
        this.upcCode = upcCode;
    }

    public Long getStockCount() {
        return stockCount;
    }

    public void setStockCount(Long stockCount) {
        this.stockCount = stockCount;
    }

    public Long getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(Long storeNumber) {
        this.storeNumber = storeNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productid=" + productid +
                ", productName='" + productName + '\'' +
                ", upcCode='" + upcCode + '\'' +
                ", stockCount=" + stockCount +
                ", storeNumber=" + storeNumber +
                ", price=" + price +
                '}';
    }
}
