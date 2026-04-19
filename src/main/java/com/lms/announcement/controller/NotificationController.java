package com.lms.announcement.controller;

import com.lms.announcement.entity.Notification;
import com.lms.announcement.repository.NotificationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcement/notifications")
@RequiredArgsConstructor
@Tag(name = "Notifications", description = "Gestion des notifications utilisateur (lecture, envoi, suppression)")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    @Operation(summary = "Notifications d'un utilisateur", description = "Retourne toutes les notifications d'un utilisateur, triées par date de création.")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable String userId) {
        return ResponseEntity.ok(notificationRepository.findByUserIdOrderByDateCreationDesc(userId));
    }

    @Operation(summary = "Notifications non lues", description = "Retourne les notifications non lues d'un utilisateur.")
    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<Notification>> getUnreadNotifications(@PathVariable String userId) {
        return ResponseEntity.ok(notificationRepository.findByUserIdAndLuFalse(userId));
    }

    @Operation(summary = "Envoyer une notification", description = "Crée et envoie une nouvelle notification à un utilisateur.")
    @PostMapping("/send")
    public ResponseEntity<Notification> sendNotification(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationRepository.save(notification));
    }

    @Operation(summary = "Marquer comme lue", description = "Marque une notification spécifique comme lue.")
    @PutMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        notificationRepository.findById(id).ifPresent(notif -> {
            notif.setLu(true);
            notificationRepository.save(notif);
        });
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Tout marquer comme lu", description = "Marque toutes les notifications d'un utilisateur comme lues.")
    @PutMapping("/user/{userId}/read-all")
    public ResponseEntity<Void> markAllAsRead(@PathVariable String userId) {
        List<Notification> unread = notificationRepository.findByUserIdAndLuFalse(userId);
        unread.forEach(n -> n.setLu(true));
        notificationRepository.saveAll(unread);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Supprimer une notification", description = "Supprime une notification par son ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
