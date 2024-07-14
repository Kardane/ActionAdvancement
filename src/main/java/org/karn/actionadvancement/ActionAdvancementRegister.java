package org.karn.actionadvancement;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ActionAdvancementRegister extends FabricAdvancementProvider {
    protected ActionAdvancementRegister(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        var root = Advancement.Builder.create()
                .display(
                        Items.DIAMOND_SWORD,
                        Text.literal("Action Advancement"),
                        Text.literal("Action Advancement Example!"),
                        null,
                        AdvancementFrame.TASK,
                        false,
                        false,
                        false
                )
                .criterion("any_item", InventoryChangedCriterion.Conditions.items(
                        ItemPredicate.Builder.create().build()
                ))
                .build(consumer, "actionadvancement:root");
        this.testAdvancement(registryLookup, consumer);
    }

    private void testAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        var left_click = Advancement.Builder.create()
                .display(
                        Items.DIAMOND_SWORD,
                        Text.literal("Left Click!"),
                        Text.literal("Left Clicked in mid air!"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        false,
                        false
                )
                .criterion("left_click", TriggerCriterion.of(ActionAdvancementTrigger.LEFT_CLICK))
                .build(consumer, "actionadvancement:root/left_click");
    }
}
