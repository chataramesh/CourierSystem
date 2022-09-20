package com.example.demo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DeliveryTimeEstimation extends DeliveryCostCalculations {

	public static List<Packages> trackPackages = new ArrayList<Packages>();
	public static List<Packages> resultPackages = new ArrayList<Packages>();
	public static int noOfVehicles = 0, maxSpeed = 0, maxCarriableWeight = 0;
	static Map<String, Double> vechicleTrack = new HashMap<String, Double>();

	public DeliveryTimeEstimation() {
	}

	public DeliveryTimeEstimation(int noOfVehicles, int maxSpeed, int maxCarriableWeight) {
		this.maxCarriableWeight = maxCarriableWeight;
		this.maxSpeed = maxSpeed;
		this.noOfVehicles = noOfVehicles;
	}

	public DeliveryTimeEstimation(int baseDeliveryCost, int noOfPackages) {
		super(baseDeliveryCost, noOfPackages);
	}

	public List<Packages> sortPackagesByWeight(List<Packages> inputList) {
		int length = inputList.size();
		Packages temp = null;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length - 1; j++) {
				if (inputList.get(j).getPackageWeight() > inputList.get(j + 1).getPackageWeight()) {
					temp = inputList.get(j);
					inputList.set(j, inputList.get(j + 1));
					inputList.set(j + 1, temp);
				} else if (inputList.get(j).getPackageWeight() == inputList.get(j + 1).getPackageWeight()) {
					if (inputList.get(j).getDistance() > inputList.get(j + 1).getDistance()) {
						temp = inputList.get(j);
						inputList.set(j, inputList.get(j + 1));
						inputList.set(j + 1, temp);
					}
				}
			}
		}
		return inputList;
	}

	private static void addTimeTPackage(double sum, String packageId) {

		for (int i = 0; i < resultPackages.size(); i++) {
			if (resultPackages.get(i) != null) {
				if (resultPackages.get(i).getPackageId().equals(packageId))
					resultPackages.get(i).setTime(sum);
			}
		}
	}

	private static double getPackageDeliveryTime(Packages res, int maxSpeed) {
		double totalSum = 0, maxTime = 0;

		if (res != null) {

			double dist = res.getDistance(), maxS = maxSpeed;
			double s = Math.floor((dist / maxS) * 100);
			totalSum = (s / 100);
			if (maxTime < totalSum)
				maxTime = totalSum;
		}

		return totalSum;
	}

	private static String getVechicelMinimumTime(Map<String, Double> vechicleTrack) {

		double minTime = 9999999;
		String vcname = "";
		Map<String, Double> minVechicle = new HashMap<String, Double>();
		for (Map.Entry<String, Double> map : vechicleTrack.entrySet()) {
			if (map.getValue() < minTime) {
				minTime = map.getValue();
				vcname = map.getKey();
			}
		}

		minVechicle.put(vcname, minTime);
		return vcname;
	}

	private void calculateVechicleTimeAndPackageCost(int baseDeliveryCost, List<Packages> packageList) {
		DeliveryCostCalculations deliveryCostCalculations = new DeliveryCostCalculations();
		String minTimeVechicleName = getVechicelMinimumTime(vechicleTrack);
		double vechicle_sum = 0, max = 0, cur_oldTime = 0;
		for (Packages pack : packageList) {
			vechicleTrack = new TreeMap<String, Double>(vechicleTrack);
			vechicle_sum = 0;
			max = 0;
			Packages st = deliveryCostCalculations.getDeliveryCostByPackage(baseDeliveryCost, pack);
			resultPackages.add(st);
			vechicle_sum = getPackageDeliveryTime(pack, maxSpeed);
			if (max < vechicle_sum)
				max = vechicle_sum;
			addTimeTPackage((vechicle_sum + vechicleTrack.get(minTimeVechicleName)), st.getPackageId());
		}
		if (minTimeVechicleName != "") {
			cur_oldTime = vechicleTrack.get(minTimeVechicleName) + (max * 2);
			vechicleTrack.put(minTimeVechicleName, cur_oldTime);
		}
	}

	public static List<Packages> findMaxWeightPackages(List<Packages> pack, int maxSum) {
		List<Packages> maxPack = new ArrayList<Packages>();
		int i, w;
		int K[][] = new int[pack.size() + 1][maxSum + 1];
		for (i = 0; i <= pack.size(); i++) {
			for (w = 0; w <= maxSum; w++) {
				if (i == 0 || w == 0)
					K[i][w] = 0;
				else if (pack.get(i - 1).getPackageWeight() <= w) {
					K[i][w] = Math.max(
							pack.get(i - 1).getPackageWeight() + K[i - 1][w - pack.get(i - 1).getPackageWeight()],
							K[i - 1][w]);
				}

				else
					K[i][w] = K[i - 1][w];
			}
		}
		int res = K[pack.size()][maxSum];
		w = maxSum;
		for (i = pack.size(); i > 0 && res > 0; i--) {

			if (res == K[i - 1][w])
				continue;
			else {
				maxPack.add(pack.get(i - 1));
				res = res - pack.get(i - 1).getPackageWeight();
				w = w - pack.get(i - 1).getPackageWeight();
			}
		}
		return maxPack;
	}

	public List<String> calculateDeliveryTime(int baseDeliveryCost, List<Packages> inputPackages, int noOfVehicles,
			int maxSpeed, int maxCarriableWeight) {
		DeliveryTimeEstimation delTimeEst = new DeliveryTimeEstimation();
		String vechileName;

		new DeliveryTimeEstimation(noOfVehicles, maxSpeed, maxCarriableWeight);
		// vehicle initialization with parameters for vehicle time tracking

		for (int i = 0; i < noOfVehicles; i++) {

			vechileName = "vc" + String.valueOf(i + 1);
			vechicleTrack.put(vechileName, (double) (0));
		}

		List<Packages> sortedPackagesByWeightList = delTimeEst.sortPackagesByWeight(inputPackages);

		for (int i = 0; i < sortedPackagesByWeightList.size(); i++) {
			trackPackages.add(sortedPackagesByWeightList.get(i));
		}
		while (trackPackages.size() > 0) {
			List<Packages> maxWeighPackages = findMaxWeightPackages(trackPackages, maxCarriableWeight);

			for (Packages pck : maxWeighPackages) {
				trackPackages.remove(pck);
			}
			calculateVechicleTimeAndPackageCost(baseDeliveryCost, maxWeighPackages);
		}

		return displayResults(resultPackages);

	}

	List<String> displayResults(List<Packages> resultPackage) {
		List<String> resultPack = new ArrayList<String>();

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
				String str = "";
				str += resultPackage.get(i).getPackageId() + "      " + resultPackage.get(i).getDisocuntAmount()
						+ "      " + resultPackage.get(i).getDeliveryPrce() + "       "
						+ df.format(resultPackage.get(i).getTime());
				resultPack.add(str);
			}

		}
		return resultPack;

	}

	public static void main(String[] args) {
		int baseDeliveryCost, noOfPackages;

		// taking the inputs
		InputOutputDetails inputOutputDetails = new InputOutputDetails();
		List<Packages> inputPackages = inputOutputDetails.getInputDetails();
		baseDeliveryCost = inputOutputDetails.baseDeliveryCost;
		noOfPackages = inputOutputDetails.noOfPackages;

		DeliveryTimeEstimation delTimeEst = new DeliveryTimeEstimation(baseDeliveryCost, noOfPackages);
		// Taking inputs for the noOfVehicles maxSpeed maxCarriableWeight
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Enter\nNo of Vechicles    maxSpeed   MaxCarriableWeight");
			String[] inputline = scanner.nextLine().split(" ");
			if (inputline.length != 3) {
				System.out.println("Please enter the correct details");
			} else {
				noOfVehicles = Integer.parseInt(inputline[0]);
				maxSpeed = Integer.parseInt(inputline[1]);
				maxCarriableWeight = Integer.parseInt(inputline[2]);
				new DeliveryTimeEstimation(noOfVehicles, maxSpeed, maxCarriableWeight);
			}
		} catch (NumberFormatException e) {

			e.printStackTrace();
		}

		// calculations
		List<String> result = delTimeEst.calculateDeliveryTime(baseDeliveryCost, inputPackages, noOfVehicles, maxSpeed,
				maxCarriableWeight);

		// display results
		System.out.println("\nFinal resultPackages:\n ");
		for (String res : result) {
			System.out.println(res);
		}

	}
}
