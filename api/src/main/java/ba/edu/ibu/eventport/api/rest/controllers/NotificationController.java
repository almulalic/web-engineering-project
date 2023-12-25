package ba.edu.ibu.eventport.api.rest.controllers;

import ba.edu.ibu.eventport.api.core.service.NotificationService;
import ba.edu.ibu.eventport.api.rest.models.dto.MessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/notifications")
public class NotificationController {
  private final NotificationService notificationService;

  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @RequestMapping(path = "/broadcast", method = RequestMethod.POST)
  public ResponseEntity<Void> sendBroadcastMessage(
    @RequestBody MessageDTO message
  ) {
    System.out.println("The message is: " + message.getMessage());
    notificationService.broadcastMessage(message.getMessage());
    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
  }

  @RequestMapping(path = "/send-to/{userId}", method = RequestMethod.POST)
  @PreAuthorize("hasAnyAuthority('LIBRARIAN', 'ADMIN')")
  public ResponseEntity<Void> sendChatMessage(
    @PathVariable String userId,
    @RequestBody MessageDTO message
  ) {
    System.out.println("The message is: " + message.getMessage());
    notificationService.sendMessage(userId, message.getMessage());
    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
  }
}
