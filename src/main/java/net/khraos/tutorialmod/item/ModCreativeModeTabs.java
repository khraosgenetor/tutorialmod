package net.khraos.tutorialmod.item;

import net.khraos.tutorialmod.TutorialMod;
import net.khraos.tutorialmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_GEMS =
            CREATIVE_MODE_TABS.register("tutorial_gems",
                    () -> CreativeModeTab.builder()
                        .icon(() -> new ItemStack(ModItems.RUBY_CRYSTAL.get()))
                        .title(Component.translatable("creativetab.tutorial_gems"))
                            .displayItems((itemDisplayParameters, output) -> {
                                output.accept(ModItems.RUBY_CRYSTAL.get());
                                output.accept(ModItems.RUBY_CRUDE.get());
                                output.accept(ModBlocks.RUBY_CRYSTAL_BLOCK.get());
                                output.accept(ModBlocks.RUBY_CRUDE_BLOCK.get());
                            })
                        .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
