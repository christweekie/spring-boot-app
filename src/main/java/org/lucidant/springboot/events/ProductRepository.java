package org.lucidant.springboot.events;

import java.util.List;
import org.lucidant.springboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByEventId(final int eventId);
}
