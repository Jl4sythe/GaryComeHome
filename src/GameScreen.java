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

public class GameScreen implements Screen
{
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer renderer;
    private Viewport viewport;
    private Texture gameBackground;
    private PowerUps power;

    private Music song;

    private GaryCharacter gary;
    private EnemyCharacter enemy;
    public static ArrayList<Laser> lasers;

    private GaryGame game;

    public GameScreen(GaryGame game) {
        this.game = game;
    }

    public void show()
    {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera);
        font = new BitmapFont();
        renderer = new ShapeRenderer();

        gary = new GaryCharacter(game);
        enemy = new EnemyCharacter(game);
        lasers = new ArrayList<Laser>();
        power = new PowerUps(game);

//        song = Gdx.audio.newMusic(Gdx.files.internal("assets/GaryComeHome.mp3"));
//        song.setLooping(true);
//        song.play();

        gameBackground = new Texture(Gdx.files.internal("assets/gameBackground.jpg"));

    }


    public void render(float delta)
    {
        Gdx.gl.glClearColor(0,0,.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        power.checkPower(delta, gary.getRectangle(), enemy.getRectangle());

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(gameBackground,0,0,Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);

        power.renderPower(batch);

        gary.updatePower(delta);
        gary.update(batch);
        enemy.updatePower(delta);
        enemy.update(batch);
        updateLasers();
        batch.end();
    }

    public void updateLasers(){
        for(Laser l: lasers){
            l.update();
        }
    }

    public void resize(int width, int height)
    {
        viewport.update(width, height, true);//not sure if it should be true or not tho
    }

    public void pause()
    {

    }

    public void resume()
    {

    }

    public void hide(){

    }

    public void dispose()
    {
        font.dispose();
        batch.dispose();

    }
}