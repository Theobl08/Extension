package net.theobl.extension.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.util.Util;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

///A [LayeredCauldronBlock] with 4 levels instead of 3
public class FourLayeredCauldronBlock extends AbstractCauldronBlock {
    public static final int MIN_FILL_LEVEL = LayeredCauldronBlock.MIN_FILL_LEVEL;
    public static final int MAX_FILL_LEVEL = 4;
    ///The [BlockStateProperties#LEVEL_CAULDRON] with 1 extra layer
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", MIN_FILL_LEVEL, MAX_FILL_LEVEL);
    private static final int BASE_CONTENT_HEIGHT = 3;
    private static final double HEIGHT_PER_LEVEL = 3.0;
    private static final VoxelShape[] FILLED_SHAPES = Util.make(
            () -> Block.boxes(3, level -> Shapes.or(AbstractCauldronBlock.SHAPE, Block.column(12.0, FLOOR_LEVEL, getPixelContentHeight(level + 1))))
    );

    public FourLayeredCauldronBlock(Properties properties, CauldronInteraction.Dispatcher interactions) {
        super(properties, interactions);
    }

    @Override
    public boolean isFull(BlockState state) {
        return state.getValue(LEVEL) == MAX_FILL_LEVEL;
    }

    @Override
    protected boolean canReceiveStalactiteDrip(Fluid fluid) {
        return true;
    }

    @Override
    protected double getContentHeight(BlockState state) {
        return getPixelContentHeight(state.getValue(LEVEL)) / 16.0;
    }

    private static double getPixelContentHeight(int level) {
        return BASE_CONTENT_HEIGHT + level * HEIGHT_PER_LEVEL;
    }

    @Override
    protected VoxelShape getEntityInsideCollisionShape(BlockState state, BlockGetter level, BlockPos pos, Entity entity) {
        return FILLED_SHAPES[state.getValue(LEVEL) - 1];
    }

    public static void lowerFillLevel(BlockState state, Level level, BlockPos pos) {
        int newLevel = state.getValue(LEVEL) - 1;
        BlockState newState = newLevel == 0 ? Blocks.CAULDRON.defaultBlockState() : state.setValue(LEVEL, newLevel);
        level.setBlockAndUpdate(pos, newState);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
        return state.getValue(LEVEL);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    @Override
    protected void receiveStalactiteDrip(BlockState state, Level level, BlockPos pos, Fluid fluid) {
        if (!this.isFull(state)) {
            BlockState newState = state.setValue(LEVEL, state.getValue(LEVEL) + 1);
            level.setBlockAndUpdate(pos, newState);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));
            level.levelEvent(1047, pos, 0);
        }
    }

    @Override
    public Item asItem() {
        return Items.CAULDRON;
    }
}
