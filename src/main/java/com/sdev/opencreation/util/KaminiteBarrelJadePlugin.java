package com.sdev.opencreation.util;

import com.sdev.opencreation.block.custom.KaminiteBarrel;
import com.sdev.opencreation.block.bes.KaminiteBarrelEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import snownee.jade.api.*;
import snownee.jade.api.config.IPluginConfig;

import java.util.HashMap;
import java.util.Map;

@WailaPlugin
public class KaminiteBarrelJadePlugin implements IWailaPlugin {

    public static final ResourceLocation BARREL_INFO = ResourceLocation.parse("opencreation:barrel_info");

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(new IServerDataProvider<BlockAccessor>() {
            @Override
            public void appendServerData(CompoundTag serverData, BlockAccessor accessor) {
                if (accessor.getBlockEntity() instanceof KaminiteBarrelEntity barrel) {
                    serverData.putInt("Water", barrel.getWater());

                    ItemStackHandler inv = barrel.getInventory();
                    Map<String, Integer> itemsMap = new HashMap<>();
                    Map<String, String> displayNames = new HashMap<>();

                    for (int i = 0; i < inv.getSlots(); i++) {
                        ItemStack stack = inv.getStackInSlot(i);
                        if (!stack.isEmpty()) {
                            String key = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
                            int count = stack.getCount();

                            itemsMap.put(key, itemsMap.getOrDefault(key, 0) + count);
                            displayNames.put(key, stack.getHoverName().getString());
                        }
                    }

                    CompoundTag itemsTag = new CompoundTag();
                    for (Map.Entry<String, Integer> entry : itemsMap.entrySet()) {
                        CompoundTag itemTag = new CompoundTag();
                        itemTag.putString("Name", displayNames.get(entry.getKey()));
                        itemTag.putInt("Count", entry.getValue());
                        itemsTag.put(entry.getKey(), itemTag);
                    }
                    serverData.put("GroupedItems", itemsTag);
                    serverData.putInt("UniqueItems", itemsMap.size());
                }
            }

            @Override
            public ResourceLocation getUid() {
                return BARREL_INFO;
            }
        }, KaminiteBarrelEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(new IComponentProvider<BlockAccessor>() {
            @Override
            public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
                CompoundTag serverData = accessor.getServerData();
                if (serverData == null) return;

                if (serverData.contains("Water")) {
                    int water = serverData.getInt("Water");
                    tooltip.add(Component.literal("💧 " + water + "mb/4000mb"));
                }

                if (serverData.contains("GroupedItems")) {
                    CompoundTag itemsTag = serverData.getCompound("GroupedItems");

                    if (itemsTag.isEmpty()) {
                        tooltip.add(Component.literal("Пусто"));
                    } else {
                        for (String key : itemsTag.getAllKeys()) {
                            CompoundTag itemTag = itemsTag.getCompound(key);
                            String name = itemTag.getString("Name");
                            int count = itemTag.getInt("Count");
                            tooltip.add(Component.literal("• " + name + " §7x" + count));
                        }
                    }
                }
            }

            @Override
            public ResourceLocation getUid() {
                return BARREL_INFO;
            }
        }, KaminiteBarrel.class);
    }
}