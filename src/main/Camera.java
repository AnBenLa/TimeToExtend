package main;

public class Camera {
    private Gerade direction;
    public float fieldOfView;

    public Camera(Vector position, Vector direction, float fieldOfView) {
        this.direction = new Gerade(position,direction);
        this.fieldOfView = fieldOfView;
    }

    public Gerade getDirection() {
        return direction;
    }

    public void setDirection(Gerade direction) {
        this.direction = direction;
    }

    public float getFieldOfView() {
        return fieldOfView;
    }

    public void setFieldOfView(float fieldOfView) {
        this.fieldOfView = fieldOfView;
    }
}
