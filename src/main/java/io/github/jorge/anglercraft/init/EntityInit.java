package io.github.jorge.anglercraft.init;

import io.github.jorge.anglercraft.AnglerCraft;
import io.github.jorge.anglercraft.entity.EntityRainbowTrout;
import io.github.jorge.anglercraft.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
    public static void registerEntities(){
        int id = 0;
        registerEntity("rainbow_trout", EntityRainbowTrout.class, id++, 8, 11520, 16742817);
    }
    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2){
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, AnglerCraft.instance, range, 1, true, color1, color2);
    }
    
}
