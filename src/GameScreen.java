import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class GameScreen implements Screen
{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Viewport viewport;

    private static float WORLD_WIDTH = 1920;
    private static float WORLD_HEIGHT = 1080;

    public void show()
    {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        font = new BitmapFont();
        //renderer = new ShapeRenderer();

    }

    public void render(float delta)
    {

    }

    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

    public void pause()
    {

    }

    public void resume()
    {

    }

    public void dispose()
    {
        font.dispose();
        batch.dispose();
    }
}