package fighting_2d.frontal.donnees;

import ca.ntro.app.frontend.ViewData;
import fighting_2d.commun.monde2d.MondeFighting2D;
import fighting_2d.frontal.vues.VueCreationProfil;

public class DonneesVueProfils implements ViewData {

    private MondeFighting2D mondeFighting2D = new MondeFighting2D();
    private String fpsCourant = "0";

    public void afficherSur(VueCreationProfil vueCreationProfil) {

        vueCreationProfil.viderCanvas();
        vueCreationProfil.afficherImagesParSeconde("FPS :" + fpsCourant);
        vueCreationProfil.afficherFighting2D(mondeFighting2D);
    }

}
