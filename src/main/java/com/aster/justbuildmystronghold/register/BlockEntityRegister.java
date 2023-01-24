package com.aster.justbuildmystronghold.register;

import com.aster.justbuildmystronghold.JustBuildMyStronghold;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegister {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, JustBuildMyStronghold.MODID);

    public static final RegistryObject<BlockEntityType<BedBlockEntity>> BIG_BED = BLOCK_ENTITIES.register("big_bed", ()-> BlockEntityType.Builder.of(BedBlockEntity::new,BlockRegister.BIG_BED_BLACK.get()).build(null));


}
