package com.mvks.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent CHIPS_LAYS = new FoodComponent.Builder().snack().saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200), 0.5f).build();
}
