/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluecollarcoder;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wayne
 */
public class Recipe {
    private final String title;
    private final String url;
    private final Map<String,String> ingredients;
    public Recipe(String title, String url) {
        this.title = title;
        this.url = url;
        this.ingredients = new HashMap<>();
    }
    public String getTitle() {
        return title;
    }
    public String getUrl() {
        return url;
    }
    public Map<String, String> getIngredients() {
        return ingredients;
    }
    public void addIngredient(String name, String amount) {
        ingredients.put(name, amount);
    }
}
