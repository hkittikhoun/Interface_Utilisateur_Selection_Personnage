package pong.frontal.fragments;

import ca.ntro.app.Ntro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pong.frontal.evenements.EvtAfficherPartie;

public class FragmentRendezVousComplet extends FragmentRendezVous {

    @FXML
    private Label labelNomDeuxiemeJoueur;

    @FXML
    private Label labelScorePremierJoueur;

    @FXML
    private Label labelScoreDeuxiemeJoueur;

    @Override
    public void initialize() {
        super.initialize();

        Ntro.assertNotNull(labelNomDeuxiemeJoueur);

        Ntro.assertNotNull(labelScorePremierJoueur);
        Ntro.assertNotNull(labelScoreDeuxiemeJoueur);

        installerEvtAfficherPartie();
    }

    private void installerEvtAfficherPartie() {

        getBoutonDebuterPartie().setOnAction(evtFx -> {

            Ntro.newEvent(EvtAfficherPartie.class)
                    .trigger();

        });
    }

    protected void actionBoutonPrincipal(String idRendezVous) {

        Ntro.newEvent(EvtAfficherPartie.class).trigger();
    }

    public void afficherNomDeuxiemeJoueur(String nomDeuxiemeJoueur) {
        labelNomDeuxiemeJoueur.setText(nomDeuxiemeJoueur);
    }

    public void afficherScorePremierJoueur(int scorePremierJoueur) {
        labelScorePremierJoueur.setText(String.valueOf(scorePremierJoueur));

    }

    public void afficherScoreDeuxiemeJoueur(int scoreDeuxiemeJoueur) {
        labelScoreDeuxiemeJoueur.setText(String.valueOf(scoreDeuxiemeJoueur));
    }
}