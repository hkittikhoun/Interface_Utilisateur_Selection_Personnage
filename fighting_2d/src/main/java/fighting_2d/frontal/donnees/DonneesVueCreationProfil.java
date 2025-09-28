package fighting_2d.frontal.donnees;

import ca.ntro.app.frontend.ViewData;
import ca.ntro.app.fx.controls.World2dMouseEventFx;
import ca.ntro.core.NtroCore;
import fighting_2d.commun.monde2d.MondeFighting2D;
import fighting_2d.frontal.vues.VueCreationProfil;

public class DonneesVueCreationProfil implements ViewData {

    private MondeFighting2D mondeFighting2D = new MondeFighting2D();
    private String fpsCourant = "0";

    private static long CALCULER_FPS_A_CHAQUE_X_MILLISECONDES = 200;

    private long horodatageDernierCalculFps = NtroCore.time().nowMilliseconds();
    private long imagesAfficheesDepuisDernierCalculFps = 0;

    public void afficherSur(VueCreationProfil vueCreationProfil) {

        calculerFpsSiNecessaire();

        vueCreationProfil.viderCanvas();
        vueCreationProfil.afficherImagesParSeconde("FPS : " + fpsCourant);
        vueCreationProfil.afficherFighting2D(mondeFighting2D);

        imagesAfficheesDepuisDernierCalculFps++;
    }

    private void calculerFpsSiNecessaire() {
        long horodatageMaintenant = NtroCore.time().nowMilliseconds();
        long millisecondesEcoulees = horodatageMaintenant - horodatageDernierCalculFps;

        if (millisecondesEcoulees > CALCULER_FPS_A_CHAQUE_X_MILLISECONDES) {
            calculerFpsMaintenant(millisecondesEcoulees);

            imagesAfficheesDepuisDernierCalculFps = 0;
            horodatageDernierCalculFps = horodatageMaintenant;
        }
    }

    private void calculerFpsMaintenant(long millisecondesEcoulees) {
        double secondesEcoulees = millisecondesEcoulees / 1E3;
        double fps = imagesAfficheesDepuisDernierCalculFps / secondesEcoulees;
        fpsCourant = String.valueOf(Math.round(fps));
    }

    public void reagirClicSouris(World2dMouseEventFx mouseEvent) {
        mondeFighting2D.dispatchMouseEvent(mouseEvent);
    }
}
