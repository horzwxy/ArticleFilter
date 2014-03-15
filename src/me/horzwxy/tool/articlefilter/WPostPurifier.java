package me.horzwxy.tool.articlefilter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
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

        Document doc;
        try {
            doc = getBuilder().parse(inputFile);
        } catch (SAXException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Document resultDoc = getBuilder().newDocument();
        Element rootElement = resultDoc.createElement("article");
        resultDoc.appendChild(rootElement);

        Element titleElement = resultDoc.createElement("title");
        titleElement.setTextContent(doc.getElementsByTagName("title").item(0).getTextContent());
        rootElement.appendChild(titleElement);

        Element contentElement = resultDoc.createElement("content");
        rootElement.appendChild(contentElement);

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
                        contentElement.appendChild(pElement);
                    }
                }

            }
        }
        return getOutputFile(inputFile, resultDoc);
    }
}
