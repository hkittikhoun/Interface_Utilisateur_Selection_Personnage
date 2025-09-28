package pong.frontal.donnees;

import ca.ntro.app.frontend.ViewData;
import ca.ntro.app.fx.controls.World2dMouseEventFx;
import ca.ntro.core.NtroCore;
import pong.commun.enums.Action;
import pong.commun.enums.Position;
import pong.commun.monde2d.MondePong2d;
import pong.frontal.vues.VuePartie;

public class DonneesVuePartie implements ViewData {

    private MondePong2d mondePong2d = new MondePong2d();
    private String fpsCourant = "0";

    private static long CALCULER_FPS_A_CHAQUE_X_MILLISECONDES = 200;

    private long horodatageDernierCalculFps = NtroCore.time().nowMilliseconds();
    private long imagesAfficheesDepuisDernierCalculFps = 0;

    public void afficherSur(VuePartie vuePartie) {

        calculerFpsSiNecessaire();

        vuePartie.viderCanvas();
        vuePartie.afficherImagesParSeconde("FPS: " + fpsCourant);
        vuePartie.afficherPong2d(mondePong2d);

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

    public void reagirTempsQuiPasse(double elapsedTime) {
        mondePong2d.onTimePasses(elapsedTime);
    }

    public void appliquerActionJoueur(Position position, Action action) {
        mondePong2d.appliquerActionJoueur(position, action);
    }

    public void reagirClicSouris(World2dMouseEventFx mouseEvent) {
        mondePong2d.dispatchMouseEvent(mouseEvent);
    }
}
