import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class VerticalMovingPlatform extends Platform{
    private int velocity;
    public VerticalMovingPlatform(float x, float y, float width, float height, int velocity){
        super(x, y, width, height);
        this.velocity = velocity;
    }

    public int getVelocity(){
        return velocity;
    }


    public void update(SpriteBatch batch, float delta){
        super.updateY(velocity * delta);
        super.update(batch, delta);
    }
}
