package video_Game.presentationLayer;


import java.security.PrivilegedActionException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;


import video_Game.businessLayer.Turn;
import video_Game.dataAccess.entities.abstracts.Character;
import video_Game.dataAccess.entities.abstracts.Opponent;
import video_Game.dataAccess.entities.abstracts.Weapon;
import video_Game.dataAccess.entities.characters.Hunter;
import video_Game.dataAccess.entities.characters.Knight;
import video_Game.dataAccess.entities.characters.Squire;
import video_Game.dataAccess.entities.characters.Villager;
import video_Game.dataAccess.entities.opponents.Goblin;
import video_Game.dataAccess.entities.opponents.Orc;
import video_Game.dataAccess.entities.opponents.Slime;
import video_Game.dataAccess.entities.opponents.Wolf;
import video_Game.dataAccess.entities.weapons.Bow;
import video_Game.dataAccess.entities.weapons.Spear;
import video_Game.dataAccess.entities.weapons.Sword;
import video_Game.exceptions.NotAUniqueNameException;

public class TBGame {
	private boolean isGameOver = false; // IMMUTABLE VARIABLE. 
	private ArrayList<Character> characters;
	private ArrayList<Opponent> opponents;
	private Deque<Turn> turnOrder;
	private Random random;
	private Initializer initializer;
	private Menu menu;
	private int opponentIds;
	private int currentMove = 1;
	private String opponentAction;
	
	public TBGame() {
		turnOrder = new LinkedList<>();
		random = new Random();
		initializer = new Initializer();
		menu = new Menu();
		characters = new ArrayList<Character>();
		opponents = new ArrayList<Opponent>();
		opponentIds = 0;
	}
	//Game play here:
	public void newGame() {
		System.out.println("Welcome to TBGame!\n");
		
		initializer.prepareRandomOpponents(opponents);
		showOpponents();
		int numberOfCharacter = menu.getNumberOfCharacter();
		ArrayList<String> characterNames = menu.getCharacterNames(numberOfCharacter);
		characters = initializer.prepareAndGetCharacters(characterNames);
		showCharacters();
		initializer.prepareTurnOrder(turnOrder);
		menu.showTurnsOrder();
		
		while(!isGameOver) {
			menu.turnManager(turnOrder.remove());
		}
		//Game over when while loop finish.
		
	}
	// This method is a necessary for opponent stats: 
	public void showOpponents() {
		for(Opponent opponent:opponents)
			System.out.println(opponent.getDetails());
	}
	// This method is a necessary for character stats:
	public void showCharacters() {
		for(Character character:characters)
			System.out.println(character.getDetails());
	}
	// For can write "Which move?" (For example Move "3" - ....):
	public void increaseMoveNumber() {
		 currentMove++;
	}
	
	
	private class Menu	{
		private Scanner scanner;
		
		public Menu() {
			scanner = new Scanner(System.in);
		}
		//get character names:
		public ArrayList<String> getCharacterNames(int numberOfCharacters) {
			ArrayList<String> characterNames = new ArrayList<>();
			for(int i=0; i<numberOfCharacters; i++) {
				while(true) {
					try {
						System.out.print("Enter name for your "+(i+1)+". character: ");
						String name = scanner.nextLine();
						if(characterNames.contains(name))
							throw new NotAUniqueNameException(name+" is not unique.");
						characterNames.add(name);
						break;
					} catch (NotAUniqueNameException e) {
						e.printStackTrace();
					}
				}
			}
			return characterNames;
		}
		//Opponent order:
		private void getMoveOrderOpponent(String opponentAction) {
			System.out.print("Move" + currentMove + "-" + turnOrder.getFirst() + opponentAction);
		}
		//enter number of character:
		private int getNumberOfCharacter() {
			System.out.print("Please enter the number of characters to create:");
			while(true) {
				try {
					String input = scanner.nextLine();
					int numberOfCharacter = Integer.parseInt(input);
					if (numberOfCharacter < 1 || numberOfCharacter > 4)
						throw new IllegalStateException();
					
					return numberOfCharacter;
				}
				catch (Exception e) {
					System.out.print("Maximum 4 characters can be created: ");
				}
			}
		}
		//Turn Order is showing:
		public void showTurnsOrder() {
			System.out.print("*** Turn Order: ");
			Iterator<Turn> iterator = turnOrder.iterator();
			while (iterator.hasNext()) {
			    Turn orderTurn = iterator.next();
			    System.out.print(orderTurn.getIdOrName()+", ");
			}
			System.out.println();
		}
		// Turn manager:
		public void turnManager(Turn turn) {
			for(Character<Weapon> character: characters)
				if(character.getName().equalsIgnoreCase(turn.getIdOrName())) {
					characterTurn(character, turn);
					turnOrder.add(turn);
				}
			
			for(Opponent opponent:opponents)
				if(opponent.getName().equalsIgnoreCase(turn.getIdOrName())) {
					opponentTurn(opponent, turn);
					turnOrder.add(turn);
				}
		}
		// Random opponent action:
		private void opponentTurn(Opponent opponent, Turn turn) {
			switch(random.nextInt(3)){ 
			case 0:
				opponentAttack(opponent, turn);
				break;
			case 1:
				opponentGuard(opponent,turn);
				break;
			case 2:
				opponentSpecial(opponent,turn);
				break;
			}
			increaseMoveNumber();
			
		}
		
		private void opponentAttack(Opponent opponent,Turn turn) {
			// Random character is selected.
			Character<Weapon> character = characters.get(random.nextInt(characters.size()));
			
			// Attacked to the character.
			int damageAmount = opponent.attack(character, turn);
			
			// Showing result.
			System.out.println("Move "+currentMove+ " - "+opponent.getName() +" attacks "+ character.getName()+". Deals "+damageAmount+" damage.");
			System.out.println(character.getStats());
		}

		private void opponentGuard(Opponent opponent,Turn turn) {
			// Opponent is guarded.
			opponent.guard();
			
			// Attack modifier should be reset.
			turn.resetAttackModifier();
			
			// Showing result.
			System.out.println("Move "+currentMove+ " - "+opponent.getName()+ " starts guarding.");
		}
		
		private void opponentSpecial(Opponent opponent,Turn turn) {
			Character<Weapon> character = characters.get(random.nextInt(characters.size()));
			
			int damageAmount = opponent.attack(character, turn);
			
			System.out.println("Move "+ currentMove + " - "+opponent.getName()+" special used.");
			
		}
		
		//Turn Order is character:
		private void characterTurn(Character character, Turn turn) {
			switch(turnForCharacter(character)){ 
			case 1:
				characterPunch(character, turn);
				break;
			case 2:
				characterAttackWithWeapon(character,turn);
				break;
			case 3:
				characterGuard(character,turn);
				break;
			case 4:
				characterSpecialMove(character,turn);
	            break;
	        case 5:
	        	character.run();
	            break;
	        default:
	            System.out.println("Invalid choice. Please try again.");
	            break;
			}
			increaseMoveNumber();
		}
		
		private Opponent chooseAnOpponent() {
			System.out.print("Please enter an opponent id: ");
			while(true) {
				try {
					String input = scanner.nextLine();
					int opponentId = Integer.parseInt(input);
					
					for(Opponent opponent:opponents)
						if (opponent.getId() == opponentId)
							return opponent;
					
					throw new NoSuchElementException();
				}
				catch (Exception e) {
					System.out.print("Enter correct input: ");
				}
			}

		}
		private int chooseAnAttackType(String attacks) {
			System.out.println("Please select weapon attack type "+attacks);
			int attackType = scanner.nextInt();
			return attackType;
		}
		
		private void characterPunch(Character character, Turn turn) {
			// Opponents is selected.
			Opponent opponent = chooseAnOpponent();
			
			// Character punches the opponent.
			int damageAmount = character.punch(opponent, turn);
			
			//Showing result.
			System.out.println("Move "+currentMove+ " - "+character.getName() +" punches "+ opponent.getName()+". Deals "+damageAmount+" damage.");
		}
		private void characterAttackWithWeapon(Character character, Turn turn) {
			int weaponAttackType = chooseAnAttackType(character.getWeapon().getWeaponAttackTypes());
			Opponent opponent = chooseAnOpponent();
			int damageAmount = character.attackWithWeapon(opponent, turn, weaponAttackType);
			System.out.println("Move "+currentMove+ " Result: "+character.getName() +" attacks "+ opponent.getName()+". Deals "+damageAmount+" damage.");
		}
		
		
		
		private void characterGuard(Character character, Turn turn) {
			// Opponent is guarded.
			character.guard();
						
			// Attack modifier should be reset.
			turn.resetAttackModifier();
						
			// Showing result.
			System.out.println("Move "+currentMove+ " - "+character.getName()+ " starts guarding.");
		}
		
		private void characterSpecialMove(Character character, Turn turn) {
			character.specialAction();
		}
		// Game Over:
		public void gameOver(boolean isGameOver) {
			gameOver(isGameOver);
		}
		
		private int turnForCharacter(Character character)	{
			int choice;
			System.out.println(" – It is the turn of " + character.getName());
			String actions = ("[1] Punch,[2] Attack with weapon,[3] Guard,[4] Special Action,[5] Run");
			StringTokenizer st = new StringTokenizer(actions, ",");
			while (st.hasMoreTokens()) {
	            String token = st.nextToken();
	            System.out.println(token.trim());
			}
			System.out.println("Please select an option:");
			choice = scanner.nextInt();
			return choice;
		}
	}
	
	
	
	
	private class Initializer{
		// Opponents have random opponent type:
		public void prepareRandomOpponents(ArrayList<Opponent> randomOpponents) {
			for (int i=0; i<random.nextInt(4)+1; i++) {
				opponentIds += 1;
				switch (random.nextInt(4)) {
				case 0-> randomOpponents.add(new Slime(opponentIds));
				case 1-> randomOpponents.add(new Goblin(opponentIds));
				case 2-> randomOpponents.add(new Orc(opponentIds));
				case 3-> randomOpponents.add(new Wolf(opponentIds));
				default-> throw new IllegalArgumentException();	
				}
			}
		}
		//Characters have random opponent type: 
		@SuppressWarnings("unchecked")
		public ArrayList<Character> prepareAndGetCharacters(ArrayList<String> characterNames) {
			ArrayList<Character> initialCharacters = new ArrayList<>();
			for (int i=0; i<characterNames.size(); i++) {
				String name = characterNames.get(i);
				switch (random.nextInt(4)) {
				case 0-> initialCharacters.add(new Hunter(name));
				case 1-> initialCharacters.add(new Knight(name));
				case 2-> initialCharacters.add(new Squire(name));
				case 3-> initialCharacters.add(new Villager(name));
				default-> throw new IllegalArgumentException();	
				}
			}
			return initialCharacters;
		}
		// TURN ORDER
		public void prepareTurnOrder(Deque<Turn> turnOrder) {
			turnOrder.clear();
			ArrayList<Turn> turnsArrayList = new ArrayList<Turn>();
			System.out.println("Character nums: "+characters.size());
			for(Character<Weapon> character:characters) 
				turnsArrayList.add(new Turn(character.getName()));
			for(Opponent opponent: opponents)
				turnsArrayList.add(new Turn(opponent.getName()));
			
			Collections.sort(turnsArrayList, new Comparator<Turn>() {
				@Override
			    public int compare(Turn turn1, Turn turn2) {
			        return Integer.compare(getSpeedOfTurn(turn2), getSpeedOfTurn(turn1));
			    }
			});
			for(int i=0; i<turnsArrayList.size(); i++) 
				turnOrder.add(turnsArrayList.get(i));
		}
		// Turn Order must be according to speed stats:  
		private int getSpeedOfTurn(Turn turn) {
			for(Character<Weapon> character:characters) 
				if(character.getName().equalsIgnoreCase(turn.getIdOrName()))
					return character.getSpeed();
			for(Opponent opponent: opponents)
				if(opponent.getName().equalsIgnoreCase(turn.getIdOrName()))
					return opponent.getSpeed();
			throw new NoSuchElementException("Turn owner does not exceed: " + turn.getIdOrName());
		}
			
	}
}
