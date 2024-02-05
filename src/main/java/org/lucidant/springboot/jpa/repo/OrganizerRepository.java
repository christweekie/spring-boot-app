package org.lucidant.springboot.jpa.repo;

import org.lucidant.springboot.jpa.entity.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
}
