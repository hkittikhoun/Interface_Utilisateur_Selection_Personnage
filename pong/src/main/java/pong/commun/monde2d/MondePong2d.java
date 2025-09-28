package pong.commun.monde2d;

import ca.ntro.app.fx.controls.ResizableWorld2dCanvasFx;
import ca.ntro.app.fx.controls.World2dMouseEventFx;
import ca.ntro.app.world2d.World2dFx;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pong.commun.enums.Action;
import pong.commun.enums.Position;

public class MondePong2d extends World2dFx {

    public static final double LARGEUR_MONDE = 640;
    public static final double HAUTEUR_MONDE = 360;

    private Balle2d balle;

    private Palette2d paletteGauche;
    private Palette2d paletteDroite;

    @Override
    protected void initialize() {
        setWidth(LARGEUR_MONDE);
        setHeight(HAUTEUR_MONDE);

        paletteGauche = new Palette2d("gauche", 30);
        paletteDroite = new Palette2d("droite", LARGEUR_MONDE - 40);

        addObject2d(paletteGauche);
        addObject2d(paletteDroite);

        balle = new Balle2d(paletteGauche, paletteDroite);
        addObject2d(balle);

    }

    @Override
    public void drawOn(ResizableWorld2dCanvasFx canvas) {
        canvas.drawOnWorld(gc -> {
            dessinerTerrain(gc);
        });

        super.drawOn(canvas);
    }

    private void dessinerTerrain(GraphicsContext gc) {
        gc.save();
        gc.setStroke(Color.LIGHTGREY);
        gc.setLineWidth(1);

        gc.strokeRect(0, 0, getWidth(), getHeight());

        gc.restore();
    }

    @Override
    protected void onMouseEventNotConsumed(World2dMouseEventFx mouseEvent) {
        paletteGauche.deselectionner();
        paletteDroite.deselectionner();

        balle.demarrerAnimation();
    }

    public void appliquerActionJoueur(Position position, Action action) {
        Palette2d palette = paletteParPosition(position);

        appliquerActionJoueur(palette, action);
    }

    private Palette2d paletteParPosition(Position position) {
        Palette2d palette;

        switch (position) {
            case GAUCHE:
            default:
                palette = paletteGauche;
                break;

            case DROITE:
                palette = paletteDroite;
                break;
        }

        return palette;
    }

    private void appliquerActionJoueur(Palette2d palette, Action action) {

        switch (action) {
            case MONTER:
                palette.monter();
                break;

            case DESCENDRE:
                palette.descendre();
                break;

            case ARRETER:
            default:
                palette.arreter();
        }
    }
}
