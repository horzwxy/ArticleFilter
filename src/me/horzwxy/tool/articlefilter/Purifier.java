package me.horzwxy.tool.articlefilter;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/14/14
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Purifier {

    private DocumentBuilder builder;

    protected Purifier() {

    }

    public static Purifier getPurifier(String type) {
        if("washingpost".equals(type)) {
            return new WPostPurifier();
        }
        else if ("bbc".equals(type)) {
            return new BbcPurifier();
        }
        return null;
    }

    public abstract File purifier(File inputFile);

    protected DocumentBuilder getBuilder() {
        if(builder != null) {
            return builder;
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }
        this.builder = builder;
        return builder;
    }

    protected File getOutputFile(File inputFile, Document resultDoc) {
        // Use a Transformer for output
        TransformerFactory tFactory =
                TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            return null;
        }

        DOMSource source = new DOMSource(resultDoc);
        StreamResult result;
        File resultFile = new File("result-" + inputFile.getName());
        try {
            result = new StreamResult(new FileOutputStream(resultFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return resultFile;
    }
}
