
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
    private Texture button2;
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
        button2 = new Texture(Gdx.files.internal("assets/ButtonState2.png"));
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


        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        pos = viewport.unproject(new Vector2(x, y));

        if(pos.x >= Constants.WORLD_WIDTH - 945 && pos.x < Constants.WORLD_WIDTH - 645 && pos.y >= Constants.WORLD_HEIGHT - 190 && pos.y <= Constants.WORLD_HEIGHT - 90)
            batch.draw(button2,Constants.WORLD_WIDTH - 945,Constants.WORLD_HEIGHT - 190,300,100);
        else if(pos.x >= Constants.WORLD_WIDTH - 630 && pos.x < Constants.WORLD_WIDTH - 330 && pos.y >= Constants.WORLD_HEIGHT - 190 && pos.y <= Constants.WORLD_HEIGHT - 90)
            batch.draw(button2,Constants.WORLD_WIDTH - 630,Constants.WORLD_HEIGHT - 190,300,100);
        else if(pos.x >= Constants.WORLD_WIDTH - 315 && pos.x < Constants.WORLD_WIDTH - 15 && pos.y >= Constants.WORLD_HEIGHT - 190 && pos.y <= Constants.WORLD_HEIGHT - 90)
            batch.draw(button2,Constants.WORLD_WIDTH - 315,Constants.WORLD_HEIGHT - 190,300,100);
        else if(pos.x >= Constants.WORLD_WIDTH - 945 && pos.x < Constants.WORLD_WIDTH - 645 && pos.y >= Constants.WORLD_HEIGHT - 350 && pos.y <= Constants.WORLD_HEIGHT - 250)
            batch.draw(button2,Constants.WORLD_WIDTH - 945,Constants.WORLD_HEIGHT - 350,300,100);
        else if(pos.x >= Constants.WORLD_WIDTH - 630 && pos.x < Constants.WORLD_WIDTH - 330 && pos.y >= Constants.WORLD_HEIGHT - 350 && pos.y <= Constants.WORLD_HEIGHT - 250)
            batch.draw(button2,Constants.WORLD_WIDTH - 630,Constants.WORLD_HEIGHT - 350,300,100);
        else if(pos.x >= Constants.WORLD_WIDTH - 315 && pos.x < Constants.WORLD_WIDTH - 15 && pos.y >= Constants.WORLD_HEIGHT - 350 && pos.y <= Constants.WORLD_HEIGHT - 250)
            batch.draw(button2,Constants.WORLD_WIDTH - 315,Constants.WORLD_HEIGHT - 350,300,100);
        else if(pos.x >= Constants.WORLD_WIDTH - 945 && pos.x < Constants.WORLD_WIDTH - 645 && pos.y >= Constants.WORLD_HEIGHT - 510 && pos.y <= Constants.WORLD_HEIGHT - 410)
            batch.draw(button2,Constants.WORLD_WIDTH - 945,Constants.WORLD_HEIGHT - 510,300,100);
        else if(pos.x >= Constants.WORLD_WIDTH - 630 && pos.x < Constants.WORLD_WIDTH - 330 && pos.y >= Constants.WORLD_HEIGHT - 510 && pos.y <= Constants.WORLD_HEIGHT - 410)
            batch.draw(button2,Constants.WORLD_WIDTH - 630,Constants.WORLD_HEIGHT - 510,300,100);
        else if(pos.x >= Constants.WORLD_WIDTH - 315 && pos.x < Constants.WORLD_WIDTH - 15 && pos.y >= Constants.WORLD_HEIGHT - 510 && pos.y <= Constants.WORLD_HEIGHT - 410)
            batch.draw(button2,Constants.WORLD_WIDTH - 315,Constants.WORLD_HEIGHT - 510,300,100);


        GlyphLayout buttonOneLayout = new GlyphLayout(font, "PINEAPPLE");
        font.draw(batch, "PINEAPPLE", Constants.WORLD_WIDTH - buttonOneLayout.width/2 - 795,Constants.WORLD_HEIGHT + buttonOneLayout.height/2 - 140);
        GlyphLayout buttonTwoLayout = new GlyphLayout(font, "KRUSTY KRAB");
        font.draw(batch, "KRUSTY KRAB", Constants.WORLD_WIDTH - buttonTwoLayout.width/2 - 480,Constants.WORLD_HEIGHT + buttonTwoLayout.height/2 - 140);
        GlyphLayout buttonThreeLayout = new GlyphLayout(font, "ROCK BOTTOM");
        font.draw(batch, "ROCK BOTTOM", Constants.WORLD_WIDTH - buttonThreeLayout.width/2 - 165,Constants.WORLD_HEIGHT + buttonThreeLayout.height/2 - 140);

        GlyphLayout buttonFourLayout = new GlyphLayout(font, "TREEDOME");
        font.draw(batch, "TREEDOME", Constants.WORLD_WIDTH - buttonFourLayout.width/2 - 795,Constants.WORLD_HEIGHT + buttonFourLayout.height/2 - 300);
        GlyphLayout buttonFiveLayout = new GlyphLayout(font, "CHUM BUCKET");
        font.draw(batch, "CHUM BUCKET", Constants.WORLD_WIDTH - buttonFiveLayout.width/2 - 480,Constants.WORLD_HEIGHT + buttonFiveLayout.height/2 - 300);
        GlyphLayout buttonSixLayout = new GlyphLayout(font, "WEENIE HUT JR'S");
        font.draw(batch, "WEENIE HUT JR'S", Constants.WORLD_WIDTH - buttonSixLayout.width/2 - 165,Constants.WORLD_HEIGHT + buttonSixLayout.height/2 - 300);

        GlyphLayout buttonSevenLayout = new GlyphLayout(font, "BARG'N-MART");
        font.draw(batch, "BARG'N-MART", Constants.WORLD_WIDTH - buttonSevenLayout.width/2 - 795,Constants.WORLD_HEIGHT + buttonSevenLayout.height/2 - 460);
        GlyphLayout buttonEightLayout = new GlyphLayout(font, "JELLYFISH FIELDS");
        font.draw(batch, "JELLYFISH FIELDS", Constants.WORLD_WIDTH - buttonEightLayout.width/2 - 480,Constants.WORLD_HEIGHT + buttonEightLayout.height/2 - 460);
        GlyphLayout buttonNineLayout = new GlyphLayout(font, "MINIGAME");
        font.draw(batch, "MINIGAME", Constants.WORLD_WIDTH - buttonNineLayout.width/2 - 165,Constants.WORLD_HEIGHT + buttonNineLayout.height/2 - 460);


        batch.end();


        if(Gdx.input.justTouched())
        {
            x = Gdx.input.getX();
            y = Gdx.input.getY();

            pos = viewport.unproject(new Vector2(x,y));
            if(pos.x >= Constants.WORLD_WIDTH - 945 && pos.x < Constants.WORLD_WIDTH - 645 && pos.y >= Constants.WORLD_HEIGHT - 190 && pos.y <= Constants.WORLD_HEIGHT - 90)
                game.setScreen(new LevelOneScreen(game));
            else if(pos.x >= Constants.WORLD_WIDTH - 630 && pos.x < Constants.WORLD_WIDTH - 330 && pos.y >= Constants.WORLD_HEIGHT - 190 && pos.y <= Constants.WORLD_HEIGHT - 90)
                game.setScreen(new LevelTwoScreen(game));
            else if(pos.x >= Constants.WORLD_WIDTH - 315 && pos.x < Constants.WORLD_WIDTH - 15 && pos.y >= Constants.WORLD_HEIGHT - 190 && pos.y <= Constants.WORLD_HEIGHT - 90)
                game.setScreen(new LevelThreeScreen(game));
            else if(pos.x >= Constants.WORLD_WIDTH - 945 && pos.x < Constants.WORLD_WIDTH - 645 && pos.y >= Constants.WORLD_HEIGHT - 350 && pos.y <= Constants.WORLD_HEIGHT - 250)
                game.setScreen(new LevelFourScreen(game));
            else if(pos.x >= Constants.WORLD_WIDTH - 630 && pos.x < Constants.WORLD_WIDTH - 330 && pos.y >= Constants.WORLD_HEIGHT - 350 && pos.y <= Constants.WORLD_HEIGHT - 250)
                game.setScreen(new LevelFiveScreen(game));
            else if(pos.x >= Constants.WORLD_WIDTH - 315 && pos.x < Constants.WORLD_WIDTH - 15 && pos.y >= Constants.WORLD_HEIGHT - 350 && pos.y <= Constants.WORLD_HEIGHT - 250)
                game.setScreen(new LevelSixScreen(game));
            else if(pos.x >= Constants.WORLD_WIDTH - 945 && pos.x < Constants.WORLD_WIDTH - 645 && pos.y >= Constants.WORLD_HEIGHT - 510 && pos.y <= Constants.WORLD_HEIGHT - 410)
                game.setScreen(new LevelSevenScreen(game));
            else if(pos.x >= Constants.WORLD_WIDTH - 630 && pos.x < Constants.WORLD_WIDTH - 330 && pos.y >= Constants.WORLD_HEIGHT - 510 && pos.y <= Constants.WORLD_HEIGHT - 410)
                game.setScreen(new LevelEightScreen(game));
            else if(pos.x >= Constants.WORLD_WIDTH - 315 && pos.x < Constants.WORLD_WIDTH - 15 && pos.y >= Constants.WORLD_HEIGHT - 510 && pos.y <= Constants.WORLD_HEIGHT - 410)
                game.setScreen(new MinigameSelectionScreen(game));

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
