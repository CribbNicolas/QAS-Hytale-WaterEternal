package org.QAS.WaterEternal.systems;


import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.RefChangeSystem;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.protocol.*;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3i;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.universe.world.ParticleUtil;
import org.QAS.WaterEternal.components.WaterEternalComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class WaterEternalSystem extends RefChangeSystem<EntityStore, WaterEternalComponent> {

    private final ComponentType<EntityStore, WaterEternalComponent> type;
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public WaterEternalSystem(ComponentType<EntityStore, WaterEternalComponent> type) {
        this.type = type;
    }

    @Nonnull
    @Override
    public ComponentType<EntityStore, WaterEternalComponent> componentType() {
        return type;
    }

    @Override
    public void onComponentAdded(
            @Nonnull Ref<EntityStore> ref,
            @Nonnull WaterEternalComponent component,
            @Nonnull Store<EntityStore> store,
            @Nonnull CommandBuffer<EntityStore> commands
    ) {
        LOGGER.atInfo().log("onComponentAdded: %s", component);
        BlockPosition pos = component.getPosition();
        Vector3f offset = component.getOffset();
        World world = store.getExternalData().getWorld();

        Vector3d worldPos = new Vector3d(
                pos.x + offset.x,
                pos.y + offset.y,
                pos.z + offset.z
        );

        Vector3i blockPos = new Vector3i(pos.x, pos.y, pos.z);

        int blockId = world.getBlock(blockPos);

        LOGGER.atInfo().log("blockId: %d", blockId);

//        ParticleUtil.spawnParticleEffect(
//                component.getParticleSystemId(),
//                worldPos,
//                commands
//        );
    }

    @Override
    public void onComponentRemoved(
            @Nonnull Ref<EntityStore> ref,
            @Nonnull WaterEternalComponent component,
            @Nonnull Store<EntityStore> store,
            @Nonnull CommandBuffer<EntityStore> commands
    ) {
        // Las partículas spawneadas con ParticleUtil se desvanecen automáticamente.
        // No es necesario (ni posible con la API actual) removerlas manualmente.
        //
        // Si implementas partículas persistentes en el futuro, aquí irá la lógica
        // de limpieza. Por ahora, este método queda vacío intencionalmente.
        //
        // POSIBLE IMPLEMENTACIÓN FUTURA:
        // Si guardas el ID de un efecto persistente en el componente, podrías:
        // - Enviar un paquete de cancelación al cliente
        // - Remover una entidad de efecto asociada
        // - etc.
    }

    @Override
    public void onComponentSet(
            @Nonnull Ref<EntityStore> ref,
            WaterEternalComponent oldComponent,
            @Nonnull WaterEternalComponent newComponent,
            @Nonnull Store<EntityStore> store,
            @Nonnull CommandBuffer<EntityStore> commands
    ) {
        // No-op: No hacemos nada cuando el componente es modificado.
        //
        // POSIBLES IMPLEMENTACIONES:
        // - Si la posición cambió, spawnear nuevas partículas en la nueva posición
        // - Si el particleSystemId cambió, spawnear el nuevo tipo de partículas
        // - etc.
        //
        // Ejemplo de implementación:
        // if (oldComponent != null && !oldComponent.getPosition().equals(newComponent.getPosition())) {
        //     // La posición cambió, spawnear en la nueva ubicación
        //     onComponentAdded(ref, newComponent, store, commands);
        // }
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return type;
    }
}
