package io.github.jorge.anglercraft.util.handlers;

import io.github.jorge.anglercraft.objects.blocks.tileentities.TileEntityHookForge;
import io.github.jorge.anglercraft.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityHookForge.class, new ResourceLocation(Reference.MODID + ":hook_forge"));
    }
}
