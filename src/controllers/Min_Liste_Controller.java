package controllers;


import models.Media;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;


public class Min_Liste_Controller {
    private List<Media> my_list;


    public Min_Liste_Controller() {
        my_list = new ArrayList<>();
    }

    public void addMediaToList(Media media) {
        ActionEvent myCoolButton;
        my_list.add(media);

    }

    //    TODO remove media from list event handler
//    Action listener/event handler, når knap: "Fjern fra min liste" trykkes:
    public void deleteMedia(Media media) {

        for (int m = 0; my_list.size() >= m; m++) {
            my_list.remove(m);
        }
    }

    public void show() {
//    new root to view my list
        System.out.print(my_list);
    }
}
