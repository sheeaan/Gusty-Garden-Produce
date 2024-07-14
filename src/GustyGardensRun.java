import javax.swing.JFrame;
/**
 * 
 * @author Shawn Wei 
 * @description This program allows an online shopper to decrypt and encrypt
 *              their information safely while making online purchases/orders. It's my own interesting twist to cyphering.
 * Gusty Gardens was based off of one my favaourite childhood games, Super Mario Galaxy
 */
public class GustyGardensRun {
	public static void main(String[] args) {
		//calling the model class
		GustyGardensModel model = new GustyGardensModel();
		//calling the view class
		GustyGardensView frame = new GustyGardensView();
		//calling the controller class
		GustyGardensController controller = new GustyGardensController(model, frame);
		//terminate the program once the window is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 650); // set frame size
		frame.setLocationRelativeTo(null);//centers the window of the program upon startup
		frame.setVisible(true); // display frame
		frame.setResizable(false);//set the resizable to false, so the user cannot resize the 
	}
}
