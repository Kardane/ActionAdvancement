package org.karn.actionadvancement;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.advancement.criterion.CriterionConditions;

public class ActionAdvancementCriteria {
    public static final TriggerCriterion TRIGGER = register("trigger", new TriggerCriterion());

    public static <E extends CriterionConditions, T extends Criterion<E>> T register(String path, T item) {
        Criteria.register(ActionAdvancement.ID + ":" + path, item);
        return item;
    }

    public static void register() {}

}
