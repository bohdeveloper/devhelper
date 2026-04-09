package com.devhelper.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ai_prompts")
@Getter
@Setter
public class AiPrompt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "integer")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(name = "prompt_text", nullable = false)
    private String promptText;

    @Column(length = 100)
    private String category;
}
