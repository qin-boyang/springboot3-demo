package com.example.springboot_demo.inventory.service;

import com.example.springboot_demo.inventory.model.Inventory;
import com.example.springboot_demo.inventory.repository.InventoryRepository;
import com.example.springboot_demo.shared.ProductCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Create a new inventory record
     * @param inventory the inventory to create
     * @return the created inventory
     */
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    /**
     * Get all inventory records
     * @return list of all inventories
     */
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    /**
     * Get an inventory by its ID
     * @param id the inventory ID
     * @return Optional containing the inventory or empty if not found
     */
    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    /**
     * Update an existing inventory
     * @param id the inventory ID to update
     * @param inventoryDetails the updated inventory details
     * @return the updated inventory
     */
    public Inventory updateInventory(Long id, Inventory inventoryDetails) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));

        // Update inventory fields (add actual fields when Inventory model is fully defined)
        // inventory.setQuantity(inventoryDetails.getQuantity());
        // inventory.setProductId(inventoryDetails.getProductId());

        return inventoryRepository.save(inventory);
    }

    /**
     * Delete an inventory by its ID
     * @param id the inventory ID to delete
     */
    public void deleteInventory(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));

        inventoryRepository.delete(inventory);
    }

    @EventListener
    public void handleProductCreatedEvent(ProductCreatedEvent event) {
        // 当产品创建时，自动创建库存记录
        Inventory inventory = new Inventory();
        inventory.setQuantity(0); // 默认库存为0
        // 可以根据需要设置其他属性
        inventoryRepository.save(inventory);
    }
}
