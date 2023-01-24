package com.aster.justbuildmystronghold.base.groups;

import com.aster.justbuildmystronghold.register.ItemsRegister;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class KeepBuildingsItemGroup extends CreativeModeTab {
    public KeepBuildingsItemGroup() {
        super("keepBuildings");
        setRecipeFolderName("keep_blocks");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemsRegister.KeepBlocksGroup.get());
    }
}
