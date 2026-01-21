package net.theobl.extension.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.InsideBlockEffectType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PotionCauldronBlock extends AbstractCauldronBlock {
    public static final MapCodec<PotionCauldronBlock> CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                    Potion.CODEC.fieldOf("potion").forGetter(b -> b.potion),
                    CauldronInteraction.CODEC.fieldOf("interactions").forGetter(b -> b.interactions),
                    propertiesCodec()
                    )
                    .apply(i, PotionCauldronBlock::new)
    );
    public static final int MIN_FILL_LEVEL = BlockStateProperties.MIN_LEVEL_CAULDRON;
    public static final int MAX_FILL_LEVEL = 4;
    public static final IntegerProperty LEVEL = IntegerProperty.create("level", MIN_FILL_LEVEL, MAX_FILL_LEVEL);
    private static final int BASE_CONTENT_HEIGHT = 3;
    private static final double HEIGHT_PER_LEVEL = 3.0;
    private static final VoxelShape[] FILLED_SHAPES = Util.make(
            () -> Block.boxes(3, level -> Shapes.or(AbstractCauldronBlock.SHAPE, Block.column(12.0, 4.0, getPixelContentHeight(level + 1))))
    );
    protected final Holder<Potion> potion;

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> codec() {
        return CODEC;
    }

    public PotionCauldronBlock(Holder<Potion> potion, CauldronInteraction.InteractionMap interactions, BlockBehaviour.Properties properties) {
        super(properties, interactions);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, MIN_FILL_LEVEL + 1));
        this.potion = potion;
    }

    @Override
    public boolean isFull(BlockState state) {
        return state.getValue(LEVEL) == MAX_FILL_LEVEL;
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

//    @Override
//    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean isPrecise) {
//        if (entity instanceof LivingEntity livingEntity && this.potion.isBound()) {
//            for(MobEffectInstance effect : this.potion.value().getEffects()) {
//                livingEntity.addEffect(new MobEffectInstance(effect));
//            }
//        }
//    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);
        if (random.nextInt(10) % 5 == 0) {
            Minecraft.getInstance().particleEngine.createParticle(
                    ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, PotionContents.getColorOptional(potion.value().getEffects()).orElse(PotionContents.BASE_POTION_COLOR)),
                    pos.getX() + 0.45 + random.nextDouble() * 0.2,
                    pos.getY() + (double) state.getValue(LEVEL) / MAX_FILL_LEVEL,
                    pos.getZ() + 0.45 + random.nextDouble() * 0.2,
                    0.0,
                    0.7,
                    0.0
            );
        }
    }

    public static void lowerFillLevel(BlockState state, Level level, BlockPos pos) {
        int newLevel = state.getValue(LEVEL) - 1;
        BlockState newState = newLevel == 1 ? Blocks.CAULDRON.defaultBlockState() : state.setValue(LEVEL, newLevel);
        level.setBlockAndUpdate(pos, newState);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));
    }

    public static void lowerFillLevelArrow(BlockState state, Level level, BlockPos pos, int levelToLower) {
        int newLevel = state.getValue(LEVEL) - levelToLower;
        BlockState newState = newLevel <= 0 ? Blocks.CAULDRON.defaultBlockState() : state.setValue(LEVEL, newLevel);
        level.setBlockAndUpdate(pos, newState);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
        return state.getValue(LEVEL) - 1;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LEVEL);
    }

    public Holder<Potion> getPotion() {
        return potion;
    }

    @Override
    public Item asItem() {
        return Items.CAULDRON;
    }
}
