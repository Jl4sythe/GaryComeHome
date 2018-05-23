import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class PowerUps
{
    private Array<Rectangle> powers;
    private float time;
    private Texture cookie;

    public PowerUps(GaryGame game)
    {
        powers = new Array<Rectangle>();
        time = 0;
        cookie = new Texture(Gdx.files.internal("assets/cookie.png"));
    }

    public void updatePowerUps()
    {

    }

    public void spawnCookie()
    {
        Rectangle cookie = new Rectangle();
        //cookie.x = MathUtils.random(0, Constants.WORLD_WIDTH - RAINDROP_WIDTH)
    }


}
