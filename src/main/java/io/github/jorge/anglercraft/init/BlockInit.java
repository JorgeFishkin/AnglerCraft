package io.github.jorge.anglercraft.init;

import java.util.ArrayList;
import java.util.List;

import io.github.jorge.anglercraft.objects.blocks.BlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block BLOCK_FISHTRAP = new BlockBase("block_fishtrap", Material.WOOD);
}
