package com.sdev.opencreation.network.blueprint;

import com.sdev.opencreation.screen.draft_table.DraftTableMenu;
import com.sdev.opencreation.screen.recipe_table.RecipeTableMenu;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record RecipeTablePacket(int direction) implements CustomPacketPayload {

    public static final Type<RecipeTablePacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("opencreation", "recipe_select"));

    public static final StreamCodec<RegistryFriendlyByteBuf, RecipeTablePacket> CODEC =
            StreamCodec.of(
                    (buf, msg) -> buf.writeInt(msg.direction),
                    buf -> new RecipeTablePacket(buf.readInt())
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(RecipeTablePacket msg, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            AbstractContainerMenu menu = player.containerMenu;

            if (menu instanceof RecipeTableMenu recipe) {
                if (msg.direction == 1) recipe.next();
                else recipe.prev();
            }
        });
    }
}