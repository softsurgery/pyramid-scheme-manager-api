package com.pyramidscheme.mainartifact.modules.moderator.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "moderator")
public class ModeratorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private ModeratorEntity parent;

    @OneToMany(mappedBy = "parent")
    private Set<ModeratorEntity> children;

    // Calculate the nesting level of the moderator
    public int getLevel() {
        int level = 0;
        ModeratorEntity current = this;
        while (current.getParent() != null) {
            level++;
            current = current.getParent();
        }
        return level;
    }
}
