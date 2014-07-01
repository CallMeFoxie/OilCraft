package cz.ondraster.oilcraft.items;

import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.blocks.BlockPipe;
import cz.ondraster.oilcraft.entities.EntityPipe;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Ondra on 1.7.2014.
 */
public class DebugTool extends Item {
   public DebugTool() {
      setUnlocalizedName(References.UnlocalizedNames.ITEMDEBUGTOOL);
      setTextureName(References.Textures.ITEMDEBUGTOOL);
      setCreativeTab(CreativeTabs.tabMisc);
   }

   public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
      if (world.isRemote)
         return false;

      Block block = world.getBlock(x, y, z);
      if (block instanceof BlockPipe) {
         EntityPipe pipe = (EntityPipe) world.getTileEntity(x, y, z);
         if (pipe.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid == null)
            player.addChatComponentMessage(new ChatComponentText("empty."));
         else
            player.addChatComponentMessage(new ChatComponentText("Pipe. Fluid: " +
                  pipe.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.getFluid().getUnlocalizedName() + ", amount: " +
                  pipe.getTankInfo(ForgeDirection.UNKNOWN)[0].fluid.amount + ", capacity: " +
                  pipe.getTankInfo(ForgeDirection.UNKNOWN)[0].capacity));
      }
      return false;
   }
}
