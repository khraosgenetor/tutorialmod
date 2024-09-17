package net.khraos.tutorialmod.item.OreDetectorItem;

import net.khraos.tutorialmod.block.ModBlocks;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class OreDetectorItem extends Item {
    public OreDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            BlockPos posClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            BlockPos closestOrePos = null;
            Block closestOre = null;
            double minDistance = Double.MAX_VALUE;

            // Get the chunk boundaries (16x16 in X and Z)
            int chunkX = posClicked.getX() & ~15;
            int chunkZ = posClicked.getZ() & ~15;

            // Scan the entire chunk
            for (int x = chunkX; x < chunkX + 16; x++) {
                for (int z = chunkZ; z < chunkZ + 16; z++) {
                    for (int y = pContext.getLevel().getMinBuildHeight(); y <= pContext.getLevel().getMaxBuildHeight(); y++) {
                        BlockPos currentPos = new BlockPos(x, y, z);
                        BlockState state = pContext.getLevel().getBlockState(currentPos);
                        Block block = state.getBlock();

                        // Check if the current block is a valuable ore
                        if (isValuableBlock(state)) {
                            double distance = posClicked.distSqr(currentPos);

                            // Update the closest ore
                            if (distance < minDistance) {
                                minDistance = distance;
                                closestOrePos = currentPos;
                                closestOre = block;
                            }
                        }
                    }
                }
            }

            // Output the closest ore found
            if (closestOre != null) {
                outputBlockCo_Ords(closestOrePos, player, closestOre);
            } else {
                player.sendSystemMessage(Component.literal("Found no valuable ore in the chunk."));
            }
        }

        // Damage the item after use
        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                pContext.getItemInHand().getEquipmentSlot()
        );

        return InteractionResult.SUCCESS;
    }

    private void outputBlockCo_Ords(BlockPos pos, Player player, Block block) {
        player.sendSystemMessage(Component.literal
                ("Found " + I18n.get(block.getDescriptionId()) + " at ("
                        + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ")"));
    }

    private static final Set<Block> VALUABLE_ORES = Set.of(
            // Vanilla ores
            Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE,
            Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE,
            Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE,
            Blocks.COAL_ORE, Blocks.DEEPSLATE_COAL_ORE,
            Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE,
            Blocks.EMERALD_ORE, Blocks.DEEPSLATE_EMERALD_ORE,
            Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE,
            Blocks.LAPIS_ORE, Blocks.DEEPSLATE_LAPIS_ORE,
            Blocks.NETHER_GOLD_ORE, Blocks.NETHER_QUARTZ_ORE,

            // Modded ores
            ModBlocks.RUBY_ORE.get(), ModBlocks.DEEPSLATE_RUBY_ORE.get(),
            ModBlocks.END_STONE_RUBY_ORE.get(), ModBlocks.NETHER_RUBY_ORE.get()
    );

    private boolean isValuableBlock(BlockState state) {
        return VALUABLE_ORES.contains(state.getBlock());
    }
}
