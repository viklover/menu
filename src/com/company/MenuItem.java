package com.company;

class MenuItem {
    private int keyOp;
    private String title;

    public MenuItem(String title, int key) {
        this.keyOp = key;
        this.title = title;
    };

    int getKeyOp() {
        return this.keyOp;
    }

    String getTitle() {
        return this.title;
    }
}
