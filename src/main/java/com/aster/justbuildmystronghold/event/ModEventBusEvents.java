package com.aster.justbuildmystronghold.event;

import com.aster.justbuildmystronghold.JustBuildMyStronghold;
import com.aster.justbuildmystronghold.register.ParticleRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.NewRegistryEvent;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = JustBuildMyStronghold.MODID , bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerParticleProvidersEvent(final RegisterParticleProvidersEvent event){
        event.register(ParticleRegister.FLAME.get(), FlameParticle.SmallFlameProvider::new);

    }

}
