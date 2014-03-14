package me.horzwxy.tool.articlefilter.test;

import me.horzwxy.tool.articlefilter.ArticleFilter;
import me.horzwxy.tool.articlefilter.Purifier;
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
        File outputFile = new File("output.xml");
        interpreter.doWork(outputFile);

        Purifier purifier = Purifier.getPurifier("washingpost");
        purifier.purifier(outputFile);
    }

}
