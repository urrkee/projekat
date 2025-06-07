package main.view;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;


import javax.swing.JFrame;

import javax.swing.JTabbedPane;


public class MainFrame extends JFrame{
	private static final long serialVersionUID = 8456560429229699542L;
	
	private JTabbedPane tabbedPane;

	public MainFrame() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
	}

	public MainFrame(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public MainFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

	public MainFrame(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public void init() {
		this.setTitle("glavni prozor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		
		RegisterPanel registracija = new RegisterPanel();
		this.getContentPane().add(registracija);
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Registruj se", registracija);
		
		LoginPanel logIn = new LoginPanel();
		this.getContentPane().add(logIn);
		tabbedPane.add("Log In",logIn);
		
		this.add(tabbedPane);
		this.setVisible(true);
		
	}
}
