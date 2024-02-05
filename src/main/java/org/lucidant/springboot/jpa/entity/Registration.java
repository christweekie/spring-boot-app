package org.lucidant.springboot.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "REGISTRATION")
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
public class Registration {
    @Id
    private int id;

    @NotNull(message = "Product id is required")
    private int productId;
    private String ticketCode;
    @NotBlank(message = "Attendee name is required")
    private String attendeeName;
}
