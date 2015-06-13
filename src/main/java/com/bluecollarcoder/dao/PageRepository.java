package com.bluecollarcoder.dao;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import java.sql.Types;
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
public class PageRepository {
    
    private static PageRepository instance;
    
    public static PageRepository getInstance() {
        return instance;
    }
    
    @Autowired private JdbcTemplate jdbc;
    
    public PageRepository() {
        if (instance == null)
            instance = this;
    }
    
    public boolean exists(String url) {
        int count = jdbc.queryForObject("SELECT count(*) FROM crawled_pages WHERE url = ?", new Object[]{url}, Integer.class);
        return count > 0;
    }
    
    public void persist(Integer crawler, Page page) {
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlData = (HtmlParseData)page.getParseData();
            jdbc.update("INSERT INTO crawled_pages (crawler, url, content) VALUES (?, ?, ?)", new Object[]{crawler, page.getWebURL().getURL(), htmlData.getHtml()}, new int[]{Types.SMALLINT, Types.VARCHAR, Types.VARCHAR});
        }
    }
    
}
