package halestormxv.eAngelus.main.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Blaze on 7/9/2017.
 */
public final class ModSounds_Records {

    public static void init() {
        String[] sounds = {
                //RECORDS GO HERE USE SOUND HANDLER FOR OTHER STUFF\\
                "eangel:music.mavismusic",
                "eangel:music.motomiyaflute",
                "eangel:music.kishuu"
        };

        for (String s : sounds) {
            ResourceLocation loc = new ResourceLocation(s);
            GameRegistry.register(new SoundEvent(loc), loc);
        }
    }

    private ModSounds_Records() {}

}
