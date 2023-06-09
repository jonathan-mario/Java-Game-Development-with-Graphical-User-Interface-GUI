import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class Utama extends JFrame{
	public JLabel jdl,nama,hp,atk,def,weapon,armor,naroGambar,naroGambarr;
	public JTextField txt1;
	public JButton play, about, exit, back, music, music1, music2;
	ButtonGroup bg = new ButtonGroup();
	Clip clip;
	
	int previousStatAtk = 0;
	int previousStatHp = 0;
	
	public Utama() 
	{

		this.getDefaultCloseOperation();
		this.setSize(675,600);
		this.setLayout(null);
		//getContentPane().setBackground(Color.lightGray);
		
		jdl = new JLabel("RPG GAME");
		jdl.setBounds(270, 25, 100, 20);
		jdl.setFont(new Font("TimesNewRoman",Font.BOLD,18));
		this.add(jdl);
		
		nama = new JLabel("Name : ");
		nama.setBounds(28, 90, 300, 20);
		this.add(nama);
		
		txt1 = new JTextField(Run.player.name);
		txt1.setBounds(75, 92, 90, 19);
		this.add(txt1);
		
		hp = new JLabel("HP : " + Run.player.hp);
		hp.setBounds(45, 122, 50, 20);
		this.add(hp);
		
		atk = new JLabel("ATK : " + Run.player.atk);
		atk.setBounds(400, 90, 100, 20);
		this.add(atk);
		
		def = new JLabel("DEF : " + Run.player.def);
		def.setBounds(402, 119, 50, 20);
		this.add(def);
		
		weapon = new JLabel("WEAPON : ");
		weapon.setBounds(10, 142, 80, 40);
		this.add(weapon);
		
		ImageIcon g = new ImageIcon("src\\Gambar\\arrow.png");
		naroGambar = new JLabel(g);
		naroGambar.setBounds(30,230,256,256);
		this.add(naroGambar);
		
		JComboBox<String> weapon = new JComboBox<String>();
		weapon.setBounds(75, 152, 180, 20);
		for(int i = 0; i < Run.player.weapon.size(); i++) 
		{
			weapon.addItem(Run.player.weapon.get(i).weaponName);
			
			if(Run.player.weapon.get(i).isSelected) 
			{
				weapon.setSelectedIndex(i);														//Set selected weapon
				previousStatAtk = Run.player.weapon.get(weapon.getSelectedIndex()).statAtk;		//Set PreviousAtk
				ImageIcon gambar = new ImageIcon(Run.player.weapon.get(i).urlImage);
				naroGambar.setIcon(gambar);
			}
			
		}
		this.add(weapon);
		
		armor = new JLabel("ARMOR : ");
		armor.setBounds(380, 140, 80, 40);
		this.add(armor);
		
		ImageIcon h = new ImageIcon("src\\Gambar\\armor.png");
		naroGambarr = new JLabel(h);
		naroGambarr.setBounds(380,230,256,256);
		this.add(naroGambarr);
		
		JComboBox<String> armor = new JComboBox<String>();
		armor.setBounds(435, 150, 180, 20);
		for(int i = 0; i < Run.player.armor.size(); i++) 
		{
			armor.addItem(Run.player.armor.get(i).armorName);
			
			if(Run.player.armor.get(i).isSelected) 
			{
				armor.setSelectedIndex(i);													
				previousStatHp = Run.player.armor.get(armor.getSelectedIndex()).statHp;		
				ImageIcon gambar = new ImageIcon(Run.player.armor.get(i).urlImage);
				naroGambarr.setIcon(gambar);
			}
		}
		this.add(armor);
		
		JRadioButton easy = new JRadioButton("Easy");
		easy.setBounds(15,515,70,25);
		this.add(easy);
		
		JRadioButton hard= new JRadioButton("Hard");
		hard.setBounds(85,515,70,25);
		this.add(hard);
		
		bg.add(easy);
		bg.add(hard);
		
		play= new JButton("Play");
		play.setBounds(310,510,100,30);
		this.add(play);
		
		
		//Drop down button weapon
		weapon.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int i = 0; i < Run.player.weapon.size(); i++) 
				{
					Run.player.weapon.get(i).isSelected = false;
				}
				
				Run.player.atk -= previousStatAtk;
				Run.player.atk += Run.player.weapon.get(weapon.getSelectedIndex()).statAtk;
				previousStatAtk = Run.player.weapon.get(weapon.getSelectedIndex()).statAtk;
				Run.player.weapon.get(weapon.getSelectedIndex()).isSelected = true; 			//set selected weapon
				atk.setText("ATK : " + Run.player.atk);
				
				if(weapon.getSelectedItem() == "Arrow")
				{
					ImageIcon i = new ImageIcon("src\\Gambar\\arrow.png");
					naroGambar.setIcon(i);
				}
				else if(weapon.getSelectedItem() == "Spear Weapon")
				{
					ImageIcon i = new ImageIcon("src\\Gambar\\spear.png");
					naroGambar.setIcon(i);
				}
				else if(weapon.getSelectedItem( )== "Warrior Weapon")
				{
					ImageIcon i = new ImageIcon("src\\Gambar\\weapon.png");
					naroGambar.setIcon(i);
				}
				else if(weapon.getSelectedItem() == "Keris")
				{
					ImageIcon i = new ImageIcon("src\\Gambar\\keris.png");
					naroGambar.setIcon(i);
				}
				else if(weapon.getSelectedItem() == "Legendary Weapon")
				{
					ImageIcon i = new ImageIcon("src\\Gambar\\legend.png");
					naroGambar.setIcon(i);
				}
			}
		});
		
		
		
		//Drop down button armor
		armor.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int i = 0; i < Run.player.armor.size(); i++) 
				{
					Run.player.armor.get(i).isSelected = false;
				}
				
				Run.player.hp -= previousStatHp;
				Run.player.hp += Run.player.armor.get(armor.getSelectedIndex()).statHp;
				previousStatHp = Run.player.armor.get(armor.getSelectedIndex()).statHp;
				Run.player.armor.get(armor.getSelectedIndex()).isSelected = true; 			//set selected weapon
				hp.setText("HP : " + Run.player.hp);
				
				if(armor.getSelectedItem() == "No Armor")
				{
					ImageIcon i = new ImageIcon("src\\Gambar\\armor.png");
					naroGambarr.setIcon(i);
				}
				else if(armor.getSelectedItem() == "Warrior Armor")
				{
					ImageIcon i = new ImageIcon("src\\Gambar\\warrior armor.png");
					naroGambarr.setIcon(i);
				}
				else if(armor.getSelectedItem( )== "Diamond Armor")
				{
					ImageIcon i = new ImageIcon("src\\Gambar\\diamond armor.png");
					naroGambarr.setIcon(i);
				}
				else if(armor.getSelectedItem() == "Dragon Armor")
				{
					ImageIcon i = new ImageIcon("src\\Gambar\\dragon armor.png");
					naroGambarr.setIcon(i);
				}
				else if(armor.getSelectedItem() == "Legendary Armor")
				{
					ImageIcon i = new ImageIcon("src\\Gambar\\legendary armor.png");
					naroGambarr.setIcon(i);
				}
			}
		});
		
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(easy.isSelected())
				{
					String nm = txt1.getText();
					Run.player.name = nm;
					Gameplay gamePlay = new Gameplay("Easy", Run.player.name, Run.player.hp, Run.player.atk, "Slime");
					gamePlay.setVisible(true);
					dispose();
				}
				else if(hard.isSelected())
				{
					String nm = txt1.getText();
					Run.player.name = nm;
					Gameplay gamePlay = new Gameplay("Hard", Run.player.name, Run.player.hp, Run.player.atk, "Ex Slime");				
					gamePlay.setVisible(true);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(rootPane, "Pilih tingkat kesulitan");
				}
			}
		});
		
		ImageIcon mu = new ImageIcon("src\\Gambar\\on.png");
		music1 = new JButton(mu);
		music1.setBounds(530, 30, 32, 32);
		this.add(music1);
		
		ImageIcon mus = new ImageIcon("src\\Gambar\\mute.png");
		music2 = new JButton(mus);
		music2.setBounds(580, 30, 32, 32);
		this.add(music2);
		
		music1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					AudioInputStream bgm = AudioSystem.getAudioInputStream(new File("src\\Gambar\\kina.wav").getAbsoluteFile());
			        clip = AudioSystem.getClip();
			        clip.open(bgm);
			        clip.start();
			    } catch(Exception ex) {
			        System.out.println("Error with playing sound.");
			    }
			}
		});
		music2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
			        clip.stop();
			    } catch(Exception ex) {
			        System.out.println("Error with playing sound.");
			    }
			}
		});
		
		about= new JButton("About");
		about.setBounds(430,510,100,30);
		this.add(about);
		
		about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					dispose();
					new About().show();
					
				
			}
		});
		
		exit= new JButton("Exit");
		exit.setBounds(550,510,100,30);
		this.add(exit);
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			}
		});
	}
}