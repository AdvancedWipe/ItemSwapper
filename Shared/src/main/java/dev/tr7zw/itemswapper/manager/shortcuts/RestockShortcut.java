package dev.tr7zw.itemswapper.manager.shortcuts;

import dev.tr7zw.itemswapper.manager.itemgroups.Icon;
import dev.tr7zw.itemswapper.manager.itemgroups.Icon.ItemIcon;
import dev.tr7zw.itemswapper.manager.itemgroups.Shortcut;
import dev.tr7zw.itemswapper.util.NetworkUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class RestockShortcut implements Shortcut {

    private final Icon icon = new ItemIcon(Items.SHULKER_BOX.getDefaultInstance(), Component.translatable("text.itemswapper.restockAll"));
    private final Component hoverText = Component.translatable("text.itemswapper.restockAll.tooltip");

    public RestockShortcut() {

    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    @Override
    public void invoke(ActionType action) {
        NonNullList<ItemStack> items = Minecraft.getInstance().player.getInventory().items;
        for(int i = 0; i < items.size(); i++) {
            ItemStack item = items.get(i);
            int space = item.getMaxStackSize() - item.getCount();
            if(space > 0) {
                NetworkUtil.refillItem(i);
            }
        }
    }

    @Override
    public boolean acceptClose() {
        return true;
    }

    @Override
    public boolean acceptClick() {
        return true;
    }

    @Override
    public boolean isVisible() {
        return !Minecraft.getInstance().player.isCreative();
    }
    
    @Override
    public Component getHoverText() {
        return hoverText;
    }

}
