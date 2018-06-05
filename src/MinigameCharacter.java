import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MinigameCharacter {
    private Vector2 velocity;
    private ShapeRenderer renderer;
    static boolean facingRight;
    private GaryGame game;
    private BitmapFont font;

    private Texture garyTexR;
    private Texture garyTexL;
    private Rectangle gary;

    private boolean onPlatform = false;

    public MinigameCharacter(GaryGame game) {
        facingRight = true;

        this.game = game;

        this.velocity = new Vector2(0, 0);
        garyTexR = new Texture(Gdx.files.internal("assets/garyRight.png"));
        garyTexL = new Texture(Gdx.files.internal("assets/garyLeft.png"));

        gary = new Rectangle();
        gary.x = 0;
        gary.y = 0;
        gary.width = Constants.GARY_WIDTH;
        gary.height = Constants.GARY_HEIGHT;

        renderer = new ShapeRenderer();
        font = new BitmapFont(Gdx.files.internal("assets/KarbyParty.fnt"));

    }

    public Rectangle getRectangle() {
        return gary;
    }

    public void update(SpriteBatch batch, float delta) {


        onPlatform = false;

        if (facingRight)
            batch.draw(garyTexR, gary.x, gary.y, gary.width, gary.height);
        else if (!facingRight)
            batch.draw(garyTexL, gary.x, gary.y, gary.width, gary.height);


        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            velocity.x = -Constants.GARY_SPEED;
            facingRight = false;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            velocity.x = Constants.GARY_SPEED;
            facingRight = true;
        } else
            velocity.x = 0;
        if (Gdx.input.isKeyJustPressed(Input.Keys.W) && velocity.y == 0) {
            velocity.y = Constants.GARY_JUMP_SPEED;
        }
        if (gary.x < 0)
            velocity.x = 200;
        else if (gary.x + Constants.GARY_WIDTH > Constants.WORLD_WIDTH)
            velocity.x = -200;

        gary.x += velocity.x * (delta);
        gary.y += velocity.y * (delta);


        if (gary.y > 0) {
            velocity.y += Constants.GRAVITY * (delta);
        } else
            velocity.y = 0;


    }


    public void dispose() {
        renderer.dispose();
    }


}
