package com.billbucket;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Payment extends PanacheEntity {
    public BigDecimal amount;
}