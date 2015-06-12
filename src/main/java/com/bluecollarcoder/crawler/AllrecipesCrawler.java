/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluecollarcoder.crawler;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author wayne
 */
public class AllrecipesCrawler extends WebCrawler {
    
    private final Pattern urlPattern = Pattern.compile("http://(?:www\\.)?allrecipes.com/recipes?/", Pattern.CASE_INSENSITIVE);

    @Override
    public void visit(Page page) {
        logger.info("Visiting " + page.getWebURL().getURL());
        super.visit(page); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean shouldVisit(Page page, WebURL url) {
        Matcher matcher = urlPattern.matcher(url.getURL());
        boolean result = matcher.find();
        logger.info("Should visit " + url.getURL() + "? " + result);
        return result;
    }
    
}
