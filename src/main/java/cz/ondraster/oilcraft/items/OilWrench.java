package cz.ondraster.oilcraft.items;

import buildcraft.api.tools.IToolWrench;
import cpw.mods.fml.common.Optional;
import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

@Optional.Interface(iface = "buildcraft.api.tools.IToolWrench", modid = "BuildCraftAPI|tools")
public class OilWrench extends Item implements IToolWrench {
   public OilWrench() {
      setUnlocalizedName(References.UnlocalizedNames.ITEMWRENCH);
      setTextureName(References.Icons.ITEMWRENCH);
      setCreativeTab(OilCraft.creativeTab);
   }

   @Override
   public boolean canWrench(EntityPlayer player, int x, int y, int z) {
      return true;
   }

   @Override
   public void wrenchUsed(EntityPlayer player, int x, int y, int z) {

   }
}
