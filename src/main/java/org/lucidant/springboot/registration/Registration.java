package org.lucidant.springboot.registration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Annotation for MongoDB
@Document("registrations")
public record Registration(
    @Id String id,
    @NotNull(message = "Product id is required") Integer productId,
    String ticketCode,
    @NotBlank(message = "Attendee name is required") String attendeeName) {
}
