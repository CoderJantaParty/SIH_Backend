package com.sih.landacquisitionsystem.dto;

import lombok.*;
import com.sih.landacquisitionsystem.model.LandParcel.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LandParcelDTO {

    private Long id;
    private Long projectId; // Project ID
    private String surveyNumber;
    private Double area;
    private Double latitude;
    private Double longitude;
    private Status status;
}