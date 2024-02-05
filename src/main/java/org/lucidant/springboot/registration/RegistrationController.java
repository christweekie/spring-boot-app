package org.lucidant.springboot.registration;

import java.util.NoSuchElementException;
import java.util.UUID;
import jakarta.validation.Valid;
import org.lucidant.springboot.jpa.entity.Registration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/registrations")
public class RegistrationController {

    private final RegistrationRepository registrationRepository;

    public RegistrationController(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @PostMapping
    public Registration create(@RequestBody @Valid Registration registration) {
        String ticketCode = UUID.randomUUID().toString();

        return registrationRepository.save(new Registration(
            1, registration.getProductId(), ticketCode, registration.getAttendeeName()));
    }

    @GetMapping(path = "/{ticketCode}")
    public Registration get(@PathVariable("ticketCode") String ticketCode) {
        return registrationRepository.findByTicketCode(ticketCode)
            .orElseThrow(() -> new NoSuchElementException("Registration with ticket code " + ticketCode + " not found"));
    }

    @PutMapping
    public Registration update(@RequestBody Registration registration) {
        // Lookup the existing registration by ticket code
        String ticketCode = registration.getTicketCode();
        var existing = registrationRepository.findByTicketCode(ticketCode)
            .orElseThrow(() -> new NoSuchElementException("Registration with ticket code " + ticketCode + " not found"));

        // Only update the attendee name
        return registrationRepository.save(new Registration(
            existing.getId(), existing.getProductId(), ticketCode, registration.getAttendeeName()));
    }

    @DeleteMapping(path = "/{ticketCode}")
    public void delete(@PathVariable("ticketCode") String ticketCode) {
        registrationRepository.deleteByTicketCode(ticketCode);
    }
}
