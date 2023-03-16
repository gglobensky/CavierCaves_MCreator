package net.gglobensky.caviercaves.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.particles.SimpleParticleType;

import net.gglobensky.caviercaves.init.CaviercavesModParticleTypes;

public class CrystalSparkleParticleProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.addParticle((SimpleParticleType) (CaviercavesModParticleTypes.CRYSTAL_SPARKLE.get()), (Math.random() * (-2) + 1 + x), (Math.random() * (-2) + 1 + y), (Math.random() * (-2) + 1 + z), (Math.random() / 10), (Math.random() / 10),
				(Math.random() / 10));
	}
}
