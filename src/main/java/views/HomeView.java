package views;

import controllers.RootController;
import models.ErrorHandler;

import java.io.IOException;

public class HomeView {
    public HomeView() {
        try {
            RootController.setCurrentRoot(RootController.loadRoot("fxml/HomeView.fxml"));
        } catch (IOException ex) {
            new ErrorHandler("Kunne ikke indlæse HomeView. ").show();
        }

    }
}
