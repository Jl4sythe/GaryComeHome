import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class GameLauncher
{
    public static void main(String[] args)
    {
        GaryGame myProgram = new GaryGame();

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1920;
        config.height = 1080;
        //keep ratio to world units
        LwjglApplication launcher = new LwjglApplication(myProgram, config);
    }
}
