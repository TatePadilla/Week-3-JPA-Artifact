

import java.util.List;
import java.util.Scanner;

import controller.ListItemHelper;
import model.ListItem;

/* Now the exciting part, the startProgram file, which contains the user prompts as well as the user interaction menu.
 * Once the user selects an option, the StartProgram file passes that option over to the listItemHelper file to actually
 * execute the logic of each option.
 */

public class StartProgram {

		static Scanner in = new Scanner(System.in);
		static ListItemHelper lih = new ListItemHelper();

		private static void addAnItem() {
			// TODO Auto-generated method stub
			System.out.print("Enter a Instrument Class: ");
			String instrumentClass = in.nextLine();
			System.out.print("Enter an Instrument: ");
			String instrument = in.nextLine();
			ListItem toAdd = new ListItem(instrumentClass, instrument);
			lih.insertItem(toAdd);
		}

		private static void deleteAnItem() {
			/*
			How it works: The user types in a store and item. We create a new item with that
			information to pass over to the deleteItem method in the List item helper.
			Once inside the deleteItem method, we search for the item with that store and item the user entered in. 
			Once we find one result, we ask the list item helper to remove it.
			 */
			System.out.print("Enter the instrument class to delete: ");
			String instrumentClass = in.nextLine();
			System.out.print("Enter the instrument to delete: ");
			String instrument = in.nextLine();
			ListItem toDelete = new ListItem(instrumentClass, instrument);
			lih.deleteItem(toDelete);

		}

		private static void editAnItem() {
			// 
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Instrument Class");
			System.out.println("2 : Search by Instrument");
			int searchBy = in.nextInt();
			in.nextLine();
			List<ListItem> foundItems;
			if (searchBy == 1) {
				System.out.print("Enter the instrument class name: ");
				String instrumentClassName = in.nextLine();
				foundItems = lih.searchForItemByStore(instrumentClassName);
				
			} else {
				System.out.print("Enter the instrument: ");
				String instrumentName = in.nextLine();
				foundItems = lih.searchForItemByItem(instrumentName);

			}

			if (!foundItems.isEmpty()) {
				System.out.println("Found Results.");
				for (ListItem l : foundItems) {
					System.out.println(l.getId() + " : " + l.toString());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				ListItem toEdit = lih.searchForItemById(idToEdit);
				System.out.println("Retrieved " + toEdit.getInstrument() + " from " + toEdit.getInstrumentClass());
				System.out.println("1 : Update Instrument Class");
				System.out.println("2 : Update Instrument");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Store: ");
					String instrumentClass = in.nextLine();
					toEdit.setInstrumentClass(instrumentClass);
				} else if (update == 2) {
					System.out.print("New Item: ");
					String instrument = in.nextLine();
					toEdit.setInstrument(instrument);
				}

				lih.updateItem(toEdit);

			} else {
				System.out.println("---- No results found");
			}

		}

		public static void main(String[] args) {
			// This method runs the user prompt menu selection shown below
			runMenu();

		}

		public static void runMenu() {
			// Menu selection and user prompt
			boolean goAgain = true;
			System.out.println("--- Hello, and welcome to Tate's Tune Tent! ---");
			while (goAgain) {
				System.out.println("*  Select an option:");
				System.out.println("*  1 -- Add an instrument");
				System.out.println("*  2 -- Edit an instrument");
				System.out.println("*  3 -- Delete an instrument");
				System.out.println("*  4 -- View the groovy list");
				System.out.println("*  5 -- Leave Tate's Tune Tent");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAnItem();
				} else if (selection == 2) {
					editAnItem();
				} else if (selection == 3) {
					deleteAnItem();
				} else if (selection == 4) {
					viewTheList();
				} else {
					lih.cleanUp();
					System.out.println("   Thanks for visiting Tate's Tune Tent, we hope you have a groovetastic day!   ");
					goAgain = false;
				}

			}

		}
		
		// This method allows the user to view the list of Instrument classes and each associated instrument
		private static void viewTheList() {
			// TODO Auto-generated method stub
			List<ListItem> allItems = lih.showAllItems();
			for(ListItem singleItem : allItems){
			System.out.println(singleItem.returnItemDetails());
			}

		}

	}