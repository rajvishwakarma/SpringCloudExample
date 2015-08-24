package com.sample.routing.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.routing.model.Product;
import com.sample.routing.model.ProductAggregated;
import com.sample.routing.model.Recommendation;
import com.sample.routing.model.Review;
import com.sample.routing.util.ProductIntegration;
import com.sample.routing.util.Util;

@RestController
public class RoutingController {


    @Autowired
    ProductIntegration integration;

    @Autowired
    Util util;

    @RequestMapping("/")
    public String getProduct() {
        return "{\"timestamp\":\"" + new Date() + "\",\"content\":\"Hello from ProductAPi\"}";
    }

    @RequestMapping("/product/{productId}")
    public ResponseEntity<ProductAggregated> getProduct(@PathVariable int productId) {

        // 1. First get mandatory product information
        ResponseEntity<Product> productResult = integration.getProduct(productId);

        if (!productResult.getStatusCode().is2xxSuccessful()) {
            // We can't proceed, return whatever fault we got from the getProduct call
            return util.createResponse(null, productResult.getStatusCode());
        }

        // 2. Get optional recommendations
        List<Recommendation> recommendations = null;
        try {
            ResponseEntity<List<Recommendation>> recommendationResult = integration.getRecommendations(productId);
            if (!recommendationResult.getStatusCode().is2xxSuccessful()) {
                // Something went wrong with getRecommendations, simply skip the recommendation-information in the response
            } else {
                recommendations = recommendationResult.getBody();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }


        // 3. Get optional reviews
        ResponseEntity<List<Review>> reviewsResult = integration.getReviews(productId);
        List<Review> reviews = null;
        if (!reviewsResult.getStatusCode().is2xxSuccessful()) {
            // Something went wrong with getReviews, simply skip the review-information in the response
        } else {
            reviews = reviewsResult.getBody();
        }

        return util.createOkResponse(new ProductAggregated(productResult.getBody(), recommendations, reviews));
    }
}
