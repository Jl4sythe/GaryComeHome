import com.badlogic.gdx.Game;
public class GaryGame extends Game
{
    public void create()
    {
        this.setScreen(new MainMenu(this));
    }
}
