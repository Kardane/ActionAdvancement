package org.karn.actionadvancement;

import net.fabricmc.api.ModInitializer;

public class ActionAdvancement implements ModInitializer {
    public static final String ID = "actionadvancement";
    @Override
    public void onInitialize() {
        System.out.println("Karn's ActionAdvancement is initialized!");
        ActionAdvancementCriteria.register();
    }
}
