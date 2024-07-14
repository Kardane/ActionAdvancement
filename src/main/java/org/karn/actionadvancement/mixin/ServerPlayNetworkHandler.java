package org.karn.actionadvancement.mixin;

import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(net.minecraft.server.network.ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandler {
    @Shadow
    public ServerPlayerEntity player;
    public boolean isDropItem = false;

    @Inject(method = "onPlayerAction", at = @At("HEAD"))
    private void karn$onPlayerAction(PlayerActionC2SPacket packet, CallbackInfo ci) {
        System.out.println(packet.getAction().toString());
        if (packet.getAction() == PlayerActionC2SPacket.Action.DROP_ITEM || packet.getAction() == PlayerActionC2SPacket.Action.DROP_ALL_ITEMS){
            //System.out.println("DropItem!");
            isDropItem = true;
        }
    }

    @Inject(method = "onHandSwing", at = @At("TAIL"), cancellable = true)
    private void karn$onHandSwing(HandSwingC2SPacket packet, CallbackInfo ci) {
        double entityreachDistance = this.player.getEntityInteractionRange();
        double blockreachDistance = this.player.getBlockInteractionRange();
        Vec3d eyePos = player.getEyePos();
        Vec3d viewVector = player.getRotationVector().multiply(blockreachDistance);
        Vec3d vec32 = eyePos.add(viewVector);
        BlockHitResult result = player.getWorld().raycast(new RaycastContext(eyePos, vec32, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, player));

        if (result != null && result.getType() != HitResult.Type.MISS) {
            return;
        }

        Box aabb = player.getBoundingBox().expand(viewVector.x,viewVector.y,viewVector.z).expand(1);
        EntityHitResult result2 = ProjectileUtil.raycast(player, eyePos, vec32, aabb, (entity) -> !entity.isSpectator(),entityreachDistance*entityreachDistance);

        if (result2 != null && result2.getType() != HitResult.Type.MISS) {
            return;
        }
        //System.out.println(isDropItem);
        //if (!isDropItem)
        //System.out.println("Swing!");
        isDropItem = false;
    }
}
