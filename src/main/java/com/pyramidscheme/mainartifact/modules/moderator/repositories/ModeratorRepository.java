package com.pyramidscheme.mainartifact.modules.moderator.repositories;

import com.pyramidscheme.mainartifact.modules.moderator.entities.ModeratorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeratorRepository extends JpaRepository<ModeratorEntity, Long> {
    Page<ModeratorEntity> findByNameContainingOrSurnameContaining(String name, String surname, Pageable pageable);
}


