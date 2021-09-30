package com.company;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.String;

import java.util.*;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Main {

    public static int exit_key;

    public static void main(String[] args) throws Exception {

        Document document = getDocumentXML("document.xml");
        LinkedList<MenuItem> items = getMenuItemsFromDocument(document);

        // CREATE MENU OBJECT
        Menu menu = new Menu(items);
        MenuItem choice;

        menu.setExitKey(exit_key);

        do {
            choice = menu.getChoice();
            if (choice != null) {
                System.out.println("Вы выбрали пункт " + choice.getTitle());
            }
        }
        while(choice != null);
    }

    public static Document getDocumentXML(String filename) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(filename);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        return document;
    }

    public static LinkedList<MenuItem> getMenuItemsFromDocument(Document document)
    {
        NodeList elements = document.getElementsByTagName("Item");

        exit_key = Integer.parseInt(document.getElementsByTagName("ExitKey").item(0).getTextContent());

        int length = elements.getLength();

        LinkedList<MenuItem> items = new LinkedList<MenuItem>();

        HashSet<Integer> keys = new HashSet<Integer>();
        keys.add(exit_key);

        for (int i = 0; i < length; ++i)
        {
            Element elem = (Element) elements.item(i);

            String title = elem.getElementsByTagName("title").item(0).getTextContent();
            int key = Integer.parseInt(elem.getElementsByTagName("key").item(0).getTextContent());

            if (!keys.contains(key)) {
                items.add(new MenuItem(title, key));
                keys.add(key);
            }
        }

        return items;
    }
}