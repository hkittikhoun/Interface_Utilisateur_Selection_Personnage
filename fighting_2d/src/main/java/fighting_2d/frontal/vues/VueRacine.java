package fighting_2d.frontal.vues;

import ca.ntro.app.Ntro;
import ca.ntro.app.frontend.ViewFx;
import fighting_2d.frontal.evenements.EvtChangerLangue;
import fighting_2d.frontal.evenements.EvtChangerTaillePolice;
import javafx.animation.Animation.Status;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class VueRacine extends ViewFx {

    @FXML
    private ComboBox<String> comboLangues;

    @FXML
    private StackPane conteneurSousVue;

    private ViewFx vueCourante;

    private Timeline transition = new Timeline();

    @Override
    public void initialize() {

        Ntro.assertNotNull(comboLangues);
        Ntro.assertNotNull(conteneurSousVue);

        installerRaccourcisClavier();

    }

    private void installerRaccourcisClavier() {
        rootNode().addEventFilter(KeyEvent.KEY_PRESSED, evtFx -> {

            switch (evtFx.getCode()) {

                case EQUALS:
                    Ntro.newEvent(EvtChangerTaillePolice.class)
                            .setFacteur(1.1)
                            .trigger();
                    break;

                case MINUS:
                    Ntro.newEvent(EvtChangerTaillePolice.class)
                            .setFacteur(0.9)
                            .trigger();
                    break;

                default:
                    Ntro.logger().info("[KEY PRESSED]: " + evtFx.getCode());
                    break;
            }

        });
    }

    public void initialiserComboLangues() {
        comboLangues.setFocusTraversable(false);
        comboLangues.getItems().add("fr");
        comboLangues.getItems().add("en");

        comboLangues.getSelectionModel().select(Ntro.currentLocale().language());

        comboLangues.setOnAction(evtFx -> {

            String langue = comboLangues.getSelectionModel().getSelectedItem();

            Ntro.newEvent(EvtChangerLangue.class)
                    .setLangue(langue)
                    .trigger();

        });
    }

    public void afficherSousVue(ViewFx nouvelleVue) {
        if (transition.getStatus() == Status.RUNNING)
            return;

        if (vueCourante instanceof VueCreationProfil
                && nouvelleVue instanceof VueAcceuil) {

            vueCourante = null;
            transitionVersNouvelleVue(nouvelleVue, 1);

        } else if (vueCourante instanceof VueAcceuil
                && nouvelleVue instanceof VueCreationProfil) {

            vueCourante = null;
            transitionVersNouvelleVue(nouvelleVue, -1);

        } else {

            conteneurSousVue.getChildren().clear();
            conteneurSousVue.getChildren().add(nouvelleVue.rootNode());
            vueCourante = nouvelleVue;

        }
    }

    private void transitionVersNouvelleVue(ViewFx nouvelleVue, int direction) {
        Pane nouveauPane = nouvelleVue.rootNode();
        conteneurSousVue.getChildren().add(nouveauPane);

        Pane ancienPane = (Pane) conteneurSousVue.getChildren().get(0);

        double largeur = conteneurSousVue.getWidth();
        largeur = direction * largeur;
        int duree = 400;

        transition = new Timeline();
        transition.getKeyFrames()
                .add(new KeyFrame(new Duration(0), new KeyValue(nouveauPane.translateXProperty(), largeur)));
        transition.getKeyFrames().add(new KeyFrame(new Duration(0), new KeyValue(nouveauPane.opacityProperty(), 0.0)));
        transition.getKeyFrames().add(new KeyFrame(new Duration(0), new KeyValue(ancienPane.translateXProperty(), 0)));
        transition.getKeyFrames().add(new KeyFrame(new Duration(0), new KeyValue(ancienPane.opacityProperty(), 1.0)));
        transition.getKeyFrames().add(new KeyFrame(new Duration(duree),
                new KeyValue(nouveauPane.translateXProperty(), 0, Interpolator.EASE_BOTH)));
        transition.getKeyFrames().add(new KeyFrame(new Duration(duree),
                new KeyValue(nouveauPane.opacityProperty(), 1.0, Interpolator.EASE_BOTH)));
        transition.getKeyFrames().add(new KeyFrame(new Duration(duree),
                new KeyValue(ancienPane.translateXProperty(), -largeur, Interpolator.EASE_BOTH)));
        transition.getKeyFrames().add(new KeyFrame(new Duration(duree),
                new KeyValue(ancienPane.opacityProperty(), 0.0, Interpolator.EASE_BOTH)));

        transition.setOnFinished(evtFx -> {
            conteneurSousVue.getChildren().remove(ancienPane);
            vueCourante = nouvelleVue;
        });

        transition.playFromStart();
    }
}
