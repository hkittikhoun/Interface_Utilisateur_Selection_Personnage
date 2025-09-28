package fighting_2d.frontal.taches;

import static ca.ntro.app.tasks.frontend.FrontendTasks.*;

import ca.ntro.app.Ntro;
import ca.ntro.app.frontend.Tick;
import ca.ntro.app.modified.Modified;
import ca.ntro.app.tasks.frontend.FrontendTasks;
import fighting_2d.commun.modeles.ModeleCreationProfil;
import fighting_2d.frontal.SessionFighting2D;
import fighting_2d.frontal.donnees.DonneesVueCreationProfil;
import fighting_2d.frontal.evenements.EvtChangerRegion;
import fighting_2d.frontal.evenements.EvtPersoSelectionne;
import fighting_2d.frontal.evenements.EvtSouris;
import fighting_2d.frontal.vues.VueCreationProfil;

public class AfficherCreationProfil {

    public static void creerTaches(FrontendTasks tasks) {

        creerDonneesVueCreationProfil(tasks);

        tasks.taskGroup("AfficherCreationProfil")

                .waitsFor(created(DonneesVueCreationProfil.class))

                .waitsFor("afficherVueCreationProfil")

                .contains(subTasks -> {

                    prochaineImagePartie(subTasks);

                    afficherCreationProfil(subTasks);

                    reagirSouris(subTasks);

                    changerRegion(subTasks);

                    reagirPersoSelectionne(subTasks);

                });
    }

    private static void reagirPersoSelectionne(FrontendTasks tasks) {
        tasks.task("reagirPersoSelectionne")
                .waitsFor(event(EvtPersoSelectionne.class))
                .waitsFor("afficherVueCreationProfil")
                .waitsFor(created(VueCreationProfil.class))
                .executes(inputs -> {
                    EvtPersoSelectionne evt = inputs.get(event(EvtPersoSelectionne.class));
                    VueCreationProfil vue = inputs.get(created(VueCreationProfil.class));
                    vue.afficherPersoSelectionne(evt.getIndex());
                });
    }

    private static void changerRegion(FrontendTasks subTasks) {
        subTasks.task("changerRegion")

                .waitsFor(event(EvtChangerRegion.class))

                .executes(inputs -> {

                    SessionFighting2D session = Ntro.session();
                    EvtChangerRegion evtChangerRegion = inputs.get(event(EvtChangerRegion.class));

                    evtChangerRegion.appliquerA(session);

                });
    }

    private static void reagirSouris(FrontendTasks tasks) {

        tasks.task("reagirClicSouris")

                .waitsFor(created(DonneesVueCreationProfil.class))

                .waitsFor(event(EvtSouris.class))

                .executes(inputs -> {

                    DonneesVueCreationProfil donneesVueCreationProfil = inputs
                            .get(created(DonneesVueCreationProfil.class));
                    EvtSouris evtSouris = inputs.get(event(EvtSouris.class));

                    evtSouris.appliquerA(donneesVueCreationProfil);

                });
    }

    private static void prochaineImagePartie(FrontendTasks subTasks) {
        subTasks.task("prochaineImagePartie")

                .waitsFor("afficherVueCreationProfil")

                .waitsFor(created(DonneesVueCreationProfil.class))

                .waitsFor(created(VueCreationProfil.class))

                .waitsFor(clock().nextTick())

                .executes(inputs -> {

                    DonneesVueCreationProfil donneesVueCreationProfils = inputs
                            .get(created(DonneesVueCreationProfil.class));
                    VueCreationProfil vueCreationProfil = inputs.get(created(VueCreationProfil.class));
                    Tick tick = inputs.get(clock().nextTick());

                    donneesVueCreationProfils.afficherSur(vueCreationProfil);
                });
    }

    private static void creerDonneesVueCreationProfil(FrontendTasks tasks) {
        tasks.task(create(DonneesVueCreationProfil.class))

                .executesAndReturnsValue(inputs -> {

                    return new DonneesVueCreationProfil();
                });
    }

    private static void afficherCreationProfil(FrontendTasks subTasks) {

        subTasks.task("afficherCreationProfil")

                .waitsFor(created(VueCreationProfil.class))

                .waitsFor(modified(ModeleCreationProfil.class))

                .executes(inputs -> {

                    SessionFighting2D session = Ntro.session();

                    VueCreationProfil vueCreationProfil = inputs.get(created(VueCreationProfil.class));
                    Modified<ModeleCreationProfil> creationProfil = inputs.get(modified(ModeleCreationProfil.class));

                    creationProfil.currentValue().afficherSur(vueCreationProfil);

                    session.afficherRegionCourante(vueCreationProfil);

                });
    }
}
