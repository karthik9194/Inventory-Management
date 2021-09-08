package com.karthik.inventory.model.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name= "CASHIER_TERMINAL")
@EntityListeners(AuditingEntityListener.class)
public class Cashier {

    @Id
    @NotNull
    @Column(name = "terminalid")
    private int cashierId;

    @Column(name = "cashier")
    @NotNull
    private String cashier;

    @Column(name = "activeindicator")
    @NotNull
    private Boolean activeIndicator;

    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public Boolean getActiveIndicator() {
        return activeIndicator;
    }

    public void setActiveIndicator(Boolean activeIndicator) {
        this.activeIndicator = activeIndicator;
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "cashierId=" + cashierId +
                ", cashier='" + cashier + '\'' +
                ", activeIndicator=" + activeIndicator +
                '}';
    }
}
