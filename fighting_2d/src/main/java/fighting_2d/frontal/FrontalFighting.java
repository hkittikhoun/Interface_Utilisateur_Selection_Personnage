package fighting_2d.frontal;

import ca.ntro.app.Ntro;
import ca.ntro.app.events.EventRegistrar;
import ca.ntro.app.frontend.FrontendFx;
import ca.ntro.app.frontend.ViewRegistrarFx;
import ca.ntro.app.session.SessionRegistrar;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import fighting_2d.commun.modeles.ModeleCreationProfil;
import fighting_2d.frontal.donnees.DonneesVueCreationProfil;
import fighting_2d.frontal.evenements.EvtAfficherAcceuil;
import fighting_2d.frontal.evenements.EvtAfficherCreationProfil;
import fighting_2d.frontal.evenements.EvtChangerLangue;
import fighting_2d.frontal.evenements.EvtChangerRegion;
import fighting_2d.frontal.evenements.EvtChangerTaillePolice;
import fighting_2d.frontal.evenements.EvtSouris;
import fighting_2d.frontal.fragments.FragmentUnProfil;
import fighting_2d.frontal.taches.AfficherCreationProfil;
import fighting_2d.frontal.taches.CreerVues;
import fighting_2d.frontal.taches.Navigation;
import fighting_2d.frontal.taches.PremierAffichage;
import fighting_2d.frontal.vues.VueAcceuil;
import fighting_2d.frontal.vues.VueCreationProfil;
import fighting_2d.frontal.vues.VueRacine;

public class FrontalFighting implements FrontendFx {

    @Override
    public void createTasks(FrontendTasks tasks) {

        ModeleCreationProfil.initialiserCaracteres();
        CreerVues.creerTaches(tasks);
        PremierAffichage.creerTaches(tasks);
        Navigation.creerTaches(tasks);
        AfficherCreationProfil.creerTaches(tasks);

    }

    @Override
    public void registerEvents(EventRegistrar registrar) {

        registrar.registerEvent(EvtAfficherCreationProfil.class);

        registrar.registerEvent(EvtAfficherAcceuil.class);

        registrar.registerEvent(EvtChangerTaillePolice.class);

        registrar.registerEvent(EvtChangerLangue.class);

        registrar.registerEvent(EvtSouris.class);

        registrar.registerEvent(EvtChangerRegion.class);

    }

    @Override
    public void registerViews(ViewRegistrarFx registrar) {

        registrar.registerView(VueRacine.class, "/vues/racine.fxml");
        registrar.registerView(VueCreationProfil.class, "/vues/creation_profil.fxml");
        registrar.registerView(VueAcceuil.class, "/vues/acceuil.fxml");

        // registrar.registerStylesheet("/style/dev.css");
        registrar.registerStylesheet("/style/prod.css");

        registrar.registerDefaultLocale(Ntro.buildLocale("fr"));
        registrar.registerTranslations(Ntro.buildLocale("fr"), "/traductions/fr.properties");
        registrar.registerTranslations(Ntro.buildLocale("en"), "/traductions/en.properties");

        registrar.registerFragment(FragmentUnProfil.class, "/fragments/un_profil.fxml");

        registrar.registerViewData(DonneesVueCreationProfil.class);

    }

    @Override
    public void registerSessionClass(SessionRegistrar registrar) {
        registrar.registerSessionClass(SessionFighting2D.class);
    }

}
