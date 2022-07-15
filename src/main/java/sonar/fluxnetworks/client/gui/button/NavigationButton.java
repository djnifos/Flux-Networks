package sonar.fluxnetworks.client.gui.button;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import sonar.fluxnetworks.client.gui.EnumNavigationTab;
import sonar.fluxnetworks.client.gui.basic.GuiButtonCore;
import sonar.fluxnetworks.client.gui.basic.GuiFocusable;

public class NavigationButton extends GuiButtonCore {

    private final EnumNavigationTab mTab;
    private boolean mSelected = false;

    public NavigationButton(GuiFocusable screen, int x, int y, EnumNavigationTab tab) {
        super(screen, x, y, 16, 16);
        mTab = tab;
    }

    @Override
    protected void drawButton(PoseStack poseStack, int mouseX, int mouseY, float deltaTicks) {
        RenderSystem.enableBlend();
        if (mClickable) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0f);
        } else {
            RenderSystem.setShaderColor(0.75f, 0.75f, 0.75f, 1.0f);
        }
        RenderSystem.setShaderTexture(0, BUTTONS);

        boolean hovered = mClickable && isMouseHovered(mouseX, mouseY);
        int state = (mSelected || hovered) ? 1 : 0;
        blit(poseStack, x, y, 16 * mTab.ordinal(), 16 * state, 16, 16);

        if (hovered) {
            drawCenteredString(poseStack, screen.getMinecraft().font, mTab.getTranslatedName(), x + width / 2, y - 10, 0xFFFFFFFF);
        }
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0f);
    }

    public EnumNavigationTab getTab() {
        return mTab;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
    }
}
