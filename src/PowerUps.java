import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class PowerUps
{
    private Array<Cookie> powers;
    private float time;
    private Texture cookie;
    private Texture purpleCookie;
    private Texture greenCookie;
    public static int garyCtr;
    public static int enemyCtr;

    public PowerUps(GaryGame game)
    {
        powers = new Array<Cookie>();
        time = 0;
        cookie = new Texture(Gdx.files.internal("assets/cookie.png"));
        purpleCookie = new Texture(Gdx.files.internal("assets/purpleCookie.png"));
        greenCookie = new Texture(Gdx.files.internal("assets/greenCookie.png"));
    }

    public void renderPower(SpriteBatch batch)
    {
        for (Cookie power : powers)
        {
            int val = power.getCheck();
            if(val == 1)
                batch.draw(cookie, power.getRectangle().x, power.getRectangle().y, power.getRectangle().width, power.getRectangle().height);
            else if(val == 2)
                batch.draw(purpleCookie, power.getRectangle().x, power.getRectangle().y, power.getRectangle().width, power.getRectangle().height);
            else if(val == 3)
                batch.draw(greenCookie, power.getRectangle().x, power.getRectangle().y, power.getRectangle().width, power.getRectangle().height);
        }
    }

    public void checkPower(float delta, Rectangle gary, Rectangle enemy)
    {
        time += delta;
        for(int i = 0; i < powers.size; i++)
        {
            Rectangle filler = powers.get(i).getRectangle();
            int check = powers.get(i).getCheck();

            filler.y -= Constants.POWER_SPEED;

            if(filler.overlaps(gary))
            {
                garyCtr = check;
                powers.removeIndex(i);
            }
            else if(filler.overlaps(enemy))
            {
                enemyCtr = check;
                powers.removeIndex(i);
            }

            if(filler.y + Constants.POWER_SIZE < 0)
                powers.removeIndex(i);
        }
        if(time > (float)(Math.random()*10+10))
            spawnPower();
    }

    public void spawnPower()
    {
        Rectangle power = new Rectangle();
        power.x = MathUtils.random(0, Constants.WORLD_WIDTH - Constants.POWER_SIZE);
        power.y = Constants.WORLD_HEIGHT;
        power.width = Constants.POWER_SIZE;
        power.height = Constants.POWER_SIZE;

        int val = (int)(Math.random()*3+1);
        Cookie set = new Cookie(power, val);

        powers.add(set);
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
