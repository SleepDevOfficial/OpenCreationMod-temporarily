package com.sdev.opencreation.chemical;

import com.sdev.opencreation.OpenCreation;
import mekanism.api.chemical.Chemical;
import mekanism.common.registration.impl.ChemicalDeferredRegister;
import mekanism.common.registration.impl.DeferredChemical;

public class OpenCreationChemical {
    public static final ChemicalDeferredRegister CHEMICALS =
            new ChemicalDeferredRegister(OpenCreation.MODID);

    public static final DeferredChemical<Chemical>
            HYDROGEN = CHEMICALS.register("hydrogen", 0xFFFFFF),
            HYDROGEN_CHLORIDE = CHEMICALS.register("hydrogen_chloride", 0xE6E6FA),
            CHLORINE = CHEMICALS.register("chlorine", 0xD4FC3D),
            VINYL_CHLORIDE = CHEMICALS.register("vinyl_chloride", 0xADD8E6),
            OXYGEN = CHEMICALS.register("oxygen", 0x87CEEB),
            NITROGEN = CHEMICALS.register("nitrogen", 0xB0C4DE),
            POLYVINYL_CHLORIDE = CHEMICALS.register("polyvinyl_chloride", 0xC8E6C9);
}
