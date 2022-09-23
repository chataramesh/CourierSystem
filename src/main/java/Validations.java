
public class Validations {

	public boolean checkValidationForBaseDeliveryCostAndPackageCount(int baseDeliveryCost, int noOfPackages) {

		if (baseDeliveryCost < 0 || noOfPackages < 0)
			return false;
		return true;
	}

	public boolean checkValidationForVechicleAndSpeedAndMaxweight(int noOfVehicles, int maxSpeed,
			int maxCarriableWeight) {
		if (noOfVehicles < 0 || maxSpeed < 0 || maxCarriableWeight < 0)
			return false;
		return true;
	}

}
