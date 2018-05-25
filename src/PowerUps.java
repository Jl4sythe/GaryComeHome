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
    private int garyCtr;
    private int enemyCtr;
    //private SpriteBatch batch;

    public PowerUps(GaryGame game)
    {
        powers = new Array<Rectangle>();
        time = 0;
        cookie = new Texture(Gdx.files.internal("assets/cookie.png"));
        //batch = new SpriteBatch();
    }

    public void renderPower(SpriteBatch batch)
    {
        for (Rectangle power : powers)
        {
            batch.draw(cookie, power.x, power.y, power.width, power.height);
        }
    }

    public void checkPower(float delta, Rectangle gary, Rectangle enemy)
    {
        time += delta;
        for(int i = 0; i < powers.size; i++)
        {
            Rectangle filler = powers.get(i);
            filler.y -= Constants.POWER_SPEED;

            if(filler.overlaps(gary))
            {
                garyCtr++;
            }
            else if(filler.overlaps(enemy))
            {
                enemyCtr++;
            }

            if(filler.y + Constants.POWER_SIZE < 0)
                powers.removeIndex(i);
        }
        if(time > Constants.SPAWN_SPEED)
            spawnPower();
    }

    public void spawnPower()
    {
        Rectangle power = new Rectangle();
        power.x = MathUtils.random(0, Constants.WORLD_WIDTH - Constants.POWER_SIZE);
        power.y = Constants.WORLD_HEIGHT;
        power.width = Constants.POWER_SIZE;
        power.height = Constants.POWER_SIZE;

        powers.add(power);
        time = 0;
    }

    public int getGaryCtr() {
        return garyCtr;
    }

    public void setGaryCtr(int garyCtr) {
        this.garyCtr = garyCtr;
    }

    public int getEnemyCtr() {
        return enemyCtr;
    }

    public void setEnemyCtr(int enemyCtr) {
        this.enemyCtr = enemyCtr;
    }
}
