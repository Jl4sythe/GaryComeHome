
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Screen;

public class SelectionScreen implements Screen
{
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private BitmapFont font;
    private Texture menuBackground;
    private ShapeRenderer renderer;
    private Texture button;
    private Texture word;
    private Vector2 pos;

    private GaryGame game;

    public SelectionScreen(GaryGame game)
    {
        this.game = game;
    }

    public void show()
    {
        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT,camera);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("assets/KarbyParty.fnt"));
        renderer = new ShapeRenderer();
        button = new Texture(Gdx.files.internal("assets/Button.png"));
        word = new Texture(Gdx.files.internal("assets/play word.png"));
        menuBackground = new Texture(Gdx.files.internal("assets/menuBackground.jpg"));
        pos = new Vector2(0,0);
    }

    public void render(float delta)
    {
        Gdx.gl.glClearColor(0,0,.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(menuBackground,0,0,Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        //batch.draw(word,Constants.WORLD_WIDTH - 320,Constants.WORLD_HEIGHT - 180,300,100);

        GlyphLayout layout = new GlyphLayout(font, "LEVEL SELECTION");
        font.draw(batch, "LEVEL SELECTION", Constants.WORLD_WIDTH/2 - layout.width/2,Constants.WORLD_HEIGHT/2 + layout.height/2 + 230);

        batch.draw(button,Constants.WORLD_WIDTH - 945,Constants.WORLD_HEIGHT - 190,300,100);
        batch.draw(button,Constants.WORLD_WIDTH - 630,Constants.WORLD_HEIGHT - 190,300,100);
        batch.draw(button,Constants.WORLD_WIDTH - 315,Constants.WORLD_HEIGHT - 190,300,100);

        batch.draw(button,Constants.WORLD_WIDTH - 945,Constants.WORLD_HEIGHT - 350,300,100);
        batch.draw(button,Constants.WORLD_WIDTH - 630,Constants.WORLD_HEIGHT - 350,300,100);
        batch.draw(button,Constants.WORLD_WIDTH - 315,Constants.WORLD_HEIGHT - 350,300,100);

        batch.draw(button,Constants.WORLD_WIDTH - 945,Constants.WORLD_HEIGHT - 510,300,100);
        batch.draw(button,Constants.WORLD_WIDTH - 630,Constants.WORLD_HEIGHT - 510,300,100);
        batch.draw(button,Constants.WORLD_WIDTH - 315,Constants.WORLD_HEIGHT - 510,300,100);

        batch.end();

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
        {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            pos = viewport.unproject(new Vector2(x,y));
            if(pos.x >= Constants.WORLD_WIDTH - 945 && pos.x < Constants.WORLD_WIDTH - 645 && pos.y >= Constants.WORLD_HEIGHT - 190 && pos.y <= Constants.WORLD_HEIGHT - 90)
                game.setScreen(new LevelOneScreen(game));
            else if(pos.x >= Constants.WORLD_WIDTH - 630 && pos.x < Constants.WORLD_WIDTH - 330 && pos.y >= Constants.WORLD_HEIGHT - 190 && pos.y <= Constants.WORLD_HEIGHT - 90)
                game.setScreen(new LevelTwoScreen(game));
            else if(pos.x >= Constants.WORLD_WIDTH - 315 && pos.x < Constants.WORLD_WIDTH - 15 && pos.y >= Constants.WORLD_HEIGHT - 190 && pos.y <= Constants.WORLD_HEIGHT - 90)
                game.setScreen(new LevelThreeScreen(game));
            if(pos.x >= Constants.WORLD_WIDTH - 945 && pos.x < Constants.WORLD_WIDTH - 645 && pos.y >= Constants.WORLD_HEIGHT - 350 && pos.y <= Constants.WORLD_HEIGHT - 250)
                game.setScreen(new LevelFourScreen(game));
            else if(pos.x >= Constants.WORLD_WIDTH - 630 && pos.x < Constants.WORLD_WIDTH - 330 && pos.y >= Constants.WORLD_HEIGHT - 350 && pos.y <= Constants.WORLD_HEIGHT - 250)
                game.setScreen(new LevelFiveScreen(game));
            else if(pos.x >= Constants.WORLD_WIDTH - 315 && pos.x < Constants.WORLD_WIDTH - 15 && pos.y >= Constants.WORLD_HEIGHT - 350 && pos.y <= Constants.WORLD_HEIGHT - 250)
                game.setScreen(new LevelSixScreen(game));
            if(pos.x >= Constants.WORLD_WIDTH - 945 && pos.x < Constants.WORLD_WIDTH - 645 && pos.y >= Constants.WORLD_HEIGHT - 510 && pos.y <= Constants.WORLD_HEIGHT - 410)
                game.setScreen(new LevelSevenScreen(game));
            else if(pos.x >= Constants.WORLD_WIDTH - 630 && pos.x < Constants.WORLD_WIDTH - 330 && pos.y >= Constants.WORLD_HEIGHT - 510 && pos.y <= Constants.WORLD_HEIGHT - 410)
                game.setScreen(new LevelEightScreen(game));
            else if(pos.x >= Constants.WORLD_WIDTH - 315 && pos.x < Constants.WORLD_WIDTH - 15 && pos.y >= Constants.WORLD_HEIGHT - 510 && pos.y <= Constants.WORLD_HEIGHT - 410)
                game.setScreen(new LevelOneScreen(game));

            GaryCharacter.time = Constants.POWER_TIME;
            EnemyCharacter.time = Constants.POWER_TIME;

        }
    }

    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
    }

    public void hide()
    {

    }

    public void pause()
    {

    }

    public void resume()
    {

    }

    public void dispose()
    {
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }
}
