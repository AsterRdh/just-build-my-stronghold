package com.aster.justbuildmystronghold;

import com.aster.justbuildmystronghold.register.BlockRegister;
import com.aster.justbuildmystronghold.register.BlockEntityRegister;
import com.aster.justbuildmystronghold.register.ItemsRegister;
import com.aster.justbuildmystronghold.register.ParticleRegister;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(JustBuildMyStronghold.MODID)
public class JustBuildMyStronghold
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "justbuildmystronghold";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace


    public static final BlockRegister blockRegister = new BlockRegister();
    public static final ItemsRegister itemsRegister = new ItemsRegister();
    public static final BlockEntityRegister blockEntityRegister = new BlockEntityRegister();
    public static final ParticleRegister particleRegister = new ParticleRegister();


    public JustBuildMyStronghold()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        blockRegister.BLOCKS.register(modEventBus);
        itemsRegister.ITEMS.register(modEventBus);
        blockEntityRegister.BLOCK_ENTITIES.register(modEventBus);
        particleRegister.PARTICLE_TYPES.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }



    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
