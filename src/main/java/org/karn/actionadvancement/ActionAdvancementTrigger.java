package org.karn.actionadvancement;

import net.minecraft.util.Identifier;

public interface ActionAdvancementTrigger {
    Identifier LEFT_CLICK = new Identifier(ActionAdvancement.ID, "left_click");
    Identifier MOVE_LEFT = new Identifier(ActionAdvancement.ID, "move_left");
    Identifier MOVE_RIGHT = new Identifier(ActionAdvancement.ID, "move_right");
    Identifier MOVE_FORWARD = new Identifier(ActionAdvancement.ID, "move_forward");
    Identifier MOVE_BACKWARD = new Identifier(ActionAdvancement.ID, "move_backward");
    Identifier JUMP = new Identifier(ActionAdvancement.ID, "jump");
    Identifier SNEAK = new Identifier(ActionAdvancement.ID, "sneak");
    Identifier SWAP_HAND = new Identifier(ActionAdvancement.ID, "swap_hand");
}
