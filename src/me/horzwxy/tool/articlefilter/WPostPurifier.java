package me.horzwxy.tool.articlefilter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/14/14
 * Time: 8:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class WPostPurifier extends Purifier {

    @Override
    public File purifier(File inputFile) {
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
        Document doc;
        try {
            doc = builder.parse(inputFile);
        } catch (SAXException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Document resultDoc = builder.newDocument();
        Element rootElement = resultDoc.createElement("article");
        resultDoc.appendChild(rootElement);

        NodeList articleNodes = doc.getElementsByTagName("article");
        for (int i = 0; i < articleNodes.getLength(); i++) {
            Node articleNode = articleNodes.item(i);
            NodeList childNodes = articleNode.getChildNodes();
            for(int j = 0; j < childNodes.getLength(); j++) {
                Node childNode = childNodes.item(j);
                if(childNode.getNodeName().equals("p")) {
                    StringBuilder textBuilder = new StringBuilder();
                    Element pElement = resultDoc.createElement("paragraph");
                    NodeList pChildNodes = childNode.getChildNodes();
                    for(int k = 0; k < pChildNodes.getLength(); k++) {
                        Node pChildNode = pChildNodes.item(k);
                        textBuilder.append(pChildNode.getTextContent());
                    }
                    String textContent = textBuilder.toString().trim();
                    if(textContent.length() != 0) {
                        pElement.setTextContent(textContent);
                        rootElement.appendChild(pElement);
                    }
                }

            }
        }

        // Use a Transformer for output
        TransformerFactory tFactory =
                TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

        DOMSource source = new DOMSource(resultDoc);
        StreamResult result = null;
        File resultFile = new File("result-" + inputFile.getName());
        try {
            result = new StreamResult(new FileOutputStream(resultFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return resultFile;
    }
}
