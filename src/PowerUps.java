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
    private Texture redCookie;
    private Texture finalCookie;
    public static int garyCtr;
    public static int enemyCtr;
    public static float spawnTime;
    private GaryGame game;

    public PowerUps(GaryGame game)
    {
        powers = new Array<Cookie>();
        time = 0;
        cookie = new Texture(Gdx.files.internal("assets/cookie.png"));
        purpleCookie = new Texture(Gdx.files.internal("assets/purpleCookie.png"));
        greenCookie = new Texture(Gdx.files.internal("assets/greenCookie.png"));
        redCookie = new Texture(Gdx.files.internal("assets/redCookie.png"));
        finalCookie = new Texture(Gdx.files.internal("assets/finalCookie.png"));

        game = new GaryGame();
    }

    public void renderPower(SpriteBatch batch)
    {
        for (Cookie power : powers)
        {
            int val = power.getCheck();
            if(val >= 1 && val <= 20)
                batch.draw(cookie, power.getRectangle().x, power.getRectangle().y, power.getRectangle().width, power.getRectangle().height);
            else if(val >= 21 && val <= 40)
                batch.draw(purpleCookie, power.getRectangle().x, power.getRectangle().y, power.getRectangle().width, power.getRectangle().height);
            else if(val >= 41 && val <= 60)
                batch.draw(greenCookie, power.getRectangle().x, power.getRectangle().y, power.getRectangle().width, power.getRectangle().height);
            else if(val >= 61 && val <= 80)
                batch.draw(redCookie, power.getRectangle().x, power.getRectangle().y, power.getRectangle().width, power.getRectangle().height);
            else if(val == 81)
                batch.draw(finalCookie, power.getRectangle().x, power.getRectangle().y, power.getRectangle().width, power.getRectangle().height);
        }
    }

    public void checkPower(float delta, Rectangle gary)
    {
        time += delta;
        for(int i = 0; i < powers.size; i++)
        {
            Rectangle filler = powers.get(i).getRectangle();
            int check = powers.get(i).getCheck();

            filler.y -= Constants.POWER_SPEED;

            if(filler.overlaps(gary))
            {
                powers.removeIndex(i);
                MinigameScreen.getGame().setScreen(new MinigameGameOverScreen(MinigameScreen.getGame()));
            }

            if(filler.y + Constants.POWER_SIZE < 0)
                powers.removeIndex(i);
        }
        if(time > (float)(Math.random()*10))
            spawnPower();
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
        if(time > (float)(Math.random()*10)+spawnTime)
            spawnPower();
    }

    public void spawnPower()
    {
        Rectangle power = new Rectangle();
        power.x = MathUtils.random(0, Constants.WORLD_WIDTH - Constants.POWER_SIZE);
        power.y = Constants.WORLD_HEIGHT;
        power.width = Constants.POWER_SIZE;
        power.height = Constants.POWER_SIZE;

        int val = (int)(Math.random()*81+1);
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
