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

/**
 * Created by Blaze on 8/26/2017.
 */
public class generateKnightAlter
{
    public generateKnightAlter(BlockPos pos, World worldIn)
    {
        if (!worldIn.isRemote)
        {
            WorldServer worldserver = (WorldServer) worldIn;
            MinecraftServer minecraftserver = worldIn.getMinecraftServer();
            TemplateManager templatemanager = worldserver.getStructureTemplateManager();
            ResourceLocation loc = new ResourceLocation(Reference.MODID, "alterknight");
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

                //Utils.getLogger().info("========2=======" + pos);
            }
        }
    }
}
