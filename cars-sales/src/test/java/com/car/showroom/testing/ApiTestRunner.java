package com.car.showroom.testing;


import com.intuit.karate.junit5.Karate;




public class ApiTestRunner {
	@Karate.Test
    Karate testAll() {
		return Karate.run().relativeTo(getClass());
	}

}
