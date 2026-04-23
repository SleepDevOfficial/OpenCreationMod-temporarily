package com.sdev.opencreation.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record BlueprintData(String typeKey, String russianName) {
    public static final Codec<BlueprintData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("type_key").forGetter(BlueprintData::typeKey),
                    Codec.STRING.fieldOf("ru_name").forGetter(BlueprintData::russianName)
            ).apply(instance, BlueprintData::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, BlueprintData> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, BlueprintData::typeKey,
            ByteBufCodecs.STRING_UTF8, BlueprintData::russianName,
            BlueprintData::new
    );
}