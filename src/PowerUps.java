import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class PowerUps
{
    private Array<Rectangle> powers;
    private float time;
    private Texture cookie;
    private SpriteBatch batch;

    public PowerUps(GaryGame game)
    {
        powers = new Array<Rectangle>();
        time = 0;
        cookie = new Texture(Gdx.files.internal("assets/cookie.png"));
        batch = new SpriteBatch();
    }

    public void updatePower(float delta)
    {
        time += delta;
        for(int i = 0; i < powers.size; i++)
        {
            Rectangle filler = powers.get(i);
            filler.y -= Constants.POWER_SPEED;

            if(filler.overlaps(filler))
            {
                ;
            }

            if(filler.y + Constants.POWER_SIZE < 0)
                powers.removeIndex(i);
            if(time > Constants.SPAWN_SPEED)
                spawnPower();
        }
    }

    public void renderPower()
    {
        for (Rectangle power : powers)
        {
            batch.draw(cookie, power.x, power.y, power.width, power.height);
        }
    }

    public void spawnPower()
    {
        Rectangle cookie = new Rectangle();
        cookie.x = MathUtils.random(0, Constants.WORLD_WIDTH - Constants.POWER_SIZE);
        cookie.y = Constants.WORLD_HEIGHT;
        cookie.width = Constants.POWER_SIZE;
        cookie.height = Constants.POWER_SIZE;

        powers.add(cookie);
        time = 0;
    }


}
