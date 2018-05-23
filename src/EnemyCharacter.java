import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EnemyCharacter {
    private Vector2 pos;
    private Vector2 velocity;
    private Sprite garySprR;
    private Sprite garySprL;
    private ShapeRenderer renderer;
    private boolean facingRight;
    private int health;
    private Texture shell;
    private GaryGame game;

    private Texture garyTexR;
    private Texture garyTexL;
    private Rectangle enemy;

    public EnemyCharacter(GaryGame game) {
        facingRight = false;

        this.game = game;

        this.pos = new Vector2(0,0); //Constants.WORLD_WIDTH - Constants.GARY_WIDTH
        this.velocity = new Vector2(0,0);
        garyTexR = new Texture(Gdx.files.internal("assets/garyRight.png"));
        garyTexL = new Texture(Gdx.files.internal("assets/garyLeft.png"));

//        this.garySprR = new Sprite(garyTexR, (int)pos.x, (int)pos.y, Constants.GARY_WIDTH, Constants.GARY_HEIGHT);//add width and height later
//        this.garySprL = new Sprite(garyTexL, (int)pos.x, (int)pos.y, Constants.GARY_WIDTH, Constants.GARY_HEIGHT);
//        garySprR.setX(Constants.WORLD_WIDTH - Constants.GARY_WIDTH);
//        garySprL.setX(Constants.WORLD_WIDTH - Constants.GARY_WIDTH);

        enemy = new Rectangle();
        enemy.x = Constants.WORLD_WIDTH - Constants.GARY_WIDTH;
        enemy.y = 0;
        enemy.width = Constants.GARY_WIDTH;
        enemy.height = Constants.GARY_HEIGHT;

        renderer = new ShapeRenderer();

        health = 3;
        shell = new Texture(Gdx.files.internal("assets/garyLeft.png"));

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

        checkHealth();
        updateHealth(batch);

        if(facingRight)
            batch.draw(garyTexR, enemy.x, enemy.y, enemy.width, enemy.height);
        else if(!facingRight)
            batch.draw(garyTexL, enemy.x, enemy.y, enemy.width, enemy.height);



        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            velocity.x = -Constants.GARY_SPEED;
            facingRight = false;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            velocity.x = Constants.GARY_SPEED;
            facingRight = true;
        }
        else
            velocity.x = 0;

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && velocity.y == 0){
            velocity.y = Constants.GARY_SPEED * 1.5f;
        }

        if(enemy.x < 0)
            velocity.x = 200;
        else if(enemy.x + Constants.GARY_WIDTH > Constants.WORLD_WIDTH)
            velocity.x = -200;


        enemy.x += velocity.x * (1.0/60);
        enemy.y += velocity.y * (1.0/60);

//        garySprR.setX(pos.x);
//        garySprR.setY(pos.y);
//        garySprL.setX(pos.x);
//        garySprL.setY(pos.y);


        if(enemy.y > 0){
            enemy.y += (0.5)*(Constants.GRAVITY)*(1.0/300);
            velocity.y += Constants.GRAVITY * (1.0/60);
        }
        else
            velocity.y = 0;




    }

    public void checkHealth()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.H))
            health--;
    }

    public void updateHealth(SpriteBatch batch)
    {

        if(health == 3)
        {
            batch.draw(shell, Constants.WORLD_WIDTH-50,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
            batch.draw(shell, Constants.WORLD_WIDTH-110,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
            batch.draw(shell, Constants.WORLD_WIDTH-170,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
        }
        else if(health == 2)
        {
            batch.draw(shell, Constants.WORLD_WIDTH-50,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
            batch.draw(shell, Constants.WORLD_WIDTH-110,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
        }
        else if(health == 1)
        {
            batch.draw(shell, Constants.WORLD_WIDTH-50,Constants.WORLD_HEIGHT-Constants.SHELL_SIZE,Constants.SHELL_SIZE,Constants.SHELL_SIZE);
        }
        else if(health == 0)
        {
            game.setScreen(new GameOverScreen(game));

        }
    }

    public void dispose(){
        renderer.dispose();
    }


}
