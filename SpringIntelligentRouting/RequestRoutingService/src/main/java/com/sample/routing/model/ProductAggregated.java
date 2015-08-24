package com.sample.routing.model;

import java.util.ArrayList;
import java.util.List;

public class ProductAggregated {
	private int productId;
	private String name;
	private int weight;
	private List<RecommendationSummary> recommendations;
	private List<ReviewSummary> reviews;

	public ProductAggregated(Product product, List<Recommendation> recommendations, List<Review> reviews) {

		// 1. Setup product info
		this.productId = product.getProductId();
		this.name = product.getName();
		this.weight = product.getWeight();

		// 2. Copy summary recommendation info, if available
		this.recommendations = new ArrayList<RecommendationSummary>();
		for (Recommendation recommendation : recommendations) {
			this.recommendations.add(new RecommendationSummary(recommendation.getRecommendationId(), recommendation.getAuthor(), recommendation.getRate()));
		}

		// 3. Copy summary review info, if available
		this.reviews = new ArrayList<ReviewSummary>();
		for (Review review : reviews) {
			this.reviews.add(new ReviewSummary(review.getReviewId(), review.getAuthor(), review.getSubject()));
		}
	}

	public int getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}

	public List<RecommendationSummary> getRecommendations() {
		return recommendations;
	}

	public List<ReviewSummary> getReviews() {
		return reviews;
	}
}
