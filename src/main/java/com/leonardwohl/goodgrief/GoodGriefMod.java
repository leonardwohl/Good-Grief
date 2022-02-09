package com.leonardwohl.goodgrief;

import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("good_grief")
public class GoodGriefMod
{
    public GoodGriefMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onMobGriefing(EntityMobGriefingEvent event){
        boolean mobGriefing = event.getEntity().getLevel().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING);
        boolean isEnemy = event.getEntity() instanceof Enemy && !(event.getEntity() instanceof Piglin);
        boolean isProjectile = event.getEntity() instanceof LargeFireball || event.getEntity() instanceof WitherSkull;
        event.setResult(!mobGriefing && (isEnemy || isProjectile) ? Event.Result.DENY : Event.Result.ALLOW);
    }
}
