package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class DeliveryCostEstimation extends DeliveryCostCalculations {

	public DeliveryCostEstimation(int baseDeliveryCost, int noOfPack) {
		super(baseDeliveryCost, noOfPack);
	}

	public DeliveryCostEstimation() {
		super();
	}

	public static List<String> calculateDeliveryCost(List<Packages> inputPackages, int baseDeliveryCost,
			int noOfPackages) {

		List<Packages> resultPackage = new ArrayList<Packages>();
		DeliveryCostEstimation delcostEst = new DeliveryCostEstimation(baseDeliveryCost, noOfPackages);

		for (int i = 0; i < inputPackages.size(); i++) {
			resultPackage.add(delcostEst.getDeliveryCostByPackage(baseDeliveryCost, inputPackages.get(i)));
		}

		// display the results
		return displayResults(resultPackage);
	}

	public static List<String> displayResults(List<Packages> resultPackage) {

		List<String> resultList = new ArrayList<String>();

		if (resultPackage.size() > 0) {
			List<Packages> result = resultPackage;

			// sort the packages
			Collections.sort(result, new Comparator<Packages>() {

				public int compare(Packages o1, Packages o2) {
					return o1.getPackageId().compareTo(o2.getPackageId());
				}
			});

			for (int i = 0; i < resultPackage.size(); i++) {
				String str = "";
				str += resultPackage.get(i).getPackageId() + "      " + resultPackage.get(i).getDisocuntAmount()
						+ "      " + resultPackage.get(i).getDeliveryPrce();
				resultList.add(str);

			}
		}
		return resultList;

	}

	public static void main(String[] args) {
		int baseDeliveryCost, noOfPackages;

		InputOutputDetails inputOutputDetails = new InputOutputDetails();

		// Take the inputs from CLI
		List<Packages> inputPackages = inputOutputDetails.getInputDetails();
		baseDeliveryCost = inputOutputDetails.baseDeliveryCost;
		noOfPackages = inputOutputDetails.noOfPackages;

		// calculateDeliveryCost

		System.out.println("\n Output\n");
		List<String> result = calculateDeliveryCost(inputPackages, baseDeliveryCost, noOfPackages);
		for (String res : result) {
			System.out.println(res);
		}
	}

}