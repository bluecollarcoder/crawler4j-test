package com.bluecollarcoder.dao;

import com.bluecollarcoder.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wayne
 */
@Repository
public class RecipeRepository {
    
    private static RecipeRepository instance;
    
    public static RecipeRepository getInstance() {
        return instance;
    }
    
    @Autowired private JdbcTemplate jdbc;
    
    public RecipeRepository() {
        if (instance == null)
            instance = this;
    }
    
    public void persist(Recipe recipe) {
        
    }
    
}
