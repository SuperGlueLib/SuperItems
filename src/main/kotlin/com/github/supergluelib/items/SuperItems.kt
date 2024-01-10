package com.github.supergluelib.items

import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

object SuperItems {
    private var plugin: JavaPlugin? = null

    fun setup(plugin: JavaPlugin) {
        val registered = this.plugin != null
        this.plugin = plugin
        if (!registered) Bukkit.getPluginManager().registerEvents(ItemListener(), plugin)
        else plugin.logger.warning("Attempted to setup custom item library multiple times")
    }

    fun register(vararg items: CustomItem) {
        if (plugin == null) throw NullPointerException("Before registering items, you must first setup the custom item library using SuperItems.setup(Plugin)")
        items.forEach(::register)
    }

    private val items: HashMap<Class<out CustomItem>, CustomItem> = hashMapOf()
    private fun <T: CustomItem> register(item: T) { items[item::class.java] = item }
    internal fun fromItemStack(item: ItemStack) = items.values.find { it.isItem(item) }?.fromItemStack(item)

    fun ItemStack.locnameIs(name: String) = itemMeta?.localizedName?.equals(name) == true
}