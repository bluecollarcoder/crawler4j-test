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
    private Long id;
    private final String title;
    private final String url;
    private final String photoUrl;
    private final Map<String,String> ingredients;
    public Recipe(String title, String url, String photoUrl) {
        this.title = title;
        this.url = url;
        this.photoUrl = photoUrl;
        this.ingredients = new HashMap<>();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public String getUrl() {
        return url;
    }
    public String getPhotoUrl() {
        return photoUrl;
    }
    public Map<String, String> getIngredients() {
        return ingredients;
    }
    public void addIngredient(String name, String amount) {
        ingredients.put(name, amount);
    }
}
