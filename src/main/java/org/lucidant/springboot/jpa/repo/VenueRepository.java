package org.lucidant.springboot.jpa.repo;

import org.lucidant.springboot.jpa.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {
}
