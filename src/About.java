import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame{
	
	JLabel bg,cr,a,b;
	JButton back;
	
	public About()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(675,600);
		setLayout(null);
		
		ImageIcon img = new ImageIcon("Picture\\white.jpg");
		bg = new JLabel ("",img,JLabel.CENTER);
		bg.setSize(600, 400);
		add(bg);
		
		cr = new JLabel("Creator");
		cr.setBounds(300,40,100,25);
		cr.setFont(new Font("TimesNewRoman",Font.BOLD,17));
		bg.add(cr);
		
		a = new JLabel("Jonathan Mario Dwi Priyadi (672020162)");
		a.setBounds(200,100,300,25);
		a.setFont(new Font("TimesNewRoman",Font.BOLD,15));
		bg.add(a);
		
		b = new JLabel("Jessica Patricia Rahardjo (672020179)");
		b.setBounds(205,150,300,25);
		b.setFont(new Font("TimesNewRoman",Font.BOLD,15));
		bg.add(b);
		
		back= new JButton("Back");
		back.setBounds(10,10,70,30);
		bg.add(back);
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Utama().show();
			}
		});
	}
	
}