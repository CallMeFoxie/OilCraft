package cz.ondraster.oilcraft.fluids;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ondraster.oilcraft.OilCraft;
import cz.ondraster.oilcraft.items.OilCraftBucket;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluid extends BlockFluidClassic {
   @SideOnly(Side.CLIENT)
   protected IIcon stillIcon;
   @SideOnly(Side.CLIENT)
   protected IIcon flowingIcon;

   private String iconStill;
   private String iconFlowing;
   private OilCraftBucket bucket = null;


   public BlockFluid(Fluid fluid, Material material, String blockName, String iconStill, String iconFlowing) {
      super(fluid, material);
      setCreativeTab(OilCraft.creativeTab);
      setBlockName(blockName);

      this.iconStill = iconStill;
      this.iconFlowing = iconFlowing;
   }

   @Override
   public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
      if (world.getBlock(x, y, z).getMaterial().isLiquid()) return false;
      return super.canDisplace(world, x, y, z);
   }

   @Override
   public boolean displaceIfPossible(World world, int x, int y, int z) {
      if (world.getBlock(x, y, z).getMaterial().isLiquid()) return false;
      return super.displaceIfPossible(world, x, y, z);
   }

   @Override
   public IIcon getIcon(int side, int meta) {
      return (side == 0 || side == 1) ? stillIcon : flowingIcon;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void registerBlockIcons(IIconRegister register) {
      stillIcon = register.registerIcon(iconStill);
      flowingIcon = register.registerIcon(iconFlowing);
   }

   public OilCraftBucket getBucket() {
      return this.bucket;
   }

   public void setBucket(OilCraftBucket bucket) {
      this.bucket = bucket;
   }

}
