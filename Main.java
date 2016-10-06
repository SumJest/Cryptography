package me.thesuje.crypting;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Main { // Main Class
	private static File file;
	private static String key = "qwerty123"; // Special key for EnCrypting
	private static Dimension dim;
  
	private static void createGUI(){
  
		final JFrame frame= new JFrame("Cryptography"); 
		JPanel panel = new JPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int height = dim.height / 2 - 100;
		int width = dim.width /2 - 450;
		frame.setSize(450, 100);
		frame.setLocation(width, height);
		frame.setResizable(false);
    JButton buttonc = new JButton("EnCrypt");
    JButton buttonde = new JButton("DeCrypt");
        
    ActionListener listenerC = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				createCGUI();
				
			}
		};
        ActionListener listenerDe = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				createDeGUI();
			}
		};
		buttonc.addActionListener(listenerC);
		buttonde.addActionListener(listenerDe);
	     panel.add(buttonc);
	        panel.add(buttonde);
        frame.add(panel);
       
        frame.setVisible(true);
	}
	private static void createCGUI(){
		final JFrame frame= new JFrame("EnCrypt File");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.setSize(450, 450);
		frame.setLocation(dim.width/2-450, dim.height/2-450);
		panel.setLayout(null);
		JLabel lbl = new JLabel("Choose File to EnCrypt!");
		lbl.setBounds(140, 5, 300, 15);
		final JTextField txt = new JTextField();
		txt.setBounds(10, 50, 300, 20);
		JButton button = new JButton("Choose");
		JButton buttoncr = new JButton("EnCrypt");
		JButton nazad = new JButton("<");
		nazad.setBounds(1, 1, 50, 25);
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(txt);
				file = chooser.getSelectedFile();
				if(file == null){
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}else{
					txt.setText(file.getAbsolutePath());
				}
			}
		};
		ActionListener list = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(file == null){
					System.err.println("Choose a file!");
				}else{
					File file1 = new File(txt.getText());
					JFileChooser choosera = new JFileChooser();
					choosera.showSaveDialog(txt);
					if(choosera.getSelectedFile() != null){
						try {
							System.out.println(file1.getAbsolutePath());
							encrypt(key, new FileInputStream(file), new FileOutputStream(choosera.getSelectedFile()));
							System.out.println("a");
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (Throwable e1) {

							e1.printStackTrace();
						}
					}
				}
			}
		};
		ActionListener listen = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				createGUI();
			}
		};
		button.addActionListener(listener);
		buttoncr.addActionListener(list);
		buttoncr.setBounds(160, 100,100, 20);
		button.setBounds(320,50 , 100, 20);
		nazad.addActionListener(listen);
		panel.add(txt);
		panel.add(lbl);
		panel.add(button);
		panel.add(buttoncr);
		panel.add(nazad);
		frame.add(panel);
		frame.setVisible(true);

	}
	private static void createDeGUI(){
		final JFrame frame= new JFrame("DeCrypt File");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		frame.setSize(450, 450);
		frame.setLocation(dim.width/2-450, dim.height/2-450);
		panel.setLayout(null);
		JLabel lbl = new JLabel("Choose File to DeCrypt!");
		lbl.setBounds(140, 5, 300, 15);
		final JTextField txt = new JTextField();
		txt.setBounds(10, 50, 300, 20);
		JButton button = new JButton("Choose");
		JButton buttoncr = new JButton("DeCrypt");
		JButton nazad = new JButton("<");
		nazad.setBounds(1, 1, 50, 25);
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(txt);
				file = chooser.getSelectedFile();
				if(file == null){
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}else{
					txt.setText(file.getAbsolutePath());
				}
			}
		};
		ActionListener list = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(file == null){
					System.err.println("Choose a file!");
				}else{
				//File file1 = new File(txt.getText());
				JFileChooser choosera = new JFileChooser();
				choosera.showSaveDialog(txt);
				if(choosera.getSelectedFile() != null){
					try {
						decrypt(key, new FileInputStream(file), new FileOutputStream(choosera.getSelectedFile()));
					} catch (FileNotFoundException e1) {
					
						e1.printStackTrace();
					} catch (Throwable e1) {
						e1.printStackTrace();
					}
				}
				}
			}
		};
		ActionListener listen = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				createGUI();
			}
		};
		button.addActionListener(listener);
		buttoncr.addActionListener(list);
		buttoncr.setBounds(160, 100,100, 20);
		button.setBounds(320,50 , 100, 20);
		nazad.addActionListener(listen);
		panel.add(txt);
		panel.add(lbl);
		panel.add(button);
		panel.add(buttoncr);
		panel.add(nazad);
		frame.add(panel);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		try {
			System.out.println("Name: EnCrypting/Derypting Files\nversion: 0.5 Beta\nauthor: TheSuJe(Roman Fakhrutdinov)\ndate: 05 October 2016");

			createGUI();


		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
	}

	public static void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
	}

	public static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");  

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}

	public static void doCopy(InputStream is, OutputStream os) throws IOException {
		byte[] bytes = new byte[64];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}
}
