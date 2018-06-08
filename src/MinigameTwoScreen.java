import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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

public class MinigameTwoScreen implements Screen {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer renderer;
    private Viewport viewport;
    private Texture gameBackground;
    private Music song;
    private Texture button;
    private Texture button2;
    private Vector2 pos;

    private static GaryGame game;
    private static int score;

    public MinigameTwoScreen(GaryGame game)
    {
        this.game = game;
    }

    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);
        font = new BitmapFont(Gdx.files.internal("assets/KarbyParty.fnt"));
        renderer = new ShapeRenderer();
        gameBackground = new Texture(Gdx.files.internal("assets/menuBackground.jpg"));
        button = new Texture(Gdx.files.internal("assets/Button.png"));
        button2 = new Texture(Gdx.files.internal("assets/ButtonState2.png"));
        pos = new Vector2(0,0);

        score = 0;
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(gameBackground,0,0,Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);

        GlyphLayout layout = new GlyphLayout(font, "PLEASE UNLOCK THE EXTRA CREDIT DLC TO PLAY THIS MINIGAME");
        font.draw(batch, "PLEASE UNLOCK THE EXTRA CREDIT DLC TO PLAY THIS MINIGAME",
                Constants.WORLD_WIDTH / 2 - layout.width / 2,
                Constants.WORLD_HEIGHT / 2 + layout.height / 2);

        batch.draw(button,Constants.WORLD_WIDTH - 945,Constants.WORLD_HEIGHT/2 +210,120,40);

        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        pos = viewport.unproject(new Vector2(x, y));

        if(pos.x >= Constants.WORLD_WIDTH - 945 && pos.x < Constants.WORLD_WIDTH - 825 && pos.y >= Constants.WORLD_HEIGHT/2 + 210 && pos.y <= Constants.WORLD_HEIGHT/2 +250)
            batch.draw(button2,Constants.WORLD_WIDTH - 945,Constants.WORLD_HEIGHT/2 +210,120,40);

        GlyphLayout backLayout = new GlyphLayout(font, "BACK");
        font.draw(batch, "BACK", Constants.WORLD_WIDTH - backLayout.width/2 - 885,Constants.WORLD_HEIGHT + backLayout.height/2 - 40);

        if(Gdx.input.justTouched()) {
            x = Gdx.input.getX();
            y = Gdx.input.getY();

            if(pos.x >= Constants.WORLD_WIDTH - 945 && pos.x < Constants.WORLD_WIDTH - 825 && pos.y >= Constants.WORLD_HEIGHT/2 + 210 && pos.y <= Constants.WORLD_HEIGHT/2 +250)
                game.setScreen(new MinigameSelectionScreen(game));
        }

        batch.end();
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
