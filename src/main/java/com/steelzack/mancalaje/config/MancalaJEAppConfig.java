package com.steelzack.mancalaje.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by joaofilipesabinoesperancinha on 11-08-16.
 */
@Configuration
@ComponentScan(basePackages = {
        "com.steelzack.mancalaje.controller",
        "com.steelzack.mancalaje.manager",
        "com.steelzack.mancalaje.model"
})
public class MancalaJEAppConfig {
}
