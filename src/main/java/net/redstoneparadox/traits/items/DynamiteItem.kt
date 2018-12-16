package net.redstoneparadox.traits.items

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.thrown.ThrownPotionEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

/**
 * Created by RedstoneParadox on 12/13/2018.
 */
class DynamiteItem(var1: Settings?) : Item(var1) {


    override fun use(world: World, playerEntity: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        var player : PlayerEntity = playerEntity as PlayerEntity

        var dynamite : ItemStack = player.getStackInHand(hand)
        var var5 : ItemStack

        if (player.abilities.creativeMode) {
            var5 = dynamite.copy()
        }
        else {
            var5 = dynamite.split(1)
        }

        world.playSound(null as PlayerEntity?, player.x, player.y, player.z, SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYER, 0.5f, 0.4f / (Item.random.nextFloat() * 0.4f + 0.8f))

        if (!world.isRemote) {
            var thrownDynamite : ThrownPotionEntity = ThrownPotionEntity(world, player)
            thrownDynamite.setItemStack(var5)
            thrownDynamite.method_7489(player, player.pitch, player.yaw, -20.0f, 0.5f, 1.0f)
            world.spawnEntity(thrownDynamite)
        }

        //Increment a stat for throwing dynamite.
        return TypedActionResult(ActionResult.SUCCESS, dynamite)
    }
}