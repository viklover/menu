package com.company;

import java.util.LinkedList;
import java.util.Scanner;
import java.lang.Exception;

public class Menu {

    private LinkedList<MenuItem> items;
    private Scanner scan;

    private int exit_key;

    public Menu(LinkedList<MenuItem> items) {
        this.items = items;
        this.scan = new Scanner(System.in);
    }

    public void setExitKey(int key) {
        this.exit_key = key;
    }

    public void printItems() {
        String output = "  Menu: \n";

        for(MenuItem item : items) {
            output += "    " + item.getKeyOp() + ": " + item.getTitle() + "\n";
        }

        output += "\\> ";

        System.out.print(output);
    }

    public MenuItem getChoice()
    {
        this.printItems();

        while (true)
        {
            try {
                int in = this.scan.nextInt();

                if (in == this.exit_key) {
                    return null;
                }

                for(MenuItem item : items) {
                    if (item.getKeyOp() == in) {
                        return item;
                    }
                }

                this.printItems();

            } catch (Exception e) {

            }
        }
    }
}
