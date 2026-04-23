package com.sdev.opencreation.blueprint;

import com.sdev.opencreation.data.BlueprintData;

import java.util.ArrayList;
import java.util.List;

public class RecipeRegistry {

    private static final List<BlueprintData> RECIPE_TYPES = new ArrayList<>();

    static {
        addType("basic_gear", "Базовая шsadadестерня");
        addType("advanced_gear", "Продвинутаadasdя шестерня");
        addType("iron_plate", "Железная пластина");
        addType("gold_plate", "Золотая пластина");
        addType("diamond_blade", "Алмазное adasdлезвие");
        addType("stone_brick", "Каменный кирпич");
        addType("redstone_circuit", "Редстоун схема");
        addType("copper_wire", "Медная проволока");
        addType("steel_beam", "Стальная балка");
        addType("wooden_handle", "Деревянная рукоять");
    }

    private static void addType(String key, String name) {
        RECIPE_TYPES.add(new BlueprintData(key, name));
    }

    public static List<BlueprintData> getTypes() {
        return RECIPE_TYPES;
    }
}