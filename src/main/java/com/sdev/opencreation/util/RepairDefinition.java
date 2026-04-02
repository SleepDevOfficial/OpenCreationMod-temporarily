package com.sdev.opencreation.util;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.function.Supplier;

public class RepairDefinition {
    private final Supplier<Ingredient> defaultIngredient;
    private final List<Supplier<Ingredient>> stages;

    private RepairDefinition(Supplier<Ingredient> defaultIngredient,
                             List<Supplier<Ingredient>> stages) {
        this.defaultIngredient = defaultIngredient;
        this.stages = stages;
    }

    public static RepairDefinition tier() {
        return new RepairDefinition(null, null);
    }

    public static RepairDefinition of(Supplier<Ingredient> ingredient) {
        return new RepairDefinition(ingredient, null);
    }

    public static RepairDefinition staged(List<Supplier<Ingredient>> stages) {
        return new RepairDefinition(null, stages);
    }

    public boolean hasStages() {
        return stages != null && !stages.isEmpty();
    }

    public List<Supplier<Ingredient>> getStages() {
        return stages;
    }

    public Supplier<Ingredient> getDefaultIngredient(Tier tier) {
        return defaultIngredient != null ? defaultIngredient : tier::getRepairIngredient;
    }
}