package halestormxv.eAngelus.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Blaze on 7/10/2017.
 */
public class Utils
{
    private static Logger logger;

    public static Logger getLogger()
    {
        if ( logger == null )
        {
            logger = LogManager.getFormatterLogger((Reference.MODID));
        }
        return logger;
    }
}
