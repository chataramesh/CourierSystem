package com.example.demo;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class InputOutputDetails {
	public int packageWeight, distance, noOfPackages, baseDeliveryCost;

	public InputOutputDetails(int baseDeliveryCost, int noOfPackages) {
		this.baseDeliveryCost = baseDeliveryCost;
		this.noOfPackages = noOfPackages;
	}

	public InputOutputDetails() {

	}

	public List<Packages> getInputDetails() {

		String packageId, offerCode, input;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter \nBase_Delivery_Cost , No_Of_Packages");
		input = scanner.nextLine();
		String[] input_array = input.split(" ");

		// check input validation
		if (input_array.length == 2) {
			baseDeliveryCost = Integer.parseInt(input_array[0]);
			noOfPackages = Integer.parseInt(input_array[1]);
			new InputOutputDetails(baseDeliveryCost, noOfPackages);
		} else {
			System.out.println("Please enter in correct format");

		}
		List<Packages> inputPackageDetails = new ArrayList<Packages>();
		for (int i = 0; i < noOfPackages; i++) {
			System.out.printf("\nEnter package %d details\n", i + 1);
			System.out.println("packageId  packageWeight distance offerCode");
			String packagesInputArray[] = scanner.nextLine().split(" ");
			// check input package validation
			if (packagesInputArray.length == 4) {
				packageId = packagesInputArray[0];
				packageWeight = Integer.parseInt(packagesInputArray[1]);
				distance = Integer.parseInt(packagesInputArray[2]);
				offerCode = packagesInputArray[3];
				Packages pack = new Packages(packageId, packageWeight, distance, offerCode);
				inputPackageDetails.add(pack);
			} else {
				System.out.println("Please enter correct details\n");
				i -= 1;
			}
		}
		return inputPackageDetails;

	}

	public void displayResults(List<Packages> resultPackage, int flag) {

		if (resultPackage.size() > 0) {
			DecimalFormat df = new DecimalFormat("#.00");
			df.format(resultPackage.get(0).getTime());
			List<Packages> result = resultPackage;
			// sort the packages
			Collections.sort(result, new Comparator<Packages>() {

				public int compare(Packages o1, Packages o2) {
					return o1.getPackageId().compareTo(o2.getPackageId());
				}
			});

			for (int i = 0; i < resultPackage.size(); i++) {
				if (flag == 1) {
					System.out.println(
							resultPackage.get(i).getPackageId() + "      " + resultPackage.get(i).getDisocuntAmount()
									+ "      " + resultPackage.get(i).getDeliveryPrce());
				} else {
					System.out.println(
							resultPackage.get(i).getPackageId() + "      " + resultPackage.get(i).getDisocuntAmount()
									+ "      " + resultPackage.get(i).getDeliveryPrce() + "       "
									+ df.format(resultPackage.get(i).getTime()));
				}

			}
		}

	}

}