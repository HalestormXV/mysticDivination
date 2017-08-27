package halestormxv.eAngelus.main.world.Structures;

import halestormxv.eAngelus.main.Reference;
import halestormxv.eAngelus.main.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
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
 * Created by Blaze on 8/5/2017.
 */
public class generateKnightAlter
{
    private int mirror;
    private int rotation;

    public generateKnightAlter(BlockPos pos, World world)
    {
        Random rN = new Random();
        mirror = rN.nextInt(Mirror.values().length);
        rotation = rN.nextInt(Rotation.values().length);

        this.loadStructure(pos, world, "alterknight", true);
    }

    public void loadStructure(BlockPos pos, World world, String name, boolean check) {
        boolean flag = false;
        if (!world.isRemote)
        {
            WorldServer worldserver = (WorldServer) world;
            MinecraftServer minecraftserver = world.getMinecraftServer();
            TemplateManager templatemanager = worldserver.getStructureTemplateManager();
            ResourceLocation loc = new ResourceLocation(Reference.MODID, name);
            Template template = templatemanager.get(minecraftserver, loc);
            if (template != null)
            {
                IBlockState iblockstate = world.getBlockState(pos);
                world.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);
                flag = true;
                if (check) {

                    for (int i = 0; i < template.getSize().getX(); i++) {
                        for (int j = 0; j < template.getSize().getZ(); j++) {
                            BlockPos down = pos.add(i, -1, j);
                            Block b = world.getBlockState(down).getBlock();
                            if (!b.equals(Blocks.SAND)) {
                                flag = false;
                            }
                        }
                    }
                }
                if (flag) {
                    PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.values()[mirror])
                            .setRotation(Rotation.values()[rotation]).setIgnoreEntities(false).setChunk((ChunkPos) null)
                            .setReplacedBlock((Block) null).setIgnoreStructureBlock(true);

                    template.addBlocksToWorldChunk(world, pos.down(), placementsettings);
                    Utils.getLogger().info("=======0=======" + template);
                    Utils.getLogger().info("=======1=======" + loc);
                    Utils.getLogger().info("========2=======" + pos);
                }
            }
        }
    }
}
