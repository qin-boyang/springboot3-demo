package com.example.springboot_demo;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class ModularityTests {
    @Test
    void verifiesModularStructure() {
        ApplicationModules modules = ApplicationModules.of(SpringbootDemoApplication.class);
        modules.verify();
    }

    @Test
    void createsDocumentation() {
        ApplicationModules modules = ApplicationModules.of(SpringbootDemoApplication.class);
        new Documenter(modules)
                .writeDocumentation();
    }
}
