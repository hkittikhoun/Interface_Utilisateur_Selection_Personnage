package pong.frontal.vues;

import java.util.HashMap;
import java.util.Map;

import ca.ntro.app.Ntro;
import ca.ntro.app.frontend.ViewFx;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import pong.commun.enums.Action;
import pong.commun.enums.Position;
import pong.commun.monde2d.MondePong2d;
import pong.frontal.controles.CanvasPartie;
import pong.frontal.evenements.EvtAfficherFileAttente;
import pong.frontal.evenements.EvtClicSouris;
import pong.frontal.utils.ActionsActivesParPosition;

public class VuePartie extends ViewFx {

    @FXML
    private Button boutonQuitterPartie;

    @FXML
    private Pane conteneurVuePartie;

    @FXML
    private CanvasPartie canvasPartie;

    @FXML
    private Label labelNomPremierJoueur;

    @FXML
    private Label labelNomDeuxiemeJoueur;

    @FXML
    private Label labelScorePremierJoueur;

    @FXML
    private Label labelScoreDeuxiemeJoueur;

    private Map<Position, Label> labelsNoms = new HashMap<>();
    private Map<Position, Label> labelsScores = new HashMap<>();

    private ActionsActivesParPosition actionsActives = new ActionsActivesParPosition();

    @Override
    public void initialize() {

        Ntro.assertNotNull(boutonQuitterPartie);
        Ntro.assertNotNull(conteneurVuePartie);
        Ntro.assertNotNull(canvasPartie);

        Ntro.assertNotNull(labelNomPremierJoueur);
        Ntro.assertNotNull(labelNomDeuxiemeJoueur);

        labelsNoms.put(Position.GAUCHE, labelNomPremierJoueur);
        labelsNoms.put(Position.DROITE, labelNomDeuxiemeJoueur);

        Ntro.assertNotNull(labelScorePremierJoueur);
        Ntro.assertNotNull(labelScoreDeuxiemeJoueur);

        labelsScores.put(Position.GAUCHE, labelScorePremierJoueur);
        labelsScores.put(Position.DROITE, labelScoreDeuxiemeJoueur);

        installerEvtAfficherFileAttente();
        installerEvtActionJoueur();
        installerEvtClicSouris();

    }

    private void installerEvtActionJoueur() {
        conteneurVuePartie.addEventFilter(KeyEvent.KEY_PRESSED, evtFx -> {

            Position position = positionPourTouche(evtFx.getCode());
            Action action = actionPourTouche(evtFx.getCode());

            actionsActives.activer(position, action);
        });

        conteneurVuePartie.addEventFilter(KeyEvent.KEY_RELEASED, evtFx -> {

            Position position = positionPourTouche(evtFx.getCode());
            Action action = actionPourTouche(evtFx.getCode());

            actionsActives.desactiver(position, action);
        });
    }

    private Position positionPourTouche(KeyCode code) {
        Position position = null;

        if (code.equals(KeyCode.W) || code.equals(KeyCode.S)) {

            position = Position.GAUCHE;

        } else if (code.equals(KeyCode.UP) || code.equals(KeyCode.DOWN)) {

            position = Position.DROITE;

        }

        return position;
    }

    private Action actionPourTouche(KeyCode code) {
        Action action = null;

        if (code.equals(KeyCode.W) || code.equals(KeyCode.UP)) {

            action = Action.MONTER;

        } else if (code.equals(KeyCode.S) || code.equals(KeyCode.DOWN)) {

            action = Action.DESCENDRE;

        }

        return action;
    }

    private void installerEvtAfficherFileAttente() {

        boutonQuitterPartie.setOnAction(evtFx -> {

            Ntro.newEvent(EvtAfficherFileAttente.class)
                    .trigger();

            Ntro.logger().info("[VuePartie] clic: " + evtFx.getEventType());

        });
    }

    public void viderCanvas() {
        canvasPartie.clearCanvas();
    }

    public void afficherImagesParSeconde(String fps) {
        canvasPartie.afficherFps(fps);
    }

    public void afficherPong2d(MondePong2d mondePong2d) {
        mondePong2d.drawOn(canvasPartie);
    }

    public void afficherNomJoueur(Position position, String nom) {

        Label labelNom = labelsNoms.get(position);

        labelNom.setText(nom);

    }

    public void afficherScore(Position position, int score) {

        Label labelScore = labelsScores.get(position);

        labelScore.setText(String.valueOf(score));
    }

    private void installerEvtClicSouris() {

        canvasPartie.onMouseEvent(mouseEventNtro -> {

            if (mouseEventNtro.mouseEventFx().getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                Ntro.newEvent(EvtClicSouris.class)
                        .setMouseEvent(mouseEventNtro)
                        .trigger();
            }
        });
    }
}
