
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

}