package net.theobl.extension.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.material.Fluid;
import net.theobl.extension.fluid.ModFluids;
import net.theobl.extension.particles.ModParticleTypes;
import net.theobl.extension.util.ModUtil;

public final class ModDripParticle {
    private static class DripstoneFallAndLandParticle extends DripParticle.FallAndLandParticle {
        private final SoundEvent sound;
        public DripstoneFallAndLandParticle(
                ClientLevel level, double x, double y, double z, Fluid type, ParticleOptions landParticle, TextureAtlasSprite sprite, SoundEvent sound
        ) {
            super(level, x, y, z, type, landParticle, sprite);
            this.sound = sound;
        }

        @Override
        protected void postMoveUpdate() {
            if (this.onGround) {
                this.remove();
                this.level.addParticle(this.landParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
                float volume = Mth.randomBetween(this.random, 0.3F, 1.0F);
                this.level.playLocalSound(this.x, this.y, this.z, this.sound, SoundSource.BLOCKS, volume, 1.0F, false);
            }
        }
    }

    public static class DripstoneBeetrootSoupFallProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public DripstoneBeetrootSoupFallProvider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        public Particle createParticle(
                SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random
        ) {
            DripParticle particle = new ModDripParticle.DripstoneFallAndLandParticle(
                    level, x, y, z, ModFluids.BEETROOT_SOUP.get(), ModParticleTypes.LANDING_BEETROOT_SOUP.get(), this.sprite.get(random), SoundEvents.POINTED_DRIPSTONE_DRIP_LAVA
            );
            ModUtil.setParticleColor(particle, ModUtil.BEETROOT_SOUP_COLOR);
            return particle;
        }
    }

    public static class DripstoneBeetrootSoupHangProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public DripstoneBeetrootSoupHangProvider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        public Particle createParticle(
                SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random
        ) {
            DripParticle particle = new DripParticle.DripHangParticle(
                    level, x, y, z, ModFluids.BEETROOT_SOUP.get(), ModParticleTypes.FALLING_DRIPSTONE_BEETROOT_SOUP.get(), this.sprite.get(random)
            );
            ModUtil.setParticleColor(particle, ModUtil.BEETROOT_SOUP_COLOR);
            return particle;
        }
    }

    public static class BeetrootSoupLandProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public BeetrootSoupLandProvider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        public Particle createParticle(
                SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random
        ) {
            DripParticle particle = new DripParticle.DripLandParticle(level, x, y, z, ModFluids.BEETROOT_SOUP.get(), this.sprite.get(random));
            ModUtil.setParticleColor(particle, ModUtil.BEETROOT_SOUP_COLOR);
            return particle;
        }
    }
}
