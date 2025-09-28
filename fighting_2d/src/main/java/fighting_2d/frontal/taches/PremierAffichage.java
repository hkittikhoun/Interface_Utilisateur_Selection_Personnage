package fighting_2d.frontal.taches;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.services.Window;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import fighting_2d.frontal.SessionFighting2D;
import fighting_2d.frontal.vues.VueAcceuil;
import fighting_2d.frontal.vues.VueCreationProfil;
import fighting_2d.frontal.vues.VueRacine;

public class PremierAffichage {

    public static void creerTaches(FrontendTasks tasks) {

        tasks.taskGroup("PremierAffichage")

                .waitsFor("CreerVues")

                .contains(subTasks -> {

                    installerVueRacine(subTasks);

                    afficherFenetre(subTasks);

                    choisirPremiereVue(subTasks);
                });
    }

    private static void installerVueRacine(FrontendTasks subTasks) {

        subTasks.task("installerVueRacine")

                .waitsFor(window())

                .waitsFor(created(VueRacine.class))

                .executes(inputs -> {

                    VueRacine vueRacine = inputs.get(created(VueRacine.class));
                    Window window = inputs.get(window());

                    window.installRootView(vueRacine);
                });
    }

    private static void afficherFenetre(FrontendTasks subTasks) {

        subTasks.task("afficherFenetre")

                .waitsFor(window())

                .executes(inputs -> {

                    Window window = inputs.get(window());

                    window.setTitle("2D Fighting");

                    window.show();

                });
    }

    private static void choisirPremiereVue(FrontendTasks subTasks) {

        subTasks.task("choisirPremiereVue")

                .waitsFor(created(VueRacine.class))

                .waitsFor(created(VueCreationProfil.class))

                .waitsFor(created(VueAcceuil.class))

                .waitsFor(session(SessionFighting2D.class))

                .executes(inputs -> {

                    SessionFighting2D session = inputs.get(session(SessionFighting2D.class));

                    session.observerRegionCourante();
                    session.envoyerEvtPourAfficherVueCourante();
                });
    }
}
