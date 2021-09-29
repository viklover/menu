package com.company;

import java.util.Scanner;

public class Menu {

    private MenuItem[] items;
    private Scanner scan;

    public Menu(MenuItem[] items) {

        this.items = items;

//        this.items = new MenuItem[lenght];
//        for (int i = 0; i < lenght; i++) {
//            this.items[i] = items[i];
//        }

        this.scan = new Scanner(System.in);
    }

    public void printItems() {
        String output = "  Menu: \n";

        for (int i = 0; i < this.items.length; ++i) {
            output += "    " + this.items[i].getKeyOp() + ": " + this.items[i].getTitle() + "\n";
        }

        output += "\\> ";

        System.out.print(output);
    }

    public MenuItem getChoice() {
        return this.items[this.scan.nextInt()];
    }

}
