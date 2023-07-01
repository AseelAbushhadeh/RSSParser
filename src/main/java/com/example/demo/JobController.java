package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {


    private static Logger logger= LoggerFactory.getLogger(JobController.class);
    @Autowired
    private GeocodingService geocodingService;

    @GetMapping("/job")
    public ModelAndView getJobs(){
        String urlString="https://careers.moveoneinc.com/rss/all-rss.xml/";
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException("url exception: ",e);
        }
        URLConnection conn = null;
        try {
            conn = url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException("connection:exception ",e);
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("document builder exception: ",e);
        }

        Document doc = null;
        try {
            doc = builder.parse(conn.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException("input stream exception: ",e);
        }

        List<Item> item_lst=new ArrayList<>();
        try{

            NodeList items= doc.getElementsByTagName("item");

            for(int i=0;i<items.getLength();++i) {

                Element itemElement = (Element) items.item(i);
                String title = itemElement.getElementsByTagName("title").item(0).getTextContent();
                String country = itemElement.getElementsByTagName("country").item(0).getTextContent();
                GeoCoordinates geoCoordinates=geocodingService.geocode(country);

                Item item=new Item(title, country, geoCoordinates);
                item_lst.add(item);

            }

        }catch (Exception e){
            logger.error("Error when accessing tags");
            throw new RuntimeException(e);
        }

        ModelAndView modelAndView = new ModelAndView("job");
        modelAndView.addObject("items", item_lst);

        return modelAndView;
    }


}
