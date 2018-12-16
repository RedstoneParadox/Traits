package net.redstoneparadox.traits.traitspackage

import net.minecraft.entity.mob.MobEntity

/**
 * Created by RedstoneParadox on 12/14/2018.
 */
class ConsoleSpamTrait(name: String, weight: Int, tickable: Boolean, minLevel: Int, maxLevel: Int) : Trait(name, weight, tickable, minLevel, maxLevel) {

    override fun tickTarget(target: MobEntity) {

    }
}