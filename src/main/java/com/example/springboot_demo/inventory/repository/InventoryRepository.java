// InventoryRepository.java
package com.example.springboot_demo.inventory.repository;

import com.example.springboot_demo.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
