import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;

public class MinigameScreen implements Screen
{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer renderer;
    private Viewport viewport;
    private Texture gameBackground;
    private PowerUps power;

    private Music song;

    private MinigameCharacter gary;
    private static GaryGame game;

    private static int timer;

    public MinigameScreen(GaryGame game) {
        this.game = game;
    }

    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);
        font = new BitmapFont(Gdx.files.internal("assets/KarbyParty.fnt"));
        renderer = new ShapeRenderer();
        gameBackground = new Texture(Gdx.files.internal("assets/LevelOneBackground.png"));

        gary = new MinigameCharacter(game);
        power = new PowerUps(game);
        timer = 0;

        PowerUps.spawnTime = -0.25f;


//        song = Gdx.audio.newMusic(Gdx.files.internal("assets/GaryComeHome.mp3"));
//        song.setLooping(true);
//        song.play();

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        timer++;
        power.checkPower(delta, gary.getRectangle());

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(gameBackground,0,0,Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);


        power.renderPower(batch);
        font.draw(batch, ""+timer, 30, Constants.WORLD_HEIGHT - 30);

        gary.update(batch, delta);

        batch.end();
    }

    public void setBackground(Texture texture){
        gameBackground = texture;
    }

    public static GaryGame getGame() {
        return game;
    }

    public static int getTimer() {
        return timer;
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);

    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide(){

    }

    public void dispose() {
        font.dispose();
        batch.dispose();

    }
}