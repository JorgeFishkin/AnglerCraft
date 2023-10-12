package io.github.jorge.anglercraft.objects.blocks.guis;

import io.github.jorge.anglercraft.objects.blocks.containers.ContainerHookForge;
import io.github.jorge.anglercraft.objects.blocks.tileentities.TileEntityHookForge;
import io.github.jorge.anglercraft.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiHookForge extends GuiContainer {
    
    private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID, "textures/gui/hook_forge_gui.png");
    private final InventoryPlayer player;
    private final TileEntityHookForge tileEntity;

    public GuiHookForge(InventoryPlayer player, TileEntityHookForge tileEntity) {
        super(new ContainerHookForge(player, tileEntity));
        this.player = player;
        this.tileEntity = tileEntity;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String tileName = this.tileEntity.getDisplayName().getUnformattedText();
        this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) + 3, 7, 4210752);
        this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 122, this.ySize - 96 + 2, 4210752);
        
    }

    

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        if(TileEntityHookForge.isBurning(tileEntity)) {
            int k = this.getBurnLeftScaled(13);
            this.drawTexturedModalRect(this.guiLeft + 57, this.guiTop + 37 + 12 - k, 176, 12-k,  14, k+1 );
        }

        int l = this.getCookProgressScaled(15);
        this.drawTexturedModalRect(this.guiLeft + 86, this.guiTop + 35 + 15 - l,176,  28 - l, 9, l + 1 );

    }

    private int getBurnLeftScaled(int pixels) {
        int i = this.tileEntity.getField(1);
        if (i == 0) 
            i = 200;
        return this.tileEntity.getField(0) * pixels / i;
    }

    private int getCookProgressScaled(int pixels) {
        int i = this.tileEntity.getField(2);
        int j = this.tileEntity.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }
    
}
