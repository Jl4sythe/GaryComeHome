import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MinigameGameOverScreen implements Screen{
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private BitmapFont font;
    private Texture background;
    private Texture button;
    private Texture button2;

    private Vector2 pos;

    private GaryGame game;

    public MinigameGameOverScreen(GaryGame game){
        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT,camera);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("assets/KarbyParty.fnt"));
        button = new Texture(Gdx.files.internal("assets/Button.png"));
        button2 = new Texture(Gdx.files.internal("assets/ButtonState2.png"));
        //background = new Texture(Gdx.files.internal("assets/"));
        background = new Texture(Gdx.files.internal("assets/GameOverBackground.png"));
        this.game = game;
        pos = new Vector2(0,0);
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

            GlyphLayout winnerLayout = new GlyphLayout(font,"FINAL SCORE");
            font.draw(batch, "FINAL SCORE", Constants.WORLD_WIDTH/2 - winnerLayout.width/2, Constants.WORLD_HEIGHT/2 + winnerLayout.height/2 + 200);
            GlyphLayout winnerLayout2 = new GlyphLayout(font,""+MinigameScreen.getTimer());
            font.draw(batch, ""+MinigameScreen.getTimer(), Constants.WORLD_WIDTH/2 - winnerLayout2.width/2, Constants.WORLD_HEIGHT/2 + winnerLayout2.height/2 + 150);

        batch.draw(button,Constants.WORLD_WIDTH/2 - 400,Constants.WORLD_HEIGHT/2 - 200,300,100);
        batch.draw(button,Constants.WORLD_WIDTH/2 + 100,Constants.WORLD_HEIGHT/2 - 200,300,100);

        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        pos = viewport.unproject(new Vector2(x, y));
        if(pos.x >= Constants.WORLD_WIDTH/2 - 400 && pos.x < Constants.WORLD_WIDTH/2 - 100 && pos.y >= Constants.WORLD_HEIGHT/2 - 200 && pos.y <= Constants.WORLD_HEIGHT/2 - 100)
            batch.draw(button2,Constants.WORLD_WIDTH/2 - 400,Constants.WORLD_HEIGHT/2 - 200,300,100);
        if(pos.x >= Constants.WORLD_WIDTH/2 + 100 && pos.x < Constants.WORLD_WIDTH/2 + 400 && pos.y >= Constants.WORLD_HEIGHT/2 - 200 && pos.y <= Constants.WORLD_HEIGHT/2 - 100)
            batch.draw(button2,Constants.WORLD_WIDTH/2 + 100,Constants.WORLD_HEIGHT/2 - 200,300,100);


        GlyphLayout buttonSevenLayout = new GlyphLayout(font, "PLAY AGAIN");
        font.draw(batch, "PLAY AGAIN", Constants.WORLD_WIDTH - buttonSevenLayout.width/2 -730,Constants.WORLD_HEIGHT + buttonSevenLayout.height/2 - 420);
        GlyphLayout buttonEightLayout = new GlyphLayout(font, "QUIT");
        font.draw(batch, "QUIT", Constants.WORLD_WIDTH - buttonEightLayout.width/2 - 230,Constants.WORLD_HEIGHT + buttonEightLayout.height/2 - 420);

        batch.end();

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            x = Gdx.input.getX();
            y = Gdx.input.getY();

            pos = viewport.unproject(new Vector2(x,y));
            if(pos.x >= Constants.WORLD_WIDTH/2 - 400 && pos.x < Constants.WORLD_WIDTH/2 - 100 && pos.y >= Constants.WORLD_HEIGHT/2 - 200 && pos.y <= Constants.WORLD_HEIGHT/2 - 100)
                game.setScreen(new SelectionScreen(game));
            if(pos.x >= Constants.WORLD_WIDTH/2 + 100 && pos.x < Constants.WORLD_WIDTH/2 + 400 && pos.y >= Constants.WORLD_HEIGHT/2 - 200 && pos.y <= Constants.WORLD_HEIGHT/2 - 100)
                System.exit(0);
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
        batch.dispose();
        font.dispose();
    }
}
