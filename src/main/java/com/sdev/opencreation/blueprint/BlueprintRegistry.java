package com.sdev.opencreation.blueprint;

import java.util.ArrayList;
import java.util.List;

public class BlueprintRegistry {

    private static final List<BlueprintData> BLUEPRINT_TYPES = new ArrayList<>();

    static {
        addType("basic_gear", "Базовая шестерня");
        addType("advanced_gear", "Продвинутая шестерня");
        addType("iron_plate", "Железная пластина");
        addType("gold_plate", "Золотая пластина");
        addType("diamond_blade", "Алмазное лезвие");
        addType("stone_brick", "Каменный кирпич");
        addType("redstone_circuit", "Редстоун схема");
        addType("copper_wire", "Медная проволока");
        addType("steel_beam", "Стальная балка");
        addType("wooden_handle", "Деревянная рукоять");
    }

    private static void addType(String key, String name) {
        BLUEPRINT_TYPES.add(new BlueprintData(key, name));
    }

    public static List<BlueprintData> getTypes() {
        return BLUEPRINT_TYPES;
    }
}