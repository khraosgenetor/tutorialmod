package net.khraos.tutorialmod.item;

import net.khraos.tutorialmod.TutorialMod;
import net.khraos.tutorialmod.item.metaldetectoritem.MetalDetectorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> RUBY_CRUDE =
            ITEMS.register("ruby_crude",
                    () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUBY_CRYSTAL =
            ITEMS.register("ruby_crystal",
                    () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CORUNDUM =
            ITEMS.register("corundum",
                    () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METAL_DETECTOR =
            ITEMS.register("metal_detector",
                    () -> new MetalDetectorItem(new Item.Properties().durability(100)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
