package net.redstoneparadox.traits.traitspackage

import net.minecraft.entity.boss.EntityWither
import net.minecraft.entity.boss.dragon.EnderDragonEntity
import net.minecraft.entity.passive.PassiveEntity
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.StringTag
import kotlin.random.Random

/**
 * Created by RedstoneParadox on 12/16/2018.
 */
object TraitFactory {

    var templates: HashMap<String,TraitTemplate> = HashMap()

    var totalWeight : Int = 0

    fun initialize() {
        registerTemplate("base", TraitTemplate(Trait(), 0))
        registerTemplate("infernal", TraitTemplate(InfernalTrait(), 1))
    }

    private fun registerTemplate(name: String, template: TraitTemplate) {
        templates.put(name, template)
    }

    fun applyRandomTraits(traitEntity: ITraitEntity): ArrayList<Trait> {

        var traitArray: ArrayList<Trait> = ArrayList()

        if (traitEntity is EnderDragonEntity || traitEntity is PassiveEntity || traitEntity is EntityWither) {
            return traitArray
        }

        var traitAmount : Int = Random.nextInt(0, 3)

        if (traitAmount == 0) {
            return traitArray
        }

        var counter = 1
        var templateSet : HashMap<String, TraitTemplate> = templates
        var weightCounter: Int = totalWeight

        while (counter <= traitAmount) {

            var rolledWeight: Int = Random.nextInt(1, weightCounter)
            for (template in templateSet) {

                var traitTemplate: TraitTemplate = template.value
                var traitName: String = template.key

                if (rolledWeight <= traitTemplate.weight) {
                    traitArray.add(constructTrait(traitName, traitTemplate))
                    counter += 1
                    totalWeight -= traitTemplate.weight
                    templateSet.remove(traitName)
                    break
                }
                else {
                    rolledWeight -= traitTemplate.weight
                }
            }
        }

        return traitArray
    }

    //Checks a given List Tag for trait IDs and returns an array of traits.
    fun getTraitsFromTag(listTag: ListTag): ArrayList<Trait> {

        val traitArray: ArrayList<Trait> = ArrayList()

        for (template in templates) {

            val name: StringTag = StringTag(template.key)

            if (listTag.contains(name)) {
                traitArray.add(constructTrait(template.key, template.value))
            }
        }
        return traitArray
    }

    //Constructs a trait given a name and template
    private fun constructTrait(name: String,template: TraitTemplate) : Trait {
        return template.constructor.newInstance(name,template.tickable, 1)
    }
}