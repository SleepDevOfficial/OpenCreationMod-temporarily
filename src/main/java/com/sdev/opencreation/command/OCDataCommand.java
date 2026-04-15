package com.sdev.opencreation.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.sdev.opencreation.blueprint.BlueprintData;
import com.sdev.opencreation.data.OCDataComponents;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class OCDataCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("ocdata")
                        .requires(source -> source.hasPermission(2))
                        .executes(OCDataCommand::execute)
        );
    }

    private static int execute(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();

        if (!(source.getEntity() instanceof Player player)) {
            source.sendFailure(Component.literal("Эту команду может выполнять только игрок."));
            return 0;
        }

        ItemStack heldItem = player.getMainHandItem();
        BlueprintData data = heldItem.get(OCDataComponents.BLUEPRINT_DATA.get());

        if (heldItem.isEmpty()) {
            source.sendFailure(Component.literal("Предмет в руке отсутствует."));
            return 0;
        }



        if (data == null) {
            source.sendFailure(Component.literal("Этот предмет не является чертежом или не содержит данных о типе чертежа."));
            return 0;
        }

        String typeKey = data.typeKey();
        String russianName = data.russianName();

        source.sendSuccess(() -> Component.literal("Tech key (type_key): " + typeKey), false);
        source.sendSuccess(() -> Component.literal("Name: " + russianName), false);

        return 1;
    }
}