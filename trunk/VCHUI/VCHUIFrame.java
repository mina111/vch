import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;

public class VCHUIFrame extends JFrame {
	
	public VCHUIFrame(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800,800);
		setVisible(true);
		setTitle("VHC");
	}
	
	public static void main(String args[]) {
		VCHUIPanel vhcP = new VCHUIPanel();
		VCHUIFrame vhcFrame = new VCHUIFrame();
		vhcFrame.add(vhcP);	
	}
}