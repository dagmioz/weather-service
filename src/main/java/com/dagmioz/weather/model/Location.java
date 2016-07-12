package com.dagmioz.weather.model;

import java.io.Serializable;

public class Location implements Serializable
{
	private String city = "";
	private String country = "";
	
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Location(String city, String country) {
		super();
		this.city = city;
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Location location = (Location) o;

		if (!city.equals(location.city)) return false;
		return country.equals(location.country);

	}

	@Override
	public int hashCode() {
		int result = city.hashCode();
		result = 31 * result + country.hashCode();
		return result;
	}
}
