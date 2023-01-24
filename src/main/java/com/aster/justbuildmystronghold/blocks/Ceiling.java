package com.aster.justbuildmystronghold.blocks;

import com.aster.justbuildmystronghold.base.HandrailEnum;
import com.aster.justbuildmystronghold.base.SHBlockStateProperties;
import com.aster.justbuildmystronghold.base.TorchEnum;
import com.aster.justbuildmystronghold.base.VoxelShapeHelper;
import com.aster.justbuildmystronghold.blocks.base.BaseBlock;
import com.aster.justbuildmystronghold.register.ParticleRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.ToIntFunction;

public class Ceiling extends BaseBlock {

    public static final IntegerProperty LIT = IntegerProperty.create("lit",0,4);
    private final static VoxelShape a1 = new VoxelShapeHelper(1, 6, 1, 15, 16, 15).getShape();
    private static int litLevel = 3;


    public Ceiling(Properties properties) {
        super(properties);
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder) {
        super.createBlockStateDefinition(stateBuilder);
        stateBuilder.add(LIT);
    }

    public Ceiling() {
        super(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL)
                .strength(5.0F)
                .sound(SoundType.METAL)
                .noOcclusion()
                .lightLevel(litBlockEmission())
        );
    }

    private static ToIntFunction<BlockState> litBlockEmission() {
        return (state) -> {
            return state.getValue(LIT)*litLevel;
        };
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return a1;
    }

    @Override
    public InteractionResult use(BlockState p_60503_, Level p_60504_, BlockPos p_60505_, Player p_60506_, InteractionHand p_60507_, BlockHitResult p_60508_) {
        Integer value = p_60503_.getValue(LIT);
        BlockState state = p_60503_.setValue(LIT, value < 4 ? ++value : 0);
        p_60504_.setBlock(p_60505_,state,10);
        return  InteractionResult.SUCCESS;
    }


    public void animateTick(BlockState p_222660_, Level p_222661_, BlockPos p_222662_, RandomSource p_222663_) {
        Integer litLevel = p_222660_.getValue(LIT);

        double candle1X = (double)p_222662_.getX() + 0.2D;
        double candle1Z = (double)p_222662_.getZ() + 0.2D;

        double candle2X = (double)p_222662_.getX() + 0.2D;
        double candle2Z = (double)p_222662_.getZ() + 0.8D;

        double candle3X = (double)p_222662_.getX() + 0.8D;
        double candle3Z = (double)p_222662_.getZ() + 0.8D;

        double candle4X = (double)p_222662_.getX() + 0.8D;
        double candle4Z = (double)p_222662_.getZ() + 0.2D;

        double candleY = (double)p_222662_.getY() + 0.74D;



        double d3 = 0.22D;
        double d4 = 0.27D;
        ParticleType<SimpleParticleType> flame = ParticleRegister.FLAME.get();
        switch (litLevel){
            case 4:
                p_222661_.addParticle(ParticleRegister.FLAME.get(), candle4X , candleY , candle4Z  , 0.0D, 0.0D, 0.0D);
            case 3:
                p_222661_.addParticle(ParticleRegister.FLAME.get(), candle2X , candleY , candle2Z  , 0.0D, 0.0D, 0.0D);
            case 2:
                p_222661_.addParticle(ParticleRegister.FLAME.get(), candle3X , candleY , candle3Z  , 0.0D, 0.0D, 0.0D);
            case 1:
                p_222661_.addParticle(ParticleRegister.FLAME.get(), candle1X , candleY , candle1Z  , 0.0D, 0.0D, 0.0D);
            case 0:

        }
    }

}
