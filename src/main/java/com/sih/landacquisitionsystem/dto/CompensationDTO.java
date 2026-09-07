package com.sih.landacquisitionsystem.dto;

import com.sih.landacquisitionsystem.model.Compensation.Status;

public class CompensationDTO {

    private Long id;
    private Long parcelId; // LandParcel ID
    private Double assessedAmount;
    private Double paidAmount;
    private String paidDate; // We'll use String for simplicity, or we could use LocalDate if we want to keep the type
    private Status status;

    // Constructors
    public CompensationDTO() {}

    public CompensationDTO(Long id, Long parcelId, Double assessedAmount, Double paidAmount, String paidDate, Status status) {
        this.id = id;
        this.parcelId = parcelId;
        this.assessedAmount = assessedAmount;
        this.paidAmount = paidAmount;
        this.paidDate = paidDate;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
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

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}