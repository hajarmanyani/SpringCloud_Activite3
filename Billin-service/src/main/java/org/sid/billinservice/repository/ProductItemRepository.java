package org.sid.billinservice.repository;

import org.sid.billinservice.entities.productItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<productItem, Long> {
}
