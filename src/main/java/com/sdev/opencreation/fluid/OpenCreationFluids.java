package com.sdev.opencreation.fluid;

public class OpenCreationFluids {

    public static final FluidDeferredRegister FLUIDS =
            new FluidDeferredRegister();

    public static final FluidRegistryObject
            RUBBER = FLUIDS.register("rubber"),
            RUBBER_WITH_IMPURITIES = FLUIDS.register("rubber_with_impurities"),
            PYROXITE_MIXTURE = FLUIDS.register("pyroxite_mixture"),
            DISTILLED_WATER = FLUIDS.register("distilled_water"),
            SEA_WATER = FLUIDS.register("sea_water"),
            HYDROCHLORIC_ACID = FLUIDS.register("hydrochloric_acid"),
            CHLORIDE_IRON = FLUIDS.register("chloride_iron"),
            MERCURY = FLUIDS.register("mercury"),
            SULFUR_NAPHTHA = FLUIDS.register("sulfur_naphtha"),
            NAPHTHA = FLUIDS.register("naphtha"),
            CRACKED_NAPHTHA = FLUIDS.register("cracked_naphtha"),
            TITANIUM_TETRACHLORIDE = FLUIDS.register("titanium_tetrachloride");
}