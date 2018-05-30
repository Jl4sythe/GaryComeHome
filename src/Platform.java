import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Platform {
    private Rectangle rectangle;
    private Texture tex;

    public Platform(float x, float y, float width, float height){
        rectangle = new Rectangle(x,y,width,height);
        tex = new Texture(Gdx.files.internal("assets/Platform.png"));
    }

    public Rectangle getRectangle(){
        return rectangle;
    }
}
