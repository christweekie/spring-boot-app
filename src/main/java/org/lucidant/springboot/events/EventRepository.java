package org.lucidant.springboot.events;

import java.util.List;
import org.lucidant.springboot.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository  extends JpaRepository<Event, Integer> {
    List<Event> findByOrganizerId(int organizerId);
}
