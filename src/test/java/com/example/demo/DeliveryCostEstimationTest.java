package com.example.demo;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.DeliveryCostEstimation;

import org.junit.jupiter.api.Test;

class DeliveryCostEstimationTest {
	
	@Test
	void deliveryCostEstimationTesting() {
		System.out.println("\n\n");
		DeliveryCostEstimation deliveryCostEstimation = new DeliveryCostEstimation();

		List<String> expectedOuput = new ArrayList<String>();
		expectedOuput.add("PKG1      0      175");
		expectedOuput.add("PKG2      0      275");
		expectedOuput.add("PKG3      35      665");

		List<Packages> inputPacks = new ArrayList<Packages>();

		inputPacks.add(new Packages("PKG1", 5, 5, "OFR001"));
		inputPacks.add(new Packages("PKG2", 15, 5, "OFR002"));
		inputPacks.add(new Packages("PKG3", 10, 100, "OFR003"));
		int baseDeliveryCost = 100, noOfPackages = 3;

		List<String> result = deliveryCostEstimation.calculateDeliveryCost(inputPacks, baseDeliveryCost, noOfPackages);
		
		for(String res:result)
		{
			System.out.println(res);
		}
		assertEquals(expectedOuput, result);
		System.out.println("\nTest success...!\n");
		System.out.println("\n\n");
	}

}
