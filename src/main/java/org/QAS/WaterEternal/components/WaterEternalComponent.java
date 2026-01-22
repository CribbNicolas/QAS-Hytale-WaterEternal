package org.QAS.WaterEternal.components;

import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.protocol.BlockPosition;
import com.hypixel.hytale.protocol.Vector3f;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import javax.annotation.Nullable;

public class WaterEternalComponent implements Component<EntityStore> {

    private final BlockPosition position;
    private final Vector3f offset;
    private final String particleSystemId;
    private float timeSinceLastSpawn = Float.MAX_VALUE; // Spawn inmediato al inicio
    private float scale = 1.0f;

    public String getParticleSystemId() {
        return this.particleSystemId;
    }

    public Vector3f getOffset() {
        return offset;
    }

    public BlockPosition getPosition() {
        return position;
    }

    public void addTime(float dt) {
        timeSinceLastSpawn += dt;
    }

    public float getTimeSinceLastSpawn() {
        return timeSinceLastSpawn;
    }

    public void resetSpawnTimer() {
        timeSinceLastSpawn = 0;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    /**
     * Constructor con todos los parámetros.
     *
     * @param particleSystemId ID del sistema de partículas (ej: "hytale:fire")
     * @param position         Posición del bloque en el mundo
     * @param offset           Desplazamiento desde la posición del bloque
     */
    public WaterEternalComponent(String particleSystemId, BlockPosition position, Vector3f offset) {
        this.position = position;
        this.offset = offset;
        this.particleSystemId = particleSystemId;
    }

    public WaterEternalComponent(BlockPosition position, Vector3f offset) {
        this.position = position;
        this.offset = offset;
        this.particleSystemId = "Plant_Eternal";
    }

    public WaterEternalComponent() {
        this.position = new BlockPosition(0, 0, 0);
        this.offset = new Vector3f(0, 0, 0);
        this.particleSystemId = "Plant_Eternal";
    }

    public WaterEternalComponent(WaterEternalComponent other) {
        this.position = other.position;
        this.offset = other.offset;
        this.particleSystemId = other.particleSystemId;
        this.timeSinceLastSpawn = other.timeSinceLastSpawn;
        this.scale = other.scale;
    }

    @Nullable
    @Override
    public Component<EntityStore> clone() {
        return new WaterEternalComponent(this);
    }


}