import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class FileMenuHandler implements ActionListener{
    JFrame jframe;
    ArrayList<Date212> dateList;

    public FileMenuHandler(JFrame jf, ArrayList<Date212> d){
        jframe = jf;
        dateList = d;
    } //constructor

    public void actionPerformed(ActionEvent event){
        String menuName = event.getActionCommand();
        if (menuName.equals("Open")) openFile();
        else if (menuName.equals("Quit")) System.exit(0);
    } //method that checks for what action was performed

    private void openFile(){
        JFileChooser selector;
        int status;
        selector = new JFileChooser( );
        status = selector.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) readSource(selector.getSelectedFile()); //the user chooses the file they wish to input
        else JOptionPane.showMessageDialog(null, "Open File dialog canceled");
    } //openFile method that handles choosing the file to be read (or if the user wishes to cancel the action)

    private void readSource(File chosenFile){
        String chosenFileName = chosenFile.getAbsolutePath(); //Retrieves absolute path for the selected file
        TextFileInput in = new TextFileInput(chosenFileName);
        String line = in.readLine(); //Reads in the initial line

        Container myContentPane = jframe.getContentPane(); //creates GUI'S content pane
        myContentPane.removeAll(); //resets content pane just in case

        TextArea UnsortedDates = new TextArea();
        TextArea SortedDates = new TextArea(); //establishes TextAreas for unsorted and sorted dates

        myContentPane.add(UnsortedDates);
        myContentPane.add(SortedDates); //appends unsorted and sorted TextAreas to the content pane

        while (line != null){	
            StringTokenizer myTokens = new StringTokenizer(line,",");			
            while (myTokens.hasMoreTokens()){
                try{
                    String dateString = myTokens.nextToken();
                    Date212 temp = new Date212(dateString);
                    if (temp.isValid()) dateList.add(temp);
                    else System.out.println("Invalid date: " + dateString);
                }catch (IllegalDate212Exception e){
                    System.out.println(e.getMessage());
                } //try-catch block handles thrown IllegalDate212Exceptions		
            } //ensures that each token (date) is read if they're separated by commas
            line = in.readLine(); //moves to next line
        } //reads in text file lines until there are no more lines to be read

        System.out.println("");
        in.close();

        UnsortedDates.setText("");
        SortedDates.setText(""); //clears TextAreas before appending new dates

        for (Date212 unsortedIterator : dateList){
            UnsortedDates.append(unsortedIterator.toString() + "\n");
        } //appends all dates in the ArrayList to the UnsortedDates TextArea

        Collections.sort(dateList, Date212.dateComparator); //sorts the array by using the compareTo method from class Date212

        for (Date212 sortedIterator : dateList){
            SortedDates.append(sortedIterator.toString() + "\n");
        } //appends all SORTED dates in the ArrayList to the SortedDates TextArea

        jframe.setVisible(true);
    } //readSource method that handles the logic IF the user does select a file to be read
} //class FileMenuHandler