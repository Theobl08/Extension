package net.theobl.extension.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
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
        this.spriteSet(ModParticleTypes.DRIPPING_DRIPSTONE_BEETROOT_SOUP.get(), Identifier.withDefaultNamespace("drip_hang"));
        this.spriteSet(ModParticleTypes.FALLING_DRIPSTONE_BEETROOT_SOUP.get(), Identifier.withDefaultNamespace("drip_fall"));
        this.spriteSet(ModParticleTypes.LANDING_BEETROOT_SOUP.get(), Identifier.withDefaultNamespace("drip_land"));
        this.spriteSet(ModParticleTypes.DRIPPING_DRIPSTONE_MUSHROOM_STEW.get(), Identifier.withDefaultNamespace("drip_hang"));
        this.spriteSet(ModParticleTypes.FALLING_DRIPSTONE_MUSHROOM_STEW.get(), Identifier.withDefaultNamespace("drip_fall"));
        this.spriteSet(ModParticleTypes.LANDING_MUSHROOM_STEW.get(), Identifier.withDefaultNamespace("drip_land"));
    }
}
