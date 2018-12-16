package net.redstoneparadox.traits.traitspackage

import net.minecraft.entity.boss.EntityWither
import net.minecraft.entity.boss.dragon.EnderDragonEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.StringTag
import kotlin.random.Random

/**
 * Created by RedstoneParadox on 12/14/2018.
 */
class Traits {

    fun initTraits() {

        if (!traitTemplates.isEmpty()) {
            return
        }
            consoleSpamTrait = addTrait(ConsoleSpamTrait("console_spammer", 1, true, 1, 1))
    }

    companion object TraitBuilder{

        var traitTemplates : ArrayList<Trait> = ArrayList()

        lateinit var infernalTrait: Trait
        lateinit var consoleSpamTrait: Trait

        var totalWeight : Int = 0

        private fun addTrait(trait: Trait) : Trait {
            traitTemplates.add(trait)
            totalWeight += trait.weight
            return trait
        }

        fun applyRandomTraits(traitEntity: ITraitEntity) {

            if (traitEntity is EnderDragonEntity || traitEntity is PassiveEntity || traitEntity is EntityWither) {
                return
            }

            var traitAmount : Int = Random.nextInt(0, 3)

            if (traitAmount == 0) {
                return
            }

            var counter = 1

            while (counter <= traitAmount) {

                var rolledWeight: Int = Random.nextInt(1, totalWeight)
                for (template in traitTemplates) {

                    if (rolledWeight <= template.weight) {
                        traitEntity.addTrait(buildTrait(template))
                        counter += 1
                        break
                    }
                    else {
                        rolledWeight -= template.weight
                    }

                }

            }
        }

        private fun buildTrait(template : Trait) : Trait {
            return template.build(1)!!
        }

        fun getTraitsFromTag(listTag: ListTag): java.util.ArrayList<Trait> {

            val traitArray : ArrayList<Trait> = ArrayList()

            for (trait in traitTemplates) {

                val name : StringTag = StringTag(trait.name)

                if (listTag.contains(name)) {
                    traitArray.add(trait.build(1)!!)
                }
            }

            return traitArray
        }
    }
}