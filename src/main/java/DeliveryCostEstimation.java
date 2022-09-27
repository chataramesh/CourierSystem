
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class DeliveryCostEstimation {

	private final DeliveryCostCalculations deliveryCostCalc;
	public static List<Packages> resultPackage = new ArrayList<Packages>();

	public DeliveryCostEstimation() {
		deliveryCostCalc = new DeliveryCostCalculations();

	}

	public List<String> calculateDeliveryCost(List<Packages> inputPackages, int baseDeliveryCost, int noOfPackages) {
		DeliveryCostEstimation delcostEst = new DeliveryCostEstimation();

		boolean validationFlag = delcostEst.checkValidationForBaseDeliveryCostAndPackageCount(baseDeliveryCost,
				noOfPackages);
		if (validationFlag) {
			for (int i = 0; i < inputPackages.size(); i++) {
				if (inputPackages.get(i) != null)
					resultPackage
							.add(deliveryCostCalc.getDeliveryCostByPackage(baseDeliveryCost, inputPackages.get(i)));
			}
			return delcostEst.buildOuput(resultPackage);

		} else {
			System.out.println("INVALID BASEDELIVERY COST OR NO OF PACKAGES DETAILS....");
			return null;
		}

	}

	public List<String> buildOuput(List<Packages> resultPackage) {

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

	boolean checkValidationForBaseDeliveryCostAndPackageCount(int baseDelCost, int packageCount) {
		Validations validations = new Validations();
		return validations.checkValidationForBaseDeliveryCostAndPackageCount(baseDelCost, packageCount);

	}

	public void displayResults(List<String> resultPacks) {
		for (String st : resultPacks) {
			System.out.println(st);
		}

	}

	public static void main(String[] args) {
		int baseDeliveryCost, noOfPackages;
		DeliveryCostEstimation deliveryCostEstimation = new DeliveryCostEstimation();
		InputOutputDetails inputOutputDetails = new InputOutputDetails();

		// Take the inputs from CLI
		List<Packages> inputPackages = inputOutputDetails.getInputDetails();
		baseDeliveryCost = inputOutputDetails.baseDeliveryCost;
		noOfPackages = inputOutputDetails.noOfPackages;
		boolean validationFlag = deliveryCostEstimation
				.checkValidationForBaseDeliveryCostAndPackageCount(baseDeliveryCost, noOfPackages);
		if (validationFlag) {
			// calculateDeliveryCost
			System.out.println("\n Output\n");
			List<String> resultPacks = deliveryCostEstimation.calculateDeliveryCost(inputPackages, baseDeliveryCost,
					noOfPackages);

			// display results
			deliveryCostEstimation.displayResults(resultPacks);
		} else {
			System.out.println("Invalid Details ....!");
		}

	}
}