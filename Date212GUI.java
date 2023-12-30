import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Date212GUI extends JFrame{
	private ArrayList<Date212> privList;
	
	public Date212GUI(ArrayList<Date212> d){
		privList = d;
		initializeGUI();
	} //no-argument constructor

	private void initializeGUI(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
		createMenu();
        this.setSize(600, 300);
        this.setLocation(100, 100);
        this.setTitle("Date Display");
        this.getContentPane();
        this.setLayout(new GridLayout(0,2));
	} //establishes GUI

	private void createMenu(){
		JMenuBar menuBar  = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem item;
		FileMenuHandler fmh = new FileMenuHandler(this, privList);
		
		item = new JMenuItem("Open"); 
		item.addActionListener(fmh);
		fileMenu.add(item);
		fileMenu.addSeparator(); 
		
		item = new JMenuItem("Quit"); 
		item.addActionListener(fmh);
		fileMenu.add(item);
		
		menuBar.add(fileMenu);

		JMenu editMenu = new JMenu("Edit");
		JMenuItem item2;
		EditMenuHandler emh = new EditMenuHandler(this, privList);

		item2 = new JMenuItem("Search");
		item2.addActionListener(emh);
		editMenu.add(item2);

		menuBar.add(editMenu);

		setJMenuBar(menuBar);
	} //createMenu
} //class Date212GUI