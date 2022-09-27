import java.io.File;
import java.util.Scanner;

public class Discount {
	private int discountPercentage1, discountPercentage2, discountPercentage3;
	private String discountOfferCode1, discountOfferCode2, discountOfferCode3;
	private int distanceLower1, distanceUpper1, distanceLower2, distanceUpper2, distanceLower3, distanceUpper3;
	private int weightLower1, weightUpper1, weightLower2, weightUpper2, weightLower3, weightUpper3;

	public Discount() {
		loadOfferCodes();
	}

	public void loadOfferCodes() {
		try {
			Scanner sc = new Scanner(new File("src/main/java/OfferCodes.csv"));
			int counter = 0;
			while (sc.hasNext()) {
				if (counter == 0)
					sc.nextLine();
				else {
					String[] values = sc.nextLine().split(",");
					if (counter == 3) {
						discountOfferCode3 = values[0];
						discountPercentage3 = Integer.parseInt(values[1]);
						distanceLower3 = Integer.parseInt(values[2]);
						distanceUpper3 = Integer.parseInt(values[3]);
						weightLower3 = Integer.parseInt(values[4]);
						weightUpper3 = Integer.parseInt(values[5]);

					} else if (counter == 2) {
						discountOfferCode2 = values[0];
						discountPercentage2 = Integer.parseInt(values[1]);
						distanceLower2 = Integer.parseInt(values[2]);
						distanceUpper2 = Integer.parseInt(values[3]);
						weightLower2 = Integer.parseInt(values[4]);
						weightUpper2 = Integer.parseInt(values[5]);
					} else if (counter == 1) {

						discountOfferCode1 = values[0];
						discountPercentage1 = Integer.parseInt(values[1]);
						distanceLower1 = Integer.parseInt(values[2]);
						distanceUpper1 = Integer.parseInt(values[3]);
						weightLower1 = Integer.parseInt(values[4]);
						weightUpper1 = Integer.parseInt(values[5]);
					}

				}
				counter++;
			}
			sc.close();
		} catch (Exception e) {

		}

	}

	public int getDiscountPercentage(int packageWeight, int distance, String discountOfferCode) {
		if (discountOfferCode.equals(discountOfferCode1) || discountOfferCode.equals(discountOfferCode2)
				|| discountOfferCode.equals(discountOfferCode3)) {
			if ((distance >= distanceLower3 && distance <= distanceUpper3)
					&& (packageWeight >= weightLower3 && packageWeight <= weightUpper3)
					&& discountOfferCode.equals(discountOfferCode3)) {
				return discountPercentage3;
			} else if ((distance >= distanceLower2 && distance <= distanceUpper2)
					&& (packageWeight >= weightLower2 && packageWeight <= weightUpper2)
					&& discountOfferCode.equals(discountOfferCode2)) {
				return discountPercentage2;
			} else if ((distance > distanceLower1 && distance < distanceUpper1)
					&& (packageWeight >= weightLower1 && packageWeight <= weightUpper1)
					&& discountOfferCode.equals(discountOfferCode1)) {
				return discountPercentage1;
			} else {
				return 0;
			}
		} else {
			return 0;
		}

	}
}
