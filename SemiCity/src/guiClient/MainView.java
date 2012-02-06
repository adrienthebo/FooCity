package guiClient;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class MainView {

	private JFrame frame;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu gameMenu = new JMenu("Game Controls");
		menuBar.add(gameMenu);
		
		JMenuItem openMap = new JMenuItem("Open Map...");
		gameMenu.add(openMap);
		
		JMenuItem quit = new JMenuItem("Quit");
		quit.setAction(action);
		gameMenu.add(quit);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Quit");
			putValue(SHORT_DESCRIPTION, "Exits Game");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
