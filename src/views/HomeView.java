package views;

import controllers.RootController;

import java.io.IOException;

public class HomeView {
    public HomeView() throws IOException {
        RootController.setCurrentRoot(RootController.loadRoot("/views/HomeView.fxml"));
    }
}
