package com.sih.landacquisitionsystem.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "compensations")
public class Compensation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parcel_id", nullable = false)
    private LandParcel parcel;

    @Column(nullable = false)
    private Double assessedAmount;

    @Column(nullable = false)
    private Double paidAmount;

    private LocalDate paidDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LandParcel getParcel() {
        return parcel;
    }

    public void setParcel(LandParcel parcel) {
        this.parcel = parcel;
    }

    public Double getAssessedAmount() {
        return assessedAmount;
    }

    public void setAssessedAmount(Double assessedAmount) {
        this.assessedAmount = assessedAmount;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        PENDING,
        PAID,
        PARTIALLY_PAID,
        DISPUTED
    }
}