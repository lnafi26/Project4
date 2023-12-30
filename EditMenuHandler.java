import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class EditMenuHandler implements ActionListener{
    JFrame jframe;
    ArrayList<Date212> dateList;

    public EditMenuHandler(JFrame jf, ArrayList<Date212> d){
        jframe = jf;
        dateList = d;
    } //constructor

    public void actionPerformed(ActionEvent event){
        String menuName = event.getActionCommand();
        if (menuName.equals("Search")) search();
    } //method that checks for what action was performed

    public void search(){
        String inputYear = JOptionPane.showInputDialog(null, "Enter a valid year:");
        int inYear = 0;
        try{
            inYear = Integer.parseInt(inputYear);
        }catch (NumberFormatException nfe){
            JOptionPane.showMessageDialog(null, inputYear + " isn't a valid year.");
            return;
        }

        Container myContentPane = jframe.getContentPane(); //creates GUI'S content pane
        myContentPane.removeAll(); //resets content pane just in case

        TextArea SpecifiedDates = new TextArea();
        myContentPane.add(SpecifiedDates);

        if (dateList.size() != 0){
            for (Date212 specifiedIterator : dateList){
                if (specifiedIterator.getYear() == inYear) SpecifiedDates.append(specifiedIterator.toString() + "\n");
            } //appends all dates in the ArrayList to the UnsortedDates TextArea
            jframe.setVisible(true);
        }else{
            SpecifiedDates.append("Please read in a file of dates before attempting to use the Search feature.");
        } //failsafe that displays a message if the user attempts to Search without reading a file in
    } //search method that allows the user to enter a year and display all of the dates with the corresponding year
}