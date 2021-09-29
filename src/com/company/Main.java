package com.company;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.lang.String;

import org.w3c.dom.Document;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, Exception {

        // PARSE XML FILE
        File file = new File("document.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);

        // GET MENU ITEMS CONTENT
        NodeList elements = document.getElementsByTagName("MenuItem");

        int exit_key = Integer.parseInt(document.getElementsByTagName("ExitKey").item(0).getTextContent());

        int length = elements.getLength();

        MenuItem[] items = new MenuItem[length];

        for (int i = 0; i < length; ++i)
        {
            Element elem = (Element) elements.item(i);

            String title = elem.getElementsByTagName("title").item(0).getTextContent();
            int key = Integer.parseInt(elem.getElementsByTagName("key").item(0).getTextContent());

            boolean check = true;

            // CHECK KEY
            for (int j = 0; j < i && check; ++j)
            {
                if ((items[j].getKeyOp() == key || items[j].getKeyOp() == exit_key)) {
                    check = false;
                }
            }

            if (check) {
                items[i] = new MenuItem(title, key);
            }
        }

        // CREATE MENU OBJECT
        Menu menu = new Menu(items);
        MenuItem choice;

        do {
            menu.printItems();
            choice = menu.getChoice();
            System.out.println("Вы выбрали пункт " + choice.getTitle());
        }
        while(choice.getKeyOp() != exit_key);
    }
}
