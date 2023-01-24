package com.aster.justbuildmystronghold.register;

import com.aster.justbuildmystronghold.JustBuildMyStronghold;
import com.aster.justbuildmystronghold.base.SHBlockStateProperties;
import com.aster.justbuildmystronghold.base.TorchEnum;
import com.aster.justbuildmystronghold.blocks.*;
import com.aster.justbuildmystronghold.blocks.base.BaseHorizontalWaterBlock;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.ToIntFunction;

import static net.minecraft.world.level.block.Blocks.POLISHED_BLACKSTONE_BRICKS;

public class BlockRegister  {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, JustBuildMyStronghold.MODID);
    public static final RegistryObject<Block> exampleBlock = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    public static final RegistryObject<Block> battlementsMiddle = BLOCKS.register("battlements_noraml", () -> new BattlementsMiddle());
    public static final RegistryObject<Block> machicolationsType1 = BLOCKS.register("machicolations_t1", () -> new MachicolationsType1());
    public static final RegistryObject<Block> wainscotSpruce = BLOCKS.register("wainscot_spruce", () -> new WainscotBlock());
    public static final RegistryObject<Block> windowMini = BLOCKS.register("window_mini", ()-> new WindowMini());
    public static final RegistryObject<Block> WINDOW_MINI_FRAME = BLOCKS.register("window_mini_frame",()-> new WindowMiniFrame());
    public static final  RegistryObject<Block> toiletBase = BLOCKS.register("toilet_base", ()-> new ToiletBase());
    public static final  RegistryObject<Block> hafeSlabOka = BLOCKS.register("hafe_slab_oka",
            ()-> new BaseSlab(
                    BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE)
                            .strength(5.0F)
                            .sound(SoundType.STONE)
                            .noOcclusion()
            ));
    public static final  RegistryObject<Block> stoneBrickVerticalSlab = BLOCKS.register("vertical_slab_stone_brick", ()-> new VerticalSlab());
    public static final  RegistryObject<Block> cobblestoneVerticalSlab = BLOCKS.register("vertical_slab_cobblestone", ()-> new VerticalSlab());
    public static final  RegistryObject<Block> VerticalSlabBook = BLOCKS.register("book_vertical_slab",
            ()-> new VerticalSlab(
                    BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                            .strength(3.0F)
                            .sound(SoundType.WOOD)
                            .noOcclusion()
            ));
    public static final  RegistryObject<Block> QuarterStoneBrick = BLOCKS.register("stone_brick_quarter",
            ()-> new QuarterSlab(POLISHED_BLACKSTONE_BRICKS.defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE)
                            .strength(5.0F)
                            .sound(SoundType.STONE)
                            .noOcclusion()
            ));
    public static final  RegistryObject<Block> BricksQuarter = BLOCKS.register("bricks_quarter",
            ()-> new QuarterSlab(POLISHED_BLACKSTONE_BRICKS.defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE)
                            .strength(5.0F)
                            .sound(SoundType.STONE)
                            .noOcclusion()
            ));

    public static final  RegistryObject<Block> BattlementsWoodSpruce = BLOCKS.register("battlements_wood_spruce",
            ()-> new BattlementsWoodBase(POLISHED_BLACKSTONE_BRICKS.defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                            .strength(3.0F)
                            .sound(SoundType.WOOD)
                            .noOcclusion()
                            .lightLevel(litBlockEmission(15))
            ));
    public static final  RegistryObject<Block> BattlementsWoodAcacia = BLOCKS.register("battlements_wood_acacia",
            ()-> new BattlementsWoodBase(POLISHED_BLACKSTONE_BRICKS.defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                            .strength(3.0F)
                            .sound(SoundType.WOOD)
                            .noOcclusion()
                            .lightLevel(litBlockEmission(15))
            ));
    public static final  RegistryObject<Block> BattlementsWoodBirch = BLOCKS.register("battlements_wood_birch",
            ()-> new BattlementsWoodBase(POLISHED_BLACKSTONE_BRICKS.defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                            .strength(3.0F)
                            .sound(SoundType.WOOD)
                            .noOcclusion()
                            .lightLevel(litBlockEmission(15))
            ));
    public static final  RegistryObject<Block> BattlementsWoodDarkOak = BLOCKS.register("battlements_wood_dark_oak",
            ()-> new BattlementsWoodBase(POLISHED_BLACKSTONE_BRICKS.defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                            .strength(3.0F)
                            .sound(SoundType.WOOD)
                            .noOcclusion()
                            .lightLevel(litBlockEmission(15))
            ));
    public static final  RegistryObject<Block> BattlementsWoodJungle = BLOCKS.register("battlements_wood_jungle",
            ()-> new BattlementsWoodBase(POLISHED_BLACKSTONE_BRICKS.defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                            .strength(3.0F)
                            .sound(SoundType.WOOD)
                            .noOcclusion()
                            .lightLevel(litBlockEmission(15))
            ));
    public static final  RegistryObject<Block> BattlementsWoodOak = BLOCKS.register("battlements_wood_oak",
            ()-> new BattlementsWoodBase(POLISHED_BLACKSTONE_BRICKS.defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.WOOD)
                            .strength(3.0F)
                            .sound(SoundType.WOOD)
                            .noOcclusion()
                            .lightLevel(litBlockEmission(15))
            ));
    public static final  RegistryObject<Block> Wardrobe = BLOCKS.register("wardrobe", ()-> new Wardrobe());
    public static final  RegistryObject<Block> HandrailOak = BLOCKS.register("handrail_oak", ()-> new WoodHandrail());
    public static final  RegistryObject<Block> HandrailAcacia = BLOCKS.register("handrail_acacia", ()-> new WoodHandrail());
    public static final  RegistryObject<Block> HandrailBirch = BLOCKS.register("handrail_birch", ()-> new WoodHandrail());
    public static final  RegistryObject<Block> HandrailDarkOak = BLOCKS.register("handrail_dark_oak", ()-> new WoodHandrail());
    public static final  RegistryObject<Block> HandrailJungle = BLOCKS.register("handrail_jungle", ()-> new WoodHandrail());
    public static final  RegistryObject<Block> HandrailSpruce = BLOCKS.register("handrail_spruce", ()-> new WoodHandrail());
    public static final  RegistryObject<Block> ChimneyStoneBricks = BLOCKS.register("chimney_stone_bricks",
            ()-> new BaseChimney(
                    BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE)
                            .strength(5.0F)
                            .sound(SoundType.STONE)
                            .noOcclusion()
            ));
    public static final  RegistryObject<Block> CEILING1 = BLOCKS.register("ceiling1", ()-> new Ceiling());
    public static final RegistryObject<Block> BIG_BED_BLACK = BLOCKS.register("big_bed_black",()->bed(DyeColor.BLACK));
    public static final RegistryObject<Block> BIG_BED_BLUE = BLOCKS.register("big_bed_blue",()->bed(DyeColor.BLUE));
    public static final RegistryObject<Block> BIG_BED_RED = BLOCKS.register("big_bed_red",()->bed(DyeColor.RED));
    public static final RegistryObject<Block> BIG_BED_BROWN = BLOCKS.register("big_bed_brown",()->bed(DyeColor.BROWN));
    public static final RegistryObject<Block> BIG_BED_CYAN = BLOCKS.register("big_bed_cyan",()->bed(DyeColor.CYAN));
    public static final RegistryObject<Block> BIG_BED_GRAY = BLOCKS.register("big_bed_gray",()->bed(DyeColor.GRAY));
    public static final RegistryObject<Block> BIG_BED_GREEN = BLOCKS.register("big_bed_green",()->bed(DyeColor.GREEN));
    public static final RegistryObject<Block> BIG_BED_LIGHT_BLUE = BLOCKS.register("big_bed_light_blue",()->bed(DyeColor.LIGHT_BLUE));
    public static final RegistryObject<Block> BIG_BED_LIGHT_GRAY = BLOCKS.register("big_bed_light_gray",()->bed(DyeColor.LIGHT_GRAY));
    public static final RegistryObject<Block> BIG_BED_LIME = BLOCKS.register("big_bed_lime",()->bed(DyeColor.LIME));
    public static final RegistryObject<Block> BIG_BED_MAGENTA = BLOCKS.register("big_bed_magenta",()->bed(DyeColor.MAGENTA));
    public static final RegistryObject<Block> BIG_BED_ORANGE = BLOCKS.register("big_bed_orange",()->bed(DyeColor.ORANGE));
    public static final RegistryObject<Block> BIG_BED_PINK = BLOCKS.register("big_bed_pink",()->bed(DyeColor.PINK));
    public static final RegistryObject<Block> BIG_BED_PURPLE = BLOCKS.register("big_bed_purple",()->bed(DyeColor.PURPLE));
    public static final RegistryObject<Block> BIG_BED_WHITE = BLOCKS.register("big_bed_white",()->bed(DyeColor.WHITE));
    public static final RegistryObject<Block> BIG_BED_YELLOW = BLOCKS.register("big_bed_yellow",()->bed(DyeColor.YELLOW));
    public static final RegistryObject<Block> MACHICOLATION_ACACIA = BLOCKS.register("machicolation_acacia",()-> new MachicolationsWood());
    public static final RegistryObject<Block> MACHICOLATION_BIRCH = BLOCKS.register("machicolation_birch",()-> new MachicolationsWood());
    public static final RegistryObject<Block> MACHICOLATION_DARK_OAK = BLOCKS.register("machicolation_dark_oak",()-> new MachicolationsWood());
    public static final RegistryObject<Block> MACHICOLATION_JUNGLE = BLOCKS.register("machicolation_jungle",()-> new MachicolationsWood());
    public static final RegistryObject<Block> MACHICOLATION_OAK = BLOCKS.register("machicolation_oak",()-> new MachicolationsWood());
    public static final RegistryObject<Block> MACHICOLATION_SPRUCE = BLOCKS.register("machicolation_spruce",()-> new MachicolationsWood());

    public static final RegistryObject<Block> SHELF_BLOCK = BLOCKS.register("shelf_block",()-> new ShelfBlock());


    private static BigBed bed(DyeColor color) {
        return new BigBed(
                color,
                BlockBehaviour.Properties.of(Material.WOOL,
                        (state) -> {
                    return state.getValue(BedBlock.PART) == BedPart.FOOT ? color.getMaterialColor() : MaterialColor.WOOL;
                })
                        .sound(SoundType.WOOD).
                        strength(0.2F)
                        .noOcclusion()
        );
    }

    private static ToIntFunction<BlockState> litBlockEmission(int light) {
        return (state) -> {
            return state.getValue(SHBlockStateProperties.TORCH) != TorchEnum.NONE ? light : 0;
        };
    }

}
