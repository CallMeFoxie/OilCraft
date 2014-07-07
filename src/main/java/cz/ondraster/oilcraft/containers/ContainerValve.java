package cz.ondraster.oilcraft.containers;

import cz.ondraster.oilcraft.client.gui.slot.SlotReadOnly;
import cz.ondraster.oilcraft.factory.blocks.TileEntityValve;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerValve extends SmartContainer {
   public ContainerValve(InventoryPlayer inv, TileEntityValve te) {
      super(inv, te, 2);
      addSlotToContainer(new Slot(te, 0, 10, 10));
      addSlotToContainer(new SlotReadOnly(te, 1, 30, 10));

      bindPlayerInventory(inv);
   }
}
