package fighting_2d.commun.monde2d;

import ca.ntro.app.fx.controls.World2dMouseEventFx;
import ca.ntro.app.world2d.Object2dFx;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class PersoFighting2d extends Object2dFx<MondeFighting2D> {

    private Image img;
    private boolean isHovered = false;
    private String id;

    public PersoFighting2d() {
        super();
    }

    public PersoFighting2d(String id, String img, Double x, Double y) {
        this.id = id;
        this.img = new Image(img);
        setTopLeftX(x);
        setTopLeftY(y);
    }

    public void setImage(Image img) {
        this.img = img;
    }

    @Override
    public void initialize() {
        setWidth(75);
        setHeight(75);
    }

    @Override
    protected boolean onMouseEvent(World2dMouseEventFx mouseEvent) {
        if (mouseEvent.mouseEventFx().getEventType().equals(MouseEvent.MOUSE_MOVED)) {
            this.getWorld2d().hoveringPersonnage(this);
            isHovered = true;
            return true;
        }
        return false;
    }

    @Override
    public void drawOnWorld(GraphicsContext gc) {
        gc.save();

        boolean isSelected = this.equals(getWorld2d().getSelectedPerso());
        double scaleFactor = (isHovered && !isSelected) ? 1.2 : 1.0;

        double scaledWidth = getWidth() * scaleFactor;
        double scaledHeight = getHeight() * scaleFactor;

        double topLeftX = getTopLeftX() - (scaledWidth - getWidth()) / 2;
        double topLeftY = getTopLeftY() - (scaledHeight - getHeight()) / 2;

        double arcWidth = 15;
        double arcHeight = 15;
        gc.beginPath();
        gc.moveTo(topLeftX + arcWidth, topLeftY);
        gc.arcTo(topLeftX + scaledWidth, topLeftY, topLeftX + scaledWidth, topLeftY + arcHeight, arcWidth);
        gc.arcTo(topLeftX + scaledWidth, topLeftY + scaledHeight, topLeftX + scaledWidth - arcWidth,
                topLeftY + scaledHeight, arcWidth);
        gc.arcTo(topLeftX, topLeftY + scaledHeight, topLeftX, topLeftY + scaledHeight - arcHeight, arcWidth);
        gc.arcTo(topLeftX, topLeftY, topLeftX + arcWidth, topLeftY, arcWidth);
        gc.closePath();
        gc.clip();

        gc.drawImage(img, topLeftX, topLeftY, scaledWidth, scaledHeight);

        gc.restore();
    }

    @Override
    public String id() {
        return id;
    }

    public void stopHovering() {
        isHovered = false;
    }

    public Image getImage() {
        return img;
    }

    public boolean contains(double mouseX, double mouseY) {
        return mouseX >= getTopLeftX() && mouseX <= getTopLeftX() + getWidth()
                && mouseY >= getTopLeftY() && mouseY <= getTopLeftY() + getHeight();
    }

    public int getX() {
        return (int) getTopLeftX();
    }

    public int getY() {
        return (int) getTopLeftY();
    }
}