package net.redstoneparadox.traits.blocks

import net.fabricmc.fabric.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.block.MaterialColor
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.redstoneparadox.traits.TraitsMain


/**
 * Created by RedstoneParadox on 12/12/2018.
 */
class ExpeditionBlocks {

    lateinit var myFirstBlock : MyFirstBlock

    fun initBlocks() {
        System.out.println("Initializing ExpeditionBlocks!")

        myFirstBlock = registerBlock("my_first_block", MyFirstBlock(constructSettings())) as MyFirstBlock
    }

    fun registerBlock(name : String, block: Block) : Block {
        var id = Identifier("modid", name)

        return Registry.register(Registry.BLOCK, id, block) as Block
    }

    fun constructSettings(
            material: Material = Material.AIR,
            color: MaterialColor = MaterialColor.AIR,
            collidable : Boolean = true,
            soundGroup: BlockSoundGroup = BlockSoundGroup.STONE,
            luminance: Int = 0,
            hardness: Float = 6.0f,
            resistance: Float = 1.5f,
            randomTicks: Boolean = false,
            slipperiness: Float = 0.6f,
            dropTableID : Identifier = Identifier(TraitsMain.MOD_ID, "missingno")
            )
            : Block.Settings {
        var settings : FabricBlockSettings = FabricBlockSettings.of(material, color)

        settings = settings.collidable(collidable)
        settings = settings.sounds(soundGroup)
        settings = settings.lightLevel(luminance)
        settings = settings.strength(hardness, resistance)

        if (randomTicks) {
            settings.ticksRandomly()
        }

        settings = settings.friction(slipperiness)

        if (dropTableID.path != "missingno") {
            settings = settings.dropsNothing()
        }
        else {
            settings = settings.drops(dropTableID)
        }

        var builtSettings : Block.Settings = settings.build()
        return builtSettings
    }
}