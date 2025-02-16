package com.workintech.fswebs18challengemaven.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card", schema = "fsweb")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "value")
    private Integer value;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color color;

    @PrePersist
    @PreUpdate
    private void validateCard() {
        if (type == Type.JOKER) {
            this.value = null;
            this.color = null;
        } else if (value != null && type != null) {
            throw new IllegalStateException("A card cannot have both a value and a type.");
        }
    }
}
