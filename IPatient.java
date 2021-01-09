public interface IPatient {
	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLasttName(String lastName);

	public Boolean getActive();

	public void setActive(Boolean active);

	public Integer getDateOfBirth();

	public void setDateOfBirth(Integer dateOfBirth);

	public String getCityOfResidence();

	public void setCityOfResidence(String cityOfResidence);

	public int getPharmStoreID();

	public void setPharmStoreID(Integer pharmStoreID);

	public String getLastVac();

	public void setLastVac(String lastVac);
	
	public String getInsurance();

	public void setInsurance(String insurance);
	
	public String getPharmacistFirstName();

	void setPharmacistFirstName(String pharmacistFirstName);
	
	public String getPharmacistLastName();

	void setPharmacistLastName(String pharmacistLastName);

	public Integer getNumDays();

	void setNumDays(Integer numDays);

}
