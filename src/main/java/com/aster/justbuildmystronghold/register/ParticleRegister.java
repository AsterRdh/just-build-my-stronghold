package com.aster.justbuildmystronghold.register;

import com.aster.justbuildmystronghold.base.groups.GroupIconItem;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.aster.justbuildmystronghold.JustBuildMyStronghold.MODID;

public class ParticleRegister {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID);
    //SimpleParticleType
    public static final RegistryObject<SimpleParticleType> FLAME = PARTICLE_TYPES.register("flame", ()-> new SimpleParticleType(true));

}
