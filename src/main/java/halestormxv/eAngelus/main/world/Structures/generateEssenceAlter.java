package halestormxv.eAngelus.main.world.Structures;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

/**
 * Created by Blaze on 8/26/2017.
 */
public class generateEssenceAlter
{
    public generateEssenceAlter(BlockPos pos, World worldIn)
    {
        if (!worldIn.isRemote)
        {
            Random rand = new Random();
            int structureGen = rand.nextInt(3 - 1 + 1) + 1;
            WorldServer worldserver = (WorldServer) worldIn;
            String structureName = "default";
            if (structureGen == 1) { structureName = "alterknight"; }
            else if (structureGen == 2) { structureName = "alterchariot"; }
            else if (structureGen == 3) { structureName = "alterstrength"; }
            else{ System.out.println(structureGen+" was called. Something went wrong.");}
            MinecraftServer minecraftserver = worldIn.getMinecraftServer();
            TemplateManager templatemanager = worldserver.getStructureTemplateManager();
            ResourceLocation loc = new ResourceLocation(Reference.MODID, structureName);
            Template template = templatemanager.getTemplate(minecraftserver, loc);
            //Utils.getLogger().info("=======0=======" + template);
            if (template != null)
            {
                IBlockState iblockstate = worldIn.getBlockState(pos);
                worldIn.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);
                PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE)
                        .setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((ChunkPos) null)
                        .setReplacedBlock((Block) null).setIgnoreStructureBlock(false);
                //Utils.getLogger().info("=======1=======" + loc);
                template.addBlocksToWorld(worldIn, pos, placementsettings);
                //Utils.getLogger().info(structureGen+" was called. Generated: "+structureName +"at: ");
                //Utils.getLogger().info("========2=======" + pos);
            }
        }
    }
}
