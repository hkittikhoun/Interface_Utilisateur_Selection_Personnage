package fighting_2d.commun.monde2d;

import ca.ntro.app.Ntro;
import ca.ntro.app.fx.controls.ResizableWorld2dCanvasFx;
import ca.ntro.app.fx.controls.World2dMouseEventFx;
import ca.ntro.app.world2d.World2dFx;
import fighting_2d.frontal.evenements.EvtPersoSelectionne;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MondeFighting2D extends World2dFx {

    public static final double LARGEUR_MONDE = 500;
    public static final double HAUTEUR_MONDE = 100;

    private PersoFighting2d personnage1;
    private PersoFighting2d personnage2;
    private PersoFighting2d personnage3;
    private PersoFighting2d personnage4;

    private PersoFighting2d hoverPerso;
    private PersoFighting2d selectedPerso;

    @Override
    protected void initialize() {
        setWidth(LARGEUR_MONDE);
        setHeight(HAUTEUR_MONDE);

        double largeurPersonnage = 75;

        double espacement = (LARGEUR_MONDE - (4 * largeurPersonnage)) / 5;

        personnage1 = new PersoFighting2d("personnage1", "/images/perso1.jpg", espacement, 10.0);
        personnage2 = new PersoFighting2d("personnage2", "/images/perso2.jpg", espacement * 2 + largeurPersonnage,
                10.0);
        personnage3 = new PersoFighting2d("personnage3", "/images/perso3.jpg", espacement * 3 + 2 * largeurPersonnage,
                10.0);
        personnage4 = new PersoFighting2d("personnage4", "/images/perso4.jpg", espacement * 4 + 3 * largeurPersonnage,
                10.0);

        addObject2d(personnage1);
        addObject2d(personnage2);
        addObject2d(personnage3);
        addObject2d(personnage4);

        selectedPerso = personnage1;
    }

    @Override
    public void drawOn(ResizableWorld2dCanvasFx canvas) {
        canvas.drawOnWorld(gc -> {
            // dessinerTerrain(gc);
            dessinerSelection(gc);
        });
        super.drawOn(canvas);
    }

    private void dessinerSelection(GraphicsContext gc) {
        if (selectedPerso != null) {
            gc.save();
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(3);
            double arcWidth = 32;
            double arcHeight = 32;
            gc.strokeRoundRect(selectedPerso.getX() - 1, selectedPerso.getY() - 1,
                    selectedPerso.getWidth() + 2, selectedPerso.getHeight() + 2,
                    arcWidth, arcHeight);
            gc.restore();
        }
    }

    private void dessinerTerrain(GraphicsContext gc) {
        gc.save();
        gc.setStroke(Color.LIGHTGREY);
        gc.setLineWidth(1);

        gc.strokeRect(0, 0, LARGEUR_MONDE, HAUTEUR_MONDE);

        gc.restore();
    }

    @Override
    protected void onMouseEventNotConsumed(World2dMouseEventFx mouseEvent) {
        if (hoverPerso != null) {
            hoverPerso.stopHovering();
            hoverPerso = null;
        }

        if (mouseEvent.mouseEventFx().isPrimaryButtonDown()) {
            double mouseX = mouseEvent.worldX();
            double mouseY = mouseEvent.worldY();

            int index = -1;
            if (personnage1.contains(mouseX, mouseY)) {
                selectedPerso = personnage1;
                index = 0;
            } else if (personnage2.contains(mouseX, mouseY)) {
                selectedPerso = personnage2;
                index = 1;
            } else if (personnage3.contains(mouseX, mouseY)) {
                selectedPerso = personnage3;
                index = 2;
            } else if (personnage4.contains(mouseX, mouseY)) {
                selectedPerso = personnage4;
                index = 3;
            }

            Ntro.logger().info("indice du perso: " + index);

            if (index >= 0) {
                Ntro.newEvent(EvtPersoSelectionne.class)
                        .setIndex(index)
                        .trigger();
            }
        }
    }

    public void hoveringPersonnage(PersoFighting2d hoverPerso) {
        this.hoverPerso = hoverPerso;
    }

    public Object getSelectedPerso() {
        return selectedPerso;
    }
}