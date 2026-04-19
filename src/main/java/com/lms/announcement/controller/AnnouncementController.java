package com.lms.announcement.controller;

import com.lms.announcement.dto.AssignmentDto;
import com.lms.announcement.dto.EventDto;
import com.lms.announcement.dto.ExamDto;
import com.lms.announcement.dto.MaintenanceDto;
import com.lms.announcement.exception.InvalidDateException;
import com.lms.announcement.exception.NotFoundException;
import com.lms.announcement.services.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/announcement")
@AllArgsConstructor
@Tag(name = "Annonces", description = "Gestion des annonces : devoirs, événements, examens et maintenance")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @Operation(summary = "Lister toutes les annonces", description = "Retourne la liste de toutes les annonces (devoirs, événements, examens, maintenance).")
    @GetMapping("/all")
    public ResponseEntity<?> getAnnouncements() {
        try {
            return ResponseEntity.ok(announcementService.listAllAnnouncements());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to get announcements");
        }
    }

    @Operation(summary = "Créer une annonce de devoir", description = "Publie une nouvelle annonce de type devoir avec date limite.")
    @PostMapping("/assignment")
    public ResponseEntity<?> createAssignment(@RequestBody AssignmentDto assignmentDto) {
        try {
            return ResponseEntity.ok(announcementService.createAssignment(assignmentDto));
        } catch (InvalidDateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create announcement");
        }
    }

    @Operation(summary = "Créer une annonce d'événement", description = "Publie une nouvelle annonce de type événement.")
    @PostMapping("/event")
    public ResponseEntity<?> createEvent(@RequestBody EventDto eventDto) {
        try {
            return ResponseEntity.ok(announcementService.createEvent(eventDto));
        } catch (InvalidDateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create announcement");
        }
    }

    @Operation(summary = "Créer une annonce d'examen", description = "Publie une nouvelle annonce de type examen.")
    @PostMapping("/exam")
    public ResponseEntity<?> createExam(@RequestBody ExamDto examDto) {
        try {
            return ResponseEntity.ok(announcementService.createExam(examDto));
        } catch (InvalidDateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create announcement");
        }
    }

    @Operation(summary = "Créer une annonce de maintenance", description = "Publie une annonce de maintenance système.")
    @PostMapping("/maintenance")
    public ResponseEntity<?> createMaintenance(@RequestBody MaintenanceDto maintenanceDto) {
        try {
            return ResponseEntity.ok(announcementService.createMaintenance(maintenanceDto));
        } catch (InvalidDateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to create announcement");
        }
    }

    @Operation(summary = "Supprimer une annonce", description = "Supprime une annonce par son ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnnouncement(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(announcementService.deleteAnnouncement(id));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to delete announcement");
        }
    }
}
