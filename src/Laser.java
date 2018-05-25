import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Laser {
    private boolean facingRight;
    private Rectangle rectangle;
    private SpriteBatch batch;
    private Texture laserTex;

    public Laser(boolean facingRight, float x, float y, SpriteBatch batch){
        this.facingRight = facingRight;
        rectangle = new Rectangle(x, y, 14, 4);
        this.batch = batch;
        laserTex = new Texture(Gdx.files.internal("assets/Laser.png"));
    }

    public void update()
    {
        batch.draw(laserTex, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if(facingRight)
            rectangle.x += Constants.LASER_SPEED * (1/60.0);
        else
            rectangle.x -= Constants.LASER_SPEED * (1/60.0);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
