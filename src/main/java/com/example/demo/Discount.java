package com.example.demo;
public class Discount {
	public String discountName;
	public int discountPercentage;
	public String discountOfferCode;

	public Discount(String discountName, int discountPercentage, String discountOfferCode) {
		this.discountName = discountName;
		this.discountPercentage = discountPercentage;
		this.discountOfferCode = discountOfferCode;
	}

	public Discount() {
	}

	public String getDiscountName() {
		return discountName;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}

	public String getDiscountOfferCode() {
		return discountOfferCode;
	}

	public int getDiscountPercentage(int packageWeight, int distance, String discountOfferCode) {
		if (discountOfferCode.equals("OFR001") || discountOfferCode.equals("OFR002")
				|| discountOfferCode.equals("OFR003"))

		{
			if ((distance >= 50 && distance <= 250) && (packageWeight >= 10 && packageWeight <= 150)&& discountOfferCode.equals("OFR003")) {
				return  5;
			} else if ((distance >= 50 && distance <= 150) && (packageWeight >= 100 && packageWeight <= 250) && discountOfferCode.equals("OFR002")) {
				return  7;
			} else if ((distance < 200) && (packageWeight >= 70 && packageWeight <= 200) && discountOfferCode.equals("OFR001")) {
				return  10;
			}
			else
			{
				return 0;
			}
		} else {
			return 0;
		}
	
	}
}
