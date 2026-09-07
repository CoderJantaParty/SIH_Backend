package com.sih.landacquisitionsystem.service;

import com.sih.landacquisitionsystem.dto.CompensationDTO;
import com.sih.landacquisitionsystem.model.Compensation;
import com.sih.landacquisitionsystem.model.LandParcel;
import com.sih.landacquisitionsystem.repository.CompensationRepository;
import com.sih.landacquisitionsystem.repository.LandParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompensationService {

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private LandParcelRepository landParcelRepository;

    public CompensationDTO createCompensation(CompensationDTO compensationDTO) {
        LandParcel parcel = landParcelRepository.findById(compensationDTO.getParcelId())
                .orElseThrow(() -> new RuntimeException("Land parcel not found"));

        Compensation compensation = new Compensation();
        compensation.setParcel(parcel);
        compensation.setAssessedAmount(compensationDTO.getAssessedAmount());
        compensation.setPaidAmount(compensationDTO.getPaidAmount());
        // For paidDate, we'll convert from String to LocalDate if needed
        if (compensationDTO.getPaidDate() != null && !compensationDTO.getPaidDate().isEmpty()) {
            compensation.setPaidDate(LocalDate.parse(compensationDTO.getPaidDate()));
        }
        compensation.setStatus(compensationDTO.getStatus());

        Compensation savedCompensation = compensationRepository.save(compensation);
        return convertToDTO(savedCompensation);
    }

    public CompensationDTO getCompensationById(Long id) {
        Compensation compensation = compensationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compensation not found"));
        return convertToDTO(compensation);
    }

    public List<CompensationDTO> getAllCompensations() {
        return compensationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<CompensationDTO> getCompensationsByParcelId(Long parcelId) {
        return compensationRepository.findAll().stream()
                .filter(comp -> comp.getParcel().getId().equals(parcelId))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CompensationDTO updateCompensation(Long id, CompensationDTO compensationDTO) {
        Compensation compensation = compensationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compensation not found"));

        LandParcel parcel = landParcelRepository.findById(compensationDTO.getParcelId())
                .orElseThrow(() -> new RuntimeException("Land parcel not found"));

        compensation.setParcel(parcel);
        compensation.setAssessedAmount(compensationDTO.getAssessedAmount());
        compensation.setPaidAmount(compensationDTO.getPaidAmount());
        // For paidDate, we'll convert from String to LocalDate if needed
        if (compensationDTO.getPaidDate() != null && !compensationDTO.getPaidDate().isEmpty()) {
            compensation.setPaidDate(LocalDate.parse(compensationDTO.getPaidDate()));
        }
        compensation.setStatus(compensationDTO.getStatus());

        Compensation updatedCompensation = compensationRepository.save(compensation);
        return convertToDTO(updatedCompensation);
    }

    public void deleteCompensation(Long id) {
        if (!compensationRepository.existsById(id)) {
            throw new RuntimeException("Compensation not found");
        }
        compensationRepository.deleteById(id);
    }

    private CompensationDTO convertToDTO(Compensation compensation) {
        return new CompensationDTO(
                compensation.getId(),
                compensation.getParcel().getId(),
                compensation.getAssessedAmount(),
                compensation.getPaidAmount(),
                compensation.getPaidDate() != null ? compensation.getPaidDate().toString() : null,
                compensation.getStatus()
        );
    }
}