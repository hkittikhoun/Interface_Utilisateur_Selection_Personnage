package pong.frontal.fragments;

import ca.ntro.app.Ntro;
import ca.ntro.app.frontend.ViewFx;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pong.commun.messages.MsgRetirerRendezVous;
import pong.frontal.SessionPong;
import pong.frontal.evenements.EvtAfficherPartie;

public class FragmentRendezVous extends ViewFx {

    @FXML
    private Button boutonDebuterPartie;

    @FXML
    private Button boutonRetirerRendezVous;

    @FXML
    private Label labelNomPremierJoueur;

    protected Button getBoutonDebuterPartie() {
        return boutonDebuterPartie;
    }

    @Override
    public void initialize() {
        Ntro.assertNotNull(boutonDebuterPartie);
        Ntro.assertNotNull(boutonRetirerRendezVous);
        Ntro.assertNotNull(labelNomPremierJoueur);

        installerEvtAfficherPartie();

    }

    private void installerEvtAfficherPartie() {

        getBoutonDebuterPartie().setOnAction(evtFx -> {

            Ntro.newEvent(EvtAfficherPartie.class)
                    .trigger();

        });
    }

    public void afficherNomPremierJoueur(String nomPremierJoueur) {
        labelNomPremierJoueur.setText(nomPremierJoueur);
    }

    public void memoriserIdRendezVous(String idRendezVous) {
        installerMsgRetirerRendezVous(idRendezVous);
        installerMsgRejoindreRendezVous(idRendezVous);
    }

    private void installerMsgRejoindreRendezVous(String idRendezVous) {

        boutonDebuterPartie.setOnAction(evtFx -> {

            actionBoutonPrincipal(idRendezVous);

        });
    }

    protected void actionBoutonPrincipal(String idRendezVous) {
        Ntro.session(SessionPong.class)
                .envoyerMsgRejoindreRendezVous(idRendezVous);
    }

    protected void installerMsgRetirerRendezVous(String idRendezVous) {

        boutonRetirerRendezVous.setOnAction(evtFx -> {

            Ntro.newMessage(MsgRetirerRendezVous.class)
                    .setIdRendezVous(idRendezVous)
                    .send();

        });
    }
}