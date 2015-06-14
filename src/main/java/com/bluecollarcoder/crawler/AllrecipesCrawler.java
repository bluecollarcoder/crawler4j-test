/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluecollarcoder.crawler;

import com.bluecollarcoder.Recipe;
import com.bluecollarcoder.dao.PageRepository;
import com.bluecollarcoder.dao.RecipeRepository;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author wayne
 */
public class AllrecipesCrawler extends WebCrawler {
    
    private final Pattern visitPattern = Pattern.compile("http://(?:www\\.)?allrecipes.com/recipes?/", Pattern.CASE_INSENSITIVE);
    private final Pattern persistPattern = Pattern.compile("http://(?:www\\.)?allrecipes.com/recipes?/[A-Za-z0-9\\-]+/detail.aspx", Pattern.CASE_INSENSITIVE);
    
    @Override
    public void visit(Page page) {
        Matcher matcher = persistPattern.matcher(page.getWebURL().getURL());
        if (!matcher.find())
            return;
        logger.info("Visiting " + page.getWebURL().getURL());
        
        // Save a copy of the page
        PageRepository.getInstance().persist(1, page);
        
        // Parse the page and get recipe
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlData = (HtmlParseData)page.getParseData();
            String html = htmlData.getHtml();
            
            Document doc = Jsoup.parse(html);
            String itemTitle = doc.select("#itemTitle").text();
            String itemPhoto = doc.select("#imgPhoto").attr("src");
            Recipe recipe = new Recipe(itemTitle, page.getWebURL().getURL(), itemPhoto);
            
            for (Element ingredient : doc.select(".ingredient-wrap #liIngredient")) {
                String amount = ingredient.select("#lblIngAmount").text();
                String name = ingredient.select("#lblIngName").text();
                recipe.addIngredient(name, amount);
            }
            
            RecipeRepository.getInstance().persist(recipe);
        }
    }

    @Override
    public boolean shouldVisit(Page page, WebURL url) {
        Matcher matcher = visitPattern.matcher(url.getURL());
        boolean result = matcher.find();
        
        if (result)
            result = !PageRepository.getInstance().exists(url.getURL());
        
        logger.info("Should visit " + url.getURL() + "? " + result);
        return result;
    }
    
}
