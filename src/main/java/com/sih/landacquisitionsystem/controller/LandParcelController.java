package com.sih.landacquisitionsystem.controller;

import com.sih.landacquisitionsystem.dto.LandParcelDTO;
import com.sih.landacquisitionsystem.service.LandParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parcels")
public class LandParcelController {

    @Autowired
    private LandParcelService landParcelService;

    @PostMapping
    @PreAuthorize("hasRole('MINISTRY_ADMIN') or hasRole('STATE_OFFICER') or hasRole('DISTRICT_OFFICER')")
    public ResponseEntity<LandParcelDTO> createParcel(@RequestBody LandParcelDTO landParcelDTO) {
        LandParcelDTO createdParcel = landParcelService.createParcel(landParcelDTO);
        return ResponseEntity.ok(createdParcel);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<LandParcelDTO> getParcelById(@PathVariable Long id) {
        LandParcelDTO parcelDTO = landParcelService.getParcelById(id);
        return ResponseEntity.ok(parcelDTO);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<LandParcelDTO>> getAllParcels() {
        List<LandParcelDTO> parcels = landParcelService.getAllParcels();
        return ResponseEntity.ok(parcels);
    }

    @GetMapping("/project/{projectId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<LandParcelDTO>> getParcelsByProjectId(@PathVariable Long projectId) {
        List<LandParcelDTO> parcels = landParcelService.getParcelsByProjectId(projectId);
        return ResponseEntity.ok(parcels);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MINISTRY_ADMIN') or hasRole('STATE_OFFICER') or hasRole('DISTRICT_OFFICER')")
    public ResponseEntity<LandParcelDTO> updateParcel(@PathVariable Long id, @RequestBody LandParcelDTO landParcelDTO) {
        LandParcelDTO updatedParcel = landParcelService.updateParcel(id, landParcelDTO);
        return ResponseEntity.ok(updatedParcel);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MINISTRY_ADMIN') or hasRole('STATE_OFFICER')")
    public ResponseEntity<Void> deleteParcel(@PathVariable Long id) {
        landParcelService.deleteParcel(id);
        return ResponseEntity.noContent().build();
    }
}