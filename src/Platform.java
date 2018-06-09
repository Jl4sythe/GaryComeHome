import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Platform {
    private Rectangle rectangle;
    private Texture tex;

    public Platform(float x, float y, float width, float height){
        rectangle = new Rectangle(x,y,width,height);
        tex = new Texture(Gdx.files.internal("assets/platform.png"));
    }

    public void update(SpriteBatch batch,float delta){
        batch.draw(tex, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public Rectangle getRectangle(){
        return rectangle;
    }
    public void updateX(float x){
        rectangle.x += x;
    }
    public void updateY(float y){
        rectangle.y += y;
    }


}
