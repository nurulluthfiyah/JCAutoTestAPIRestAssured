package com.juaracoding;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestAPITheMovieDB {
    String baseUrl = "https://api.themoviedb.org";
    String key = "70f3cd9cfd56bd50c1ba3fed36e81508";
    String endPoint(String endPoint) {
        return baseUrl + endPoint;
    }

    @Test
    public void testGetMovieNowPlaying(){
        given()
                .queryParam("api_key",key)
                .when()
                .get(endPoint("/3/movie/now_playing"))
                .then()
                .statusCode(200)
                .body("results.id[1]",equalTo(667538));
    }

    @Test
    public void testGetMoviePopular(){
        given()
                .queryParam("api_key",key)
                .when()
                .get(endPoint("/3/movie/popular"))
                .then()
                .statusCode(200)
                .body("results.id[0]",equalTo(569094));
    }

    @Test
    public void testPostMovieRating(){
        JSONObject request = new JSONObject();
        request.put("value","8.50");
        System.out.println(request.toJSONString());

        given()
                .header("Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MGYzY2Q5Y2ZkNTZiZDUwYzFiYTNmZWQzNmU4MTUwOCIsInN1YiI6IjY0ZGI4ZDgwMDAxYmJkMDQxOGMzOGMwMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.1sBDUcggs3mhFNA55c-UTROBINRiwVJaV7wLVcxhiOo")
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post((endPoint("/3/movie/678512/rating")))
                .then()
                .statusCode(201)

                .log().all();
    }


}
