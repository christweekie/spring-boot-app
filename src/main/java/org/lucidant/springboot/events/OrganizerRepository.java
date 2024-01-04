package org.lucidant.springboot.events;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class OrganizerRepository {

    private final List<Organizer> organizers = List.of(
            new Organizer(101, "Globomantics", "Globomantics Technology Corporation"),
            new Organizer(102, "Carved Rock", "Carved Rock Sports Equipment"));

    public List<Organizer> findAll() {
        return organizers;
    }

    public Optional<Organizer> findById(int id) {
        return organizers.stream().filter(org -> org.id() == id).findAny();
    }
}
