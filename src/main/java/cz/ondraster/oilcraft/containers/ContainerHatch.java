package cz.ondraster.oilcraft.containers;

import cz.ondraster.oilcraft.containers.slot.SlotReadOnly;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityHatch;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerHatch extends SmartContainer {
   public ContainerHatch(InventoryPlayer inv, TileEntityHatch te) {
      super(inv, te, 1);

      addSlotToContainer(new SlotReadOnly(te, 0, 27, 38));

      bindPlayerInventory(inv);
   }
}
