package com.example.RakuTool;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/*
 * 縦方向破壊斧 アイテムクラス
 * */
public class AxeOfPillar extends ItemAxe
{
	static final int DAMAGE_MAX   = 256;// 耐久
	static final int ALTITUDE_MAX = 256;// 切り倒し最大高度
	static final int CAN_TYPE_MAX = 7;// 切り倒し可能なブロック名の最大値
	static final String[] ARRAY_CAN_CUT_NAME = {"Block{minecraft:planks}",
												"Block{minecraft:lit_pumpkin}",
												"Block{minecraft:bookshelf}",
												"Block{minecraft:log}",
												"Block{minecraft:log2}",
												"Block{minecraft:melon_block}",
												"Block{minecraft:pumpkin}"};// 切り倒し可能なブロック名

    // コンストラクタ
    public AxeOfPillar() {
    	super(Item.ToolMaterial.STONE);
        // 色んな設定をする
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.tabTools);
        setUnlocalizedName("AxeOfPillar");
        setMaxDamage(DAMAGE_MAX);
        super.showDurabilityBar(new ItemStack(this));
        setHarvestLevel("axe", 1);
    }
    // 一括破壊処理
    public static int Cut(World w, IBlockState state, BlockPos pos ){
        String   strSaveBlockState = state.toString();                               // 直前のブロック
        BlockPos nextpos           = new BlockPos(pos.getX(),pos.getY(),pos.getZ()); // 操作座標
        int      iCnt              = 1;                                              // 消耗回数
        // 最大高度までループ
        while( nextpos.getY() < ALTITUDE_MAX )
        {
        	nextpos = nextpos.add(0, 1, 0);//高度上昇
        	if(w.getBlockState(nextpos).toString().equals(strSaveBlockState))// 上のブロックと直前のブロックが完全一致なら破壊
        	{
        		w.destroyBlock(nextpos, true);// ブロックを破壊してアイテム化する
        		iCnt++;
        		if(DebugManager.DEBUG)
        		{
        			System.out.println(nextpos.getX()+","+nextpos.getY()+","+nextpos.getZ());
        			System.out.println("---------------");
        		}
            }else{
                break;
            }
        }
        return iCnt;
    }
    // アイテム耐久０
    public static void Broken(ItemStack itemStack, EntityPlayer entityIn)
    {
        entityIn.renderBrokenItemStack(itemStack);
        --itemStack.stackSize;
        entityIn.addStat(StatList.func_188059_c(itemStack.getItem()));
        if (itemStack.stackSize < 0)
        {
        	itemStack.stackSize = 0;
        }
        itemStack.setItemDamage(0);
    }
    // 材質チェック
    public static boolean Check(String s)
    {
    	for(int iCnt = 0; iCnt < AxeOfPillar.CAN_TYPE_MAX; iCnt++)
    	{
    		if(ARRAY_CAN_CUT_NAME[iCnt].equals(s)){
    			return true;
    		}
    	}
    	return false;
    }
    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        par1ItemStack.damageItem(1, par3EntityLivingBase);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState blockIn, BlockPos pos, EntityLivingBase entityLiving)
    {
    	if(DebugManager.DEBUG)System.out.println("onBlockDestroyed="+entityLiving.getHeldItemMainhand().getItem().canHarvestBlock(blockIn));
/*        if ((double)blockIn.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(1, entityLiving);
        }
*/
        return true;
    }
}
