package com.github.supergluelib.items

import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * A class to extend when creating custom items.
 * Provided are a number of functions which are called when certain actions occur.
 *
 * Note that methods that encapsulate multiple other methods are all called, for example;
 * when a player right-clicks a block, both onRightClick & onRightClickBlock are called, only one needs to be handled.
 */
abstract class CustomItem() {
    val settings = Settings()

    abstract fun getItem(): ItemStack
    abstract fun isItem(item: ItemStack): Boolean
    /** This method assumed that [isItem] is true */
    abstract fun fromItemStack(item: ItemStack): CustomItem

    fun onRightClick(player: Player, item: ItemStack) {}
    fun onLeftClick(player: Player, item: ItemStack) {}

    fun onRightClickBlock(player: Player, block: Block, item: ItemStack) {}
    fun onRightClickAir(player: Player, item: ItemStack) {}
    fun onRightClickEntity(player: Player, entity: Entity, item: ItemStack) {}
    fun onLeftClickBlock(player: Player, block: Block, item: ItemStack) {}
    fun onLeftClickAir(player: Player, item: ItemStack) {}
    fun onLeftClickEntity(player: Player, entity: Entity, item: ItemStack) {}

    class Settings(
        var cancelEventsAutomatically: Boolean = true
    )
}
