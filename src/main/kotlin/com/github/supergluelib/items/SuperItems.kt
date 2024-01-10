package com.github.supergluelib.items

import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import kotlin.text.Typography.registered

object SuperItems {
    private var plugin: JavaPlugin? = null

    private fun setup(plugin: JavaPlugin) {
        if (this.plugin != null) return
        this.plugin = plugin
        Bukkit.getPluginManager().registerEvents(ItemListener(), plugin)
    }

    fun register(plugin: JavaPlugin, vararg items: CustomItem) {
        setup(plugin)
        for (item in items) register(item)
    }

    private val items: HashMap<Class<out CustomItem>, CustomItem> = hashMapOf()
    private fun <T: CustomItem> register(item: T) { items[item::class.java] = item }
    internal fun fromItemStack(item: ItemStack) = items.values.find { it.isItem(item) }?.fromItemStack(item)

    fun ItemStack.locnameIs(name: String) = itemMeta?.localizedName?.equals(name) == true
}