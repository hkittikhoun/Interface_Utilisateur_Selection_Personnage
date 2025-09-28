package pong.frontal.taches;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.Ntro;
import ca.ntro.app.services.Window;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import pong.frontal.SessionPong;
import pong.frontal.evenements.EvtAfficherFileAttente;
import pong.frontal.evenements.EvtAfficherPartie;
import pong.frontal.evenements.EvtChangerLangue;
import pong.frontal.evenements.EvtChangerTaillePolice;
import pong.frontal.vues.VueFileAttente;
import pong.frontal.vues.VuePartie;
import pong.frontal.vues.VueRacine;

public class Navigation {

    public static void creertaches(FrontendTasks tasks) {

        tasks.taskGroup("Navigation")

                .waitsFor("PremierAffichage")

                .contains(subTasks -> {

                    afficherVueFileAttente(subTasks);

                    afficherVuePartie(subTasks, tasks);

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

    private static void afficherVuePartie(FrontendTasks subTasks, FrontendTasks tasks) {

        subTasks.task("afficherVuePartie")

                .waitsFor(event(EvtAfficherPartie.class))

                .waitsFor(created(VueRacine.class))

                .waitsFor(created(VuePartie.class))

                .executes(inputs -> {

                    inputs.cancelTask("afficherVueFileAttente");

                    SessionPong session = Ntro.session();
                    EvtAfficherPartie evtAfficherPartie = inputs.get(event(EvtAfficherPartie.class));
                    VueRacine vueRacine = inputs.get(created(VueRacine.class));
                    VuePartie vuePartie = inputs.get(created(VuePartie.class));

                    evtAfficherPartie.appliquerA(session)
                            .appliquerA(vueRacine, vuePartie);

                });

    }

    private static void afficherVueFileAttente(FrontendTasks tasks) {

        tasks.task("afficherVueFileAttente")

                .waitsFor(event(EvtAfficherFileAttente.class))

                .waitsFor(created(VueRacine.class))

                .waitsFor(created(VueFileAttente.class))

                .executes(inputs -> {

                    inputs.cancelTask("afficherVuePartie");

                    SessionPong session = Ntro.session();
                    EvtAfficherFileAttente evtAfficherFileAttente = inputs.get(event(EvtAfficherFileAttente.class));
                    VueRacine vueRacine = inputs.get(created(VueRacine.class));
                    VueFileAttente vueFileAttente = inputs.get(created(VueFileAttente.class));

                    evtAfficherFileAttente.appliquerA(session)
                            .appliquerA(vueRacine, vueFileAttente);

                });
    }
}
