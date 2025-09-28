package fighting_2d.frontal.controles;

import ca.ntro.app.fx.controls.ResizableWorld2dCanvasFx;
import fighting_2d.commun.monde2d.MondeFighting2D;

public class CanvasCreationProfil extends ResizableWorld2dCanvasFx {

    @Override
    protected void initialize() {
        setInitialWorldSize(MondeFighting2D.LARGEUR_MONDE, MondeFighting2D.HAUTEUR_MONDE);
    }

    public void afficherFps(String fps) {
        drawOnCanvas(gc -> {
            gc.fillText(fps, 0, 12);
        });
    }

}
