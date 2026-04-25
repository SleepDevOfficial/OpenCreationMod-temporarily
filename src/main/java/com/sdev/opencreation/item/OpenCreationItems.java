package com.sdev.opencreation.item;

import com.sdev.opencreation.data.BlueprintData;
import com.sdev.opencreation.data.OCDataComponents;
import com.sdev.opencreation.item.custom.*;
import com.sdev.opencreation.item.custom.tools.*;
import com.sdev.opencreation.util.OpenCreationTags;
import com.sdev.opencreation.util.OpenCreationTiers;
import com.sdev.opencreation.util.RepairDefinition;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

import static com.sdev.opencreation.OpenCreation.MODID;

public class OpenCreationItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item>
            DEBUG_TOAST = ITEMS.register("debug_toast",
                    () -> new Item(new Item.Properties())),
            PENCIL = ITEMS.register("pencil",
                    () -> new RepirableItem(new Item.Properties().durability(2), Items.COAL, null)),
            RULER = ITEMS.register("ruler",
                    () -> new RepirableItem(new Item.Properties().durability(4), null, OpenCreationTags.Items.PLANK)),
            DRAFTING_COMPASS = ITEMS.register("drafting_compass",
                    () -> new RepirableItem(new Item.Properties().durability(8), Items.COPPER_INGOT, null)),
            BLUEPRINT = ITEMS.register("blueprint",
                    () -> new BlueprintItem(new Item.Properties().component(OCDataComponents.BLUEPRINT_DATA.get(), new BlueprintData("empty_type", "Пустой чертеж")))),
            RECIPE = ITEMS.register("recipe",
                    () -> new BlueprintItem(new Item.Properties().component(OCDataComponents.BLUEPRINT_DATA.get(), new BlueprintData("empty_type", "Пустой рецепт")))),
            FRAGMENTS_OF_STONES = ITEMS.register("fragments_of_stones",
                    () -> new Item(new Item.Properties())),
            TWIG = ITEMS.register("twig",
                    () -> new Item(new Item.Properties())),
            PEBBLE = ITEMS.register("pebble",
                    () -> new Item(new Item.Properties())),
            BLADE_OF_GRASS = ITEMS.register("blade_of_grass",
                    () -> new Item(new Item.Properties().stacksTo(96))),
            LATEX = ITEMS.register("latex",
                    () -> new Item(new Item.Properties())),
            AXE_HEAD = ITEMS.register("axe_head",
                    () -> new Item(new Item.Properties().stacksTo(1))),
            PRIMITIVE_HANDLE = ITEMS.register("primitive_handle",
                    () -> new Item(new Item.Properties().stacksTo(1))),
            IMPROVED_PRIMITIVE_HANDLE = ITEMS.register("improved_primitive_handle",
                    () -> new Item(new Item.Properties().stacksTo(1))),
            PRIMITIVE_WEDGE = ITEMS.register("primitive_wedge",
                    () -> new Item(new Item.Properties().stacksTo(1))),
            IMPROVED_PRIMITIVE_WEDGE = ITEMS.register("improved_primitive_wedge",
                    () -> new Item(new Item.Properties().stacksTo(1))),
            PRIMITIVE_CAMPFIRE = ITEMS.register("primitive_campfire",
                    () -> new PrimitiveCampfire(new Item.Properties())),
            ASH = ITEMS.register("ash",
                    () -> new Item(new Item.Properties())),
            PRIMITIVE_IGNITE = ITEMS.register("primitive_ignite",
                    () -> new PrimitiveIgnite(new Item.Properties())),
            BARK = ITEMS.register("bark",
                    () -> new Item(new Item.Properties())),
            OAK_PLANK = ITEMS.register("oak_plank",
                    () -> new Item(new Item.Properties().stacksTo(96))),
            SPRUCE_PLANK = ITEMS.register("spruce_plank",
                    () -> new Item(new Item.Properties().stacksTo(96))),
            BIRCH_PLANK = ITEMS.register("birch_plank",
                    () -> new Item(new Item.Properties().stacksTo(96))),
            JUNGLE_PLANK = ITEMS.register("jungle_plank",
                    () -> new Item(new Item.Properties().stacksTo(96))),
            ACACIA_PLANK = ITEMS.register("acacia_plank",
                    () -> new Item(new Item.Properties().stacksTo(96))),
            DARK_OAK_PLANK = ITEMS.register("dark_oak_plank",
                    () -> new Item(new Item.Properties().stacksTo(96))),
            MANGROVE_PLANK = ITEMS.register("mangrove_plank",
                    () -> new Item(new Item.Properties().stacksTo(96))),
            CHERRY_PLANK = ITEMS.register("cherry_plank",
                    () -> new Item(new Item.Properties().stacksTo(96))),
            BAMBOO_PLANK = ITEMS.register("bamboo_plank",
                    () -> new Item(new Item.Properties().stacksTo(96))),
            CRIMSON_PLANK = ITEMS.register("crimson_plank",
                    () -> new Item(new Item.Properties().stacksTo(96))),
            WARPED_PLANK = ITEMS.register("warped_plank",
                    () -> new Item(new Item.Properties().stacksTo(96))),
            KAMINITE_MIXTURE = ITEMS.register("kaminite_mixture",
                    () -> new Item(new Item.Properties())),
            KAMINITE = ITEMS.register("kaminite",
                    () -> new Item(new Item.Properties())),
            DRIED_BUNCH_OF_GRASS = ITEMS.register("dried_bunch_of_grass",
                    () -> new Item(new Item.Properties())),
            CRUSHED_GRANITE = ITEMS.register("crushed_granite",
                    () -> new Item(new Item.Properties())),
            CRUSHED_DIORITE = ITEMS.register("crushed_diorite",
                    () -> new Item(new Item.Properties())),
            CRUSHED_DEEPSLATE = ITEMS.register("crushed_deepslate",
                    () -> new Item(new Item.Properties())),
            CRUSHED_ANDESITE = ITEMS.register("crushed_andesite",
                    () -> new Item(new Item.Properties())),
            CRUSHED_BASALT = ITEMS.register("crushed_basalt",
                    () -> new Item(new Item.Properties())),
            CRUSHED_BLACKSTONE = ITEMS.register("crushed_blackstone",
                    () -> new Item(new Item.Properties())),
            GRANITE_ALLOY = ITEMS.register("granite_alloy",
                    () -> new Item(new Item.Properties())),
            DIORITE_ALLOY = ITEMS.register("diorite_alloy",
                    () -> new Item(new Item.Properties())),
            DEEPSLATE_ALLOY = ITEMS.register("deepslate_alloy",
                    () -> new Item(new Item.Properties())),
            BASALT_ALLOY = ITEMS.register("basalt_alloy",
                    () -> new Item(new Item.Properties())),
            BLACKSTONE_ALLOY = ITEMS.register("blackstone_alloy",
                    () -> new Item(new Item.Properties())),
            GRANITE_BODY_FRAME = ITEMS.register("granite_body_frame",
                    () -> new Item(new Item.Properties())),
            DIORITE_BODY_FRAME = ITEMS.register("diorite_body_frame",
                    () -> new Item(new Item.Properties())),
            DEEPSLATE_BODY_FRAME = ITEMS.register("deepslate_body_frame",
                    () -> new Item(new Item.Properties())),
            ANDESITE_BODY_FRAME = ITEMS.register("andesite_body_frame",
                    () -> new Item(new Item.Properties())),
            BASALT_BODY_FRAME = ITEMS.register("basalt_body_frame",
                    () -> new Item(new Item.Properties())),
            BLACKSTONE_BODY_FRAME = ITEMS.register("blackstone_body_frame",
                    () -> new Item(new Item.Properties())),
            SAW_BLADE = ITEMS.register("saw_blade",
                    () -> new Item(new Item.Properties())),
            LATEX_SHEET = ITEMS.register("latex_sheet",
                    () -> new Item(new Item.Properties())),
            HOT_RUBBER = ITEMS.register("hot_rubber",
                    () -> new Item(new Item.Properties())),
            HUGE_IRON_SHEET = ITEMS.register("huge_iron_sheet",
                    () -> new Item(new Item.Properties())),
            PRESSED_LAPIS_BLANK = ITEMS.register("pressed_lapis_blank",
                    () -> new Item(new Item.Properties())),
            PRESSED_REDSTONE_BLANK = ITEMS.register("pressed_redstone_blank",
                    () -> new Item(new Item.Properties())),
            LAPIS_BLANK = ITEMS.register("lapis_blank",
                    () -> new Item(new Item.Properties())),
            REDSTONE_BLANK = ITEMS.register("redstone_blank",
                    () -> new Item(new Item.Properties())),
            LAPIS_TEXTOLITE = ITEMS.register("lapis_textolite",
                    () -> new Item(new Item.Properties())),
            REDSTONE_TEXTOLITE = ITEMS.register("redstone_textolite",
                    () -> new Item(new Item.Properties())),
            REDSTONE_CHIP = ITEMS.register("redstone_chip",
                    () -> new Item(new Item.Properties())),
            COOLED_PYROXITE = ITEMS.register("cooled_pyroxite",
                    () -> new Item(new Item.Properties())),
            LAPIS_CHIP = ITEMS.register("lapis_chip",
                    () -> new Item(new Item.Properties())),
            MODIFIED_REDSTONE_SHIP = ITEMS.register("modified_redstone_chip",
                    () -> new Item(new Item.Properties())),
            MODIFIED_LAPIS_SHEP = ITEMS.register("modified_lapis_chip",
                    () -> new Item(new Item.Properties())),
            FLASKS = ITEMS.register("flasks",
                    () -> new RepirableItem(new Item.Properties().durability(4), Items.GLASS, null)),
            MORTAR_AND_PESTLE = ITEMS.register("mortar_and_pestle",
                    () -> new RepirableItem(new Item.Properties().durability(8), Items.IRON_INGOT, null)),
            MERCURY = ITEMS.register("mercury",
                    () -> new Item(new Item.Properties())),
            MERCURY_LAMP = ITEMS.register("mercury_lamp",
                    () -> new Item(new Item.Properties())),
            QUARTZ_LAMP = ITEMS.register("quartz_lamp",
                    () -> new Item(new Item.Properties())),
            CINNABAR = ITEMS.register("cinnabar",
                    () -> new Item(new Item.Properties())),
            HYDROCARBON_FIBERS = ITEMS.register("hydrocarbon_fibers",
                    () -> new Item(new Item.Properties())),
            HYDROCARBON_COMPOSITE = ITEMS.register("hydrocarbon_composite",
                    () -> new Item(new Item.Properties())),
            RESISTOR = ITEMS.register("resistor",
                    () -> new Item(new Item.Properties())),
            MAGNETIC_STEEL = ITEMS.register("magnetic_steel",
                    () -> new Item(new Item.Properties())),
            PRECIOUS_METAL = ITEMS.register("precious_metal",
                    () -> new Item(new Item.Properties())),

            WROUGHT_IRON = ITEMS.register("wrought_iron",
                    () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item>
            DEEPSLATE_SHOVEL_HOE = ITEMS.register("deepslate_shovel_hoe",
                    () -> new OCShovelHoe(OpenCreationTiers.ALLOYING, new Item.Properties()
                            .attributes(DiggerItem.createAttributes(OpenCreationTiers.ALLOYING, 2f, 2f)),
                            RepairDefinition.of(() -> Ingredient.of(OpenCreationItems.DEEPSLATE_ALLOY.get())))),
            BLACKSTONE_SHOVEL_HOE = ITEMS.register("blackstone_shovel_hoe",
                    () -> new OCShovelHoe(OpenCreationTiers.NETHER_ALLOYING, new Item.Properties()
                            .attributes(DiggerItem.createAttributes(OpenCreationTiers.NETHER_ALLOYING, 3f, 3f)),
                            RepairDefinition.of(() -> Ingredient.of(OpenCreationItems.BLACKSTONE_ALLOY.get())))),
            STEEL_SHOVEL_HOE = ITEMS.register("steel_shovel_hoe",
                    () -> new OCShovelHoe(OpenCreationTiers.STEEL, new Item.Properties()
                            .attributes(DiggerItem.createAttributes(OpenCreationTiers.STEEL, 4f, 4f)),
                            RepairDefinition.of(() -> Ingredient.of(OpenCreationItems.MAGNETIC_STEEL.get()))));
    public static final DeferredItem<AxeItem>
            PRIMITIVE_AXE = ITEMS.register("primitive_axe",
                    () -> new OCAxe(OpenCreationTiers.PRIMITIVE,new Item.Properties()
                            .attributes(AxeItem.createAttributes(OpenCreationTiers.PRIMITIVE, -1f, -4f)),
                            RepairDefinition.staged(List.of(
                                    () -> Ingredient.of(OpenCreationItems.FRAGMENTS_OF_STONES),
                                    () -> Ingredient.of(OpenCreationTags.Items.PLANK)
                            )))),
            IMPROVED_AXE = ITEMS.register("improved_axe",
                    () -> new OCAxe(OpenCreationTiers.IMPROVED, new Item.Properties()
                            .attributes(AxeItem.createAttributes(OpenCreationTiers.IMPROVED, 0f, -3.6f)),
                            RepairDefinition.tier())),
            KAMINITE_AXE = ITEMS.register("kaminite_axe",
                    () -> new OCAxe(OpenCreationTiers.KAMINITE, new Item.Properties()
                            .attributes(AxeItem.createAttributes(OpenCreationTiers.KAMINITE, 1f, -3.5f)),
                            RepairDefinition.tier())),

            ANDESITE_AXE = ITEMS.register("andesite_axe",
                    () -> new OCAxe(OpenCreationTiers.ALLOYING, new Item.Properties()
                            .attributes(AxeItem.createAttributes(OpenCreationTiers.ALLOYING, 2f, -3.6f)),
                            RepairDefinition.of(() -> Ingredient.of(BuiltInRegistries.ITEM.get(ResourceLocation.tryParse("create:andesite_alloy")))))),

            BASALT_AXE = ITEMS.register("basalt_axe",
                    () -> new OCAxe(OpenCreationTiers.NETHER_ALLOYING, new Item.Properties()
                            .attributes(AxeItem.createAttributes(OpenCreationTiers.NETHER_ALLOYING, 4f, -3.8f)),
                            RepairDefinition.of(() -> Ingredient.of(OpenCreationItems.BASALT_ALLOY.get())))),

            WROUGHT_IRON_AXE = ITEMS.register("wrought_iron_axe",
                    () -> new OCAxe(OpenCreationTiers.STEEL, new Item.Properties()
                            .attributes(AxeItem.createAttributes(OpenCreationTiers.STEEL,5f, -3.6f)),
                            RepairDefinition.of(() -> Ingredient.of(OpenCreationItems.WROUGHT_IRON.get()))));

    public static final DeferredItem<PickaxeItem>
            PRIMITIVE_PICKAXE = ITEMS.register("primitive_pickaxe",
            () -> new OCPickaxe(OpenCreationTiers.IMPROVED, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(OpenCreationTiers.IMPROVED, -2f, 3f)),
                    RepairDefinition.tier())),

            KAMINITE_PICKAXE = ITEMS.register("kaminite_pickaxe",
            () -> new OCPickaxe(OpenCreationTiers.KAMINITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(OpenCreationTiers.KAMINITE, -1f, 4f)),
                    RepairDefinition.tier())),
            DIORITE_PICKAXE = ITEMS.register("diorite_pickaxe",
                    () -> new OCPickaxe(OpenCreationTiers.ALLOYING, new Item.Properties()
                            .attributes(PickaxeItem.createAttributes(OpenCreationTiers.ALLOYING, 1f, 2f)),
                            RepairDefinition.of(() -> Ingredient.of(OpenCreationItems.DIORITE_ALLOY.get())))),

            BLACKSTONE_PICKAXE = ITEMS.register("blackstone_pickaxe",
                    () -> new OCPickaxe(OpenCreationTiers.NETHER_ALLOYING, new Item.Properties()
                            .attributes(PickaxeItem.createAttributes(OpenCreationTiers.NETHER_ALLOYING, 2f, 1f)),
                            RepairDefinition.of(() -> Ingredient.of(OpenCreationItems.MAGNETIC_STEEL.get())))),

            STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
                    () -> new OCPickaxe(OpenCreationTiers.STEEL, new Item.Properties()
                            .attributes(PickaxeItem.createAttributes(OpenCreationTiers.STEEL, 2f, 1f)),
                            RepairDefinition.of(() -> Ingredient.of(BuiltInRegistries.ITEM.get(ResourceLocation.tryParse("create:andesite_alloy"))))));

    public static final DeferredItem<ShovelItem>
            PRIMITIVE_SHOVEL = ITEMS.register("primitive_shovel",
            () -> new OCShovel(OpenCreationTiers.IMPROVED, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(OpenCreationTiers.IMPROVED, -1f, -4f)),
                    RepairDefinition.tier())),

            KAMINITE_SHOVEL = ITEMS.register("kaminite_shovel",
                    () -> new OCShovel(OpenCreationTiers.KAMINITE, new Item.Properties()
                            .attributes(ShovelItem.createAttributes(OpenCreationTiers.KAMINITE, 0f, -2f)),
                            RepairDefinition.tier()));


    public static final DeferredItem<SwordItem>
            PRIMITIVE_SWORD = ITEMS.register("primitive_sword",
            () -> new OCSword(OpenCreationTiers.IMPROVED, new Item.Properties()
                    .attributes(SwordItem.createAttributes(OpenCreationTiers.IMPROVED, 1f, -3f)),
                    RepairDefinition.tier())),

            KAMINITE_SWORD = ITEMS.register("kaminite_sword",
                    () -> new OCSword(OpenCreationTiers.KAMINITE, new Item.Properties()
                            .attributes(SwordItem.createAttributes(OpenCreationTiers.KAMINITE, 2f, -2f)),
                            RepairDefinition.tier())),
            GRANITE_SWORD = ITEMS.register("granite_sword",
                    () -> new OCSword(OpenCreationTiers.IMPROVED, new Item.Properties()
                            .attributes(SwordItem.createAttributes(OpenCreationTiers.ALLOYING, 2f, -1f)),
                            RepairDefinition.of(() -> Ingredient.of(OpenCreationItems.GRANITE_ALLOY.get())))),

            BASALT_SWORD = ITEMS.register("basalt_sword",
                    () -> new OCSword(OpenCreationTiers.NETHER_ALLOYING, new Item.Properties()
                            .attributes(SwordItem.createAttributes(OpenCreationTiers.NETHER_ALLOYING, 3f, 1f)),
                            RepairDefinition.of(() -> Ingredient.of(OpenCreationItems.BASALT_ALLOY.get())))),

            WROUGHT_IRON_SWORD = ITEMS.register("wrought_iron_sword",
                    () -> new OCSword(OpenCreationTiers.STEEL, new Item.Properties()
                            .attributes(SwordItem.createAttributes(OpenCreationTiers.STEEL,4f, 2f)),
                            RepairDefinition.of(() -> Ingredient.of(OpenCreationItems.WROUGHT_IRON.get()))));

    public static final DeferredItem<HoeItem>
            KAMINITE_HOE = ITEMS.register("kaminite_hoe",
            () -> new OCHoe(OpenCreationTiers.KAMINITE, new Item.Properties()
                    .attributes(HoeItem.createAttributes(OpenCreationTiers.KAMINITE, 0f, -5f)),
                    RepairDefinition.tier()));
}
