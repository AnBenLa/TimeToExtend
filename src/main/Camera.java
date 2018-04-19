package main;

public class Camera {
    public Vector position;
    public Vector direction;
    public float fieldOfView;

    public Camera(Vector position, Vector direction, float fieldOfView) {
        this.direction = direction;
        this.position = position;
        this.fieldOfView = fieldOfView;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public float getFieldOfView() {
        return fieldOfView;
    }

    public void setFieldOfView(float fieldOfView) {
        this.fieldOfView = fieldOfView;
    }
}
