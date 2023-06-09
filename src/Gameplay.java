import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Gameplay extends JFrame{

	JLabel dif,hp,atk,name,nama,ehp,eatk,ename,gambar,gambar1,gambar2;
	JButton atkBtn, healBtn, back;
	String playerName, enemyName;
	int playerHp, playerAtk, enemyHp, enemyAtk;
	Random rand = new Random();
	
	public Gameplay(String mode, String namePlayer, int hpPlayer, int atkPlayer, String _eName)
	{
		
		playerName = namePlayer;
		playerHp = hpPlayer;
		playerAtk = atkPlayer;
		enemyName = _eName;
		
		this.getDefaultCloseOperation();
		this.setSize(675,600);
		this.setLayout(null);
		
		dif = new JLabel("Difficulty : " + mode);
		dif.setBounds(10, 525, 150, 20);
		dif.setFont(new Font("TimesNewRoman",Font.BOLD,14));
		this.add(dif);
		
		back= new JButton("Back");
		back.setBounds(570,510,80,40);
		this.add(back);
		
		hp = new JLabel("HP      : " + playerHp);
		hp.setBounds(20,20,100,15);
		hp.setFont(new Font("TimesNewRoman",Font.BOLD,14));
		this.add(hp);
		
		atk = new JLabel("ATK    : " + playerAtk);
		atk.setBounds(20,55,200,15);
		atk.setFont(new Font("TimesNewRoman",Font.BOLD,14));
		this.add(atk);
		
		name = new JLabel("Name : " + playerName);
		name.setBounds(20,90,300,20);
		name.setFont(new Font("TimesNewRoman",Font.BOLD,14));
		this.add(name);
		
		ImageIcon potion = new ImageIcon("src\\Gambar\\Potion.jpeg");
		healBtn = new JButton(potion);
		healBtn.setBounds(300,35,64,64);
		this.add(healBtn);
		JLabel potionAmount = new JLabel(String.valueOf(Run.player.potion));
		potionAmount.setBounds(367,58,100,64);
		potionAmount.setFont(new Font("TimesNewRoman",Font.BOLD,14));
		this.add(potionAmount);
		
		healBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(Run.player.potion > 0) 					
					{
						playerHp += 100;
						hp.setText("HP      : " + String.valueOf(playerHp));
						Run.player.potion--;
						potionAmount.setText(String.valueOf(Run.player.potion));
					}
				else if(Run.player.potion == 0)
					{
						JOptionPane.showMessageDialog(rootPane, "Tidak ada potion");
					} 
				}
			}
		);
		
		ImageIcon sword = new ImageIcon("src\\Gambar\\Sword.jpeg");
		
		if(mode == "Easy")
			{
				enemyHp = 60;
				int random = rand.nextInt(10);
				enemyHp += random;
				
				ehp = new JLabel("Enemy HP      : " + enemyHp);
				ehp.setBounds(455,20,150,15);
				ehp.setFont(new Font("TimesNewRoman",Font.BOLD,14));
				this.add(ehp);
				
				enemyAtk = 5;
				int random1 = rand.nextInt(10);
				enemyAtk += random1;
				
				eatk = new JLabel("Enemy ATK    : " + enemyAtk);
				eatk.setBounds(455,55,150,15);
				eatk.setFont(new Font("TimesNewRoman",Font.BOLD,14));
				this.add(eatk);
				
				atkBtn= new JButton(sword);
				atkBtn.setBounds(220,35,64,64);
				this.add(atkBtn);
				
				atkBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {

						//Player ke Enemy
						if(Chance(50)) 					//Critical damage
						{
							enemyHp -= (playerAtk * 2);
							JOptionPane.showMessageDialog(rootPane, "You deal Critical damage = " + (playerAtk * 2));						
							ehp.setText("Enemy HP      : " + String.valueOf(enemyHp));
							if(enemyHp <= 0) 
							{		
								enemyHp = 0;
								hp.setText("HP      : " + String.valueOf(playerHp));
								ehp.setText("Enemy HP      : " + String.valueOf(enemyHp));
								JOptionPane.showMessageDialog(rootPane, "Victory");
								GetReward();
								dispose();
								new Utama().show();								
							}
						}
						else 								//Normal damage
						{
							enemyHp -= playerAtk;
							JOptionPane.showMessageDialog(rootPane, "You deal damage = " + playerAtk);						
							ehp.setText("Enemy HP      : " + String.valueOf(enemyHp));
							if(enemyHp <= 0) 
							{		
								enemyHp = 0;
								hp.setText("HP      : " + String.valueOf(playerHp));
								ehp.setText("Enemy HP      : " + String.valueOf(enemyHp));
								JOptionPane.showMessageDialog(rootPane, "Victory");
								GetReward();
								dispose();
								new Utama().show();
							}
						}
							
						//Enemy ke Player
						if(enemyHp > 0) 
						{
							if(Chance(10)) 					//Critical Damage
							{
								
								int dealDamage = enemyAtk;							//get attack sebelumnya
								enemyAtk = 5 + rand.nextInt((10));					
								eatk.setText("Enemy ATK    : " + String.valueOf(enemyAtk));
								playerHp -= (dealDamage * 2);
								JOptionPane.showMessageDialog(rootPane, "Enemy deal Critical damage = " + (dealDamage * 2));						
								hp.setText("HP      : " + String.valueOf(playerHp));
						
								if(playerHp <= 0) 
								{
									playerHp = 0;
									hp.setText("HP      : " + String.valueOf(playerHp));
									//ehp.setText("Enemy HP      : " + String.valueOf(enemyHp));
									JOptionPane.showMessageDialog(rootPane, "Defeat");
									RemoveAllItem();
									dispose();
									new Utama().show();							
								}
							}
							else 								//Normal Damage
							{
								
								int dealDamage = enemyAtk;
								enemyAtk = 5 + rand.nextInt((10));					
								eatk.setText("Enemy ATK    : " + String.valueOf(enemyAtk));
								playerHp -= dealDamage;
													
								hp.setText("HP      : " + String.valueOf(playerHp));	
								JOptionPane.showMessageDialog(rootPane, "Enemy deal damage = "+ dealDamage);	
								
								if(playerHp <= 0) 
								{
									playerHp = 0;
									hp.setText("HP      : " + String.valueOf(playerHp));
									//ehp.setText("Enemy HP      : " + String.valueOf(enemyHp));
									JOptionPane.showMessageDialog(rootPane, "Defeat");
									RemoveAllItem();
									dispose();
									new Utama().show();	
								}
							}
						}
					}
					
					
					boolean Chance(int percentage) 
					{
						boolean a = false;
						
						int chance = rand.nextInt(101);
						if(chance <= percentage) 
						{
							a = true;
						}
						
						return a;
						
					}
					
					boolean NoItem(String itemName) 
					{
						
						boolean b = true;
						
						for(int i = 0; i < Run.player.weapon.size(); i++) 
						{
							if(Run.player.weapon.get(i).weaponName == itemName)
								b = false;
						}
						
						for(int i = 0; i < Run.player.armor.size(); i++) 
						{
							if(Run.player.armor.get(i).armorName == itemName)
								b = false;
						}
						
						return b;
						
					}
					
					void GetReward() 
					{
						
						if(Chance(100)) 					
						{
							
							Run.player.potion += 1;		
							
						}
						
						if(Chance(50))
						{
							if(Chance(50))
							{
								if(NoItem("Warrior Weapon"))
								Run.player.weapon.add(new Weapon("Warrior Weapon", 12, "src\\Gambar\\weapon.png"));
							}
							else
							{
								if(NoItem("Warrior Armor"))
								Run.player.armor.add(new Armor("Warrior Armor", 100, "src\\Gambar\\warrior armor.png"));
							}
						}
						else
						{
							if(Chance(50))
							{
								if(NoItem("Spear Weapon"))
								Run.player.weapon.add(new Weapon("Spear Weapon", 15, "src\\Gambar\\spear.png"));
							}
							else
							{
								if(NoItem("Diamond Armor"))
								Run.player.armor.add(new Armor("Diamond Armor", 150, "src\\Gambar\\diamond armor.png"));
							}
						}
					}
					
					
					
					void RemoveAllItem() 
					{
						
						Run.player.weapon.clear();
						Run.player.armor.clear();
						Run.player.atk = 6;
						Run.player.hp = 100;
						
						Run.player.weapon.add(new Weapon("Arrow", 0, "src\\Gambar\\arrow.png"));
						Run.player.armor.add(new Armor("No Armor", 0, "src\\Gambar\\armor.png"));
						
					}
					
				});
				
				ename = new JLabel("Enemy Name : " + enemyName);
				ename.setBounds(455,90,150,15);
				ename.setFont(new Font("TimesNewRoman",Font.BOLD,14));
				this.add(ename);
			
				ImageIcon gi = new ImageIcon("src\\Gambar\\GI.png");
				gambar= new JLabel(gi,JLabel.CENTER);
				gambar.setSize(700,313);
				gambar.setBounds(0,160,670,300);
				this.add(gambar);
				
				ImageIcon kirby = new ImageIcon("src\\Gambar\\Kirby.png");
				gambar1= new JLabel(kirby);
				gambar1.setBounds(10,20,300,300);
				this.add(gambar1);
				
				ImageIcon slime = new ImageIcon("src\\Gambar\\Slime.png");
				gambar2= new JLabel(slime);
				gambar2.setBounds(355,80,330,287);
				this.add(gambar2);
				
				gambar.add(gambar1);
				gambar.add(gambar2);
			}
		
		//Hard Mode
		else if(mode == "Hard")
			{
				enemyHp = 200;
				int random = rand.nextInt(150);
				enemyHp += random;
				
				ehp = new JLabel("Enemy HP      : " + enemyHp);
				ehp.setBounds(455,20,150,15);
				ehp.setFont(new Font("TimesNewRoman",Font.BOLD,14));
				this.add(ehp);
				
				enemyAtk = 20;
				int random1 = rand.nextInt(25);
				enemyAtk += random1;
				
				eatk = new JLabel("Enemy ATK    : " + enemyAtk);	
				eatk.setBounds(455,55,150,15);
				eatk.setFont(new Font("TimesNewRoman",Font.BOLD,14));
				this.add(eatk);
				
				atkBtn= new JButton(sword);
				atkBtn.setBounds(220,35,64,64);
				this.add(atkBtn);
				
				atkBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						//Player ke Enemy
						if(Chance(40)) 					//Critical damage
						{
							enemyHp -= (playerAtk * 2);
							JOptionPane.showMessageDialog(rootPane, "You deal Critical damage = " + (playerAtk * 2));						
							ehp.setText("Enemy HP      : " + String.valueOf(enemyHp));
							if(enemyHp <= 0) 
							{		
								enemyHp = 0;
								hp.setText("HP      : " + String.valueOf(playerHp));
								ehp.setText("Enemy HP      : " + String.valueOf(enemyHp));
								JOptionPane.showMessageDialog(rootPane, "Victory");
								GetReward();
								dispose();
								new Utama().show();								
							}
						}
						else 								//Normal damage
						{
							enemyHp -= playerAtk;
							JOptionPane.showMessageDialog(rootPane, "You deal damage = " + playerAtk);						
							ehp.setText("Enemy HP      : " + String.valueOf(enemyHp));
							if(enemyHp <= 0) 
							{		
								enemyHp = 0;
								hp.setText("HP      : " + String.valueOf(playerHp));
								ehp.setText("Enemy HP      : " + String.valueOf(enemyHp));
								JOptionPane.showMessageDialog(rootPane, "Victory");
								GetReward();
								dispose();
								new Utama().show();
							}
						}
						
						
						//Enemy ke Player
						if(enemyHp > 0) 
						{
							if(Chance(30)) 					//Critical Damage
							{
								int dealDamage = enemyAtk;							//get attack sebelumnya
								enemyAtk = 20 + rand.nextInt((25));					
								eatk.setText("Enemy ATK    : " + String.valueOf(enemyAtk));
								playerHp -= (dealDamage * 2);
								JOptionPane.showMessageDialog(rootPane, "Enemy deal Critical damage = " + (dealDamage * 2));					
								hp.setText("HP      : " + String.valueOf(playerHp));						
								if(playerHp <= 0) 
								{
									playerHp = 0;
									hp.setText("HP      : " + String.valueOf(playerHp));
									//ehp.setText("Enemy HP      : " + String.valueOf(enemyHp));
									JOptionPane.showMessageDialog(rootPane, "Defeat");
									RemoveAllItem();
									dispose();
									new Utama().show();
								}
							}
							else 								//Normal Damage
							{
								int dealDamage = enemyAtk;							//get attack sebelumnya
								enemyAtk = 20 + rand.nextInt((25));					
								eatk.setText("Enemy ATK    : " + String.valueOf(enemyAtk));
								playerHp -= (dealDamage);
								JOptionPane.showMessageDialog(rootPane, "Enemy deal damage = " + (dealDamage));						
								hp.setText("HP      : " + String.valueOf(playerHp));						
								if(playerHp <= 0) 
								{
									playerHp = 0;
									hp.setText("HP      : " + String.valueOf(playerHp));
									//ehp.setText("Enemy HP      : " + String.valueOf(enemyHp));
									JOptionPane.showMessageDialog(rootPane, "Defeat");
									RemoveAllItem();
									dispose();
									new Utama().show();
								}
							}
						}
					}
					
					
					boolean Chance(int percentage) 
					{
						boolean c = false;
						
						int chance = rand.nextInt(101);
						if(chance <= percentage) 
						{
							c = true;
						}
						
						return c;
						
					}
					
					boolean NoItem(String itemName) 
					{
						
						boolean d = true;
						
						for(int i = 0; i < Run.player.weapon.size(); i++) 
						{
							if(Run.player.weapon.get(i).weaponName == itemName)
								d = false;
						}
						
						for(int i = 0; i < Run.player.armor.size(); i++) 
						{
							if(Run.player.armor.get(i).armorName == itemName)
								d = false;
						}
						
						return d;
						
					}
					
					void GetReward() 
					{
						
						if(Chance(100)) 					//get potion 100 %
						{
							
							Run.player.potion += 1;		//get 1 potion
							
						}
						
						//Get Equipment
						if(Chance(50))
						{
							if(Chance(50))
							{
								if(NoItem("Keris"))
								Run.player.weapon.add(new Weapon("Keris", 50, "src\\Gambar\\keris.png"));
							}
							else
							{
								if(NoItem("Dragon Armor"))
								Run.player.armor.add(new Armor("Dragon Armor", 200, "src\\Gambar\\dragon armor.png"));
							}
						}
						else
						{
							if(Chance(50))
							{
								if(NoItem("Legendary Weapon"))
								Run.player.weapon.add(new Weapon("Legendary Weapon", 100, "src\\Gambar\\legend.png"));
							}
							else
							{
								if(NoItem("Legendary Armor"))
								Run.player.armor.add(new Armor("Legendary Armor", 400, "src\\Gambar\\legendary armor.png"));								}
							}
					}
					
					void RemoveAllItem() 
					{
						
						Run.player.weapon.clear();
						Run.player.armor.clear();
						Run.player.atk = 6;
						Run.player.hp = 100;
						
						Run.player.weapon.add(new Weapon("Arrow", 0, "src\\Gambar\\arrow.png" ));
						Run.player.armor.add(new Armor("No Armor", 0, "src\\Gambar\\armor.png"));
						
					}
					
				});
				
				ename = new JLabel("Enemy Name : " + enemyName);
				ename.setBounds(455,90,200,20);
				ename.setFont(new Font("TimesNewRoman",Font.BOLD,14));
				this.add(ename);
				
				ImageIcon gi = new ImageIcon("src\\Gambar\\GI.png");
				gambar= new JLabel(gi,JLabel.CENTER);
				gambar.setSize(700,313);
				gambar.setBounds(0,150,670,300);
				this.add(gambar);
				
				ImageIcon kirby = new ImageIcon("src\\Gambar\\Kirby.png");
				gambar1= new JLabel(kirby);
				gambar1.setBounds(15,20,300,300);
				this.add(gambar1);
				
				ImageIcon slime = new ImageIcon("src\\Gambar\\slimee.png");
				gambar2= new JLabel(slime);
				gambar2.setBounds(360,30,300,300);
				this.add(gambar2);
				
				gambar.add(gambar1);
				gambar.add(gambar2);
			}

		
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Utama().show();
			}
		});
	}
}