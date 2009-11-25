import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;


public class StatisticsTable {
  final String[] headers = {"count","preValue", "curValue", "state","curResult","method"};
  final String[][] data ;
  ArrayList statistics;
  JFrame table ;
  public StatisticsTable(ArrayList al){
	  table = new JFrame("Statistics Table");
	  statistics = al;     
	  table.setSize(800, 600); 
      data = new String[statistics.size()][headers.length];
      final AbstractTableModel dm = new AbstractTableModel() {
          public int getRowCount() {
              return data.length;
          }

          public int getColumnCount() {
              return headers.length;
          }

          public String getColumnName(int col) {
              return headers[col];
          }

          public Object getValueAt(int row, int col) {
              return data[row][col];
          }
      };
      JScrollPane scroll = new JScrollPane(new JTable(dm));
      table.add(scroll);
      table.addWindowListener(new WindowAdapter(){
    	  public void windowClosed(WindowEvent e) {
    		  table.dispose();
    	  }
      });
      table.setVisible(true);
  }
  
  

}
