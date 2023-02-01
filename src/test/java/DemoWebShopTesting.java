import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;



public class DemoWebShopTesting {

    static Faker faker = new Faker(new Locale("en"));
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String cookieString = "D627AECC77161EB7845BF363FD63E42799278BFEDE9A4BB07818F99375CDDF808640CE3" +
            "18D633F9BDD080C5CB923AB04397863CF2F309EF88B483EA882717CADA7F9FE61B67075AF2FA3C12" +
            "3B27022C5C4D3CC9CB27DF3F8DFDB6A20FED6EFF7EE5A6DB02E21F269DCBA5DCDEFB9AE448B72F98A0" +
            "901FFBC56D08DDCE12A58AE86E037C85350928F2BC0A33CE4D2A0A78E9D073B9019EB8DFF48167978CCAE6E;";
    String body = "billing_address_id=" +
            "&BillingNewAddress.Id=0" +
            "&BillingNewAddress.FirstName=" + firstName +
            "&BillingNewAddress.LastName=" + lastName +
            "&BillingNewAddress.Email=example1000%40gmail.com" +
            "&BillingNewAddress.Company=" +
            "&BillingNewAddress.CountryId=82" +
            "&BillingNewAddress.StateProvinceId=0" +
            "&BillingNewAddress.City=Stambul" +
            "&BillingNewAddress.Address1=Kabul+Street" +
            "&BillingNewAddress.Address2=" +
            "&BillingNewAddress.ZipPostalCode=0900" +
            "&BillingNewAddress.PhoneNumber=%2B79222222222" +
            "&BillingNewAddress.FaxNumber=";
    String endPoint = "checkout/OpcSaveBilling/";

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://demowebshop.tricentis.com/";
        Configuration.baseUrl = "https://demowebshop.tricentis.com/";

    }

    @Test
    void addShippingAdressOnCheckout() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie("NOPCOMMERCE.AUTH", cookieString)
                .body(body)
                .when()
                .post(endPoint)
                .then()
                .log().all()
                .statusCode(200);
    }
}
