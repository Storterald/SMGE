package math;

public class Parallelepiped {

    private final float x1, y1, z1, x2, y2, z2;

    public Parallelepiped(float x1, float y1, float z1, float x2, float y2, float z2) {
        if (x2 < x1) throw new IllegalArgumentException("x2 must be higher or equal to x1");
        if (y2 < y1) throw new IllegalArgumentException("y2 must be higher or equal to y1");
        if (z2 < z1) throw new IllegalArgumentException("z2 must be higher or equal to z1");
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }

    public Parallelepiped(float width, float height, float depth) {
        if (width < 0.0f) throw new IllegalArgumentException("The width must be a positive number.");
        if (height < 0.0f) throw new IllegalArgumentException("The height must be a positive number.");
        if (depth < 0.0f) throw new IllegalArgumentException("The depth must be a positive number.");
        this.x1 = 0.0f;
        this.y1 = 0.0f;
        this.z1 = 0.0f;
        this.x2 = width;
        this.y2 = height;
        this.z2 = depth;
    }

    public Parallelepiped(Vec3 position, float width, float height, float depth) {
        if (width < 0.0f) throw new IllegalArgumentException("The width must be a positive number.");
        if (height < 0.0f) throw new IllegalArgumentException("The height must be a positive number.");
        if (depth < 0.0f) throw new IllegalArgumentException("The depth must be a positive number.");
        this.x1 = position.getX();
        this.y1 = position.getY();
        this.z1 = position.getZ();
        this.x2 = position.getX() + width;
        this.y2 = position.getY() + height;
        this.z2 = position.getZ() + depth;
    }

    public Parallelepiped(Vec3 position, Vec3 size) {
        if (size.getX() < 0.0f) throw new IllegalArgumentException("The x size (width) must be a positive number.");
        if (size.getY() < 0.0f) throw new IllegalArgumentException("The y size (height) must be a positive number.");
        if (size.getZ() < 0.0f) throw new IllegalArgumentException("The z size (depth) must be a positive number.");
        this.x1 = position.getX();
        this.y1 = position.getY();
        this.z1 = position.getZ();
        this.x2 = position.getX() + size.getX();
        this.y2 = position.getY() + size.getY();
        this.z2 = position.getZ() + size.getZ();
    }

    public float getX1() {
        return x1;
    }

    public float getY1() {
        return y1;
    }

    public float getZ1() {
        return z1;
    }

    public float getX2() {
        return x2;
    }

    public float getY2() {
        return y2;
    }

    public float getZ2() {
        return z2;
    }

}
