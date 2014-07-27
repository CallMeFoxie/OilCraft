package cz.ondraster.oilcraft.items;

import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.References;
import cz.ondraster.oilcraft.blocks.BlockPipe;
import cz.ondraster.oilcraft.entities.EntityPipe;
import cz.ondraster.oilcraft.factory.IMachineRequiresHeat;
import cz.ondraster.oilcraft.factory.IMachineRequiresPower;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockController;
import cz.ondraster.oilcraft.factory.multiblock.MultiblockPart;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityController;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityPart;
import cz.ondraster.oilcraft.factory.tileentities.TileEntityPartWithInventory;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class DebugTool extends Item {
   public DebugTool() {
      setUnlocalizedName(References.UnlocalizedNames.ITEMDEBUGTOOL);
      setTextureName(References.Textures.ITEMDEBUGTOOL);
      setCreativeTab(OilCraft.creativeTab);
   }

   public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
      if (world.isRemote)
         return false;

      Block block = world.getBlock(x, y, z);
      int meta = world.getBlockMetadata(x, y, z);
      TileEntity te = world.getTileEntity(x, y, z);
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

      if (block instanceof MultiblockPart) {
         MultiblockPart part = (MultiblockPart) block;
         if (te instanceof TileEntityPart) {
            TileEntityPart tep = (TileEntityPart) world.getTileEntity(x, y, z);
            player.addChatComponentMessage(new ChatComponentText("part. Meta: " + meta + ", part of multiblock: " + tep.isComplete()));
         } else if (te instanceof TileEntityPartWithInventory) {
            TileEntityPartWithInventory tep = (TileEntityPartWithInventory) world.getTileEntity(x, y, z);
            player.addChatComponentMessage(new ChatComponentText("part. Meta: " + meta + ", part of multiblock: " + tep.isComplete()));
         }

      }
      if (block instanceof MultiblockController) {
         MultiblockController part = (MultiblockController) block;
         TileEntityController tileEntityController = (TileEntityController) world.getTileEntity(x, y, z);
         player.addChatComponentMessage(new ChatComponentText("Controller. Formed: " + tileEntityController.isFormed() + ", Meta: " + meta));
      }

      if (te instanceof IMachineRequiresPower) {
         player.addChatComponentMessage(new ChatComponentText("Machine power: " + ((IMachineRequiresPower) te).getPower()));
      }

      if (te instanceof IMachineRequiresHeat) {
         player.addChatComponentMessage(new ChatComponentText("Machine temperature: " + ((IMachineRequiresHeat) te).getTemperature()));
      }

      if (te instanceof IInventory) {
         IInventory inv = (IInventory) te;
         player.addChatComponentMessage(new ChatComponentText("Inventories: " + inv.getSizeInventory()));
      }


      return false;
   }
}
