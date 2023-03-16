
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.gglobensky.caviercaves.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.gglobensky.caviercaves.CaviercavesMod;

public class CaviercavesModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, CaviercavesMod.MODID);
	public static final RegistryObject<SimpleParticleType> CRYSTAL_SPARKLE = REGISTRY.register("crystal_sparkle", () -> new SimpleParticleType(false));
}
