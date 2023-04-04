package net.gglobensky.caviercaves.utils;

import net.gglobensky.caviercaves.init.CaviercavesModParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.LevelAccessor;

public class ParticleManager {
    public static void crystalSparkleParticle(LevelAccessor world, double x, double y, double z) {
        world.addParticle((SimpleParticleType) (CaviercavesModParticleTypes.CRYSTAL_SPARKLE.get()), (Math.random() * (-2) + 1 + x), (Math.random() * (-2) + 1 + y), (Math.random() * (-2) + 1 + z), (Math.random() / 10), (Math.random() / 10),
                (Math.random() / 10));
    }
}
