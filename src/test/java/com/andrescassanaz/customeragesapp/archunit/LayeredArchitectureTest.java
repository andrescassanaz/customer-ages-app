package com.andrescassanaz.customeragesapp.archunit;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

@AnalyzeClasses(packages = "com.andrescassanaz.customeragesapp", importOptions = ImportOption.DoNotIncludeTests.class)
public class LayeredArchitectureTest {

    private static final String DOMAIN = "Domain";
    private static final String ADAPTERS = "Adapters";
    private static final String APPLICATION = "Application";
    private static final String CONFIG = "Config";

    @ArchTest
    public static final ArchRule layer_dependencies_are_respected = Architectures.layeredArchitecture()

            .layer(CONFIG).definedBy("com.andrescassanaz.customeragesapp.config..")
            .layer(DOMAIN).definedBy("com.andrescassanaz.customeragesapp.domain..")
            .layer(ADAPTERS).definedBy("com.andrescassanaz.customeragesapp.adapter..")
            .layer(APPLICATION).definedBy("com.andrescassanaz.customeragesapp.application..")

            .whereLayer(APPLICATION).mayOnlyBeAccessedByLayers(ADAPTERS, CONFIG)
            .whereLayer(ADAPTERS).mayOnlyBeAccessedByLayers(CONFIG)
            .whereLayer(DOMAIN).mayOnlyBeAccessedByLayers(APPLICATION, ADAPTERS, CONFIG);
}
