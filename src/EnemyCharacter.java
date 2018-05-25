import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EnemyCharacter {
    private Vector2 velocity;
    private ShapeRenderer renderer;
    private boolean facingRight;
    private int health;
    private Texture shell;
    private GaryGame game;

    private Texture garyTexR;
    private Texture garyTexL;
    private Rectangle enemy;

    private int coolDown = 0;

    public EnemyCharacter(GaryGame game) {
        facingRight = false;

        this.game = game;

        this.velocity = new Vector2(0,0);
        garyTexR = new Texture(Gdx.files.internal("assets/garyRight.png"));
        garyTexL = new Texture(Gdx.files.internal("assets/garyLeft.png"));

        enemy = new Rectangle();
        enemy.x = Constants.WORLD_WIDTH - Constants.GARY_WIDTH;
        enemy.y = 0;
        enemy.width = Constants.GARY_WIDTH;
        enemy.height = Constants.GARY_HEIGHT;

        renderer = new ShapeRenderer();

        health = 3;
        shell = new Texture(Gdx.files.internal("assets/garyLeft.png"));

    }

    public Rectangle getRectangle()
    {
        return enemy;
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

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
            shoot(batch);

        if(enemy.x < 0)
            velocity.x = 200;
        else if(enemy.x + Constants.GARY_WIDTH > Constants.WORLD_WIDTH)
            velocity.x = -200;


        enemy.x += velocity.x * (1.0/60);
        enemy.y += velocity.y * (1.0/60);


        if(enemy.y > 0){
            enemy.y += (0.5)*(Constants.GRAVITY)*(1.0/360);
            velocity.y += Constants.GRAVITY * (1.0/60);
        }
        else
            velocity.y = 0;

        if(coolDown > 0){
            coolDown--;
        }


    }

    public void checkHealth()
    {
        if(Gdx.input.isKeyJustPressed(Input.Keys.H))
            health--;

        for(int i = 0; i < GameScreen.lasers.size(); i++){
            if(enemy.overlaps(GameScreen.lasers.get(i).getRectangle())) {
                health--;
                GameScreen.lasers.remove(i);
                i--;
            }
        }
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

    public void  shoot(SpriteBatch batch){
        if(coolDown == 0) {
            if (facingRight) {
                GameScreen.lasers.add(new Laser(facingRight, enemy.x + enemy.width + 1, enemy.y + enemy.height - 15, batch));
                coolDown = 1 * 60;
            }
            else {
                GameScreen.lasers.add(new Laser(facingRight, enemy.x - Constants.LASER_WIDTH, enemy.y + enemy.height - 15, batch));
                coolDown = 1 * 60;
            }
        }
    }

    public void dispose(){
        renderer.dispose();
    }


}
