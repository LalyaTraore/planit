package com.descodeuses.planit.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.descodeuses.planit.dto.ContactDTO;
import com.descodeuses.planit.entity.ContactEntity;
import com.descodeuses.planit.repository.ContactRepository;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    private ContactDTO convertToDTO(ContactEntity entity) {
        return new ContactDTO(
            entity.getId(),
            entity.getName(),
            entity.getEmail(),
           entity.getTelephone(),
            entity.getMessage()

        );
    }

    private ContactEntity convertToEntity(ContactDTO dto) {
        ContactEntity entity = new ContactEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setTelephone(dto.getTelephone());
        entity.setMessage(dto.getMessage());
        return entity;
    }

    public List<ContactDTO> getAll() {
        List<ContactEntity> entities = contactRepository.findAll();
        List<ContactDTO> dtos = new ArrayList<>();
        for (ContactEntity item : entities) {
            dtos.add(convertToDTO(item));
        }
        return dtos;
    }

    public ContactDTO getById(Long id) {
        ContactEntity entity = contactRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contact not found"));
        return convertToDTO(entity);
    }

    public ContactDTO create(ContactDTO dto) {
        ContactEntity entity = convertToEntity(dto);
        return convertToDTO(contactRepository.save(entity));
    }

    public ContactDTO update(Long id, ContactDTO dto) {
        ContactEntity existing = contactRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contact not found"));
        existing.setName(dto.getName());
        //existing.setEmail(dto.getEmail());
        //existing.setTelephone(dto.getTelephone());
        //existing.setMessage(dto.getMessage());
        return convertToDTO(contactRepository.save(existing));
    }

    public void delete(Long id) {
        contactRepository.deleteById(id);
    }
}
