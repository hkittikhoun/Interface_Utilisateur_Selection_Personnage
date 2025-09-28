package fighting_2d.frontal.taches;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.Ntro;
import ca.ntro.app.services.Window;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import fighting_2d.frontal.SessionFighting2D;
import fighting_2d.frontal.evenements.EvtAfficherAcceuil;
import fighting_2d.frontal.evenements.EvtAfficherCreationProfil;
import fighting_2d.frontal.evenements.EvtChangerLangue;
import fighting_2d.frontal.evenements.EvtChangerTaillePolice;
import fighting_2d.frontal.vues.VueAcceuil;
import fighting_2d.frontal.vues.VueCreationProfil;
import fighting_2d.frontal.vues.VueRacine;

public class Navigation {

    public static void creerTaches(FrontendTasks tasks) {

        tasks.taskGroup("Navigation")

                .waitsFor("PremierAffichage")

                .contains(subTasks -> {

                    afficherVueCreationProfil(subTasks);

                    afficherVueAcceuil(subTasks);

                    changerTaillePolice(subTasks);

                    changerLangue(subTasks);

                });
    }

    private static void changerLangue(FrontendTasks subTasks) {
        subTasks.task("changerLangue")

                .waitsFor(event(EvtChangerLangue.class))

                .executes(inputs -> {

                    EvtChangerLangue evtChangerLangue = inputs.get(event(EvtChangerLangue.class));

                    evtChangerLangue.appliquer();

                });
    }

    private static void changerTaillePolice(FrontendTasks subTasks) {
        subTasks.task("changerTaillePolice")

                .waitsFor(window())

                .waitsFor(event(EvtChangerTaillePolice.class))

                .executes(inputs -> {

                    EvtChangerTaillePolice evtChangerTaillePolice = inputs.get(event(EvtChangerTaillePolice.class));
                    Window window = inputs.get(window());

                    evtChangerTaillePolice.appliquerA(window);

                });
    }

    private static void afficherVueAcceuil(FrontendTasks subTasks) {

        subTasks.task("afficherVueAcceuil")

                .waitsFor(created(VueRacine.class))

                .waitsFor(created(VueAcceuil.class))

                .waitsFor(event(EvtAfficherAcceuil.class))

                .executes(inputs -> {

                    SessionFighting2D session = Ntro.session();

                    VueRacine vueRacine = inputs.get(created(VueRacine.class));
                    VueAcceuil vueAcceuil = inputs.get(created(VueAcceuil.class));
                    EvtAfficherAcceuil evtAfficherAcceuil = inputs.get(event(EvtAfficherAcceuil.class));

                    evtAfficherAcceuil.appliquerA(session)
                            .appliquerA(vueRacine, vueAcceuil);
                });
    }

    private static void afficherVueCreationProfil(FrontendTasks subTasks) {

        subTasks.task("afficherVueCreationProfil")

                .waitsFor(created(VueRacine.class))

                .waitsFor(created(VueCreationProfil.class))

                .waitsFor(event(EvtAfficherCreationProfil.class))

                .executes(inputs -> {

                    SessionFighting2D session = Ntro.session();

                    VueRacine vueRacine = inputs.get(created(VueRacine.class));
                    VueCreationProfil vueCreationProfil = inputs.get(created(VueCreationProfil.class));
                    EvtAfficherCreationProfil evtAfficherCreationProfil = inputs
                            .get(event(EvtAfficherCreationProfil.class));

                    evtAfficherCreationProfil.appliquerA(session)
                            .appliquerA(vueRacine, vueCreationProfil);
                });
    }
}
