import java.util.LinkedHashSet;

import javafx.application.Application; //JavaFX packages must be loaded in
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Complete the JavaDoc code comments
 * 
 * @author Sarah R. Blette
 * @date 4/30/2020
 * Java Programming 2
 * Spring 2020
 * Final Project: Pharmacy Vaccination System Patient Information Table
 * 
 */

public class PharmacyVaccinationSystem extends Application { // ..'extends Application' is required for JavaFX load in
	
	Stage window;
	Scene scene;

	public static void main(String[] args) {	
		
		Application.launch(args); // JavaFX mandatory method 'launch(args)' to call JavaFX packages
             
	}

	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage) throws Exception { // JavaFX mandatory method to display the stage or GUI
		window = primaryStage;
        Scene scene = new Scene(new Group());
        final Label label1 = new Label("Patient Database");
        label1.setFont(new Font("Arial", 20));   
        window.setScene(scene);
        window.setTitle("Pharmacy Vaccination System");
        window.show();
        
        
        //Linked Hash Set takes patient’s most recent vaccination, and their insurance, and finds the union, difference, and intersection of the 2 sets.  
        LinkedHashSet<Patient> linkedhashset = new LinkedHashSet<Patient>(); // <Patient> is used to call dat from the Patient class 
        
        
		TableView<Patient> table = new TableView<Patient>(); // create table using TableView JavaFX UI Control with Patient class inside <>

		TableColumn<Patient, String> firstNameCol// Patient "First Name" Column object created from TableColumn<> JavaFX UI Control to display patient's first name
				= new TableColumn<Patient, String>("Patient First Name");//'firstNameCol' object defines Pharmacist Column in GUI

		TableColumn<Patient, String> lastNameCol // Patient "Last Name" Column object created from TableColumn<> JavaFX UI Control to display patient's last name
				= new TableColumn<Patient, String>("Patient Last Name");//'lastNameCol' object defines Pharmacist Column in GUI

		TableColumn<Patient, String> activeCol // Patient "Active" Column object created from TableColumn<> JavaFX UI Control to determine if patient is active in system or out of date
				= new TableColumn<Patient, String>("Active");

		TableColumn<Patient, String> dateOfBirthCol // Patient "Date of Birth" Column object created from TableColumn<> JavaFX UI Control to display Patient's date of birth
				= new TableColumn<Patient, String>("Date of Birth");//'dateOfBirthCol' object defines Pharmacist Column in GUI

		TableColumn<Patient, String> cityOfResidenceCol // Patient "City of Residence" Column object created from TableColumn<> JavaFX UI Control to display patient's city of residence
				= new TableColumn<Patient, String>("City of Residence");//'cityOfResidenceCol' object defines Pharmacist Column in GUI

		TableColumn<Patient, String> pharmStoreIDCol // Patient "Pharmacy Store ID" Column object created from TableColumn<> JavaFX UI Control to display patient's 'home' pharmacy store ID they visit
				= new TableColumn<Patient, String>("Pharmacy Store ID");//'pharmStoreIDCol' object defines Pharmacist Column in GUI

		TableColumn<Patient, String> lastVacCol // Patient "Most Recent Vaccination" Column object created from TableColumn<> JavaFX UI Control to display patient's most recent vaccination received
				= new TableColumn<Patient, String>("Most Recent Vaccination");//'lastVacCol' object defines Pharmacist Column in GUI

		TableColumn<Patient, String> insuranceCol // Patient "Insurance" Column object created from TableColumn<> JavaFX UI Control to display patient's insurance used
				= new TableColumn<Patient, String>("Insurance");//'insuranceCol' object defines Pharmacist Column in GUI

		TableColumn<Patient, String> pharmacistFirstNameCol // Patient "Pharmacist" Column object created from TableColumn<> JavaFX UI Control to display patient's pharmacist that helped them last
				= new TableColumn<Patient, String>("Pharmacist First Name"); //'pharmacistCol' object defines Pharmacist Column in GUI
		

		TableColumn<Patient, String> pharmacistLastNameCol // Patient "Pharmacist" Column object created from TableColumn<> JavaFX UI Control to display patient's pharmacist that helped them last
				= new TableColumn<Patient, String>("Pharmacist Last Name"); //'pharmacistCol' object defines Pharmacist Column in GUI
		
		TableColumn<Patient, String> numDaysCol // Patient "numDaysCol" Column object created from TableColumn<> JavaFX UI Control to display patient's number of days until the patients next scheduled appt
		= new TableColumn<Patient, String>("Numbers of Days Until Next Appt"); //'amountVacsCol' object defines Number of Vaccines Received Column in GUI

		//use 'setCellFactory' method to create cell areas for each column, to be able to input real-world Patient info data
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName")); 
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));
		dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
		cityOfResidenceCol.setCellValueFactory(new PropertyValueFactory<>("cityOfResidence"));
		pharmStoreIDCol.setCellValueFactory(new PropertyValueFactory<>("pharmStoreID"));
		lastVacCol.setCellValueFactory(new PropertyValueFactory<>("lastVac"));
		insuranceCol.setCellValueFactory(new PropertyValueFactory<>("insurance"));
		pharmacistFirstNameCol.setCellValueFactory(new PropertyValueFactory<>("pharmacistFirstName"));
		pharmacistLastNameCol.setCellValueFactory(new PropertyValueFactory<>("pharmacistLastName"));
		numDaysCol.setCellValueFactory(new PropertyValueFactory<>("numDays"));

		firstNameCol.setSortType(TableColumn.SortType.DESCENDING); // this will enable the first column to being the sorting in descending order
		lastNameCol.setSortable(false);

		ObservableList<Patient> patientList = getPatientList(); // using an ObservableList<Patient> while extending to the Patient class
		table.setItems(patientList);

		table.getColumns().addAll(firstNameCol, lastNameCol, activeCol, dateOfBirthCol, cityOfResidenceCol,
				pharmStoreIDCol, lastVacCol, insuranceCol, pharmacistFirstNameCol, pharmacistLastNameCol, numDaysCol); // the list of columns displayed on the GUI

		FilteredList<Patient> pharmacistflName = new FilteredList<Patient>(patientList, p -> true);//Pass the data to a filtered list
        table.setItems(pharmacistflName );//Set the table's items using the filtered list
        
        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("First Name","Last Name");
        choiceBox.setValue("Select Pharmacist");

        TextField textField = new TextField();
        textField.setPromptText("Search for Pharmacist");
        textField.setOnKeyReleased(keyEvent ->
        {
            switch (choiceBox.getValue())//Switch on choiceBox value
            {
                case "First Name":
                	pharmacistflName .setPredicate(p -> p.getPharmacistFirstName().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by first name
                    break;
                case "Last Name":
                	pharmacistflName .setPredicate(p -> p.getLastName().toLowerCase().contains(textField.getText().toLowerCase().trim()));//filter table by first name
                    break;

            }
        });
        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal != null)
            {
                textField.setText("");
                pharmacistflName .setPredicate(null); 
            }
        });
	        
	        //vactype choice box practice
			FilteredList<Patient> patientflName = new FilteredList<Patient>(patientList, p -> true);//Pass the data to a filtered list
	        table.setItems(patientflName);//Set the table's items using the filtered list
	        
	        //Adding ChoiceBox and TextField here!
	        ChoiceBox<String> choiceBox2 = new ChoiceBox();
	        choiceBox2.getItems().addAll("First Name", "Last Name");
	        choiceBox2.setValue("Patient Name");

	        TextField textField2 = new TextField();
	        textField2.setPromptText("Search for Patient");
	        textField2.setOnKeyReleased(keyEvent ->
	        {
	            switch (choiceBox2.getValue()) //Switch on choiceBox value
	            {
	                case "First Name":
	                	patientflName.setPredicate(p -> p.getFirstName().toLowerCase().contains(textField2.getText().toLowerCase().trim()));//filter table by first name
	                    break;
	                case "Last Name":
	                	patientflName.setPredicate(p -> p.getLastName().toLowerCase().contains(textField2.getText().toLowerCase().trim()));//filter table by first name
	                    break;
	            }
	        });
	        choiceBox2.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
	        {
	            if (newVal != null)
	            {
	                textField2.setText("");
	                patientflName.setPredicate(null); 
	            }
	        });

	        HBox hBox2 = new HBox(choiceBox, textField, choiceBox2, textField2);
	        hBox2.setAlignment(Pos.BASELINE_LEFT);
	        final VBox vbox2 = new VBox();
	        vbox2.setSpacing(5);
	        vbox2.getChildren().addAll(label1, table, hBox2);
	        ((Group) scene.getRoot()).getChildren().addAll(vbox2);
			primaryStage.setScene(scene);
			primaryStage.show();   
	}

	private ObservableList<Patient> getPatientList() { // ObservableList<Patient> creates 100 patient objects to make up the Database and takes parameters and corresponding data-types from the Patient class

		Patient patient1 = new Patient("Jane", "Smith", true, 5151984, "Waterford", 5, "Tetanus", "UnitedHealth",
				"Kim", "Grey", 55);
		Patient patient2 = new Patient("Noah", "Johnson", true, 6171991, "New London", 5, "Influenza", "Blue Cross Blue Shield ",
				"Kim", "Grey", 47);
		Patient patient3 = new Patient("John", "Doe", true, 4101990, "Bristol", 6, "Measles", "Cigna",
				"Madeline" , "Gold", 37);
		Patient patient4 = new Patient("Carl", "James", true, 11121994, "Waterbury", 7, "Covid-19", "Wellcare",
				"Sally", "Fields", 12);
		Patient patient5 = new Patient("Samantha", "Cook", true, 1132003, "New Haven", 7, "Influenza", "Kaiser Foundation",
				"Sally", "Fields", 21);
		Patient patient6 = new Patient("Terry", "Wilson", true, 7111974, "Hartford", 6, "Tuberculosis", "UnitedHealth",
				"Madeline","Gold", 199);
		Patient patient7 = new Patient("Maurice", "Reed", true, 3151947, "Orange", 7, "Hepatitis B", "Blue Cross Blue Shield ",
				"Sally", "Fields", 182);
		Patient patient8 = new Patient("David", "Moore", true, 2151991, "Wallington", 1, "Covid-19", "Anthem",
				"Tom", " Jenkins", 59);
		Patient patient9 = new Patient("Selena", "Allen", true, 4261985, "Cornwall", 2, "Polio", "Cigna",
				"David", "Hughes", 31);
		Patient patient10 = new Patient("Jacob", "Greens", true, 2121990, "Montville", 5, "Chickenpox", "Blue Cross Blue Shield ",
				"Kim", "Grey", 159);
		Patient patient11 = new Patient("Annie", "Taylor", true, 2151985, "Norwich", 5, "Measles", "Kaiser Foundation",
				"Kim", " Grey", 361);
		Patient patient12 = new Patient("Jennifer", "Young", true, 2121990, "Oakdale", 5, "Tetanus", "UnitedHealth",
				"Kim","Grey", 2);
		Patient patient13 = new Patient("Erin", "Coleman", true, 3151965, "Groton", 5, "Covid-19", "Kaiser Foundation",
				"Kim", "Grey", 5);
		Patient patient14 = new Patient("Sean", "Bell", true, 1271961, "Killingly", 8, "Chickenpox", "Wellcare",
				"Halle", " Simmons", 97);
		Patient patient15 = new Patient("Paul", "Martinez", true, 1181998, "Fairfield", 4, "Tetanus", "UnitedHealth",
				"Anthony", "Wood", 101);
		Patient patient16 = new Patient("Seth", "Garcia", true, 5291972, "Greenwich", 4, "Measles", "Cigna",
				"Anthony","Wood", 316);
		Patient patient17 = new Patient("Sandra", "Patterson", true, 4161974, "Glastonbury", 6, "Tuberculosis", "Blue Cross Blue Shield ",
				"Madeline", "Gold", 17);
		Patient patient18 = new Patient("Tom", "Watson", true, 6141992, "Plainfield", 8, "Shingles", "Anthem",
				"Halle","Simmons", 15);
		Patient patient19 = new Patient("Bob", "Brooks", true, 9191967, "Middletown", 3, "Chickenpox", "Kaiser Foundation",
				"Alfred","Sullivan", 73);
		Patient patient20 = new Patient("Lily", "Russel", true, 8121995, "Torrington", 2, "Measles", "Blue Cross Blue Shield ",
				"David", "Hughes", 77);
		Patient patient21 = new Patient("Adam", "Hayes", true, 10112007, "Westbrook", 3, "Covid-19", "UnitedHealth",
				"Alfred","Sullivan", 77);
		Patient patient22 = new Patient("Aiden", "Smitz", true, 10251950, "Old Saybrook", 3, "Tuberculosis", "Wellcare",
				"Alfred","Sullivan", 111);
		Patient patient23 = new Patient("Caroline", "Mills", true, 10171980, "Old Lyme", 5, "Chickenpox", "UnitedHealth",
				"Kim","Grey", 94);
		Patient patient24 = new Patient("Melinda", "Williams", true, 6251939, "East Lyme", 5, "Tetanus", "Blue Cross Blue Shield ",
				"Kim", "Grey", 19);
		Patient patient25 = new Patient("Liam", "Murphey", true, 10111951, "Norfolk", 2, "Measles", "Anthem",
				"David", "Hughes", 156);
		Patient patient26 = new Patient("Bill", "Adams", true, 12141947, "Bridgeport", 4, "Covid-19", "Cigna",
				"Anthony", "Wood", 86);
		Patient patient27 = new Patient("Zoe", "Scott", true, 6141992, "Suffield", 6, "Tetanus", "Kaiser Foundation",
				"Madeline", "Gold", 5);
		Patient patient28 = new Patient("Mallory", "Brown", true, 9191967, "Stamford", 4, "Shingles", "UnitedHealth",
				"Anthony", "Wood", 196);
		Patient patient29 = new Patient("Cynthia", "Miller", true, 6251947, "Conventry", 1, "Polio", "Blue Cross Blue Shield ",
				"Tom","Jenkins", 322);
		Patient patient30 = new Patient("Gavin", "Davis", true, 6261939, "New London", 5, "Measles", "Wellcare",
				"Kim","Grey", 325);
		Patient patient31 = new Patient("Darla", "Long", true, 12211989, "Norwich", 5, "Tetanus", "UnitedHealth",
				"Kim", "Grey", 72);
		Patient patient32 = new Patient("Stephanie", "Lee", true, 10141972, "Waterford", 5, "Tetanus", "Anthem",
				"Kim", "Grey", 213);
		Patient patient33 = new Patient("Steven", "Walker", true, 2241968, "Groton", 5, "Covid-19", "Blue Cross Blue Shield ",
				"Kim", "Grey", 12);
		Patient patient34 = new Patient("Olivia", "Baker", true, 1181990, "Old Lyme", 5, "Rabies", "Cigna",
				"Kim", "Grey", 16);
		Patient patient35 = new Patient("Blake", "Rivera", true, 11231952, "Bloomfield", 6, "Polio", "UnitedHealth",
				"Madeline", "Gold", 17);
		Patient patient36 = new Patient("Patricia", "Lewis", true, 9171966, "Middlebury", 7, "Shingles", "Kaiser Foundation",
				"Sally","Fields", 9);
		Patient patient37 = new Patient("Tony", "Cooper", true, 12151987, "Southington", 6, "Chickenpox", "UnitedHealth",
				"Madeline", "Gold", 1);
		Patient patient38 = new Patient("Ellen", "Ross", true, 2151978, "Brooklyn", 8, "Measles", "Anthem",
				"Halle", "Simmons", 14);
		Patient patient39 = new Patient("Carl", "Maine", true, 3261942, "Darien", 4, "Tetanus", "Wellcare",
				"Anthony", "Wood", 17);
		Patient patient40 = new Patient("Antonia", "Thompson", true, 12151940, "Killingly", 8, "Tuberculosis", "Blue Cross Blue Shield ",
				"Halle", "Simmons", 13);
		Patient patient41 = new Patient("Chris", "Pena", true, 12121925, "Middletown", 3, "Tetanus", "Kaiser Foundation",
				"Alfred", "Sullivan", 58);
		Patient patient42 = new Patient("Jospeh", "Torres", true, 114974, "Coventry", 1, "Tetanus", "UnitedHealth",
				"Tom","Jenkins", 254);
		Patient patient43 = new Patient("Joan", "Hawes", true, 1561985, "Orange", 7, "Tuberculosis", "Cigna",
				"Sally", "Fields", 46);
		Patient patient44 = new Patient("Stephan", "Campbell", true, 11111911, "Norfolk", 2, "Chickenpox", "Blue Cross Blue Shield ",
				"David","Hughes", 42);
		Patient patient45 = new Patient("Doris", "Foster", true, 12161980, "Waterford", 5, "Covid-19", "Anthem",
				"Kim", "Grey", 31);
		Patient patient46 = new Patient("Ronald", "Butler", true, 10131929, "Brooklyn", 8, "Polio", "Kaiser Foundation",
				"Halle","Simmons", 30);
		Patient patient47 = new Patient("Mark", "Ward", true, 6251984, "Wallington", 1, "Measles", "Wellcare",
				"Tom","Jenkins", 226);
		Patient patient48 = new Patient("Nancy", "Martin", true, 3231956, "Old Saybrook", 3, "Tetanus", "Blue Cross Blue Shield ",
				"Alfred","Sullivan", 221);
		Patient patient49 = new Patient("George", "Evans", true, 11121984, "Cornwall", 2, "Tuberculosis", "UnitedHealth",
				"David","Hughes", 211);
		Patient patient50 = new Patient("Lisa", "Kelly", true, 1111974, "Plainfield", 8, "Measles", "Cigna",
				"Halle","Simmons", 29);
		Patient patient51 = new Patient("Jane", "Smith", true, 10282004, "Wallington", 1, "Chickenpox", "Kaiser Foundation",
				"Tom", "Jenkins", 299);
		Patient patient52 = new Patient("Frank", "Sinatra", true, 1232011, "Westbrook", 3, "Tetanus", "Blue Cross Blue Shield ",
				"Alfred","Sullivan", 198);
		Patient patient53 = new Patient("John", "Snow", true, 9181969, "East Lyme", 5, "Shingles", "Anthem",
				"Kim", "Grey", 107);
		Patient patient54 = new Patient("Margaret", "Russel", true, 1252006, "Brooklyn", 1, "Typhoid Fever", "Wellcare",
				"Tom","Jenkins", 97);
		Patient patient55 = new Patient("Kobe", "Bryant", true, 8151955, "Torrington", 2, "Covid-19", "Cigna",
				"David", "Hughes", 52);
		Patient patient56 = new Patient("Matthew", "Griffen", true, 2011937, "Old Saybrook", 3, "Tetanus", "Kaiser Foundation",
				"Alfred", "Sullivan", 51);
		Patient patient57 = new Patient("Karen", "Philips", true, 11061994, "Coventry", 1, "Chickenpox", "Blue Cross Blue Shield ",
				"Tom","Jenkins", 10);
		Patient patient58 = new Patient("Harry", "Potter", true, 2231981, "Plainfield", 8, "Tetanus", "Anthem",
				"Halle","Simmons", 247);
		Patient patient59 = new Patient("Jack", "Spparow", true, 8191972, "Westbrook", 3, "Typhoid Fever", "UnitedHealth",
				"Alfred","Sullivan", 108);
		Patient patient60 = new Patient("Peter", "Wisener", true, 3262008, "Norwich", 5, "Rabiess", "Blue Cross Blue Shield ",
				"Kim", "Grey", 40);
		Patient patient61 = new Patient("Angela", "French", true, 4171986, "Southington", 6, "Polio", "Wellcare",
				"Madeline", "Gold", 105);
		Patient patient62 = new Patient("Donald", "James", true, 4021941, "Killingly", 8, "Rabies", "Blue Cross Blue Shield ",
				"Halle","Simmons", 45);
		Patient patient63 = new Patient("Mickey", "Mouse", true, 11291965, "Middletown", 3, "Shingles", "Anthem",
				"Alfred ", "Sullivan", 49);
		Patient patient64 = new Patient("Kenneth", "Lopez", true, 12252009, "Wallington", 1, "Covid-19", "Cigna",
				"Tom","Jenkins", 290);
		Patient patient65 = new Patient("Rachel", "Ray", true, 12291963, "Torrington", 2, "Rabies", "Kaiser Foundation",
				"David", "Hughes", 159);
		Patient patient66 = new Patient("Shirley", "Rodriguez", true, 8141939, "Oakdale", 5, "Tuberculosis", "Blue Cross Blue Shield ",
				"Kim", "Grey", 123);
		Patient patient67 = new Patient("Debra", "Sanchez", true, 11171971, "Killingly", 8, "Chickenpox", "Wellcare",
				"Halle", " Simmons", 81);
		Patient patient68 = new Patient("Evelyn", "Ramirez", true, 1131952, "Coventry", 1, "Typhoid Fever", "Anthem",
				"Tom", "Jenkins", 92);
		Patient patient69 = new Patient("Arya", "Stark", true, 6161975, "Bridgeport", 4, "Tetanus", "Kaiser Foundation",
				"Kim"," Grey", 200);
		Patient patient70 = new Patient("Alice", "Norris", true, 12151975, "Plainfield", 8, "Polio", "Blue Cross Blue Shield ",
				"Halle", "Simmons", 22);
		Patient patient71 = new Patient("Kevin", "Brooks", true, 2301956, "Bloomfield", 6, "Covid-19", "Cigna",
				"Madeline", "Gold", 24);
		Patient patient72 = new Patient("Anne", "Frank", true, 3201950, "Norwich", 5, "Tuberculosis", "Anthem", 
				"Kim", "Grey", 369);
		Patient patient73 = new Patient("Lauren", "Chase", true, 2231956, "Brooklyn", 8, "Typhoid Fever", "Blue Cross Blue Shield ",
				"Halle", "Simmons", 32);
		Patient patient74 = new Patient("Tom", "Hanks", true, 10111981, "Bristol", 6, "Tetanus", "UnitedHealth",
				"Madeline", "Gold", 71);
		Patient patient75 = new Patient("Oprah", "Winfrey", true, 9141974, "Middletown", 3, "Measles", "Kaiser Foundation",
				"Alfred", "Sullivan", 33);
		Patient patient76 = new Patient("Angelina", "Jolie", true, 6161974, "Killingly", 8, "Chickenpox", "Blue Cross Blue Shield ",
				"Halle", "Simmons", 70);
		Patient patient77 = new Patient("Barack", "Obama", true, 7181949, "Cornwall", 2, "Tetanus", "Anthem",
				"David", "Hughes", 58);
		Patient patient78 = new Patient("Donald", "Trump", true, 5091992, "Middlebury", 7, "Typhoid Fever", "UnitedHealth",
				"Sally", "Fields", 336);
		Patient patient79 = new Patient("Bill", "Gates", true, 11171945, "Brooklyn", 8, "Tetanus", "Blue Cross Blue Shield ",
				"Halle", "Simmons", 316);
		Patient patient80 = new Patient("Ada", "Lovelace", true, 6231999, "Old Saybrook", 3, "Shingles", "Cigna",
				"Alfred","Sullivan", 213);
		Patient patient81 = new Patient("Marilyn", "Monroe", true, 3131929, "Greenwich", 4, "Polio", "Anthem",
				"Kim","Grey", 252);
		Patient patient82 = new Patient("Abraham", "Lincoln", true, 4191967, "Brooklyn", 8, "Covid-19", "Wellcare",
				"Halle","Simmons", 66);
		Patient patient83 = new Patient("Tiger", "Woods", true, 8081955, "Waterford", 5, "Typhoid Fever", "Blue Cross Blue Shield ",
				"Kim", "Grey", 39);
		Patient patient84 = new Patient("Bard", "Flores", true, 9301960, "Coventry", 1, "Measles", "Kaiser Foundation",
				"Tom", "Jenkins", 312);
		Patient patient85 = new Patient("Edwin", "Cox", true, 4271982, "Norfolk", 2, "Rabies", "UnitedHealth", 
				"David", " Hughes", 251);
		Patient patient86 = new Patient("Stephen", "Hawking", true, 6302008, "Killingly", 8, "Chickenpox", "Cigna",
				"Halle","Simmons", 83);
		Patient patient87 = new Patient("William", "Shakespeare", true, 5152012, "Westbrook", 3, "Tetanus", "Blue Cross Blue Shield ",
				"Alfred", "Sullivan", 28);
		Patient patient88 = new Patient("Sigmund", "Freud", true, 7141996, "Wallington", 1, "Typhoid Fever", "Anthem",
				"Tom", "Jenkins", 287);
		Patient patient89 = new Patient("Albert", "Einstein", true, 2221985, "Stamford", 4, "Covid-19", "Kaiser Foundation",
				"Anthony", "Wood", 362);
		Patient patient90 = new Patient("Steve", "Jobs", true, 10301984, "New London", 5, "Shingles", "Blue Cross Blue Shield ",
				"Kim","Grey", null);
		Patient patient91 = new Patient("Michael", "Bloomberg", true, 2171985, "Orange", 7, "Polio", "Wellcare",
				"Sally", "Fields", 25);
		Patient patient92 = new Patient("Hillary", "Clinton", true, 2281996, "Plainfield", 8, "Chickenpox", "Cigna",
				"Halle", "Simmons", 314);
		Patient patient93 = new Patient("Andie", "MacDowell", true, 3151998, "Wallington", 1, "Tetanus", "Blue Cross Blue Shield ",
				"Tom", "Jenkins", 291);
		Patient patient94 = new Patient("Stephen", "King", true, 5181961, "Westbrook", 3, "Typhoid Fever", "Anthem",
				"Alfred", "Sullivan", 13);
		Patient patient95 = new Patient("Benji", "Madden", true, 10111975, "East Lyme", 5, "Measles", "Blue Cross Blue Shield",
				"Kim", "Grey", 341);
		Patient patient96 = new Patient("Michael", "Jordan", true, 9191963, "Killingly", 8, "Covid-19", "Kaiser Foundation",
				"Halle", "Simmons", 258);
		Patient patient97 = new Patient("Larry", "Byrd", true, 11141938, "Torrington", 2, "Rabies", "Cigna",
				"David", "Hughes", 314);
		Patient patient98 = new Patient("Louisa", "Alcott", true, 12151974, "Oakdale", 5, "Shingles", "UnitedHealth",
				"Kim", "Grey", 16);
		Patient patient99 = new Patient("Susan", "Collins", true, 3262015, "Hartford", 6, "Typhoid Fever", "Blue Cross Blue Shield ",
				"Madeline", "Gold", 37);
		Patient patient100 = new Patient("Thomas", "Edison", true, 4052002, "New Haven", 7, "Covid-19", "Anthem",
				"Sally", "Fields", 84);

		ObservableList<Patient> patientList2 = FXCollections.observableArrayList(patient1, patient2, patient3, patient4,
				patient5, patient6,

				patient7, patient8, patient9, patient10, patient11, patient12, patient13, patient14, patient15,
				patient16, patient17, patient18, patient19, patient20, patient21, patient22, patient23, patient24,
				patient25, patient26, patient27, patient28, patient29, patient30, patient31, patient32, patient33,
				patient34, patient35, patient36, patient37, patient38, patient39, patient40, patient41, patient42,
				patient43, patient44, patient45, patient46, patient47, patient48, patient49, patient50, patient51,
				patient52, patient53, patient54, patient55, patient56, patient57, patient58, patient59, patient60,
				patient61, patient62, patient63, patient64, patient65, patient66, patient67, patient68, patient69,
				patient70, patient71, patient72, patient73, patient74, patient75, patient76, patient77, patient78,
				patient79, patient80, patient81, patient82, patient83, patient84, patient85, patient86, patient87,
				patient88, patient89, patient90, patient91, patient92, patient93, patient94, patient95, patient96,
				patient97, patient98, patient99, patient100); // call all 100 patients, and their above parameters from the 'Patient.java' class

		return patientList2; // return and display the Patient list
	}	
	}


