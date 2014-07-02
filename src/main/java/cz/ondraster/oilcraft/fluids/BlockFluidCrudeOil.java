package cz.ondraster.oilcraft.fluids;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cz.ondraster.oilcraft.References;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockFluidCrudeOil extends BlockFluidClassic {

   @SideOnly(Side.CLIENT)
   protected IIcon stillIcon;
   @SideOnly(Side.CLIENT)
   protected IIcon flowingIcon;

   public BlockFluidCrudeOil() {
      super(Fluids.fluidCrudeOil, Material.water);
      setCreativeTab(CreativeTabs.tabTools);
      setBlockName(References.UnlocalizedNames.FLUIDCRUDEOIL);
   }

   @Override
   public IIcon getIcon(int side, int meta) {
      return (side == 0 || side == 1) ? stillIcon : flowingIcon;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void registerBlockIcons(IIconRegister register) {
      stillIcon = register.registerIcon(References.Textures.FLUIDCRUDEOILSTILL);
      flowingIcon = register.registerIcon(References.Textures.FLUIDCRUDEOILFLOWING);
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
}
