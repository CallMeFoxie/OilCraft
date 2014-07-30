package cz.ondraster.oilcraft.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ondraster.oilcraft.References;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class RubberWood extends Block {

   /*
    *   Meta: bit 0: hasResin
    *         bit 1: regeneratesResin
    */

   private static final int MASK_HAS_RESIN = 0x01;
   private static final int MASK_REGENERATES = 0x02;

   private IIcon iconRubberTap;

   protected RubberWood() {
      super(Material.wood);
      setHarvestLevel("axe", 2);
      setTickRandomly(true);
   }

   public static boolean scoopResin(World world, int x, int y, int z) {
      int meta = world.getBlockMetadata(x, y, z);
      if ((meta & MASK_HAS_RESIN) == 0)
         return false;

      meta &= ~MASK_HAS_RESIN;
      world.setBlockMetadataWithNotify(x, y, z, meta, 2);
      return true;
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister reg) {
      this.blockIcon = reg.registerIcon(References.Textures.RUBBERWOOD);
      this.iconRubberTap = reg.registerIcon(References.Textures.RUBBERTAP);
   }

   @SideOnly(Side.CLIENT)
   public IIcon getIcon(int side, int meta) {
      if (side == 0 || side == 1)
         return Blocks.log.getIcon(side, 0);

      if ((meta & MASK_HAS_RESIN) != 0)
         return iconRubberTap;
      return blockIcon;
   }

   @Override
   public void updateTick(World world, int x, int y, int z, Random random) {
      if (world.isRemote)
         return;

      int meta = world.getBlockMetadata(x, y, z);
      if ((meta & MASK_HAS_RESIN) == 0 && (meta & MASK_REGENERATES) == 0)
         return;

      if (random.nextInt(10) == 0)
         if ((meta & MASK_REGENERATES) != 0) {
            if ((meta & MASK_HAS_RESIN) != 0) {
               Block block = world.getBlock(x, y - 1, z);
               if (block.getClass() == this.getClass() && random.nextInt(10) == 0) {
                  world.setBlockMetadataWithNotify(x, y - 1, z, world.getBlockMetadata(x, y - 1, z) | MASK_HAS_RESIN, 2);
               }
            } else {
               world.setBlockMetadataWithNotify(x, y, z, MASK_HAS_RESIN | MASK_REGENERATES, 2);
            }
         }
   }

}
