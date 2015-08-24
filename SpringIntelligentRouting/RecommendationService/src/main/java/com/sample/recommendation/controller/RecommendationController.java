package com.sample.recommendation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.recommendation.model.Recommendation;
import com.sample.recommendation.util.SetProcTimeBean;


@RestController
public class RecommendationController {


	 @Autowired
	 private SetProcTimeBean setProcTimeBean;

	
	    /**
	     * Sample usage: curl $HOST:$PORT/recommendation?productId=1
	     *
	     * @param productId
	     * @return
	     */
	    @RequestMapping("/recommendation")
	    public List<Recommendation> getRecommendations(
	            @RequestParam(value = "productId",  required = true) int productId) {

	        int pt = setProcTimeBean.calculateProcessingTime();

	        sleep(pt);

	        List<Recommendation> list = new ArrayList<Recommendation>();
	        list.add(new Recommendation(productId, 1, "Author 1", 1, "Content 1"));
	        list.add(new Recommendation(productId, 2, "Author 2", 2, "Content 2"));
	        list.add(new Recommendation(productId, 3, "Author 3", 3, "Content 3"));

	        return list;
	    }

	    private void sleep(int pt) {
	        try {
	            Thread.sleep(pt);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * Sample usage:
	     *
	     *  curl "http://localhost:10002/set-processing-time?minMs=1000&maxMs=2000"
	     *
	     * @param minMs
	     * @param maxMs
	     */
	    @RequestMapping("/set-processing-time")
	    public void setProcessingTime(
	        @RequestParam(value = "minMs", required = true) int minMs,
	        @RequestParam(value = "maxMs", required = true) int maxMs) {

	        setProcTimeBean.setDefaultProcessingTime(minMs, maxMs);
	    }
}
