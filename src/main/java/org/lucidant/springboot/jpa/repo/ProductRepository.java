package org.lucidant.springboot.jpa.repo;

import java.util.List;
import org.lucidant.springboot.jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByEventId(final int eventId);
}
