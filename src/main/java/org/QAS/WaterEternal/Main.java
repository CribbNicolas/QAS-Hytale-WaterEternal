package org.QAS.WaterEternal;

import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.Interaction;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.QAS.WaterEternal.components.WaterEternalComponent;
import org.QAS.WaterEternal.interactions.PlaceFluidParticlesInteraction;
import org.QAS.WaterEternal.systems.WaterEternalSystem;

import javax.annotation.Nonnull;

/**
 * Here start Logic of WaterEternal mode.
 * Get all WaterEternal in the world and check if have a particles.
 */
public class Main extends JavaPlugin {

    private static Main instance;
    public static ComponentType<EntityStore, WaterEternalComponent> waterEternalComponentType;
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public Main(@Nonnull JavaPluginInit init) {
        super(init);
        instance = this;
    }

    @Override
    protected void setup() {
        // Registrar codec PRIMERO para que esté disponible cuando se cargue el asset pack
        this.getCodecRegistry(Interaction.CODEC).register("QAS_PlaceFluidParticles", PlaceFluidParticlesInteraction.class, PlaceFluidParticlesInteraction.CODEC);

        this.getCommandRegistry().registerCommand(new ExampleCommand(this.getName(), this.getManifest().getVersion().toString()));
        Main.waterEternalComponentType = this.getEntityStoreRegistry()
                .registerComponent(WaterEternalComponent.class, WaterEternalComponent::new);
        this.getEntityStoreRegistry().registerSystem(new WaterEternalSystem(this.waterEternalComponentType));
    }

    public ComponentType<EntityStore, WaterEternalComponent> getWaterEternalComponentType() {
        return Main.waterEternalComponentType;
    }

    public static Main get() {
        return instance;
    }
}