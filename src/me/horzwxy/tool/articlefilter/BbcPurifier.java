package me.horzwxy.tool.articlefilter;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: horzwxy
 * Date: 3/14/14
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class BbcPurifier extends Purifier {

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
        NodeList divs = doc.getElementsByTagName("div");
        Element storyBodyNode = null;
        for(int i = 0; i < divs.getLength(); i++) {
            Element divNode = (Element)divs.item(i);
            String className = divNode.getAttribute("class");

            if (className != null && className.equals("story-body")) {
                storyBodyNode = divNode;
                break;
            }
        }
        if(storyBodyNode == null) {
            return null;
        }

        Element rootNode = resultDoc.createElement("article");
        resultDoc.appendChild(rootNode);

        Element titleNode = resultDoc.createElement("title");
        rootNode.appendChild(titleNode);
        titleNode.setTextContent(doc.getElementsByTagName("title").item(0).getTextContent());

        Element contentNode = resultDoc.createElement("content");
        rootNode.appendChild(contentNode);

        NodeList childNodes = storyBodyNode.getChildNodes();
        for(int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if(childNode.getNodeName().equals("p")) {
                Element pElement = resultDoc.createElement("paragraph");
                pElement.setTextContent(childNode.getTextContent());
                contentNode.appendChild(pElement);
            }
        }

        return getOutputFile(inputFile, resultDoc);
    }
}
