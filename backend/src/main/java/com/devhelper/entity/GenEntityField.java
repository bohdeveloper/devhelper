package com.devhelper.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "entity_fields")
@Getter
@Setter
public class GenEntityField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "integer")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false, columnDefinition = "integer")
    private GenEntity entity;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 100)
    private String type;

    private Boolean nullable = true;

    @Column(name = "primary_key")
    private Boolean primaryKey = false;
}
