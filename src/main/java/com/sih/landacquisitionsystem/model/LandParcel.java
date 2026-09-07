package com.sih.landacquisitionsystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "land_parcels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LandParcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String surveyNumber;

    @Column(nullable = false)
    private Double area; // in hectares or acres, as per requirement

    private Double latitude;  // for geo-tagging
    private Double longitude; // for geo-tagging

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        ACQUIRED,
        NOT_ACQUIRED,
        DISPUTED
    }
}