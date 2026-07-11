package org.KViEternal.combat_maid.client;

/**
 * A simple damped spring simulation for smooth, realistic physics animation.
 * Uses a critically-damped spring model to prevent oscillation from looking mechanical.
 */
public class SpringPhysics {
    private float position;
    private float velocity;
    private final float stiffness;
    private final float damping;
    private final float minValue;
    private final float maxValue;

    /**
     * @param restPosition The resting position of the spring.
     * @param stiffness    How quickly the spring snaps back (higher = stiffer). Typical: 8-25.
     * @param damping      How quickly oscillations die out (higher = less bouncy). Typical: 3-8.
     * @param minValue     Minimum clamped value.
     * @param maxValue     Maximum clamped value.
     */
    public SpringPhysics(float restPosition, float stiffness, float damping, float minValue, float maxValue) {
        this.position = restPosition;
        this.velocity = 0.0f;
        this.stiffness = stiffness;
        this.damping = damping;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * Advance the spring simulation by one step.
     *
     * @param targetPosition Where the spring is being pulled towards.
     * @param deltaTime      Time step in seconds (use 1/20f for one tick, or partial tick delta for smooth frames).
     */
    public void update(float targetPosition, float deltaTime) {
        float displacement = position - targetPosition;
        float springForce = -stiffness * displacement;
        float dampingForce = -damping * velocity;
        float acceleration = springForce + dampingForce;

        velocity += acceleration * deltaTime;
        position += velocity * deltaTime;

        // Clamp to prevent extreme values
        position = Math.max(minValue, Math.min(maxValue, position));
    }

    public float getPosition() {
        return position;
    }

    public float getVelocity() {
        return velocity;
    }

    public void reset(float pos) {
        this.position = pos;
        this.velocity = 0.0f;
    }
}
