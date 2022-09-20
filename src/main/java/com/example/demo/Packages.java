package com.example.demo;
public class Packages {
	String packageId, offerCode;
	int packageWeight, distance, deliveryPrce, disocuntAmount;
	double time = 0;

	public Packages(String packageId, int packageWeight, int distance, String offerCode) {
		setPackageId(packageId);
		setDistance(distance);
		setPackageWeight(packageWeight);
		setOfferCode(offerCode);
	}

	public Packages() {

	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public int getDisocuntAmount() {
		return disocuntAmount;
	}

	public void setDisocuntAmount(int disocuntAmount) {
		this.disocuntAmount = disocuntAmount;
	}

	public int getDeliveryPrce() {
		return deliveryPrce;
	}

	public void setDeliveryPrce(int deliveryPrce) {
		this.deliveryPrce = deliveryPrce;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public int getPackageWeight() {
		return packageWeight;
	}

	public void setPackageWeight(int packageWeight) {
		this.packageWeight = packageWeight;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

}
