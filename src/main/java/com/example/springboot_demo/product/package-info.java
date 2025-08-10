// src/main/java/com/example/product/package-info.java
/**
 * The product module manages product catalog, pricing, and product information.
 * Provides the core product data used by other modules like inventory and orders.
 *
 * @author Your Name
 */
@ApplicationModule(
        displayName = "product",
        allowedDependencies = {"shared"} // This module doesn't depend on others
)
package com.example.springboot_demo.product;

import org.springframework.modulith.ApplicationModule;