package com.sdev.opencreation.util;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class OCHelpers {
    public static void Stupid(Player player) {
        player.displayClientMessage(Component.literal("Похоже, вы не знаете что это такое."), true);
    }
}
