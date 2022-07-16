package ApiTests.Services;

import APIs.Endpoints;
import ApiTests.Models.Activity;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class ActivityService {
    //private final String baseUri="https://fakerestapi.azurewebsites.net";
   // private final String basePath ="/api/v1/Activities";
    private RequestSpecification requestSpecification;

    public ActivityService() {
        requestSpecification= new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setBaseUri(Endpoints.BASE_URL)
                .setBasePath(Endpoints.ACTIVITY_SERVICE)
                .setContentType(ContentType.JSON)
                .build();
    }


    public void SaveNewActivty(Activity activity) {

        Response response=  RestAssured
                .given()
                    .spec(requestSpecification)
                    .body(activity)
                .when()
                    .post()
                .then()
                    .log().all()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .extract().response();
       System.out.println(response.jsonPath());
    }

    public void GetAllActivities() {
        List<Activity> actActivities= Arrays.asList( RestAssured
                .given()
                    .spec(requestSpecification)
                .when()
                    .get()
                .then()
                    .log().all()
                    .assertThat().statusCode(HttpStatus.SC_OK)
                    .extract().body().as(Activity[].class));

//        //for(Activity expActivity:expActivities){
//            Assert.assertTrue(actActivities.contains(expActivities));
//       // }

    }

    public void GetSingleActivity(int id) {
        Response response=  RestAssured
                .given()
                .spec(requestSpecification)

                .when()
                .get("/"+id)
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .extract().response();
        int Id = response.jsonPath().getInt("id");
        Assert.assertEquals(Id,id);
        System.out.println(Id);
    }

    public void UpdateActivity(int id, Activity activity) {
        Activity updatedActivity=  RestAssured
                .given()
                .spec(requestSpecification)
                .body(activity)
                .when()
                .put("/"+id)
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .extract().body().as(Activity.class);
        Assert.assertEquals(updatedActivity,activity);
    }

    public void DeleteActivity(int id) {
        RestAssured
                .given()
                .spec(requestSpecification)
                .when()
                .delete("/"+id)
                .then()
                .log().all()
                .assertThat().statusCode(HttpStatus.SC_OK);
    }
}
