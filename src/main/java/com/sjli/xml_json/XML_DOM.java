package com.sjli.xml_json;

/**
 * @Classname xml
 * @Description TODO
 * @Date 2021/8/26 14:48
 * @Created by steven
 */


import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * DOM：一次性读取XML，并在内存中表示为树形结构；
 * SAX：以流的形式读取XML，使用事件回调。
 */
public class XML_DOM {
    public static void main(String[] args) throws Exception {
        InputStream input = XML_DOM.class.getResourceAsStream("/book.xml");
        // 解析并获取Document对象:
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(input);
        printNode(doc, 0);
    }

    static void printNode(Node n, int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print(' ');
        }
        switch (n.getNodeType()) {
            case Node.DOCUMENT_NODE:
                System.out.println("Document: " + n.getNodeName());
                break;
            case Node.ELEMENT_NODE:
                System.out.println("Element: " + n.getNodeName());
                break;
            case Node.TEXT_NODE:
                System.out.println("Text: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            case Node.ATTRIBUTE_NODE:
                System.out.println("Attr: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            case Node.CDATA_SECTION_NODE:
                System.out.println("CDATA: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            case Node.COMMENT_NODE:
                System.out.println("Comment: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            default:
                System.out.println("NodeType: " + n.getNodeType() + ", NodeName: " + n.getNodeName());
        }
        for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling()) {
            printNode(child, indent + 1);
        }
    }
}
