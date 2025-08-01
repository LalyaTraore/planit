package com.descodeuses.planit.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.descodeuses.planit.dto.ActionDTO;
import com.descodeuses.planit.entity.ActionEntity;
import com.descodeuses.planit.entity.ContactEntity;
import com.descodeuses.planit.entity.ProjetEntity;
import com.descodeuses.planit.entity.UtilisateurEntity;
import com.descodeuses.planit.repository.ActionRepository;
import com.descodeuses.planit.repository.ContactRepository;
import com.descodeuses.planit.repository.ProjetRepository;
import com.descodeuses.planit.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ActionService {

    private final ActionRepository repository;
    private final ProjetRepository projetRepository;
    private final ContactRepository contactRepository;
    private  final UserRepository userRepository;

    public ActionService(ActionRepository repository,
                         ProjetRepository projetRepository,
                         ContactRepository contactRepository,
                         UserRepository userRepository) {
        this.repository = repository;
        this.projetRepository = projetRepository;
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    private ActionDTO convertToDTO(ActionEntity action) {
        Long projetId = (action.getProjet() != null) ? action.getProjet().getId() : null;

        List<String> memberNames = new ArrayList<>();
        List<Long> memberIds = new ArrayList<>();
        if (action.getMembers() != null) {
            for (ContactEntity contact : action.getMembers()) {
                memberNames.add(contact.getName());
                memberIds.add(contact.getId());
            }
        }
        Long userId = (action.getUser() != null) ? action.getUser().getId() : null;
    
        ActionDTO dto = new ActionDTO(
            action.getId(),
            action.getTitle(),
            action.getCompleted(),
            action.getDueDate(),
            action.getPriority(),
            action.getDescription(),
            projetId, 
            userId,
            memberNames
        );
        dto.setMemberIds(memberIds);
        return dto;
    }

    private ActionEntity convertToEntity(ActionDTO dto) {
        ActionEntity action = new ActionEntity();

        action.setId(dto.getId());
        action.setTitle(dto.getTitle());
        action.setCompleted(dto.getCompleted());
        action.setDueDate(dto.getDueDate());
        action.setPriority(dto.getPriority());
        action.setDescription(dto.getDescription());

        if (dto.getProjetId() != null) {
            ProjetEntity projet = projetRepository.findById(dto.getProjetId())
                    .orElseThrow(() -> new EntityNotFoundException("Projet not found with id: " + dto.getProjetId()));
            action.setProjet(projet);
        }

        if (dto.getMemberIds() != null && !dto.getMemberIds().isEmpty()) {
            Set<ContactEntity> members = new HashSet<>();
            for (Long contactId : dto.getMemberIds()) {
                ContactEntity contact = contactRepository.findById(contactId)
                        .orElseThrow(() -> new EntityNotFoundException("Contact not found with id: " + contactId));
                members.add(contact);
            }
            action.setMembers(members);
        } else {
            action.setMembers(null);
        }

        return action;
    }

    public List<ActionDTO> getAll() {
        List<ActionEntity> entities = repository.findAll();
        List<ActionDTO> dtos = new ArrayList<>();

        for (ActionEntity item : entities) {
            dtos.add(convertToDTO(item));
        }
        return dtos;
    }

    public ActionDTO getActionById(Long id) {
        Optional<ActionEntity> action = repository.findById(id);
        if (action.isEmpty()) {
            throw new EntityNotFoundException("Action not found with id: " + id);
        }
        return convertToDTO(action.get());
    }

    public ActionDTO create(ActionDTO dto, UtilisateurEntity user) {
    ActionEntity entity = convertToEntity(dto);
    entity.setUser(user); 
    ActionEntity savedEntity = repository.save(entity);
    return convertToDTO(savedEntity);
}


    public ActionDTO update(Long id, ActionDTO dto) {
        ActionEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Action not found with id: " + id));

        existingEntity.setTitle(dto.getTitle());
        existingEntity.setCompleted(dto.getCompleted());
        existingEntity.setDueDate(dto.getDueDate());
        existingEntity.setPriority(dto.getPriority());
        existingEntity.setDescription(dto.getDescription());
    
        if (dto.getUserId() != null) {
            UtilisateurEntity user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + dto.getProjetId()));
            existingEntity.setUser(user);
        } else {
            existingEntity.setUser(null);
        } 

        if (dto.getProjetId() != null) {
            ProjetEntity projet = projetRepository.findById(dto.getProjetId())
                    .orElseThrow(() -> new EntityNotFoundException("Projet not found with id: " + dto.getProjetId()));
            existingEntity.setProjet(projet);
        } else {
            existingEntity.setProjet(null);
        }

        if (dto.getMemberIds() != null) {
            Set<ContactEntity> newMembers = new HashSet<>();
            for (Long contactId : dto.getMemberIds()) {
                ContactEntity contact = contactRepository.findById(contactId)
                        .orElseThrow(() -> new EntityNotFoundException("Contact not found with id: " + contactId));
                newMembers.add(contact);
            }
            existingEntity.setMembers(newMembers);
        } else {
            existingEntity.setMembers(null);
        }

        ActionEntity updatedEntity = repository.save(existingEntity);
        return convertToDTO(updatedEntity);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Action not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
