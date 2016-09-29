package TestMethods

import com.jayway.restassured.builder.RequestSpecBuilder
import com.jayway.restassured.response.Response
import com.jayway.restassured.specification.RequestSpecification
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static com.jayway.restassured.RestAssured.baseURI
import static com.jayway.restassured.RestAssured.baseURI
import static com.jayway.restassured.RestAssured.given
import static com.jayway.restassured.RestAssured.given
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.equalTo

/**
 * Created by vinodk on 23-09-2016.
 */
class A {
    @BeforeMethod
    public setup() {
        baseURI = "http://dweb5s4.dev.affinnova.com/platformservices/j_spring_security_check/";
    }

    @Test
    public void m3() {
//        authentication=preemptive().basic("Vinod.Komeershetty.cons","jetfty");
        print "uff"
//        TestMethods.auth(baseURI);

        RequestSpecBuilder rbuild = new RequestSpecBuilder();
        rbuild.setContentType("application/x-www-form-urlencoded")
        rbuild.addParameter("j_username","Vinod.Komeershetty.consultant@nielsen.com")
        rbuild.addParameter("j_password","Affinnova")
        RequestSpecification rSpec = rbuild.build();
        Response resp = given().spec(rSpec).when().post(baseURI).then().statusCode(200);



//        given().auth().preemptive().basic("Vinod.Komeershetty.consultant@nielsen.com", "Affinnova").
//        given().auth().form("Vinod.Komeershetty.consultant@nielsen.com", "Affinnova",FormAuthConfig.springSecurity()).
//                expect().statusCode(200).
        given().    when().get("projects/56177").
                then().statusCode(200).
                then().body("project.id", equalTo("56177")).
                and().body("project.status", equalTo("Staging"))
    }
}
