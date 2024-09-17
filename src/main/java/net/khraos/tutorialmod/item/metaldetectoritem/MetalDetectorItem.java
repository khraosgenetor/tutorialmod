package net.khraos.tutorialmod.item.metaldetectoritem;

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
import net.minecraftforge.registries.RegistryObject;
import java.util.Set;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            BlockPos posClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundValuable = false;

            for (int i = 0; i <= posClicked.getY() + 64; i++) {
                BlockState state = pContext.getLevel().getBlockState(posClicked.below(i));

                if(isValuableBlock(state)) {
                    outputBlockCo_Ords(posClicked.below(i), player, state.getBlock());
                    foundValuable = true;
                    break;
                }
            }

            if (!foundValuable) {
                player.sendSystemMessage(Component.literal("Found no valuable ore."));
            }

        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                pContext.getItemInHand().getEquipmentSlot()
        );

        return InteractionResult.SUCCESS;
    }

    private void outputBlockCo_Ords(BlockPos pos, Player player, Block block) {
        player.sendSystemMessage(Component.literal
                ("Found "
                + I18n.get(block.getDescriptionId())+" at ("
                +pos.getX()+", "
                +pos.getY()+", "
                +pos.getZ()
                +")"));
    }

    private static final Set<Block> VANILLA_ORES = Set.of(
            // Vanilla ores
            Blocks.IRON_ORE, Blocks.DEEPSLATE_IRON_ORE,
            Blocks.GOLD_ORE, Blocks.DEEPSLATE_GOLD_ORE,
            Blocks.DIAMOND_ORE, Blocks.DEEPSLATE_DIAMOND_ORE,
            Blocks.COAL_ORE, Blocks.DEEPSLATE_COAL_ORE,
            Blocks.COPPER_ORE, Blocks.DEEPSLATE_COPPER_ORE,
            Blocks.EMERALD_ORE, Blocks.DEEPSLATE_EMERALD_ORE,
            Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE,
            Blocks.LAPIS_ORE, Blocks.DEEPSLATE_LAPIS_ORE,
            Blocks.NETHER_GOLD_ORE, Blocks.NETHER_QUARTZ_ORE
    );

    private static final Set<RegistryObject<Block>> MODDED_ORES = Set.of(
            ModBlocks.DEEPSLATE_RUBY_ORE,
            ModBlocks.END_STONE_RUBY_ORE,
            ModBlocks.RUBY_ORE,
            ModBlocks.NETHER_RUBY_ORE,
            ModBlocks.RUBY_CRYSTAL_BLOCK,
            ModBlocks.RUBY_CRUDE_BLOCK
    );

    private boolean isValuableBlock(BlockState state) {
        Block block = state.getBlock();

        if (VANILLA_ORES.contains(block)) {
            return true;
        }

        for (RegistryObject<Block> moddedOre : MODDED_ORES) {
            if (moddedOre.get().equals(block)) {
                return true;
            }
        }

        return false;
    }
}