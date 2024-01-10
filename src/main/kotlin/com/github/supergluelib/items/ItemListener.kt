package com.github.supergluelib.items

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerInteractEvent

internal class ItemListener: Listener {

    @EventHandler fun onInteract(event: PlayerInteractEvent) {
        val item = event.item ?: return
        val customItem = SuperItems.fromItemStack(item) ?: return
        val player = event.player
        val block = event.clickedBlock
        when (event.action) {
            Action.RIGHT_CLICK_BLOCK -> {
                customItem.onRightClick(player, item, event)
                customItem.onRightClickBlock(player, block!!, item, event)
            }
            Action.RIGHT_CLICK_AIR -> {
                customItem.onRightClick(player, item, event)
                customItem.onRightClickAir(player, item, event)
            }
            Action.LEFT_CLICK_BLOCK -> {
                customItem.onLeftClick(player, item, event)
                customItem.onLeftClickBlock(player, block!!, item, event)
            }
            Action.LEFT_CLICK_AIR -> {
                customItem.onLeftClick(player, item, event)
                customItem.onLeftClickAir(player, item, event)
            }
            else -> {}
        }
    }

    @EventHandler fun onInteractWithEntity(event: PlayerInteractAtEntityEvent) {
        val player = event.player
        val item = player.inventory.getItem(event.hand)
        if (item == null || item.type.isAir) return
        val customItem = SuperItems.fromItemStack(item) ?: return
        val entity = event.rightClicked
        customItem.onRightClick(player, item, event)
        customItem.onRightClickEntity(player, entity, item, event)
    }

    @EventHandler fun onPunchEntity(event: EntityDamageByEntityEvent) {
        val player = event.damager as? Player ?: return
        val item = player.inventory.itemInMainHand
        if (item.type.isAir) return
        val customItem = SuperItems.fromItemStack(item) ?: return
        val entity = event.entity
        customItem.onLeftClick(player, item, event)
        customItem.onLeftClickEntity(player, entity, item, event)
    }

}