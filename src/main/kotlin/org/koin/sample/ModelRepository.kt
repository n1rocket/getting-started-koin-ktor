package org.koin.sample

import java.time.LocalDate


data class Model(val name: String, val items: List<Item>, val date: LocalDate = LocalDate.of(2018, 4, 21))
data class Item(val key: String, val value: String)

class ModelRepository {
    private val model = Model("root", listOf(Item("A", "Apache"), Item("B", "Bing")))

    fun getModels() = model
    fun getItemByKey(key: String) = model.items.firstOrNull { it.key == key }
}