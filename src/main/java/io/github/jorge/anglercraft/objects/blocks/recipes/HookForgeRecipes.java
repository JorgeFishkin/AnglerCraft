package io.github.jorge.anglercraft.objects.blocks.recipes;

import java.util.Map;
import java.util.Map.Entry;

//import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
//import com.google.common.collect.Table;

import io.github.jorge.anglercraft.init.ItemInit;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class HookForgeRecipes {
    private static final HookForgeRecipes INSTANCE = new HookForgeRecipes();
    private final Map<ItemStack, ItemStack> forgingList = Maps.<ItemStack, ItemStack>newHashMap();
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    public static HookForgeRecipes getInstance() {
        return INSTANCE;
    }

    private HookForgeRecipes() {
            addHookForgingRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(ItemInit.HOOK, 3), 0.7F);
    }

    public void addHookForgingRecipe(ItemStack input, ItemStack result, float exp) {
        this.forgingList.put(input, result);
        this.experienceList.put(result, Float.valueOf(exp));
    }

    public ItemStack getHookForgingResult(ItemStack input) {
        for(Entry<ItemStack, ItemStack> entry : this.forgingList.entrySet()) {
            if(this.compareItemStacks(input, (ItemStack)entry.getKey()))
                return (ItemStack)entry.getValue();
        }
        return ItemStack.EMPTY;
    }

    public boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map<ItemStack, ItemStack> getHookForgingList() {
        return this.forgingList;
    }

    public float getHookForgingExperience(ItemStack stack) {
        for(Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
            if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) {
                return ((Float)entry.getValue()).floatValue();
            }
        }
        return 0.0F;
    }


}
