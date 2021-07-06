	import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameState {
	public File map1 = new File("map1.txt");
	public File map2 = new File("map2.txt");
	public File map3 = new File("map3.txt");
	public File map4 = new File("map4.txt");
	public File level = this.map1;
	public File save = new File("save.txt");
	public Player player;
	public double totalPossiblePoints;
	public double numOfItems;
	public ArrayList<Tile> goodsList;
	public Map map;
	public ArrayList<Tile> tileList;

	// loads a file to create a map, returns a list of all objects on the map

	@SuppressWarnings("finally")
	public ArrayList<Tile> loadLevel(Map theMap){
		this.map = theMap;
		String line ="";
		int j = 0;
		this.totalPossiblePoints =0;
		this.numOfItems = 0;
		this.goodsList = new ArrayList<Tile>();
		this.tileList = new ArrayList<Tile>();
		try(Scanner inScanner = new Scanner(this.level)){
			while(inScanner.hasNextLine()){

				line = inScanner.nextLine();
				for(int i =0; i < line.length(); i++){
					if(line.charAt(i) == 'D'){
						DragonRange range = new DragonRange((i*40) -80, (i*40)-80, map);
						tileList.add(range);
						Dragon dragon = new Dragon(i*40, j*40,map,false);
						tileList.add(dragon);

					}else if(line.charAt(i) == 'T'){
						Treasure treasure = new Treasure(i*40, j*40, map);
						tileList.add(treasure);
						this.totalPossiblePoints += 1; //gold weight 1
						this.numOfItems += 1;

					}else if(line.charAt(i) == 'P'){
						Player player = new Player(i*40, j*40, map);
						this.player = player;
						tileList.add(player);

					}
					else if(line.charAt(i)=='W'){
						Wall w = new Wall(i*40,j*40);
						tileList.add(w);
					}
					else if(line.charAt(i)=='R'){
						Rock r = new Rock(i*40,j*40);
						tileList.add(r);
						this.totalPossiblePoints += 10; //diamond weight 10
						this.numOfItems += 1;
					}
					else if(line.charAt(i)=='B'){
						Ruby b = new Ruby(i*40,j*40);
						tileList.add(b);
						this.totalPossiblePoints += 5; //ruby weight 5
						this.numOfItems += 1;
					}
				}
				j++;
			}
			inScanner.close();
		}finally{
			return tileList;
		}

	}
	
	public double getTotalPossiblePoints(){
	return totalPossiblePoints;
	}
	
	public double getNumOfItems(){
		return this.numOfItems;
	}

	public ArrayList<Tile> levelUp(){
		if(this.level == this.map1){
			this.level = this.map2;
		}else if(this.level == this.map2){
			this.level = this.map3;
		}else if(this.level == this.map3){
			this.level = this.map4;
		}else if(this.level == this.map4){
			this.map.getGameFrame().setVisible(false);
		}

		return loadLevel(this.map);
	}

	public void loadSave(){
		String line ="";
		 ArrayList<Tile> temp = new ArrayList();
		try(Scanner inScanner = new Scanner(this.save)){
				line = inScanner.nextLine();
				if(Integer.parseInt(line)== 1){
					this.level = this.map1;
				}
				else if(Integer.parseInt(line)== 2){
					this.level = this.map2;
				}
				else if(Integer.parseInt(line)== 3){
					this.level = this.map3;
				}
				else{
					this.level = this.map4;
				}
				this.map.setTiles(loadLevel(this.map));
				line = inScanner.nextLine();
				this.map.getPlayer().points = Double.parseDouble(line);
				line = inScanner.nextLine();
				this.map.totalPoints = Double.parseDouble(line);
				line = inScanner.nextLine();
			inScanner.close();
		}finally{
			return;
		}
	}
	
	public void saveGame(){
		FileWriter fw;
		try {
			fw = new FileWriter("save.txt");
			int levelNum = 1;
			if(this.level == this.map1){
				levelNum = 1;
			}
			else if(this.level == this.map2){
				levelNum = 2;
			}
			else if(this.level == this.map3){
				levelNum = 3;
			}
			else {
				levelNum = 4;
			}
			
			
			fw.write(levelNum + "\n" + this.map.getPlayer().points + "\n" + this.map.totalPoints);
	    	
	     
	    	fw.close();
		} catch (IOException exception) {
			// TODO Auto-generated catch-block stub.
			exception.printStackTrace();
		}
    	 
	}
	
}
