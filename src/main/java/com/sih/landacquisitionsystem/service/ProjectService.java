package com.sih.landacquisitionsystem.service;

import com.sih.landacquisitionsystem.dto.ProjectDTO;
import com.sih.landacquisitionsystem.model.Project;
import com.sih.landacquisitionsystem.model.User;
import com.sih.landacquisitionsystem.repository.ProjectRepository;
import com.sih.landacquisitionsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public ProjectDTO createProject(ProjectDTO projectDTO, Long createdById) {
        User createdBy = userRepository.findById(createdById)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = new Project();
        project.setTitle(projectDTO.getTitle());
        project.setMinistry(projectDTO.getMinistry());
        project.setState(projectDTO.getState());
        project.setDistrict(projectDTO.getDistrict());
        project.setStatus(projectDTO.getStatus());
        project.setCreatedBy(createdBy);
        project.setCreatedAt(LocalDateTime.now());

        Project savedProject = projectRepository.save(project);
        return convertToDTO(savedProject);
    }

    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return convertToDTO(project);
    }

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> getProjectsByState(String state) {
        return projectRepository.findByState(state).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> getProjectsByDistrict(String district) {
        return projectRepository.findByDistrict(district).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> getProjectsByStatus(String status) {
        return projectRepository.findByStatus(Project.Status.valueOf(status)).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setTitle(projectDTO.getTitle());
        project.setMinistry(projectDTO.getMinistry());
        project.setState(projectDTO.getState());
        project.setDistrict(projectDTO.getDistrict());
        project.setStatus(projectDTO.getStatus());

        Project updatedProject = projectRepository.save(project);
        return convertToDTO(updatedProject);
    }

    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new RuntimeException("Project not found");
        }
        projectRepository.deleteById(id);
    }

    private ProjectDTO convertToDTO(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .title(project.getTitle())
                .ministry(project.getMinistry())
                .state(project.getState())
                .district(project.getDistrict())
                .status(project.getStatus())
                .createdBy(project.getCreatedBy().getId())
                .build();
    }
}