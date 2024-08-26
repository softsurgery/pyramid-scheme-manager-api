package com.pyramidscheme.mainartifact.modules.moderator.controllers;

import com.pyramidscheme.mainartifact.modules.moderator.services.ModeratorService;
import com.pyramidscheme.mainartifact.modules.moderator.entities.ModeratorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/moderators")
public class ModeratorController {
    @Autowired
    private ModeratorService moderatorService;

    @GetMapping
    public List<ModeratorEntity> getAllModerators() {
        return moderatorService.getAllModerators();
    }

    @GetMapping("/{id}")
    public ModeratorEntity getModeratorById(@PathVariable Long id) {
        return moderatorService.getModeratorById(id);
    }

    @PostMapping
    public ModeratorEntity createModerator(@RequestBody ModeratorEntity moderatorEntity) {
        return moderatorService.saveModerator(moderatorEntity);
    }

    @PutMapping("/{id}")
    public ModeratorEntity updateModerator(@PathVariable Long id, @RequestBody ModeratorEntity moderatorEntity) {
        return moderatorService.updateModerator(id, moderatorEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteModerator(@PathVariable Long id) {
        moderatorService.deleteModerator(id);
    }

    @GetMapping("/level/{level}")
    public List<ModeratorEntity> getModeratorsByLevel(@PathVariable int level) {
        return moderatorService.getModeratorsByLevel(level);
    }
}
