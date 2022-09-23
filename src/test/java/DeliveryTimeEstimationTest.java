
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class DeliveryTimeEstimationTest {
	int baseDeliveryCost = 100, inputPackageCount = 5;
	int noOfVehicles = 2, maxSpeed = 70, maxCarriableWeight = 200;
	DeliveryTimeEstimation delTimeEst = new DeliveryTimeEstimation();

	// check for the negative input values of noOfVehicles,
	// maxSpeed,maxCarriableWeight
	@Test
	void validationForVechicleAndSpeedAndMaxweightTest() {
		System.out.println("\nInput Validation For noOfVehicles maxSpeed,maxCarriableWeight\n");
		boolean expectedFalg = true;
		boolean resultFlag = delTimeEst.checkValidationForVechicleAndSpeedAndMaxweight(noOfVehicles, maxSpeed,
				maxCarriableWeight);
		assertEquals(expectedFalg, resultFlag);
		System.out.println("         Test success...!\n");
	}

	// check for the negative input values of baseDeliveryCost, inputPackageCount
	@Test
	void validationForBaseDeliveryCostAndPackageCountTest() {
		System.out.println("\nInput Validation For baseDeliveryCost, inputPackageCount\n");
		boolean expectedFalg = true;
		boolean resultFlag = delTimeEst.checkValidationForBaseDeliveryCostAndPackageCount(baseDeliveryCost,
				inputPackageCount);
		assertEquals(expectedFalg, resultFlag);
		System.out.println("         Test success...!\n");
	}

	/// Check the application flow
	@Test
	void deliveryTimeEstimationTest() {
		System.out.println("\nFunctional Flow \n");

		List<Packages> inputPackages = new ArrayList<Packages>();

		inputPackages.add(new Packages("PKG1", 50, 30, "OFR001"));
		inputPackages.add(new Packages("PKG3", 175, 100, "OFR003"));
		inputPackages.add(new Packages("PKG2", 75, 125, "OFR008"));
		inputPackages.add(new Packages("PKG5", 155, 95, "NA"));
		inputPackages.add(new Packages("PKG4", 110, 60, "OFR002"));

		List<String> expectedResult = new ArrayList<String>();
		expectedResult.add("PKG1      0      750       3.98");
		expectedResult.add("PKG2      0      1475       1.78");
		expectedResult.add("PKG3      0      2350       1.42");
		expectedResult.add("PKG4      105      1395       .85");
		expectedResult.add("PKG5      0      2125       4.19");

		List<String> resultSet = delTimeEst.calculateDeliveryTime(baseDeliveryCost, inputPackages, noOfVehicles,
				maxSpeed, maxCarriableWeight);
		System.out.println("	Expected Ouput\n");
		for (String s : resultSet) {
			System.out.println("	" + s);
		}
		assertEquals(expectedResult, resultSet);

		System.out.println("\n	Test success...!");
		System.out.println("\n");

	}

}
