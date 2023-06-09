import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.sun.net.httpserver.Authenticator.Result;
import java.util.*;
import java.io.*;

public class tgs extends JFrame {
	
	JTextArea txt1,txt2;
	JButton open,save,clear,proses;
	JToggleButton wrap;
	ButtonGroup bg = new ButtonGroup();
	JRadioButton backspace,palindrom,cecar,its;
	String txt;

	public tgs() 
	{
		this.getDefaultCloseOperation();
		this.setSize(550,725);
		this.setTitle("Program Nge-Alay");
		this.setLayout(null);
		this.setResizable(false);
		
		JLabel jdl = new JLabel(": : ALAY & RAHASIA2AN YUK : :");
		jdl.setBounds(120, 20, 300, 20);
		jdl.setFont(new Font("TimesNewRoman",Font.BOLD,20));
		this.add(jdl);
		
		txt1 = new JTextArea();
		txt1.setBounds(40,75,455,200);
		txt1.setFont(new Font("TimesNewRoman",Font.BOLD,17));
		this.add(txt1);
		
		txt2 = new JTextArea();
		txt2.setBounds(40,295,455,200);
		txt2.setFont(new Font("TimesNewRoman",Font.BOLD,17));
		txt2.setEditable(false);
		this.add(txt2);
		
		open = new JButton("OPEN");
		open.setBounds(50,515,80,35);
		this.add(open);
		
		open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e){
				JFileChooser open = new JFileChooser();
				int pilih = open.showOpenDialog(txt1);
				int buka = pilih;
				JFileChooser file = open;
				if (buka == JFileChooser.APPROVE_OPTION){
					txt1.setText("");
					try{
						Scanner scan = new Scanner(new FileReader(file.getSelectedFile().getPath()));
						while (scan.hasNext())
							txt1.append(scan.nextLine());
					} catch (Exception ex){
						System.out.println(ex.getMessage());
					}
				}
			}
		});
		
		ItemListener wrapbtn = new ItemListener(){
		    public void itemStateChanged(ItemEvent itemEvent) {
		        int wb = itemEvent.getStateChange();
		        if (wb == ItemEvent.SELECTED) {
		            txt1.setLineWrap(true);
		        } else {
		            txt1.setLineWrap(false);
		        }
		    }
		};
		
		wrap = new JToggleButton("WRAP");
		wrap.setBounds(50,570,80,35);
		this.add(wrap);
		wrap.addItemListener(wrapbtn);
		
		save = new JButton("SAVE");
		save.setBounds(155,515,80,35);
		this.add(save);
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e){
				JFileChooser save = new JFileChooser();
				int pilih = save.showOpenDialog(txt1);
				int simpan = pilih;
				JFileChooser file = save;
				if (simpan == JFileChooser.APPROVE_OPTION){
					try {
						BufferedWriter out = new BufferedWriter(new FileWriter(file.getSelectedFile().getPath()));
						out.write(txt1.getText());
						out.close();
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
				}
			});
		
		clear = new JButton("CLEAR");
		clear.setBounds(155,570,80,35);
		this.add(clear);
		
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txt1.setText("");
				txt2.setText("");
				bg.clearSelection(); 
			}
		});
		
		backspace = new JRadioButton("Backspace");
		backspace.setBounds(270,520,100,25);
		this.add(backspace);
		
		palindrom = new JRadioButton("Palindrom");
		palindrom.setBounds(270,575,100,25);
		this.add(palindrom);
		
		cecar = new JRadioButton("Cecar");
		cecar.setBounds(390,520,70,25);
		this.add(cecar);
		
		its = new JRadioButton("Int to String");
		its.setBounds(390,575,120,25);
		this.add(its);
		
		bg.add(backspace);
		bg.add(palindrom);
		bg.add(cecar);
		bg.add(its);
		
		proses = new JButton("PROSES");
		proses.setBounds(335,620,100,50);
		this.add(proses);
		
		proses.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(backspace.isSelected())
				{
					txt = txt1.getText();
					int panjang1 = 0, panjang2 = 0, vokal1 = 0, vokal2 = 0;
					panjang1 = txt.length();
					
					StringBuilder st = new StringBuilder();
					int j =0;
					
					for(int i = 0 ; i<txt.length();i++)
					{
						char c = txt.charAt(i);
						if(c == '<')
						{
							st.deleteCharAt(j-1); 
							j--;
						}
						else
						{
							st.append(c); 
							j++;
						}
						
						char huruf1 = Character.toLowerCase(txt.charAt(i));
						if (huruf1 == 'a' || huruf1 == 'i' || huruf1 == 'u' || huruf1 == 'e' || huruf1 == 'o')
						{
							vokal1++;
						}
					}

					for(int i = 0 ; i<st.length();i++)
					{
						char huruf2 = Character.toLowerCase(st.charAt(i));
						if (huruf2 == 'a' || huruf2 == 'i' || huruf2 == 'u' || huruf2 == 'e' || huruf2 == 'o')
						{
							vokal2++;
						}
					}
					
					panjang2 = st.length();
					
					txt2.setText(st.toString());
					txt2.append("\n\nJumlah Karakter Kalimat Asli ada " + panjang1);
					txt2.append("\nJumlah Huruf Vokal Kalimat Asli ada " + vokal1);
					txt2.append("\n\nJumlah Karakter Setelah dienkripsi ada " + panjang2);
					txt2.append("\nJumlah Huruf Vokal Setelah dienkripsi ada " + vokal2);
				}
				else if(palindrom.isSelected())
				{
					String x = "";
					txt = txt1.getText();
					int a = txt.length();
					
					for ( int i = a - 1; i >= 0; i-- )  
					{
						x = x + txt.charAt(i);  
					}
				    if (txt.equals(x)) 
				    {
				    	txt2.setText("TRUE");  
				    }
				    else  
				    {
				    	txt2.setText("FALSE");
				    }
				}
				else if(cecar.isSelected())
				{
					txt = txt1.getText();
					int panjang1 = 0, panjang2 = 0, vokal1 = 0, vokal2 = 0;
					panjang1 = txt.length();
					int a = 3 + 26;
					String out="";
					
					StringBuilder ss = new StringBuilder();
					for (int i = 0; i < txt.length(); i ++)
					{
						char huruf1 = Character.toLowerCase(txt.charAt(i));
						if (huruf1 == 'a' || huruf1 == 'i' || huruf1 == 'u' || huruf1 == 'e' || huruf1 == 'o')
						{
							vokal1++;
						}
					}

					for(int i = 0; i < txt.length(); i ++)
					{
						char c = txt.charAt(i);

			            if (Character.isAlphabetic(c)) {
			                char ch = Character.isUpperCase(c) ? 'A' : 'a';
			                out += (char) ((c - ch + a) % 26 + ch);
			            } else {
			                out += c;
			            }
			            
			            char huruf2 = Character.toLowerCase(out.charAt(i));
						if (huruf2 == 'a' || huruf2 == 'i' || huruf2 == 'u' || huruf2 == 'e' || huruf2 == 'o')
						{
							vokal2++;
						}
					}
					
					panjang2 = out.length();
					
					txt2.setText(out); 
					txt2.append("\n\nJumlah Karakter Kalimat Asli ada " + panjang1);
					txt2.append("\nJumlah Huruf Vokal Kalimat Asli ada " + vokal1);
					txt2.append("\n\nJumlah Karakter Setelah dienkripsi ada " + panjang2);
					txt2.append("\nJumlah Huruf Vokal Setelah dienkripsi ada " + vokal2);
				}
				else if(its.isSelected())
				{
					txt = txt1.getText();
					int panjang1 = 0,panjang2 = 0,vokal1=0,vokal2=0;
					panjang1 = txt.length();
					StringBuilder st = new StringBuilder();
					
					try
					{
						int b = Integer.parseInt(txt);
						
				        String[] satuan = {"", " Satu", " Dua", " Tiga", " Empat", " Lima", " Enam", " Tujuh", " Delapan", " Sembilan"};
				        String[] puluhan = {"", " Belas", " Dua Puluh", " Tiga Puluh", " Empat Puluh", " Lima Puluh", " Enam Puluh", " Tujuh Puluh", " Delapan Puluh", " Sembilan Puluh"};
				        String[] ribuan = {"", " Seribu", " Dua Ribu", " Tiga Ribu", " Empat Ribu", " Lima Ribu", " Enam Ribu", " Tujuh Ribu", " Delapan Ribu", " Sembilan Ribu"};
				        String[] ratusan = {"", " Seratus", " Dua Ratus", " Tiga Ratus", " Empat Ratus", " Lima Ratus", " Enam Ratus", " Tujuh Ratus", " Delapan Ratus", " Sembilan Ratus"};
				        
				        int a = b;
				        if (a <= 9999 && a >= 0) {
				            int ribu, ratus, puluh, satu;
				            ribu = a / 1000;
				            ratus = (a % 1000) / 100;
				            puluh = (a % 100) / 10;
				            satu = a % 10;
				            if (puluh == 1) {
				            	if (satu == 1) {
				                    st.append(ribuan[ribu] + ratusan[ratus] + " Se" + puluhan[puluh]);
				                }
				                else if(satu==0){
				                	st.append(ribuan[ribu] + ratusan[ratus] + " Sepuluh");
				                }
				                else {
				                	st.append(ribuan[ribu] + ratusan[ratus] + satuan[satu] + puluhan[puluh]);
				                }
				                
				            } else {
				            	st.append(ribuan[ribu] + ratusan[ratus] + puluhan[puluh] + satuan[satu]);
				            }
				        }
					}catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, "Memasukan Angka 1 - 9999", "", JOptionPane.INFORMATION_MESSAGE);
					}

					for (int i = 0; i < txt.length(); i ++)
					{
						char huruf1 = Character.toLowerCase(txt.charAt(i));
						if (huruf1 == 'a' || huruf1 == 'i' || huruf1 == 'u' || huruf1 == 'e' || huruf1 == 'o')
							{
								vokal1++;
							}
					}
					
					for (int i = 0; i < st.length(); i ++)
					{
						char huruf2 = Character.toLowerCase(st.toString().charAt(i));
						if (huruf2 == 'a' || huruf2 == 'i' || huruf2 == 'u' || huruf2 == 'e' || huruf2 == 'o')
							{
								vokal2++;
							}
					}
					
					panjang2 = st.length();
					
					txt2.setText(st.toString());;
					txt2.append("\n\nJumlah Karakter Kalimat Asli ada " + panjang1);
					txt2.append("\nJumlah Huruf Vokal Kalimat Asli ada " + vokal1);
					txt2.append("\n\nJumlah Karakter Setelah dienkripsi ada " + panjang2);
					txt2.append("\nJumlah Huruf Vokal Setelah dienkripsi ada " + vokal2 );
				}
			}
		});
	}
	
	public static void main(String[] args)
	{
		new tgs().show();
	}
	
}
