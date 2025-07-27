package io.github.kssd2952.event

import io.github.kssd2952.Main
import io.papermc.paper.event.player.PlayerPickItemEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import io.github.kssd2952.event.GameManager.Companion.playerFinished

class RISEvent : Listener {
    @EventHandler
    fun entityPickupItemEvent(event: EntityPickupItemEvent) {
        if (event.entity !is Player) return
        if (!Main.started) return

        val player = event.entity as Player
        val item = event.item.itemStack

        if (item.type == Main.itemList[player.name]) {
            playerFinished(player)
        }
    }

    //TODO: 아이템을 다른 방법으로 주웠을 때도 작동하도록 이벤트 추가하기
}