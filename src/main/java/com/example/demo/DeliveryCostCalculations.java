package com.example.demo;
import java.util.ArrayList;
import java.util.List;

public class DeliveryCostCalculations {

	public int baseDeliveryCost, noOfPackages;

	public DeliveryCostCalculations(int baseDeliveryCost, int noOfPack) {
		this.baseDeliveryCost = baseDeliveryCost;
		this.noOfPackages = noOfPack;

	}

	public DeliveryCostCalculations() {

	}

	public Packages calculateDeliveryCost(int baseDeliveryCost, int discountPercentage, Packages pack) {
		Packages costCalcPackage = new Packages();
		List<String> costCalculate = new ArrayList<String>();
		int discountAmount = 0, totalDeliveryCost = 0, finalDeliveryCost = 0;
		int pw = pack.getPackageWeight(), pd = pack.getDistance();
		totalDeliveryCost = baseDeliveryCost + (pw * 10) + (pd * 5);
		discountAmount = (totalDeliveryCost * discountPercentage) / 100;
		finalDeliveryCost = totalDeliveryCost - discountAmount;
		costCalculate.add(pack.getPackageId());
		costCalculate.add(String.valueOf(discountAmount));
		costCalculate.add(String.valueOf(finalDeliveryCost));

		costCalcPackage.setPackageId(pack.getPackageId());
		costCalcPackage.setDisocuntAmount(discountAmount);
		costCalcPackage.setDeliveryPrce(finalDeliveryCost);
		return costCalcPackage;

	}

	public Packages getDeliveryCostByPackage(int baseDeliveryCost, Packages packages) {
		int discountPercentage = 0;
		int packageWeight, distance;
		String discountOfferCode;
		Discount discount = new Discount();
		packageWeight = packages.getPackageWeight();
		distance = packages.getDistance();
		discountOfferCode = packages.getOfferCode();
		discountPercentage = discount.getDiscountPercentage(packageWeight, distance, discountOfferCode);
		return calculateDeliveryCost(baseDeliveryCost, discountPercentage, packages);
	}

}