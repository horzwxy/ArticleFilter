package me.horzwxy.tool.articlefilter.test;

import me.horzwxy.tool.articlefilter.ArticleFilter;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/11/14
 * Time: 9:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        ArticleFilter interpreter = new ArticleFilter(new File("example.html"));
        interpreter.doWork(new File("output.xml"));

//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document doc = builder.parse(new File("example.html"));
//        System.out.println(doc.getElementsByTagName("article"));
    }

}
