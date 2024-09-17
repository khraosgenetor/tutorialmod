package net.khraos.tutorialmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties CHOCOLATE_WAFFLE = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.45F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0), 1.0f)
            .build();

}
