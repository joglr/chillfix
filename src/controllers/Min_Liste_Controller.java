package controllers;


import javafx.fxml.FXML;
import models.Media;

import java.util.ArrayList;
import java.util.List;


public class Min_Liste_Controller {
    private List<Media> my_list;


    public Min_Liste_Controller() {
        my_list = new ArrayList<>();
    }

    @FXML
// lav togglebutton for save og delete i master
    public void saveButton(Media media) {
//        ActionEvent saveButton;
        my_list.add(media);

    }

    @FXML
    //    TODO remove media from list event handler
//    Action listener/event handler, når knap: "Fjern fra min liste" trykkes:
    public void deleteMedia(Media media) {

        for (int m = 0; my_list.size() >= m; m++) {
            my_list.remove(m);
        }
    }

    public void showMyList() {
//    new root to view my list

//     fra HomeViewController:   displayMedia();
    }
}
