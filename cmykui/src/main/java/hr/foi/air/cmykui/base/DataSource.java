package hr.foi.air.cmykui.base;
import java.util.Random;

public class DataSource {

    public String Name;
    public double Value;
    public int Color;

    public DataSource() {}

    public DataSource(String name, double value){
        this.Name = name;
        this.Value = value;
        this.Color = randomColor();
    }

    public DataSource(String name, double value, int color){
        this.Name = name;
        this.Value = value;
        this.Color = color;
    }

    private int randomColor(){
        Random rnd = new Random();
        int color = android.graphics.Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }

}
