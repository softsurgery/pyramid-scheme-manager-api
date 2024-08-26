package com.pyramidscheme.mainartifact.modules.moderator.services;

import com.pyramidscheme.mainartifact.modules.moderator.repositories.ModeratorRepository;
import com.pyramidscheme.mainartifact.modules.moderator.entities.ModeratorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModeratorService {
    @Autowired
    private ModeratorRepository moderatorRepository;

    public List<ModeratorEntity> getAllModerators() {
        return moderatorRepository.findAll();
    }

    public ModeratorEntity getModeratorById(Long id) {
        return moderatorRepository.findById(id).orElse(null);
    }

    public ModeratorEntity saveModerator(ModeratorEntity moderator) {
        if (moderator.getParent() != null) {
            Optional<ModeratorEntity> parent = moderatorRepository.findById(moderator.getParent().getId());
            parent.ifPresent(moderator::setParent);
        }
        return moderatorRepository.save(moderator);
    }

    public ModeratorEntity updateModerator(Long id, ModeratorEntity moderator) {
        ModeratorEntity existingModerator = moderatorRepository.findById(id).orElse(null);
        if (existingModerator != null) {
            existingModerator.setName(moderator.getName());
            existingModerator.setSurname(moderator.getSurname());
            return moderatorRepository.save(existingModerator);
        }
        return null;
    }

    public void deleteModerator(Long id) {
        moderatorRepository.deleteById(id);
    }

    // Method to get moderators at a specific level
    public List<ModeratorEntity> getModeratorsByLevel(int level) {
        return moderatorRepository.findAll().stream()
                .filter(moderator -> moderator.getLevel() == level)
                .collect(Collectors.toList());
    }
}
