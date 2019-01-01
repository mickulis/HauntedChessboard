import ControlPack.Controller;
import ModelPack.Model;
import ViewPack.View;

public class Main
{
	public static void main(String[] args)
	{
		Model m = new Model(8, 8);
		View v = new View(8, 8);
		Controller c = new Controller(m, v);
	}
}
