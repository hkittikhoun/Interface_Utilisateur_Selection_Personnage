package pong.frontal.fragments;

import java.net.URL;

import ca.ntro.app.Ntro;
import ca.ntro.app.frontend.ViewFx;
import ca.ntro.app.fx.controls.ResizableImage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class FragmentUneLangue extends ViewFx {

    @FXML
    private Label labelLangue;

    @FXML
    private ResizableImage imageLangue;

    @FXML
    private Pane espace;

    @Override
    public void initialize() {
        Ntro.assertNotNull(labelLangue);
        Ntro.assertNotNull(imageLangue);
    }

    public void afficherLangue(String langue) {
        labelLangue.setText(langue);

        String cheminImage = "/images/" + langue + ".png";

        URL image = FragmentUneLangue.class.getResource(cheminImage);

        if (image != null) {

            espace.setVisible(true);
            imageLangue.setVisible(true);
            imageLangue.setImage(new Image(cheminImage));

        } else {

            espace.setVisible(false);
            imageLangue.setVisible(false);
        }
    }

}