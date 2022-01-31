package com.nationalize.dto;

import java.util.List;

public class CountryDetailResponse{
    public String name;
    public List<Country> country;
    
    
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the country
	 */
	public List<Country> getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(List<Country> country) {
		this.country = country;
	}
    
    
}
