package io.github.jorge.anglercraft.objects.items;

import io.github.jorge.anglercraft.util.IHasModel;
import io.github.jorge.anglercraft.AnglerCraft;
import io.github.jorge.anglercraft.init.ItemInit;
import io.github.jorge.anglercraft.proxy.ClientProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
    public ItemBase(String name) {
        setUnlocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MATERIALS);

        ItemInit.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        AnglerCraft.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
