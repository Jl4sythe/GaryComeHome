import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

public class MinigameSelectionScreen implements Screen {

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

    public MinigameSelectionScreen(GaryGame game)
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

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(menuBackground,0,0,Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);

        GlyphLayout layout = new GlyphLayout(font, "MINIGAME LEVEL SELECTION");
        font.draw(batch, "MINIGAME LEVEL SELECTION", Constants.WORLD_WIDTH/2 - layout.width/2,Constants.WORLD_HEIGHT/2 + layout.height/2 + 230);

        batch.draw(button, Constants.WORLD_WIDTH / 2 - 400, Constants.WORLD_HEIGHT / 2 - 50, 300, 100);
        batch.draw(button, Constants.WORLD_WIDTH / 2 + 100, Constants.WORLD_HEIGHT / 2 - 50, 300, 100);

        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        pos = viewport.unproject(new Vector2(x, y));

        if(pos.x >= Constants.WORLD_WIDTH - 880 && pos.x < Constants.WORLD_WIDTH - 580 && pos.y >= Constants.WORLD_HEIGHT - 320 && pos.y <= Constants.WORLD_HEIGHT - 220)
            batch.draw(button2, Constants.WORLD_WIDTH / 2 - 400, Constants.WORLD_HEIGHT / 2 - 50, 300, 100);
        else if(pos.x >= Constants.WORLD_WIDTH - 360 && pos.x < Constants.WORLD_WIDTH - 60 && pos.y >= Constants.WORLD_HEIGHT - 320 && pos.y <= Constants.WORLD_HEIGHT - 220)
            batch.draw(button2, Constants.WORLD_WIDTH / 2 + 100, Constants.WORLD_HEIGHT / 2 - 50, 300, 100);

        GlyphLayout buttonSevenLayout = new GlyphLayout(font, "TIMER GAME");
        font.draw(batch, "TIMER GAME", Constants.WORLD_WIDTH - buttonSevenLayout.width/2 -730,Constants.WORLD_HEIGHT + buttonSevenLayout.height/2 - 270);
        GlyphLayout buttonEightLayout = new GlyphLayout(font, "DIRTY BUBBLE");
        font.draw(batch, "DIRTY BUBBLE", Constants.WORLD_WIDTH - buttonEightLayout.width/2 - 230,Constants.WORLD_HEIGHT + buttonEightLayout.height/2 - 270);

        batch.end();

        if(Gdx.input.justTouched())
        {
            x = Gdx.input.getX();
            y = Gdx.input.getY();
            pos = viewport.unproject(new Vector2(x, y));

            if(pos.x >= Constants.WORLD_WIDTH - 880 && pos.x < Constants.WORLD_WIDTH - 580 && pos.y >= Constants.WORLD_HEIGHT - 320 && pos.y <= Constants.WORLD_HEIGHT - 220)
                game.setScreen(new MinigameScreen(game));
            else if(pos.x >= Constants.WORLD_WIDTH - 360 && pos.x < Constants.WORLD_WIDTH - 60 && pos.y >= Constants.WORLD_HEIGHT - 320 && pos.y <= Constants.WORLD_HEIGHT - 220)
                game.setScreen(new MinigameTwoScreen(game));
        }
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
        batch.dispose();
        font.dispose();
    }
}
