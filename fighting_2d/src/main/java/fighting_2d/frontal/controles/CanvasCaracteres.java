package fighting_2d.frontal.controles;

import ca.ntro.app.fx.controls.ResizableWorld2dCanvasFx;

public class CanvasCaracteres extends ResizableWorld2dCanvasFx {

    @Override
    protected void initialize() {
        setInitialWorldSize(50, 50);
    }

    public void afficherFPS(String fps) {
        drawOnCanvas(gc -> {
            gc.fillText(fps, 0, 12);
        });

    }

}
