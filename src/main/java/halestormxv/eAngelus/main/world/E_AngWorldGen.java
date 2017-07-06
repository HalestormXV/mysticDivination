package halestormxv.eAngelus.main.world;

import java.util.Random;

import halestormxv.eAngelus.main.init.eAngelusBlocks;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class E_AngWorldGen implements IWorldGenerator {
	//@formatter:off

	private WorldGenerator gen_AngelicOre;
	private WorldGenerator gen_AzureiteOre;
	private WorldGenerator gen_DemonicOre;
	private WorldGenerator gen_MystalCite;
	private WorldGenerator gen_SerpentineOre;
	private WorldGenerator gen_TopazOre;

	//@formatter:on

	public E_AngWorldGen()
	{
		this.gen_AngelicOre = new WorldGenMinable(eAngelusBlocks.angelicOre.getDefaultState(), 2, BlockMatcher.forBlock(Blocks.STONE));
		this.gen_AzureiteOre = new WorldGenMinable(eAngelusBlocks.azureite_Ore.getDefaultState(), 4, BlockMatcher.forBlock(Blocks.STONE));
		this.gen_DemonicOre = new WorldGenMinable(eAngelusBlocks.demonicOre.getDefaultState(), 2, BlockMatcher.forBlock(Blocks.STONE));
		this.gen_MystalCite = new WorldGenMinable(eAngelusBlocks.mystalCite.getDefaultState(), 3, BlockMatcher.forBlock(Blocks.STONE));
		this.gen_SerpentineOre = new WorldGenMinable(eAngelusBlocks.serpentine_Ore.getDefaultState(), 3, BlockMatcher.forBlock(Blocks.STONE));
		this.gen_TopazOre = new WorldGenMinable(eAngelusBlocks.topazOre.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.STONE));
	}


	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) 
	{
		switch (world.provider.getDimension())
		{
		case 0: //Overworld
			//Chances to Spawn, Min Height, Max Height
			this.runGenerator(this.gen_AngelicOre, world, random, chunkX, chunkZ, 6, 72, 128);
			this.runGenerator(this.gen_AzureiteOre, world, random, chunkX, chunkZ, 12, 24, 128); 
			this.runGenerator(this.gen_DemonicOre, world, random, chunkX, chunkZ, 6, 8, 16); 
			this.runGenerator(this.gen_MystalCite, world, random, chunkX, chunkZ, 8, 12, 128); 
			this.runGenerator(this.gen_SerpentineOre, world, random, chunkX, chunkZ, 13, 12, 48); 
			this.runGenerator(this.gen_TopazOre, world, random, chunkX, chunkZ, 16, 12, 128);
			break;
			
		case -1: //Nether
			break;
			
		case 1: //End
			break;
			
		default:
			break;
		}

	}

	private void runGenerator(WorldGenerator generator, World world, Random random, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight)
	{
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++)
		{
			int x = chunk_X * 16 + random.nextInt(16);
			int y = minHeight + random.nextInt(heightDiff);
			int z = chunk_Z * 16 + random.nextInt(16);
			generator.generate(world, random, new BlockPos(x, y, z));
		}
	}

}
