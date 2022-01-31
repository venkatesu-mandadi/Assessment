package com.nationalize.dto;

public class Country{
	
    public String country_id;
    public double probability;
    
    
	/**
	 * @return the country_id
	 */
	public String getCountry_id() {
		return country_id;
	}
	/**
	 * @param country_id the country_id to set
	 */
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	/**
	 * @return the probability
	 */
	public double getProbability() {
		return probability;
	}
	/**
	 * @param probability the probability to set
	 */
	public void setProbability(double probability) {
		this.probability = probability;
	}
    
    
}

