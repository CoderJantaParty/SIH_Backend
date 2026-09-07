package com.sih.landacquisitionsystem.dto;

import lombok.*;
import com.sih.landacquisitionsystem.model.Project.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {

    private Long id;
    private String title;
    private String ministry;
    private String state;
    private String district;
    private Status status;
    private Long createdBy; // User ID
    // createdAt is not typically needed in DTO for creation, but can be included if needed
}