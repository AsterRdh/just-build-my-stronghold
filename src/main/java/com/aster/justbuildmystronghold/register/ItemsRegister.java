package com.aster.justbuildmystronghold.register;

import com.aster.justbuildmystronghold.base.groups.GroupIconItem;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.aster.justbuildmystronghold.JustBuildMyStronghold.MODID;

public class ItemsRegister {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final RegistryObject<Item> KeepBlocksGroup = ITEMS.register("group_keep", GroupIconItem::new) ;//group_keep.json
    public static final RegistryObject<Item> SiegeBlocksGroup = ITEMS.register("group_siege", GroupIconItem::new) ;//group_keep.json
    public static final RegistryObject<Item> TownBlocksGroup = ITEMS.register("group_town", GroupIconItem::new) ;//group_keep.json
    public static final RegistryObject<Item> FoodBlocksGroup = ITEMS.register("group_food", GroupIconItem::new) ;//group_keep.json
    public static final RegistryObject<Item> IndustryBlocksGroup = ITEMS.register("group_industry", GroupIconItem::new) ;//group_keep.json
    public static final RegistryObject<Item> WarBlocksGroup = ITEMS.register("group_war", GroupIconItem::new) ;//group_keep.json



    public static final RegistryObject<Item> CEILING1 = ITEMS.register("ceiling1", () -> new BlockItem(BlockRegister.CEILING1.get(), new Item.Properties().tab(GroupRegister.TOWN_BUILDING_BLOCKS)));


    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(BlockRegister.exampleBlock.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> battlementsMiddle = ITEMS.register("battlements_normal", () -> new BlockItem(BlockRegister.battlementsMiddle.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> machicolationsType1 = ITEMS.register("machicolations_t1", () -> new BlockItem(BlockRegister.machicolationsType1.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> wainscotSpruce = ITEMS.register("wainscot_spruce", () -> new BlockItem(BlockRegister.wainscotSpruce.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> windowMini = ITEMS.register("window_mini", () -> new BlockItem(BlockRegister.windowMini.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> stoneBrickVerticalSlab = ITEMS.register("vertical_slab_stone_brick", () -> new BlockItem(BlockRegister.stoneBrickVerticalSlab.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> cobblestoneVerticalSlab = ITEMS.register("vertical_slab_cobblestone", () -> new BlockItem(BlockRegister.cobblestoneVerticalSlab.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> toiletBase = ITEMS.register("toilet_base", () -> new BlockItem(BlockRegister.toiletBase.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> hafeSlabOka = ITEMS.register("hafe_slab_oka", () -> new BlockItem(BlockRegister.hafeSlabOka.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> bookVerticalSlab = ITEMS.register("book_vertical_slab", () -> new BlockItem(BlockRegister.VerticalSlabBook.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> QuarterStoneBrick = ITEMS.register("stone_brick_quarter", () -> new BlockItem(BlockRegister.QuarterStoneBrick.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> BricksQuarter = ITEMS.register("bricks_quarter", () -> new BlockItem(BlockRegister.BricksQuarter.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> BattlementsWoodSpruce = ITEMS.register("battlements_wood_spruce", () -> new BlockItem(BlockRegister.BattlementsWoodSpruce.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> BattlementsWoodAcacia = ITEMS.register("battlements_wood_acacia", () -> new BlockItem(BlockRegister.BattlementsWoodAcacia.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> BattlementsWoodBirch = ITEMS.register("battlements_wood_birch", () -> new BlockItem(BlockRegister.BattlementsWoodBirch.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> BattlementsWoodDarkOak = ITEMS.register("battlements_wood_dark_oak", () -> new BlockItem(BlockRegister.BattlementsWoodDarkOak.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> BattlementsWoodJungle = ITEMS.register("battlements_wood_jungle", () -> new BlockItem(BlockRegister.BattlementsWoodJungle.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> BattlementsWoodOak = ITEMS.register("battlements_wood_oak", () -> new BlockItem(BlockRegister.BattlementsWoodOak.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> Wardrobe = ITEMS.register("wardrobe", () -> new BlockItem(BlockRegister.Wardrobe.get(), new Item.Properties().tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> SHELF_BLOCK = ITEMS.register("shelf_block", () -> new BlockItem(BlockRegister.SHELF_BLOCK.get(), new Item.Properties().tab(GroupRegister.TOWN_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> HandrailOak = ITEMS.register("handrail_oak", () -> new BlockItem(BlockRegister.HandrailOak.get(), new Item.Properties().tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> HandrailAcacia = ITEMS.register("handrail_acacia", () -> new BlockItem(BlockRegister.HandrailAcacia.get(), new Item.Properties().tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> HandrailBirch = ITEMS.register("handrail_birch", () -> new BlockItem(BlockRegister.HandrailBirch.get(), new Item.Properties().tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> HandrailDarkOak = ITEMS.register("handrail_dark_oak", () -> new BlockItem(BlockRegister.HandrailDarkOak.get(), new Item.Properties().tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> HandrailJungle = ITEMS.register("handrail_jungle", () -> new BlockItem(BlockRegister.HandrailJungle.get(), new Item.Properties().tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> HandrailSpruce = ITEMS.register("handrail_spruce", () -> new BlockItem(BlockRegister.HandrailSpruce.get(), new Item.Properties().tab(GroupRegister.TOWN_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> ChimneyStoneBricks = ITEMS.register("chimney_stone_bricks", () -> new BlockItem(BlockRegister.ChimneyStoneBricks.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> WINDOW_MINI_FRAME = ITEMS.register("window_mini_frame", () -> new BlockItem(BlockRegister.WINDOW_MINI_FRAME.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> MACHICOLATION_ACACIA = ITEMS.register("machicolation_acacia", () -> new BlockItem(BlockRegister.MACHICOLATION_ACACIA.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> MACHICOLATION_BIRCH = ITEMS.register("machicolation_birch", () -> new BlockItem(BlockRegister.MACHICOLATION_BIRCH.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> MACHICOLATION_DARK_OAK = ITEMS.register("machicolation_dark_oak", () -> new BlockItem(BlockRegister.MACHICOLATION_DARK_OAK.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> MACHICOLATION_JUNGLE = ITEMS.register("machicolation_jungle", () -> new BlockItem(BlockRegister.MACHICOLATION_JUNGLE.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> MACHICOLATION_OAK = ITEMS.register("machicolation_oak", () -> new BlockItem(BlockRegister.MACHICOLATION_OAK.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    public static final RegistryObject<Item> MACHICOLATION_SPRUCE = ITEMS.register("machicolation_spruce", () -> new BlockItem(BlockRegister.MACHICOLATION_SPRUCE.get(), new Item.Properties().tab(GroupRegister.KEEP_BUILDING_BLOCKS)));
    //public static final RegistryObject<Item> BigBedRed = ITEMS.register("big_bed_red", () -> new BlockItem(BlockRegister.BigBedRed.get(), new Item.Properties().tab(GroupRegister.TOWN_BUILDING_BLOCKS)));

    public static final RegistryObject<Item>  BIG_BED_BLACK =  ITEMS.register("big_bed_black",()->new BedItem(BlockRegister.BIG_BED_BLACK.get(), (new Item.Properties()).stacksTo(1).tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item>  BIG_BED_BLUE =  ITEMS.register("big_bed_blue",()->new BedItem(BlockRegister.BIG_BED_BLUE.get(), (new Item.Properties()).stacksTo(1).tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item>  BIG_BED_RED =  ITEMS.register("big_bed_red",()->new BedItem(BlockRegister.BIG_BED_RED.get(), (new Item.Properties()).stacksTo(1).tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item>  BIG_BED_BROWN =  ITEMS.register("big_bed_brown",()->new BedItem(BlockRegister.BIG_BED_BROWN.get(), (new Item.Properties()).stacksTo(1).tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item>  BIG_BED_CYAN =  ITEMS.register("big_bed_cyan",()->new BedItem(BlockRegister.BIG_BED_CYAN.get(), (new Item.Properties()).stacksTo(1).tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item>  BIG_BED_GRAY =  ITEMS.register("big_bed_gray",()->new BedItem(BlockRegister.BIG_BED_GRAY.get(), (new Item.Properties()).stacksTo(1).tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item>  BIG_BED_GREEN =  ITEMS.register("big_bed_green",()->new BedItem(BlockRegister.BIG_BED_GREEN.get(), (new Item.Properties()).stacksTo(1).tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item>  BIG_BED_LIGHT_BLUE =  ITEMS.register("big_bed_light_blue",()->new BedItem(BlockRegister.BIG_BED_LIGHT_BLUE.get(), (new Item.Properties()).stacksTo(1).tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item>  BIG_BED_LIGHT_GRAY =  ITEMS.register("big_bed_light_gray",()->new BedItem(BlockRegister.BIG_BED_LIGHT_GRAY.get(), (new Item.Properties()).stacksTo(1).tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item>  BIG_BED_LIME =  ITEMS.register("big_bed_lime",()->new BedItem(BlockRegister.BIG_BED_LIME.get(), (new Item.Properties()).stacksTo(1).tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item>  BIG_BED_MAGENTA =  ITEMS.register("big_bed_magenta",()->new BedItem(BlockRegister.BIG_BED_MAGENTA.get(), (new Item.Properties()).stacksTo(1).tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item>  BIG_BED_ORANGE =  ITEMS.register("big_bed_orange",()->new BedItem(BlockRegister.BIG_BED_ORANGE.get(), (new Item.Properties()).stacksTo(1).tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item>  BIG_BED_PINK =  ITEMS.register("big_bed_pink",()->new BedItem(BlockRegister.BIG_BED_PINK.get(), (new Item.Properties()).stacksTo(1).tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item>  BIG_BED_PURPLE =  ITEMS.register("big_bed_purple",()->new BedItem(BlockRegister.BIG_BED_PURPLE.get(), (new Item.Properties()).stacksTo(1).tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item>  BIG_BED_WHITE =  ITEMS.register("big_bed_white",()->new BedItem(BlockRegister.BIG_BED_WHITE.get(), (new Item.Properties()).stacksTo(1).tab(GroupRegister.TOWN_BUILDING_BLOCKS)));
    public static final RegistryObject<Item>  BIG_BED_YELLOW =  ITEMS.register("big_bed_yellow",()->new BedItem(BlockRegister.BIG_BED_YELLOW.get(), (new Item.Properties()).stacksTo(1).tab(GroupRegister.TOWN_BUILDING_BLOCKS)));

    private static Item registerBlock(Block p_42808_, CreativeModeTab p_42809_) {
        return registerBlock(new BlockItem(p_42808_, (new Item.Properties()).tab(p_42809_)));
    }
    private static Item registerBlock(BlockItem p_42804_) {
        return registerBlock(p_42804_.getBlock(), p_42804_);
    }
    protected static Item registerBlock(Block p_42811_, Item p_42812_) {
        return registerItem(Registry.BLOCK.getKey(p_42811_), p_42812_);
    }
    private static Item registerItem(ResourceLocation p_42817_, Item p_42818_) {
        if (p_42818_ instanceof BlockItem) {
            ((BlockItem)p_42818_).registerBlocks(Item.BY_BLOCK, p_42818_);
        }

        return Registry.register(Registry.ITEM, p_42817_, p_42818_);
    }




}
