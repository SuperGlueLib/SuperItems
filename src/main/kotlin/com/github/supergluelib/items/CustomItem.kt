package com.github.supergluelib.items

import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

/**
 * A class to extend when creating custom items.
 * Provided are a number of functions which are called when certain actions occur.
 *
 * Note that methods that encapsulate multiple other methods are all called, for example;
 * when a player right-clicks a block, both onRightClick & onRightClickBlock are called, only one needs to be handled.
 *
 * - Each item must be registered via [SuperItems.register]
 */
abstract class CustomItem() {

    abstract fun getItem(): ItemStack
    abstract fun isItem(item: ItemStack): Boolean
    /** This method assumed that [isItem] is true */
    abstract fun fromItemStack(item: ItemStack): CustomItem

    fun onRightClick(player: Player, item: ItemStack, event: Event) {}
    fun onLeftClick(player: Player, item: ItemStack, event: Event) {}

    fun onRightClickBlock(player: Player, block: Block, item: ItemStack, event: PlayerInteractEvent) {}
    fun onRightClickAir(player: Player, item: ItemStack, event: PlayerInteractEvent) {}
    fun onRightClickEntity(player: Player, entity: Entity, item: ItemStack, event: PlayerInteractAtEntityEvent) {}
    fun onLeftClickBlock(player: Player, block: Block, item: ItemStack, event: PlayerInteractEvent) {}
    fun onLeftClickAir(player: Player, item: ItemStack, event: PlayerInteractEvent) {}
    fun onLeftClickEntity(player: Player, entity: Entity, item: ItemStack, event: EntityDamageByEntityEvent) {}

}
