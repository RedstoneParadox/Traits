package net.redstoneparadox.traits.traitspackage

import net.minecraft.entity.LivingEntity

/**
 * Created by RedstoneParadox on 12/14/2018.
 */
class ConsoleSpamTrait(weight: Int, tickable: Boolean, name: String) : BaseTrait(weight, tickable, name) {

    override fun copy() : ConsoleSpamTrait {
        return ConsoleSpamTrait(weight, tickable, name)
    }

    override fun tickTarget(target: LivingEntity) {

    }
}