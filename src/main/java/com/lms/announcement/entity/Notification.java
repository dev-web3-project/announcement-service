package com.lms.announcement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId; // destinataire (username)

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contenu;

    private Boolean lu = false;

    private String genre; // QUIZ, COURS, BADGE, SYSTEME, MENTORING

    private LocalDateTime dateCreation;

    private LocalDateTime dateEnvoi;

    @PrePersist
    protected void onCreate() {
        this.dateCreation = LocalDateTime.now();
        if (this.dateEnvoi == null) {
            this.dateEnvoi = this.dateCreation;
        }
    }
}
