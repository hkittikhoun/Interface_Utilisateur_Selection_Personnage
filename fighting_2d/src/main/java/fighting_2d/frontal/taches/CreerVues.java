package fighting_2d.frontal.taches;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import fighting_2d.frontal.fragments.FragmentUnProfil;
import fighting_2d.frontal.vues.VueAcceuil;
import fighting_2d.frontal.vues.VueCreationProfil;
import fighting_2d.frontal.vues.VueRacine;

public class CreerVues {

    public static void creerTaches(FrontendTasks tasks) {
        tasks.taskGroup("CreerVues")

                .waitsFor("ViewLoaders")

                .contains(subTasks -> {

                    creerVueRacine(subTasks);

                    creerVueCreationProfil(subTasks);

                    creerVueAcceuil(subTasks);

                });
    }

    private static void creerVueRacine(FrontendTasks subTasks) {

        subTasks.task(create(VueRacine.class))

                .waitsFor(viewLoader(VueRacine.class))

                .executesAndReturnsValue(inputs -> {

                    ViewLoader<VueRacine> viewLoader = inputs.get(viewLoader(VueRacine.class));

                    VueRacine vueRacine = viewLoader.createView();

                    vueRacine.initialiserComboLangues();

                    return vueRacine;
                });
    }

    private static void creerVueCreationProfil(FrontendTasks subTasks) {

        subTasks.task(create(VueCreationProfil.class))

                .waitsFor(viewLoader(VueCreationProfil.class))

                .waitsFor(viewLoader(FragmentUnProfil.class))

                .executesAndReturnsValue(inputs -> {

                    ViewLoader<VueCreationProfil> viewLoader = inputs.get(viewLoader(VueCreationProfil.class));

                    ViewLoader<FragmentUnProfil> viewLoaderUnProfil = inputs.get(viewLoader(FragmentUnProfil.class));

                    VueCreationProfil vueCreationProfil = viewLoader.createView();

                    vueCreationProfil.setViewLoaderUnProfil(viewLoaderUnProfil);

                    vueCreationProfil.initialiserComboProfils();

                    return vueCreationProfil;
                });
    }

    private static void creerVueAcceuil(FrontendTasks subTasks) {

        subTasks.task(create(VueAcceuil.class))

                .waitsFor(viewLoader(VueAcceuil.class))

                .executesAndReturnsValue(inputs -> {

                    ViewLoader<VueAcceuil> viewLoader = inputs.get(viewLoader(VueAcceuil.class));

                    VueAcceuil vueAcceuil = viewLoader.createView();

                    return vueAcceuil;
                });
    }
}
