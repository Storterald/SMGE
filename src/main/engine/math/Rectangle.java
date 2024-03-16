package math;

import org.jetbrains.annotations.NotNull;
import org.joml.Vector2f;

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

    public Rectangle(Vector2f position, float width, float height) {
        if (width < 0.0f) throw new IllegalArgumentException("The width must be a positive number.");
        if (height < 0.0f) throw new IllegalArgumentException("The height must be a positive number.");
        this.x1 = position.x;
        this.y1 = position.y;
        this.x2 = position.x + width;
        this.y2 = position.y + height;
    }

    public Rectangle(Vector2f position, Vector2f size) {
        if (size.x < 0.0f) throw new IllegalArgumentException("The x size (width) must be a positive number.");
        if (size.y < 0.0f) throw new IllegalArgumentException("The y size (height) must be a positive number.");
        this.x1 = position.x;
        this.y1 = position.y;
        this.x2 = position.x + size.x;
        this.y2 = position.y + size.y;
    }

    public float[] toVerticesArray(@NotNull Vector2f windowSize) {
        float width = windowSize.x;
        float height = windowSize.y;

        return new float[] {
                x1 / width * 2 - 1, y2 / height * 2 - 1, 0.0f,
                x1 / width * 2 - 1, y1 / height * 2 - 1, 0.0f,
                x2 / width * 2 - 1, y1 / height * 2 - 1, 0.0f,
                x2 / width * 2 - 1, y2 / height * 2 - 1, 0.0f
        };
    }

    public Rectangle plus(@NotNull Rectangle toAdd) {
        return new Rectangle(x1 + toAdd.x1, y1 + toAdd.y1, x2 + toAdd.x2, y2 + toAdd.y2);
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
