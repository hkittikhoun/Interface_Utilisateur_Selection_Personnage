package pong.frontal;

import ca.ntro.app.Ntro;
import ca.ntro.app.events.EventRegistrar;
import ca.ntro.app.frontend.FrontendFx;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.session.SessionRegistrar;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import pong.frontal.donnees.DonneesVuePartie;
import pong.frontal.evenements.EvtActionJoueur;
import pong.frontal.evenements.EvtChangerLangue;
import pong.frontal.evenements.EvtChangerRegion;
import pong.frontal.evenements.EvtChangerTaillePolice;
import pong.frontal.evenements.EvtClicSouris;
import pong.frontal.fragments.FragmentRendezVous;
import pong.frontal.fragments.FragmentRendezVousComplet;
import pong.frontal.fragments.FragmentUneLangue;
import pong.frontal.taches.AfficherFileAttente;
import pong.frontal.taches.AfficherPartie;
import pong.frontal.taches.CreerVues;
import pong.frontal.taches.Navigation;
import pong.frontal.taches.PremierAffichage;
import pong.frontal.vues.VueFileAttente;
import pong.frontal.vues.VuePartie;
import pong.frontal.vues.VueRacine;

public class FrontalPong implements FrontendFx {

    @Override
    public void createTasks(FrontendTasks tasks) {

        CreerVues.creerTaches(tasks);
        PremierAffichage.creerTaches(tasks);
        Navigation.creertaches(tasks);
        AfficherFileAttente.creerTaches(tasks);
        AfficherPartie.creerTaches(tasks);
    }

    @Override
    public void registerEvents(EventRegistrar registrar) {

        registrar.registerEvent(EvtChangerTaillePolice.class);
        registrar.registerEvent(EvtChangerLangue.class);
        registrar.registerEvent(EvtActionJoueur.class);
        registrar.registerEvent(EvtClicSouris.class);
        registrar.registerEvent(EvtChangerRegion.class);
    }

    @Override
    public void registerViews(ViewRegistrarFx registrar) {

        registrar.registerView(VueRacine.class, "/vues/racine.fxml");
        registrar.registerView(VueFileAttente.class, "/vues/file_attente.fxml");
        registrar.registerView(VuePartie.class, "/vues/partie.fxml");

        // registrar.registerStylesheet("/style/dev.css");
        registrar.registerStylesheet("/style/prod.css");

        registrar.registerDefaultLocale(Ntro.buildLocale("fr"));
        registrar.registerTranslations(Ntro.buildLocale("fr"), "/traductions/fr.properties");
        registrar.registerTranslations(Ntro.buildLocale("en"), "/traductions/en.properties");

        registrar.registerFragment(FragmentRendezVous.class, "/fragments/rendez_vous.fxml");
        registrar.registerFragment(FragmentRendezVousComplet.class, "/fragments/rendez_vous_complet.fxml");
        registrar.registerFragment(FragmentUneLangue.class, "/fragments/une_langue.fxml");

        registrar.registerViewData(DonneesVuePartie.class);
    }

    @Override
    public void registerSessionClass(SessionRegistrar registrar) {
        registrar.registerSessionClass(SessionPong.class);
    }

}
