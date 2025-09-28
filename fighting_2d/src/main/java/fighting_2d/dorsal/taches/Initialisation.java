package fighting_2d.dorsal.taches;

import ca.ntro.app.Ntro;
import ca.ntro.app.tasks.backend.BackendTasks;
import fighting_2d.commun.Region;
import fighting_2d.commun.messages.MsgInitialiserCreationProfil;
import fighting_2d.commun.modeles.ModeleCreationProfil;

public class Initialisation {

    public static void creerTaches(BackendTasks tasks) {

        tasks.taskGroup("initialisation")

                .contains(subTasks -> {

                    initialiserCreationProfil(subTasks);

                });
    }

    private static void initialiserCreationProfil(BackendTasks subTasks) {
        subTasks.task("initialiserCreationProfil")

                .executes(inputs -> {

                    for (Region region : Region.values()) {

                        Ntro.newMessage(MsgInitialiserCreationProfil.class)
                                .setModelSelection(ModeleCreationProfil.class, region.name())
                                .setRegion(region)
                                .send();
                    }

                });
    }
}
