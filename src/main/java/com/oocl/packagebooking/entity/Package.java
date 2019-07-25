package com.oocl.packagebooking.entity;

import com.oocl.packagebooking.enums.PackageEnum;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
public class Package {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @NotNull
    @Column(unique = true)
    private String no;

    @NotNull
    @Column
    private String customerName;

    @NotNull
    @Column
    private String phone;

    @Column
    @Enumerated(EnumType.STRING)
    private PackageEnum status = PackageEnum.PENDING;

    @Column
    private Instant reserveTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PackageEnum getStatus() {
        return status;
    }

    public void setStatus(PackageEnum status) {
        this.status = status;
    }

    public Instant getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Instant reserveTime) {
        this.reserveTime = reserveTime;
    }

    public Package() {
    }
}
