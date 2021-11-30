import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class GetAllBeers {

    @Test
    public void getAllBeers() {
        Response response = given()
                .get("https://api.punkapi.com/v2/beers");
        System.out.println("Status Code is " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test
    public void getResponseBodyParameters() {
        Response response = given()
                .get("https://api.punkapi.com/v2/beers");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is " + responseBody);

        List<Object> list;
        list = response.jsonPath().getList("$");
        System.out.println(response.jsonPath().getList("$"));
        int responseObjectsSize = list.size();
        System.out.println("JsonResponse Size: " + responseObjectsSize + "\n");

        String[] params = {"id", "name", "description", "abv"};
        for (String param : params) {
            list = response.jsonPath().getList(param);
            System.out.println(list);
            int checkParamInListSize = list.size();
            System.out.println(checkParamInListSize);
            if (checkParamInListSize == responseObjectsSize) {
                System.out.println("Size of " + param + " in JSON: " + checkParamInListSize
                        + " is equal to JsonResponse Size: " + responseObjectsSize + ". Validated successfully!\n");
            } else {
                System.out.println("Size of " + param + " in JSON: " + checkParamInListSize
                        + " is not equal to JsonResponse Size: " + responseObjectsSize + ". Validation failed!\n");
            }

//        System.out.println("Response Body ID is "+ response.jsonPath().get("id"));
//        Assert.assertEquals(responseBody.contains("id"),true);
//        System.out.println("Response Body name is "+ response.jsonPath().get("name"));
//        Assert.assertEquals(responseBody.contains("name"),true);
//        System.out.println("Response Body description is "+ response.jsonPath().get("description"));
//        Assert.assertEquals(responseBody.contains("description"),true);
//        System.out.println("Response Body abv is "+ response.jsonPath().get("abv"));
//        Assert.assertEquals(responseBody.contains("abv"),true);


        }
    }
}
