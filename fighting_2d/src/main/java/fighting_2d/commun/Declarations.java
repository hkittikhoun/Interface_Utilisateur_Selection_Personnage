package fighting_2d.commun;

import ca.ntro.app.common.ServerRegistrar;
import ca.ntro.app.messages.MessageRegistrar;
import ca.ntro.app.models.ModelRegistrar;
import fighting_2d.commun.messages.MsgAjouterProfil;
import fighting_2d.commun.messages.MsgInitialiserCreationProfil;
import fighting_2d.commun.messages.MsgProfilCourant;
import fighting_2d.commun.messages.MsgRetirerProfil;
import fighting_2d.commun.modeles.ModeleCreationProfil;
import fighting_2d.commun.valeurs.Caractere;
import fighting_2d.commun.valeurs.Profil;

public class Declarations {

    public static void declarerMessages(MessageRegistrar registrar) {
        registrar.registerMessage(MsgAjouterProfil.class);
        registrar.registerMessage(MsgProfilCourant.class);
        registrar.registerMessage(MsgInitialiserCreationProfil.class);
        registrar.registerMessage(MsgRetirerProfil.class);
    }

    public static void declarerModeles(ModelRegistrar registrar) {
        registrar.registerModel(ModeleCreationProfil.class);

        registrar.registerValue(Profil.class);
        registrar.registerValue(Caractere.class);
    }

    public static void declarerServeur(ServerRegistrar registrar) {
        registrar.registerName("localhost");
        registrar.registerPort(8002);
    }

}