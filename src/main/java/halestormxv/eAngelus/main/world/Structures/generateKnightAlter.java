package halestormxv.eAngelus.main.world.Structures;

import halestormxv.eAngelus.main.init.eAngelusBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by Blaze on 8/5/2017.
 */
public class generateKnightAlter implements IWorldGenerator
{

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        switch(world.provider.getDimension())
        {
            case -1:
                break;
            case 0:
                generateOverworldAlter(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 1:
                break;
        }
    }

    private int fetchTopBlock(World world, int x, int z, boolean ignoreFluids, boolean ignorWoord, boolean ignoreFoliage)
    {
        int currentTop = world.getHeight(x, z);
        boolean pass;
        Block blockBelow;
        for (int i = currentTop; i > 0; i--)
        {
            //blockBelow = world.getBlockState(new BlockPos(x, i - 1, z).getBlock());
        }

        return currentTop;

    }

    private void generateOverworldAlter(World world, Random random, int x, int z)
    {
        if (random.nextInt(100) % 5 == 0)
        {
            int randomX = x + random.nextInt(16);
            int randomZ = z + random.nextInt(16);
            int randomY = fetchTopBlock(world, x, z, false, false,false); //Height

            world.setBlockState(new BlockPos(randomX, randomY, randomZ), eAngelusBlocks.alter_chariot.getDefaultState());
        }
    }
}
