package halestormxv.eAngelus.achievements;

import halestormxv.eAngelus.main.init.eAngelusBlocks;
import halestormxv.eAngelus.main.init.eAngelusItems;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class EA_Achievements 
{
	public static Achievement AchievementGetAngelicOre;
	public static Achievement AchievementGetDemonicOre;
	public static Achievement AchievementMakeAngelicIngot;
	
	public static void AchievementRegistry()
	{
		AchievementGetAngelicOre = new Achievement("achievement.GetAngelicOre", "GetAngelicOre", 0, 0, eAngelusBlocks.angelicOre, null).registerStat();
		AchievementGetDemonicOre = new Achievement("achievement.GetDemonicOre", "GetDemonicOre", 1, 0, eAngelusBlocks.demonicOre, null).registerStat();
		AchievementMakeAngelicIngot = new Achievement("achievement.MakeAngelicIngot", "MakeAngelicIngot", 0, -1, eAngelusItems.angelic_ingot, AchievementGetAngelicOre).registerStat();

		//Achievement Page
		AchievementPage.registerAchievementPage(new AchievementPage("Elementum Angelus", new Achievement[]
				{
						AchievementGetAngelicOre,
						AchievementGetDemonicOre,
						AchievementMakeAngelicIngot		
				}
				));
		
		//FMLCommonHandler.instance().bus().register(new EA_AchievementsEvents());
		MinecraftForge.EVENT_BUS.register(new EA_AchievementsEvents());
	}
}
