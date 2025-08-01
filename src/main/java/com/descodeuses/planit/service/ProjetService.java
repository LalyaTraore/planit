package com.descodeuses.planit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.descodeuses.planit.dto.ProjetDTO;
import com.descodeuses.planit.entity.ProjetEntity;
import com.descodeuses.planit.repository.ProjetRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProjetService {

    private final ProjetRepository repository;

    public ProjetService(ProjetRepository repository) {
        this.repository = repository;
    }

    private ProjetDTO convertToDTO(ProjetEntity projet) {
        return new ProjetDTO(
            projet.getId(),
            projet.getTitle(),
            projet.getDescription(),
            projet.getDateDebut(),
            projet.getDateFin(),
            projet.getStatut(),
            projet.getPriorite()
        );
    }

    private ProjetEntity convertToEntity(ProjetDTO dto) {
        ProjetEntity projet = new ProjetEntity();
        projet.setId(dto.getId());
        projet.setTitle(dto.getTitle());
        projet.setDescription(dto.getDescription());
        projet.setDateDebut(dto.getDateDebut());
        projet.setDateFin(dto.getDateFin());
        projet.setStatut(dto.getStatut());
        projet.setPriorite(dto.getPriorite());
        return projet;
    }
// Create
    public ProjetDTO create(ProjetDTO dto) {
        ProjetEntity projet= convertToEntity(dto);
        ProjetEntity savedEntity = repository.save(projet);
        return convertToDTO(savedEntity);
    }
// Update
    public ProjetDTO update(Long id, ProjetDTO dto) {
      ProjetEntity existing = repository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("Projet non trouvé avec l'ID : " + id));

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setDateDebut(dto.getDateDebut());
        existing.setDateFin(dto.getDateFin());
       existing.setStatut(dto.getStatut());
        existing.setPriorite(dto.getPriorite());

       ProjetEntity updated = repository.save(existing);
        return convertToDTO(updated);
    }
// Delete 
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Projet non trouvé avec l'ID : " + id);
        }
        repository.deleteById(id);
    }
 // Méthode pour récupérer tous les projets
    public List<ProjetDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    // Méthode pour récupérer un projet par ID
    public ProjetDTO getById(Long id) {
        ProjetEntity projet = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Projet non trouvé avec l'ID : " + id));
        return convertToDTO(projet);
    }
  

 
}
