package fighting_2d.dorsal.taches;

import static ca.ntro.app.tasks.backend.BackendTasks.*;

import ca.ntro.app.tasks.backend.BackendTasks;
import fighting_2d.commun.messages.MsgAjouterProfil;
import fighting_2d.commun.messages.MsgInitialiserCreationProfil;
import fighting_2d.commun.messages.MsgProfilCourant;
import fighting_2d.commun.messages.MsgRetirerProfil;
import fighting_2d.commun.modeles.ModeleCreationProfil;

public class ModifierCreationProfil {

    public static void creerTaches(BackendTasks tasks) {

        tasks.taskGroup("ModifierFileAttente")

                .waitsFor(model(ModeleCreationProfil.class))

                .contains(subTasks -> {

                    initialiser(subTasks);

                    ajouterCreationProfil(subTasks);

                    retirerProfilCourant(subTasks);

                    // selectionnerProfilCourant(subTasks);

                });
    }

    private static void retirerProfilCourant(BackendTasks subTasks) {
        subTasks.task("retirerProfilCourant")

                .waitsFor(model(ModeleCreationProfil.class))

                .waitsFor(message(MsgRetirerProfil.class))

                .executes(inputs -> {

                    MsgRetirerProfil msgRetirerProfil = inputs.get(message(MsgRetirerProfil.class));
                    ModeleCreationProfil creationProfil = inputs.get(model(ModeleCreationProfil.class));

                    msgRetirerProfil.retirerDe(creationProfil);
                });
    }

    private static void initialiser(BackendTasks subTasks) {
        subTasks.task("initialiser")

                .waitsFor(model(ModeleCreationProfil.class))

                .waitsFor(message(MsgInitialiserCreationProfil.class))

                .executes(inputs -> {

                    MsgInitialiserCreationProfil msgInitialiserCreationProfil = inputs
                            .get(message(MsgInitialiserCreationProfil.class));
                    ModeleCreationProfil creationProfil = inputs.get(model(ModeleCreationProfil.class));

                    msgInitialiserCreationProfil.appliquerA(creationProfil);
                });
    }

    private static void selectionnerProfilCourant(BackendTasks subTasks) {
        subTasks.task("selectionnerProfilCourant")

                .waitsFor(model(ModeleCreationProfil.class))

                .waitsFor(message(MsgProfilCourant.class))

                .executes(inputs -> {

                    MsgProfilCourant msgProfilCourant = inputs.get(message(MsgProfilCourant.class));
                    ModeleCreationProfil creationProfil = inputs.get(model(ModeleCreationProfil.class));

                    msgProfilCourant.ajouterA(creationProfil);
                });
    }

    private static void ajouterCreationProfil(BackendTasks subTasks) {
        subTasks.task("ajouterCreationProfil")

                .waitsFor(model(ModeleCreationProfil.class))

                .waitsFor(message(MsgAjouterProfil.class))

                .executes(inputs -> {

                    MsgAjouterProfil msgAjouterProfil = inputs.get(message(MsgAjouterProfil.class));
                    ModeleCreationProfil creationProfil = inputs.get(model(ModeleCreationProfil.class));

                    msgAjouterProfil.ajouterA(creationProfil);

                });
    }
}
