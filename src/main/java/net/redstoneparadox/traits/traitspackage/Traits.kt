package net.redstoneparadox.traits.traitspackage

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.boss.EntityWither
import net.minecraft.entity.boss.dragon.EnderDragonEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.StringTag

/**
 * Created by RedstoneParadox on 12/14/2018.
 */
class Traits {

    fun initTraits() {
        consoleSpamTrait = ConsoleSpamTrait(1, true, "console_spam")
    }

    fun registerTrait(trait: BaseTrait) : BaseTrait {
        traitRegistry.add(trait)
        return trait
    }

    companion object TraitFactory{

        var traitRegistry : ArrayList<BaseTrait> = ArrayList()

        lateinit var infernalTrait: BaseTrait
        lateinit var consoleSpamTrait: ConsoleSpamTrait

        fun applyTrait(target: LivingEntity) {

            if (target is PlayerEntity || target is EnderDragonEntity || target is PassiveEntity || target is EntityWither) {
                return
            }

            (target as ITraitEntity).addTrait(consoleSpamTrait.copy())
        }

        fun getTraitsFromTag(listTag: ListTag): java.util.ArrayList<BaseTrait> {

            val traitArray : ArrayList<BaseTrait> = ArrayList()

            for (trait in traitRegistry) {

                val name : StringTag = StringTag(trait.name)

                if (listTag.contains(name)) {
                    traitArray.add(trait.copy())
                }
            }

            return traitArray
        }
    }
}