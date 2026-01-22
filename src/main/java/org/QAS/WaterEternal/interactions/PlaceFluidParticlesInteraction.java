package org.QAS.WaterEternal.interactions;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.protocol.BlockPosition;
import com.hypixel.hytale.protocol.InteractionSyncData;
import com.hypixel.hytale.protocol.InteractionType;
import com.hypixel.hytale.protocol.Vector3f;
import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.entity.InteractionContext;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.inventory.container.SimpleItemContainer;
import com.hypixel.hytale.server.core.meta.DynamicMetaStore;
import com.hypixel.hytale.server.core.modules.interaction.interaction.CooldownHandler;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.SimpleInstantInteraction;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.QAS.WaterEternal.Main;
import org.QAS.WaterEternal.components.WaterEternalComponent;
import org.QAS.WaterEternal.systems.WaterEternalSystem;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import java.util.Map;
import java.util.function.Function;

public class PlaceFluidParticlesInteraction extends SimpleInstantInteraction {
    private final String targetRootInteractionId = "**Container_Bucket_State_Filled_Water_Eternal_Interactions_Secondary";

    public static final BuilderCodec<PlaceFluidParticlesInteraction> CODEC = BuilderCodec.builder(
            PlaceFluidParticlesInteraction.class, PlaceFluidParticlesInteraction::new, SimpleInstantInteraction.CODEC
    ).build();
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    @Override
    protected void firstRun(@NonNullDecl InteractionType interactionType, @NonNullDecl InteractionContext interactionContext, @NonNullDecl CooldownHandler cooldownHandler) {
        BlockPosition position = interactionContext.getTargetBlock();
        String rootInteractionId = interactionContext.getRootInteractionId(interactionType);

        if(this.targetRootInteractionId.equals(rootInteractionId)) {
            CommandBuffer<EntityStore> commandBuffer = interactionContext.getCommandBuffer();
            WaterEternalComponent component = new WaterEternalComponent(position, new Vector3f(0.5f, 0.5f,1));
            Ref<EntityStore> ref = interactionContext.getOwningEntity();
            commandBuffer.addComponent(ref, Main.waterEternalComponentType, component);
        }

    }
}