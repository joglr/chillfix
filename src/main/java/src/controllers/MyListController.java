package main.java.src.controllers;

import java.util.ArrayList;
import java.util.List;

public class MyListController {
    private static final List<String> myList = new ArrayList<>();

    public static List<String> getMyList() {

        return MyListController.myList;
    }

}