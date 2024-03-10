package math;

public class Rectangle {
    
    private final float x1, y1, x2, y2;
    
    public Rectangle(float x1, float y1, float x2, float y2) {
        if (x2 < x1) throw new IllegalArgumentException("x2 must be higher or equal to x1");
        if (y2 < y1) throw new IllegalArgumentException("y2 must be higher or equal to y1");
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public Rectangle(float width, float height) {
        if (width < 0.0f) throw new IllegalArgumentException("The width must be a positive number.");
        if (height < 0.0f) throw new IllegalArgumentException("The height must be a positive number.");
        this.x1 = 0.0f;
        this.y1 = 0.0f;
        this.x2 = width;
        this.y2 = height;
    }

    public Rectangle(Vec2 position, float width, float height) {
        if (width < 0.0f) throw new IllegalArgumentException("The width must be a positive number.");
        if (height < 0.0f) throw new IllegalArgumentException("The height must be a positive number.");
        this.x1 = position.getX();
        this.y1 = position.getY();
        this.x2 = position.getX() + width;
        this.y2 = position.getY() + height;
    }

    public Rectangle(Vec2 position, Vec2 size) {
        if (size.getX() < 0.0f) throw new IllegalArgumentException("The x size (width) must be a positive number.");
        if (size.getY() < 0.0f) throw new IllegalArgumentException("The y size (height) must be a positive number.");
        this.x1 = position.getX();
        this.y1 = position.getY();
        this.x2 = position.getX() + size.getX();
        this.y2 = position.getY() + size.getY();
    }

    public float getX1() {
        return x1;
    }

    public float getY1() {
        return y1;
    }

    public float getX2() {
        return x2;
    }

    public float getY2() {
        return y2;
    }
}
