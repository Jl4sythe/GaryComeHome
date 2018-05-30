import com.badlogic.gdx.math.Rectangle;

public class Cookie
{
    private Rectangle rectangle;
    private int check;

    public Cookie(Rectangle rectangle, int check)
    {
        this.rectangle = rectangle;
        this.check = check;
    }

    public void setCheck(int check)
    {
        this.check = check;
    }

    public void setRectangle(Rectangle rectangle)
    {
        this.rectangle = rectangle;
    }

    public int getCheck()
    {
        return check;
    }

    public Rectangle getRectangle()
    {
        return rectangle;
    }
}
