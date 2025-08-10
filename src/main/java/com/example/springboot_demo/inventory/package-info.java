// src/main/java/com/example/inventory/package-info.java
/**
 * The inventory module handles stock management, inventory tracking,
 * and warehouse operations.
 *
 * @author Your Name
 */
@ApplicationModule(
        displayName = "inventory",
        allowedDependencies = {"shared"} // Explicitly declare dependencies
)
package com.example.springboot_demo.inventory;

import org.springframework.modulith.ApplicationModule;