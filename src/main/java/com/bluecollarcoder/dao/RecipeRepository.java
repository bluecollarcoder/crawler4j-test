package com.bluecollarcoder.dao;

import com.bluecollarcoder.Recipe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
    
    public void persist(final Recipe recipe) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbc.update((Connection con) -> {
            PreparedStatement stmt = con.prepareStatement("insert into recipes (recipe_name, recipe_url, recipe_photo) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, recipe.getTitle());
            stmt.setString(2, recipe.getUrl());
            stmt.setString(3, recipe.getPhotoUrl());
            return stmt;
        }, key);
        
        Number recipeId = (Number)key.getKeys().get("recipe_id");
        
        for (String name : recipe.getIngredients().keySet()) {
            key = new GeneratedKeyHolder();
            jdbc.update((Connection con) -> {
                PreparedStatement stmt = con.prepareStatement("insert into ingredients (ingredient_name) values (?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, name);
                return stmt;
            }, key);
            
            Number ingredientId = (Number)key.getKeys().get("ingredient_id");
            String amount = recipe.getIngredients().get(name);
            jdbc.update("insert into ingredient_amount (recipe_id, ingredient_id, amount) values (?, ?, ?)", 
                    new Object[]{recipeId, ingredientId, amount}, 
                    new int[]{Types.INTEGER, Types.INTEGER, Types.VARCHAR}
            );
        }
    }
    
}
