package org.KViEternal.combat_maid.client;

import net.minecraft.entity.LivingEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manages per-entity animation state for the maid suit.
 * Stores spring physics and previous-frame data so animations are smooth
 * across frames and don't reset every render call.
 */
public class MaidSuitAnimator {

    private static final AnimationState globalState = new AnimationState();

    public static AnimationState getOrCreate(Object state) {
        return globalState;
    }

    public static void cleanup(UUID uuid) {
    }

    public static class AnimationState {
        // Chest spring: Y-axis bounce
        public final SpringPhysics chestBounceY;
        // Chest spring: pitch rotation (forward/back tilt)
        public final SpringPhysics chestPitch;

        // Previous entity Y position for computing vertical velocity
        public float prevEntityY;
        public boolean initialized = false;

        // Previous sneaking state for detecting transitions
        public boolean wasSneaking = false;

        // Smoothed leg swing for skirt (avoids jitter)
        public float smoothedLegSwing = 0.0f;

        // Track last render tick to detect new ticks vs partial tick re-renders
        public long lastTickAge = -1;

        public AnimationState() {
            // Chest Y bounce: moderate stiffness, moderate damping for a natural jiggle
            // Rest at 0 (offset from base position), allowed to move ±2.0 units
            this.chestBounceY = new SpringPhysics(0.0f, 15.0f, 4.5f, -2.0f, 2.5f);
            // Chest pitch: stiffer so it doesn't flop around too much
            this.chestPitch = new SpringPhysics(0.0f, 18.0f, 5.0f, -0.25f, 0.25f);
            this.prevEntityY = 0.0f;
        }

        /**
         * Run the physics simulation for one tick.
         * Call this once per game tick per entity, not per frame.
         */
        public void tick(net.minecraft.client.render.entity.state.BipedEntityRenderState state, boolean isSneaking, float legSwingAmount) {
            float dt = 1.0f / 20.0f; // One tick = 1/20th of a second

            if (!initialized) {
                prevEntityY = (float) state.y;
                initialized = true;
                return;
            }

            // --- Chest Y bounce ---
            float currentY = (float) state.y;
            float verticalDelta = currentY - prevEntityY;
            prevEntityY = currentY;

            // The "target" for the chest spring is an offset driven by forces:
            // 1. Vertical velocity (jumping/falling/landing): chest lags behind
            // 2. Walking bounce: subtle up-down from leg movement
            // 3. Crouch transition: sudden dip

            float verticalForce = -verticalDelta * 4.0f; // Inertia opposing motion

            // Walking bounce removed per user request
            float walkBounce = 0.0f;
            
            // Keep smoothedLegSwing for other physics (like pitch)
            smoothedLegSwing += (legSwingAmount - smoothedLegSwing) * 0.3f;

            // Crouch transition: apply a one-time impulse when entering/leaving sneak
            if (isSneaking && !wasSneaking) {
                chestBounceY.update(chestBounceY.getPosition(), dt); // just ensure state exists
                // Kick the velocity downward for a bounce on crouch-enter
                chestPitch.update(-0.75f, dt);
            } else if (!isSneaking && wasSneaking) {
                // Kick upward when standing back up
                chestPitch.update(0.5f, dt);
            }
            wasSneaking = isSneaking;

            float targetY = verticalForce + walkBounce;
            chestBounceY.update(targetY, dt);

            // Chest pitch: tilt forward slightly when moving fast, backward when jumping up
            float targetPitch = -verticalDelta * 3.0f; // Removed leg swing effect
            chestPitch.update(targetPitch, dt);
        }

        /**
         * Get interpolated chest Y offset for the current partial tick.
         */
        public float getChestOffsetY() {
            return chestBounceY.getPosition();
        }

        /**
         * Get interpolated chest pitch for the current partial tick.
         */
        public float getChestPitchOffset() {
            return chestPitch.getPosition();
        }
    }
}
