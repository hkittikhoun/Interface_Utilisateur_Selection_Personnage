package pong.commun;

import ca.ntro.app.common.ServerRegistrar;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import pong.commun.messages.MsgActionAutreJoueur;
import pong.commun.messages.MsgAjouterPoint;
import pong.commun.messages.MsgAjouterRendezVous;
import pong.commun.messages.MsgCreerPartie;
import pong.commun.messages.MsgInitialiserFileAttente;
import pong.commun.messages.MsgRejoindreRendezVous;
import pong.commun.messages.MsgRetirerRendezVous;
import pong.commun.modeles.ModeleFileAttente;
import pong.commun.modeles.ModelePartie;
import pong.commun.valeurs.InfoJoueur;
import pong.commun.valeurs.Joueur;
import pong.commun.valeurs.RendezVous;
import pong.commun.valeurs.RendezVousComplet;

public class Declarations {

    public static void declarerMessages(MessageRegistrar registrar) {
        registrar.registerMessage(MsgAjouterRendezVous.class);
        registrar.registerMessage(MsgRetirerRendezVous.class);
        registrar.registerMessage(MsgAjouterPoint.class);
        registrar.registerMessage(MsgActionAutreJoueur.class);
        registrar.registerMessage(MsgRejoindreRendezVous.class);
        registrar.registerMessage(MsgCreerPartie.class);
        registrar.registerMessage(MsgInitialiserFileAttente.class);
    }

    public static void declarerModeles(ModelRegistrar registrar) {
        registrar.registerModel(ModeleFileAttente.class);

        registrar.registerValue(Joueur.class);
        registrar.registerValue(RendezVous.class);
        registrar.registerValue(RendezVousComplet.class);

        registrar.registerModel(ModelePartie.class);
        registrar.registerValue(InfoJoueur.class);
    }

    public static void declarerServeur(ServerRegistrar registrar) {

        registrar.registerName("localhost");
        registrar.registerPort(8002);
    }

}