/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluecollarcoder.crawler;

import com.bluecollarcoder.dao.PageRepository;
import com.bluecollarcoder.dao.RecipeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author wayne
 */
@Configuration
public class ApplicationConfig {
    public @Bean PageRepository getPageRepository() {
        return new PageRepository();
    }
    public @Bean RecipeRepository getRecipeRepository() {
        return new RecipeRepository();
    }
}
