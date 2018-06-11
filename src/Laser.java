import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Laser {
    private boolean facingRight;
    private Rectangle rectangle;
    private SpriteBatch batch;
    private Texture laserTex;
    private Music laserSound;
    private int ctr = 0;

    public Laser(boolean facingRight, float x, float y, SpriteBatch batch){
        this.facingRight = facingRight;
        rectangle = new Rectangle(x, y, 14, 4);
        this.batch = batch;
        laserTex = new Texture(Gdx.files.internal("assets/Laser.png"));
        laserSound = Gdx.audio.newMusic(Gdx.files.internal("assets/Pew_Pew.mp3"));
        laserSound.play();
    }

    public void update(int i) {
        ctr++;
        batch.draw(laserTex, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if(ctr < 30) {
            if (facingRight)
                rectangle.x += Constants.LASER_SPEED * (1 / 60.0);
            else
                rectangle.x -= Constants.LASER_SPEED * (1 / 60.0);

            for(Platform p: GameScreen.getPlatforms()){
                if(rectangle.overlaps(p.getRectangle()))
                    GameScreen.lasers.remove(i);
            }
        }
        else
            GameScreen.lasers.remove(i);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
