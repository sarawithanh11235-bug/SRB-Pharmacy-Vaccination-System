public class ReadFilePatientData {

	public static void main(String[] args) {
		String fileName = "PatientData.txt";

		HashMap<String, String> patientlist = new HashMap<String, String>();
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {

				String[] names = line.split(",");

				String firstName = names[0].trim();
				String lastName = names[1].trim();

				System.out.println(firstName + " " + lastName);

				patientlist.put(firstName, lastName);
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "");
		}

		System.out.println();
		System.out.println("Value/Last Name for the key Noah: " + patientlist.get("Noah"));
		System.out.println("Value/Last Name for the key Jane: " + patientlist.get("Jane"));
		System.out.println("Value/Last Name for the key Carl: " + patientlist.get("Carl"));
		System.out.println("Value/Last Name for the key Samantha: " + patientlist.get("Samantha"));
		System.out.println("Value/Last Name for the key Terry: " + patientlist.get("Terry"));
		System.out.println("Value/Last Name for the key Maurice: " + patientlist.get("Maurice"));
		System.out.println("Value/Last Name for the key David: " + patientlist.get("David"));
		System.out.println("Value/Last Name for the key Selena: " + patientlist.get("Selena"));
		System.out.println("Value/Last Name for the key Jacob: " + patientlist.get("Jacob"));
		System.out.println("Value/Last Name for the key Annie: " + patientlist.get("Annie"));
	}
}


