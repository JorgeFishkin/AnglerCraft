package io.github.jorge.anglercraft.util.handlers;

import io.github.jorge.anglercraft.objects.blocks.containers.ContainerHookForge;
import io.github.jorge.anglercraft.objects.blocks.guis.GuiHookForge;
import io.github.jorge.anglercraft.objects.blocks.tileentities.TileEntityHookForge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof TileEntityHookForge)  {
            TileEntityHookForge tileEntityHookForge = (TileEntityHookForge)tileEntity;
            return new ContainerHookForge(player.inventory, tileEntityHookForge);
        }
                    
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof TileEntityHookForge)  {
            TileEntityHookForge tileEntityHookForge = (TileEntityHookForge)tileEntity;
            return new GuiHookForge(player.inventory, tileEntityHookForge);
        }
        return null;

    }
}
