import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class GameLauncher
{
    public static void main(String[] args)
    {
        GaryGame myProgram = new GaryGame();

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = (int)Constants.WORLD_WIDTH;
        config.height = (int)Constants.WORLD_HEIGHT;
        //keep ratio to world units
        LwjglApplication launcher = new LwjglApplication(myProgram, config);
    }
}
