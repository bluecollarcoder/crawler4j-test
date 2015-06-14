/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bluecollarcoder.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author wayne
 */
@SpringBootApplication
@EnableAutoConfiguration
public class CrawlerApplication implements CommandLineRunner {
    
    private CrawlController controller;

    @Override
    public void run(String... args) throws Exception {
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder("/tmp/crawler4j");
        config.setPolitenessDelay(2000);
        config.setMaxConnectionsPerHost(1);
        config.setMaxPagesToFetch(10); // Change later
        
        PageFetcher fetcher = new PageFetcher(config);
        RobotstxtConfig robotsConfig = new RobotstxtConfig();
        robotsConfig.setEnabled(true);
        RobotstxtServer robotsSvr = new RobotstxtServer(robotsConfig, fetcher);
        
        controller = new CrawlController(config, fetcher, robotsSvr);
        controller.setCustomData(new String[]{"http://allrecipes.com"});
        controller.addSeed("http://allrecipes.com/Recipes");
        
        controller.startNonBlocking(AllrecipesCrawler.class, 1);
        while (!controller.isFinished())
            controller.wait(10000);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(CrawlerApplication.class, args);
    }
    
}
