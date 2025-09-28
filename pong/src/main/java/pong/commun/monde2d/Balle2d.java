package pong.commun.monde2d;

import ca.ntro.app.Ntro;
import ca.ntro.app.fx.controls.World2dMouseEventFx;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import pong.commun.enums.Position;
import pong.commun.messages.MsgAjouterPoint;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Balle2d extends ObjetPong2d {

    private static final double EPSILON = 1;

    private Palette2d paletteGauche;
    private Palette2d paletteDroite;

    private Image image = new Image("/images/balle.png");

    private static final double DUREE_ANIMATION = 2.5;

    private double secondes_restantes_animation = 0;

    private transient Media sonPoc = new Media(Balle2d.class.getResource("/sons/poc.wav").toString());

    public Balle2d(Palette2d paletteGauche, Palette2d paletteDroite) {
        super();

        this.paletteGauche = paletteGauche;
        this.paletteDroite = paletteDroite;
    }

    public Balle2d() {
        super();
    }

    @Override
    public void initialize() {
        setWidth(10);
        setHeight(10);

        choisirEtatInitial();
    }

    @Override
    public void drawOnWorld(GraphicsContext gc) {
        double echelonnage = 1.0;
        if (secondes_restantes_animation > 0) {
            double pourcentage_ecoule_animation = secondes_restantes_animation / DUREE_ANIMATION;
            echelonnage = 1 + 2 * Math.sin(Math.PI * pourcentage_ecoule_animation);
        }

        gc.drawImage(image,
                getTopLeftX(),
                getTopLeftY(),
                getWidth() * echelonnage,
                getHeight() * echelonnage);
    }

    @Override
    protected boolean onMouseEvent(World2dMouseEventFx mouseEvent) {
        return false;
    }

    @Override
    public String id() {
        return "balle";
    }

    @Override
    public void onTimePasses(double secondsElapsed) {
        super.onTimePasses(secondsElapsed);

        if (secondes_restantes_animation > 0) {
            secondes_restantes_animation -= secondsElapsed;
        }

        if (balleFrappeMurGauche()) {

            choisirEtatInitial();
            ajouterPoint(Position.DROITE);

        } else if (balleFrappeMurDroit()) {

            choisirEtatInitial();
            ajouterPoint(Position.GAUCHE);

        } else if (balleFrappePalette(paletteGauche)) {

            balleRebondiSurPalette(paletteGauche);

        } else if (balleFrappePalette(paletteDroite)) {

            balleRebondiSurPalette(paletteDroite);

        } else if (balleFrappePlafond()) {

            balleRebondiSurPlafond();

        } else if (balleFrappePlancher()) {

            balleRebondiSurPlancher();
        }
    }

    private void jouerSonPoc() {
        new MediaPlayer(sonPoc).play();
    }

    private void choisirEtatInitial() {

        setTopLeftY(10);
        setSpeedY(100 + Ntro.random().nextInt(20));

        if (Ntro.random().nextBoolean()) {

            setTopLeftX(100);
            setSpeedX(100 + Ntro.random().nextInt(20));

        } else {

            setTopLeftX(MondePong2d.LARGEUR_MONDE - 100 - getWidth());
            setSpeedX(-100 - Ntro.random().nextInt(20));

        }
    }

    private void ajouterPoint(Position position) {
        Ntro.newMessage(MsgAjouterPoint.class)
                .setPosition(position)
                .send();
    }

    private boolean balleFrappePlancher() {
        return getTopLeftY() + getHeight() >= getWorld2d().getHeight();
    }

    private boolean balleFrappePlafond() {
        return getTopLeftY() <= 0;
    }

    private boolean balleFrappeMurDroit() {
        return getTopLeftX() + getWidth() >= getWorld2d().getWidth();
    }

    private boolean balleFrappeMurGauche() {
        return getTopLeftX() <= 0;
    }

    private void balleRebondiSurPlancher() {
        jouerSonPoc();

        setTopLeftY(getWorld2d().getHeight() - this.getHeight() - EPSILON);
        setSpeedY(-getSpeedY());
    }

    private void balleRebondiSurPlafond() {
        jouerSonPoc();

        setTopLeftY(0 + EPSILON);
        setSpeedY(-getSpeedY());
    }

    private void balleRebondiSurMurDroit() {
        setTopLeftX(getWorld2d().getWidth() - this.getWidth() - EPSILON);
        setSpeedX(-getSpeedX());
    }

    private void balleRebondiSurMurGauche() {
        setTopLeftX(0 + EPSILON);
        setSpeedX(-getSpeedX());
    }

    private boolean balleFrappePalette(Palette2d palette) {
        return collidesWith(palette);
    }

    private void balleRebondiSurPalette(Palette2d palette) {
        jouerSonPoc();

        if (getTopLeftX() < palette.getTopLeftX()) {

            setTopLeftX(palette.getTopLeftX() - getWidth() - EPSILON);

        } else {

            setTopLeftX(palette.getTopLeftX() + palette.getWidth() + EPSILON);

        }

        setSpeedX(-getSpeedX());

        palette.insererEffet();
    }

    public void demarrerAnimation() {
        secondes_restantes_animation = DUREE_ANIMATION;
    }
}
