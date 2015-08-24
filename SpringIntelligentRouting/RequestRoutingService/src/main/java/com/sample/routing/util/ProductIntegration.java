package com.sample.routing.util;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.sample.routing.model.Product;
import com.sample.routing.model.Recommendation;
import com.sample.routing.model.Review;

@Component
public class ProductIntegration {


    @Autowired
    Util util;

    private RestTemplate restTemplate = new RestTemplate();

    
    // PRODUCTS 
    public ResponseEntity<Product> getProduct(int productId) {

        URI uri = util.getServiceUrl("product-service", "http://localhost:8081");
        String url = uri.toString() + "/product/" + productId;
        ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
        Product product = response2Product(resultStr);
        return util.createOkResponse(product);
    }
    
    //Recommendation
    public ResponseEntity<List<Recommendation>> getRecommendations(int productId) {
            URI uri = util.getServiceUrl("recommendation-service", "http://localhost:8082");
            String url = uri.toString() + "/recommendation?productId=" + productId;
            ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
            List<Recommendation> recommendations = response2Recommendations(resultStr);
            return util.createOkResponse(recommendations);
    }


    // REVIEWS 
    public ResponseEntity<List<Review>> getReviews(int productId) {
        URI uri = util.getServiceUrl("review-service", "http://localhost:8083");
        String url = uri.toString() + "/review?productId=" + productId;
        ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
        List<Review> reviews = response2Reviews(resultStr);
        return util.createOkResponse(reviews);
    }

    // UTILS //

    private ObjectReader productReader = null;
    private ObjectReader getProductReader() {

        if (productReader != null) return productReader;

        ObjectMapper mapper = new ObjectMapper();
        return productReader = mapper.reader(Product.class);
    }

    private ObjectReader reviewsReader = null;
    private ObjectReader getReviewsReader() {
        if (reviewsReader != null) return reviewsReader;

        ObjectMapper mapper = new ObjectMapper();
        return reviewsReader = mapper.reader(new TypeReference<List<Review>>() {});
    }

    public Product response2Product(ResponseEntity<String> response) {
        try {
            return getProductReader().readValue(response.getBody());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: Gereralize with <T> method, skip objectReader objects!
    private List<Recommendation> response2Recommendations(ResponseEntity<String> response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List list = mapper.readValue(response.getBody(), new TypeReference<List<Recommendation>>() {});
            List<Recommendation> recommendations = list;
            return recommendations;

        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (RuntimeException re) {
            throw re;
        }
    }

    private List<Review> response2Reviews(ResponseEntity<String> response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List list = mapper.readValue(response.getBody(), new TypeReference<List<Review>>() {});
            List<Review> reviews = list;
            return reviews;

        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (RuntimeException re) {
            throw re;
        }
    }
}
