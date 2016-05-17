package org.healthcare.web.form;

import java.util.ArrayList;
import java.util.List;

public class SearchForm {
	private List<String> specilizations = new ArrayList<String>();

	private List<String> city = new ArrayList<String>();

	private Integer page;
	
	public SearchForm(){
		
	}
	
	public SearchForm(List<String> specilizations, List<String> city, Integer page){
		this.specilizations = specilizations;
		this.city = city;
		this.page = page;
	}

	public List<String> getSpecilizations() {
		if (specilizations.isEmpty()) {
			specilizations.add("NONE");
		}

		return specilizations;
	}

	public void setSpecilizations(List<String> specilizations) {
		this.specilizations = specilizations;
	}

	public List<String> getCity() {
		if (city.isEmpty()) {
			city.add("NONE");
		}

		return city;
	}

	public void setCity(List<String> city) {
		this.city = city;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
