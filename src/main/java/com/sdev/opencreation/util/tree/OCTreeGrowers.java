package com.sdev.opencreation.util.tree;

import com.sdev.opencreation.OpenCreation;
import com.sdev.opencreation.worldgen.OCConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class OCTreeGrowers {
    public static final TreeGrower HEVEA = new TreeGrower(OpenCreation.MODID + ":hevea",
            Optional.empty(), Optional.of(OCConfiguredFeatures.HEVEA_KEY), Optional.empty());
}
