import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GaryCharacter {
    private Vector2 pos;
    private Vector2 velocity;
    private Sprite garySprR;
//    private Sprite garySprL;

    private ShapeRenderer renderer;

    public GaryCharacter() {
        this.pos = new Vector2(0,0);
        this.velocity = new Vector2(0,0);
        Texture garyTexR = new Texture(Gdx.files.internal("assets/garyRight.png"));
//        Texture garyTexL = new Texture(Gdx.files.internal("assets/garyLeft.png"));
        this.garySprR = new Sprite(garyTexR, (int)pos.x, (int)pos.y, Constants.GARY_WIDTH, Constants.GARY_HEIGHT);//add width and height later
//        this.garySprL = new Sprite(garyTexL, (int)pos.x, (int)pos.y, Constants.GARY_WIDTH, Constants.GARY_HEIGHT);

        renderer = new ShapeRenderer();
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void update(SpriteBatch batch){
            garySprR.draw(batch);

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            velocity.x = -Constants.GARY_SPEED;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            velocity.x = Constants.GARY_SPEED;
        }
        else
            velocity.x = 0;

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && velocity.y == 0){
            velocity.y = Constants.GARY_SPEED;
        }

        if(pos.x < 0)
            velocity.x = 200;
        else if(pos.x + Constants.GARY_WIDTH > Constants.WORLD_WIDTH)
            velocity.x = -200;


        pos.x += velocity.x * (1.0/60);
        pos.y += velocity.y * (1.0/60);

        garySprR.setX(pos.x);
        garySprR.setY(pos.y);
//        garySprL.setX(pos.x);
//        garySprL.setY(pos.y);


        if(pos.y > 0){
            pos.y += (0.5)*(Constants.GRAVITY)*(1.0/300);
            velocity.y += Constants.GRAVITY * (1.0/60);
        }
        else
            velocity.y = 0;


    }

    public void dispose(){
        renderer.dispose();
    }


}
