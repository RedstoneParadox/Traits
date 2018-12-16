package net.redstoneparadox.traits.items

import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

/**
 * Created by RedstoneParadox on 12/12/2018.
 */
class ExpeditionItems {

    lateinit var myFirstItem : MyFirstItem
    lateinit var dynamiteItem: DynamiteItem

    fun initItems() {
        System.out.println("Initializing ExpeditionItems!")

        myFirstItem = registerItem("my_first_item", MyFirstItem(Item.Settings())) as MyFirstItem
        dynamiteItem = registerItem("dynamite_stick", DynamiteItem(Item.Settings())) as DynamiteItem
    }

    fun registerItem(name : String, item: Item) : Item {
        var id = Identifier("modid", name)

        return Registry.register(Registry.ITEM, id, item) as Item
    }

    fun constructSettings() : Item.Settings {
        var settings : Item.Settings = Item.Settings()

        return settings
    }
}