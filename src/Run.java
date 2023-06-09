import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Run {
	public static PlayerInfo player = new PlayerInfo();
	
	public static void main(String[] args){
		
		player.weapon.add(new Weapon("Arrow", 0, "src\\Gambar\\arrow.png"));
		player.armor.add(new Armor("No Armor", 0, "src\\Gambar\\armor.png"));
		new Utama().show();
	}

}
