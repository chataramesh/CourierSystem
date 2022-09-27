
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class DeliveryCostEstimationTest {
	int baseDeliveryCost = 100, inputPackageCount = 3;
	List<Packages> inputPacks = new ArrayList<Packages>();
	DeliveryCostEstimation deliveryCostEstimation = new DeliveryCostEstimation();

	// check for the negative input values of baseDeliveryCost, inputPackageCount
	@Test
	void validationForBaseDeliveryCostAndPackageCountTest() throws Exception {
		System.out.println("\nValidation For baseDeliveryCost, inputPackageCount\n");
		boolean expectedFalg = true;
		boolean resultFlag = deliveryCostEstimation.checkValidationForBaseDeliveryCostAndPackageCount(baseDeliveryCost,
				inputPackageCount);
		assertEquals(expectedFalg, resultFlag);
		System.out.println("         Test success...!\n");
	}

	// Testing for functional flow
	@Test
	void deliveryCostEstimationTesting() {
		System.out.println("\nValidation For functional flow\n");
		List<String> expectedOuput = new ArrayList<String>();
		expectedOuput.add("PKG1      0      175");
		expectedOuput.add("PKG2      0      275");
		expectedOuput.add("PKG3      35      665");

		inputPacks.add(new Packages("PKG1", 5, 5, "OFR001"));
		inputPacks.add(new Packages("PKG2", 15, 5, "OFR002"));
		inputPacks.add(new Packages("PKG3", 10, 100, "OFR003"));

		List<String> result = deliveryCostEstimation.calculateDeliveryCost(inputPacks, baseDeliveryCost,
				inputPackageCount);

		assertEquals(expectedOuput, result);
		System.out.println("         Test success...!\n");
	}

}
