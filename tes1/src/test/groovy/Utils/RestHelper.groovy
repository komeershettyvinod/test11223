package Utils

import com.jayway.restassured.RestAssured
import com.jayway.restassured.http.ContentType
import com.jayway.restassured.path.json.JsonPath
import com.jayway.restassured.response.Response
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeSuite

import static com.jayway.restassured.RestAssured.basePath

/**
 * Created by vinodk on 23-09-2016.
 */
public class RestHelper {

   //Global Setup Variables
    public static String path; //Rest request path
    public ConfigHelper cfg;

    /*
    ***Sets Base URI***
    Before starting the test, we should set the RestAssured.baseURI
    */
    @BeforeSuite
    public  void setBaseURI (){
        cfg=new ConfigHelper("build.properties");
        RestAssured.baseURI = cfg.getProperty("baseUrl");
        RestAssured.basePath = cfg.getProperty("basePath");


    }

    /*
   ***Sets base path***
   Before starting the test, we should set the RestAssured.basePath
   */
    public void setBasePath(String basePath){
            RestAssured.basePath = basePath;
    }

    /*
    ***Returns JsonPath object***
    * First convert the API's response to String type with "asString()" method.
    * Then, send this String formatted json response to the JsonPath class and return the JsonPath
    */
    public static JsonPath getJsonPath (Response res) {
        String json = res.asString();
        System.out.print("returned json: " + json +"\n");
        return new JsonPath(json);
    }

    private String createResource(String path, Object bodyPayload) {
        return given()
                .spec(spec)
                .body(bodyPayload)
                .when()
                .post(path)
                .then()
                .statusCode(201)
                .extract().header("location");
    }


    private <T> T getResourceWithClass(String locationHeader, Class<T> responseClass) {
        return given()
                .spec(spec)
                .when()
                .get(locationHeader)
                .then()
                .statusCode(200)
                .extract().as(responseClass);
    }

    /*
    ***Reset Base URI (after test)***
    After the test, we should reset the RestAssured.baseURI
    */
    @AfterSuite
    public static void resetBaseURIPATH (){
        RestAssured.baseURI = null;
        RestAssured.basePath = null;
    }

}
