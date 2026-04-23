package com.sdev.opencreation.network.blueprint;

import com.sdev.opencreation.screen.draft_table.DraftTableMenu;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record DraftTablePacket(int direction) implements CustomPacketPayload {

    public static final Type<DraftTablePacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("opencreation", "draft_select"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DraftTablePacket> CODEC =
            StreamCodec.of(
                    (buf, msg) -> buf.writeInt(msg.direction),
                    buf -> new DraftTablePacket(buf.readInt())
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(DraftTablePacket msg, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            AbstractContainerMenu menu = player.containerMenu;

            if (menu instanceof DraftTableMenu draft) {
                if (msg.direction == 1) draft.next();
                else draft.prev();
            }
        });
    }
}