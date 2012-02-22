package guiClient;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JToggleButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.GridLayout;


public class TestApp
{

	private JFrame frmFoocity;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					TestApp window = new TestApp();
					window.frmFoocity.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestApp()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmFoocity = new JFrame();
		frmFoocity.setTitle("FooCity");
		frmFoocity.setBounds(100, 100, 450, 300);
		frmFoocity.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmFoocity.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Game");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New...");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Load...");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Save...");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exit");
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("Change");
		menuBar.add(mnNewMenu_1);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Continuous Time");
		mnNewMenu_1.add(chckbxmntmNewCheckItem);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Step Time Forward");
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu_1.add(separator_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Tax Rates...");
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_2 = new JMenu("View");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Reports...");
		mnNewMenu_2.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Mini Map...");
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		JToolBar toolBar = new JToolBar();
		frmFoocity.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Commercial");
		toolBar.add(tglbtnNewToggleButton);
		
		JToggleButton tglbtnNewToggleButton_1 = new JToggleButton("Industrial");
		toolBar.add(tglbtnNewToggleButton_1);
		
		JToggleButton tglbtnNewToggleButton_2 = new JToggleButton("Residential");
		toolBar.add(tglbtnNewToggleButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		frmFoocity.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(128, 128, 0, 0));
	}

}
