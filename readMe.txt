
1.Download the assignment and extract it

2.Open CMD and go to project path
	your local path\CourierServiceSystem

TESTING
3. Run the below command for test

	for single test 
		mvn -Dtest=DeliveryTimeEstimationTest test
				or
		mvn -Dtest=DeliveryCostEstimationTest test
		
	for all testcases
		mvn clean test
		

RUN THE INVIDIDUAL FILES
4.Open CMD and go to project path
	your local path\CourierServiceSystem\src\main\java
	Then run the below command for 1st question
		1.javac DeliveryCostEstimation.java
		
		2.java DeliveryCostEstimation
		
		3.pass the inputs
				baseDeliveryCost=100 noOfPackages=3
				pkgnam weight distance ofrcode
				PKG1 5 5 OFR001
				PKG2 15 5 OFR002
				PKG3 10 100 OFR003
	
	run the below command for 2nd question
		1.javac DeliveryTimeEstimation.java
		
		2.java DeliveryTimeEstimation
		
		3.pass the inputs
				baseDeliveryCost=100 noOfPackages=5
				
				pkgnam weight distance ofrcode
				PKG1 50 30 OFR001
				PKG2 75 125 OFFR0008
				PKG3 175 100 OFFR003
				PKG4 110 60 OFR002
				PKG5 155 95 NA
				
				noOfVechiles   maxSpeed  maxWeight
				2 70 200
		
	
	
	


		