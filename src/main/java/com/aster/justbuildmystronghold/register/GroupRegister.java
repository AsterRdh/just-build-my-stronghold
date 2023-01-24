package com.aster.justbuildmystronghold.register;

import com.aster.justbuildmystronghold.base.groups.KeepBuildingsItemGroup;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class GroupRegister {
    public static final KeepBuildingsItemGroup KEEP_BUILDING_BLOCKS = new KeepBuildingsItemGroup();
    public static final CreativeModeTab SIEGE_BUILDING_BLOCKS = new CreativeModeTab("siegeBuildings"){@Override public ItemStack makeIcon() {return new ItemStack(ItemsRegister.SiegeBlocksGroup.get());}}.setRecipeFolderName("siege_blocks");
    public static final CreativeModeTab TOWN_BUILDING_BLOCKS = new CreativeModeTab("townBuildings"){@Override public ItemStack makeIcon() {return new ItemStack(ItemsRegister.TownBlocksGroup.get());}}.setRecipeFolderName("town_blocks");
    public static final CreativeModeTab FOOD_BUILDING_BLOCKS = new CreativeModeTab("foodBuildings"){@Override public ItemStack makeIcon() {return new ItemStack(ItemsRegister.FoodBlocksGroup.get());}}.setRecipeFolderName("food_blocks");
    public static final CreativeModeTab INDUSTRY_BUILDING_BLOCKS = new CreativeModeTab("industryBuildings"){@Override public ItemStack makeIcon() {return new ItemStack(ItemsRegister.IndustryBlocksGroup.get());}}.setRecipeFolderName("industry_blocks");
    public static final CreativeModeTab WAR_BUILDING_BLOCKS = new CreativeModeTab("warBuildings"){@Override public ItemStack makeIcon() {return new ItemStack(ItemsRegister.WarBlocksGroup.get());}}.setRecipeFolderName("war_blocks");

}
