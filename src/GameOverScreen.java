import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameOverScreen implements Screen{
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private BitmapFont font;
    private Texture background;
    private Texture button;

    private boolean garyWinner;

    private GaryGame game;

    public GameOverScreen(GaryGame game, boolean garyWinner){
        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT,camera);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("assets/KarbyParty.fnt"));
        button = new Texture(Gdx.files.internal("assets/Button.png"));
        //background = new Texture(Gdx.files.internal("assets/"));
        background = new Texture(Gdx.files.internal("assets/GameOverBackground.jpg"));
        this.garyWinner = garyWinner;
        this.game = game;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0,0,.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background,0f,0f,Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);

        if(garyWinner){
            GlyphLayout winnerLayout = new GlyphLayout(font,"Gary Wins!!");
            font.draw(batch, "Gary Wins!!", Constants.WORLD_WIDTH/2 - winnerLayout.width/2, Constants.WORLD_HEIGHT/2 + winnerLayout.height/2 + 200);
        }
        else{
            GlyphLayout winnerLayout = new GlyphLayout(font,"The Other Guy Wins!!");
            font.draw(batch, "The Other Guy Wins!!", Constants.WORLD_WIDTH/2 - winnerLayout.width/2, Constants.WORLD_HEIGHT/2 + winnerLayout.height/2 + 200);
        }
        GlyphLayout layout = new GlyphLayout(font, "GAME OVER FOOLS");
        font.draw(batch, "GAME OVER FOOLS", Constants.WORLD_WIDTH/2 - layout.width/2,Constants.WORLD_HEIGHT/2 + layout.height/2 + 30);
        batch.draw(button,Constants.WORLD_WIDTH/2 - 400,Constants.WORLD_HEIGHT/2 - 200,300,100);
        batch.draw(button,Constants.WORLD_WIDTH/2 + 100,Constants.WORLD_HEIGHT/2 - 200,300,100);
        //batch.draw(background,0f,0f,Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);

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
        batch.dispose();
        font.dispose();
    }
}
