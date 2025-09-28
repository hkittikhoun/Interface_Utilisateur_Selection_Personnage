package pong.frontal.taches;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.Ntro;
import ca.ntro.app.modified.Modified;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import pong.commun.modeles.ModeleFileAttente;
import pong.frontal.SessionPong;
import pong.frontal.evenements.EvtChangerRegion;
import pong.frontal.vues.VueFileAttente;

public class AfficherFileAttente {

    public static void creerTaches(FrontendTasks tasks) {

        tasks.taskGroup("AfficherFileAttente")

                .waitsFor("afficherVueFileAttente")

                .contains(subTasks -> {

                    // Prêt à observer le modèle
                    afficherFileAttente(subTasks);

                    changerRegion(subTasks);

                });
    }

    private static void changerRegion(FrontendTasks subTasks) {
        subTasks.task("changerRegion")

                .waitsFor(event(EvtChangerRegion.class))

                .executes(inputs -> {

                    SessionPong session = Ntro.session();
                    EvtChangerRegion evtChangerRegion = inputs.get(event(EvtChangerRegion.class));

                    evtChangerRegion.appliquerA(session);

                });
    }

    private static void afficherFileAttente(FrontendTasks tasks) {

        tasks.task("afficherFileAttente")

                .waitsFor(modified(ModeleFileAttente.class))

                .waitsFor(created(VueFileAttente.class))

                .executes(inputs -> {

                    SessionPong session = Ntro.session();

                    VueFileAttente vueFileAttente = inputs.get(created(VueFileAttente.class));
                    Modified<ModeleFileAttente> fileAttente = inputs.get(modified(ModeleFileAttente.class));

                    fileAttente.currentValue().afficherSur(vueFileAttente);

                    session.afficherRegionCourante(vueFileAttente);
                });
    }
}
