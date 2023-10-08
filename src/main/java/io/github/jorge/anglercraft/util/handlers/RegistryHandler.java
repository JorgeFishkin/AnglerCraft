package io.github.jorge.anglercraft.util.handlers;

import io.github.jorge.anglercraft.init.BlockInit;
import io.github.jorge.anglercraft.init.EntityInit;
import io.github.jorge.anglercraft.init.ItemInit;
import io.github.jorge.anglercraft.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for(Item item : ItemInit.ITEMS) {
            if(item instanceof IHasModel){
                ((IHasModel)item).registerModels();
            }
        }

        for(Block block : BlockInit.BLOCKS){
            if(block instanceof IHasModel) {
                ((IHasModel)block).registerModels();
            }
        }
    }

    @SubscribeEvent
    public void onRegisterEntities(RegistryEvent.Register<EntityEntry> event){
 
    }

    public static void preInitRegistries() {
        EntityInit.registerEntities();
        RenderHandler.registerEntityRenders();
    }

    public static void postInitRegistries() {
        
    }
}
