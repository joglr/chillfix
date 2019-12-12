package views;

import controllers.RootController;

import java.io.IOException;

public class MainView {
    public MainView() throws IOException {
        RootController.setCurrentRoot(RootController.loadRoot("/views/MainView.fxml"));
    }
}
