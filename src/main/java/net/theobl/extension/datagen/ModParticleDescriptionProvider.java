package net.theobl.extension.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.data.ParticleDescriptionProvider;
import net.theobl.extension.Extension;
import net.theobl.extension.particles.ModParticleTypes;

public class ModParticleDescriptionProvider extends ParticleDescriptionProvider {
    /**
     * Creates an instance of the data provider.
     *
     * @param output the expected root directory the data generator outputs to
     */
    protected ModParticleDescriptionProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void addDescriptions() {
        this.spriteSet(ModParticleTypes.ENDER_FIRE_FLAME.get(), Extension.asResource("ender_fire_flame"));
    }
}
