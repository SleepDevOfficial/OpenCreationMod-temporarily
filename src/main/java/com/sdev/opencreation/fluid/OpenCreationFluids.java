package com.sdev.opencreation.fluid;

public class OpenCreationFluids {

    public static final FluidDeferredRegister FLUIDS =
            new FluidDeferredRegister();

    public static final FluidRegistryObject
            RUBBER = FLUIDS.register("rubber", 0, 0, 0),
            RUBBER_WITH_IMPURITIES = FLUIDS.register("rubber_with_impurities", 0, 0, 0),
            PYROXITE_MIXTURE = FLUIDS.register("pyroxite_mixture", 0, 0, 0),
            DISTILLED_WATER = FLUIDS.register("distilled_water", 0, 0, 0),
            SEA_WATER = FLUIDS.register("sea_water", 0, 0, 0),
            HYDROCHLORIC_ACID = FLUIDS.register("hydrochloric_acid", 0, 0, 0),
            CHLORIDE_IRON = FLUIDS.register("chloride_iron", 0, 0, 0),
            MERCURY = FLUIDS.register("mercury", 0, 0, 0),
            SULFUR_NAPHTHA = FLUIDS.register("sulfur_naphtha", 0, 0, 0),
            NAPHTHA = FLUIDS.register("naphtha", 0, 0, 0),
            CRACKED_NAPHTHA = FLUIDS.register("cracked_naphtha", 0, 0, 0),
            TITANIUM_TETRACHLORIDE = FLUIDS.register("titanium_tetrachloride", 0, 0, 0);
}