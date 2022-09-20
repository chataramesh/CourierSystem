package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class DeliveryTimeEstimationTest {

	@Test
	void deliveryTimeEstimationTest() {
		System.out.println("\n\n");
		int baseDeliveryCost = 100;
		int noOfVehicles = 2, maxSpeed = 70, maxCarriableWeight = 200;

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

		DeliveryTimeEstimation delTimeEst = new DeliveryTimeEstimation();
		List<String> resultSet = delTimeEst.calculateDeliveryTime(baseDeliveryCost, inputPackages, noOfVehicles,
				maxSpeed, maxCarriableWeight);

		for (String s : resultSet) {
			System.out.println(s);
		}
		assertEquals(expectedResult, resultSet);

		System.out.println("\nTest success...!");
		System.out.println("\n\n");

	}

}
