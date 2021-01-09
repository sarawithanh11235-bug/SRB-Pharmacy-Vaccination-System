

public class Patient implements IPatient { 
	
	public class QueueUsingLinkedListMain {

	}

	protected String firstName;
	protected String lastName;
	protected Boolean active;
	protected Integer dateOfBirth;
	protected String cityOfResidence;
	protected Integer pharmStoreID;
	protected String lastVac;
	protected String insurance;
	protected String pharmacistFirstName;
	protected String pharmacistLastName;
	protected Integer numDays;
	
	public Patient(String firstName, String lastName, Boolean active, Integer dateOfBirth, String cityOfResidence,
			Integer pharmStoreID, String lastVac, String insurance, String pharmacistFirstName, String pharmacistLastName, Integer numDays) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.active = active;
		this.dateOfBirth = dateOfBirth;
		this.cityOfResidence = cityOfResidence;
		this.pharmStoreID = pharmStoreID;
		this.lastVac = lastVac;
		this.insurance = insurance;
		this.pharmacistFirstName = pharmacistFirstName;
		this.pharmacistLastName = pharmacistLastName;
		this.numDays = numDays;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}
  
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLasttName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public Boolean getActive() {
		return active;
	}

	@Override
	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public Integer getDateOfBirth() {
		return dateOfBirth;
	}

	@Override
	public void setDateOfBirth(Integer dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String getCityOfResidence() {
		return cityOfResidence;
	}

	@Override
	public void setCityOfResidence(String cityOfResidence) {
		this.cityOfResidence = cityOfResidence;
	}

	@Override
	public int getPharmStoreID() {
		return pharmStoreID;
	}

	@Override
	public void setPharmStoreID(Integer pharmStoreID) {
		this.pharmStoreID = pharmStoreID;
	}
	
	@Override
	public String getLastVac() {
		return lastVac;
	}

	@Override
	public void setLastVac(String lastVac) {
		this.lastVac = lastVac;
	}

	@Override
	public String getInsurance() {
		return insurance;
	}

	@Override
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	@Override
	public String getPharmacistFirstName() {
		return pharmacistFirstName;
	}

	@Override
	public void setPharmacistFirstName(String pharmacistFirstName) {
		this.pharmacistFirstName = pharmacistFirstName;
	}
	
	@Override
	public String getPharmacistLastName() {
		return pharmacistFirstName;
	}

	@Override
	public void setPharmacistLastName(String pharmacistLastName) {
		this.pharmacistLastName = pharmacistLastName;
	}


	@Override
	public Integer getNumDays() {
		return numDays;
	}
	
	@Override
	public void setNumDays(Integer numDays) {
		this.numDays = numDays;
	}

	public int searchNumDays(int arrLinear[], int numDays)  
	   {
	      int num = arrLinear.length; 
	      for(int a = 0; a < num; a++) 
	      { 
	         if(arrLinear[a] == numDays) // here we are returning the index of the element if found
	            return a; 
	      }
	      return -1; // return -1 if element is not found 
	   }
	
	
	// Quick sort algorithm is used to sort the number of days before next appointment that each patient has from lowest to highest so 
	//pharmacist can see which patient appointments are coming up soonest. 
	public int quickSortNumDays(double[] a, int p, int q){
		if (q - p < 2)
			return q;
		int m = partition(a, p, q);
		quickSortNumDays(a, p, m);
		return quickSortNumDays(a, m + 1, q);
	}

	public int partition(double[] a, int p, int q) {
		double pivot = a[p];
		int i = p, j = q;
		while (i < j) {
			while (i < j && a[--j] >= pivot)
				;
			if (i < j) {
				a[i] = a[j];
			}
			while (i < j && a[++i] <= pivot)
				;
			if (i < j) {
				a[j] = a[i];
			}
		}
		a[j] = pivot;
		return j;
	}
	
	// Merge sort algorithm is used to sort the pharmacy store IDs from lowest to highest and still displaying the duplicates. 
	//There are many duplicates in this array since there are 100 rows of data and only 8 different pharmacy stores - 1 for every CT county
			public int mergeSortPharmStoreID(int arr[], int l, int m, int r) { 
		        int n1 = m - l + 1; 
		        int n2 = r - m; 
		        int L[] = new int [n1]; 
		        int R[] = new int [n2]; 
		        for (int i=0; i<n1; ++i) 
		            L[i] = arr[l + i]; 
		        for (int j=0; j<n2; ++j) 
		            R[j] = arr[m + 1+ j]; 
		        int i = 0, j = 0; 
		        int k = l; // Initial index of merged subarry array 
		        while (i < n1 && j < n2) 
		        { 
		            if (L[i] <= R[j]) 
		            { 
		                arr[k] = L[i]; 
		                i++; 
		            } 
		            else
		            { 
		                arr[k] = R[j]; 
		                j++; 
		            } 
		            k++; 
		        } 
		        while (i < n1) 
		        { 
		            arr[k] = L[i]; 
		            i++; 
		            k++; 
		        } 
		        while (j < n2) 
		        { 
		            arr[k] = R[j]; 
		            j++; 
		            k++; 
		        }
				return k; 
		    } 
		    void sort(int arr[], int l, int r) 
		    { 
		        if (l < r) 
		        { 
		            int m = (l + r)/2; // Find the middle point 
		            sort(arr, l, m); // Merge the sorted halves
		            sort(arr , m + 1, r); 
		            mergeSortPharmStoreID(arr, l, m, r); // Merge the sorted halves
		        } 
		    } 
		  
		    static void printArray(int arr[]) // function to print array of size n
		    { 
		        int n = arr.length; 
		        for (int i = 0; i < n; ++i) 
		            System.out.print(arr[i] + " "); 
		    }
		   
		    
		    // // linear/sequential search will be able to search for specific pharmacy store ID numbered 1 - 8 for the 8 pharmacy stores located in each of CT's 8 counties 
		    public int linearSearch(int arr[], int x) {  // take the param's 'arr[]' with int datatype to represent array to be sorted through - it will be 1 - 8.
		    	int n = arr.length; 
		        for(int i = 0; i < n; i++) 
		        { 
		            if(arr[i] == x) 
		                return i; 
		        } 
		        return -1; 
		    }
		    
		    // Binary search will be able to search through the 100 rows list of unique patient date of births to
		    // display a specific date of birth based on the user’s input in a JavaFX text-field.
		    public int binarySearchDateOfBirth(int arr[], int l, int r, int x) {   
		            if (r >= l) { 
		                int mid = l + (r - l) / 2; // this line find the mid point of the array 
		                if (arr[mid] == x) 
		                    return mid; 
		                // If element is smaller than mid, then 
		                // it can only be present in left subarray 
		                if (arr[mid] > x) // if the element is smaller than the mid, then it is only found in the left part of the array - so the subarray
		                    return binarySearchDateOfBirth(arr, l, mid - 1, x); 
		                return binarySearchDateOfBirth(arr, mid + 1, r, x); 
		            }
		            return -1; 
}
}
	
	

