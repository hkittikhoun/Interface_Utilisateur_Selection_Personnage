package pong.frontal.taches;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.frontend.ViewLoader;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import pong.frontal.fragments.FragmentRendezVous;
import pong.frontal.fragments.FragmentRendezVousComplet;
import pong.frontal.fragments.FragmentUneLangue;
import pong.frontal.vues.VueFileAttente;
import pong.frontal.vues.VuePartie;
import pong.frontal.vues.VueRacine;

public class CreerVues {

    public static void creerTaches(FrontendTasks tasks) {

        tasks.taskGroup("CreerVues")

                .waitsFor("ViewLoaders")

                .contains(subTasks -> {

                    creerVueRacine(subTasks);

                    creerVueFileAttente(subTasks);

                    creerVuePartie(subTasks);

                });
    }

    private static void creerVueFileAttente(FrontendTasks subTasks) {

        subTasks.task(create(VueFileAttente.class))

                .waitsFor(viewLoader(VueFileAttente.class))

                .waitsFor(viewLoader(FragmentRendezVous.class))

                .waitsFor(viewLoader(FragmentRendezVousComplet.class))

                .executesAndReturnsValue(inputs -> {

                    ViewLoader<VueFileAttente> viewLoader = inputs.get(viewLoader(VueFileAttente.class));
                    ViewLoader<FragmentRendezVous> viewLoaderRendezVous = inputs
                            .get(viewLoader(FragmentRendezVous.class));
                    ViewLoader<FragmentRendezVousComplet> viewLoaderRendezVousComplet = inputs
                            .get(viewLoader(FragmentRendezVousComplet.class));

                    VueFileAttente vueFileAttente = viewLoader.createView();

                    vueFileAttente.setViewLoaderRendezVous(viewLoaderRendezVous);
                    vueFileAttente.setViewLoaderRendezVousComplet(viewLoaderRendezVousComplet);

                    return vueFileAttente;
                });
    }

    private static void creerVueRacine(FrontendTasks subTasks) {

        subTasks.task(create(VueRacine.class))

                .waitsFor(viewLoader(VueRacine.class))

                .waitsFor(viewLoader(FragmentUneLangue.class))

                .executesAndReturnsValue(inputs -> {

                    ViewLoader<VueRacine> viewLoader = inputs.get(viewLoader(VueRacine.class));

                    ViewLoader<FragmentUneLangue> viewLoaderUneLangue = inputs.get(viewLoader(FragmentUneLangue.class));

                    VueRacine vueRacine = viewLoader.createView();

                    vueRacine.setViewLoaderUneLangue(viewLoaderUneLangue);

                    vueRacine.initialiserComboLangues();

                    return vueRacine;
                });
    }

    private static void creerVuePartie(FrontendTasks subTasks) {

        subTasks.task(create(VuePartie.class))

                .waitsFor(viewLoader(VuePartie.class))

                .executesAndReturnsValue(inputs -> {

                    ViewLoader<VuePartie> viewLoader = inputs.get(viewLoader(VuePartie.class));

                    VuePartie vuePartie = viewLoader.createView();

                    return vuePartie;
                });
    }
}
