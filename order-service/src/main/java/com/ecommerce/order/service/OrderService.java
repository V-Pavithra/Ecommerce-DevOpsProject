package com.ecommerce.order.service;

import com.ecommerce.order.dto.ProductDTO;
import com.ecommerce.order.dto.UserDTO;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id){
        return orderRepository.findById(id);
    }

    public Order saveOrder(Order order){

        // System.out.println("ORDER-SERVICE: Fetching User ID -> " + order.getUserId());
        // Fetch User Details
        String userServiceUrl = "http://user-service:8081/api/users/id/" + order.getUserId();
        // System.out.println("ORDER-SERVICE: Making API Call to -> " + userServiceUrl);

        UserDTO user = restTemplate.getForObject(userServiceUrl, UserDTO.class);

        if (user == null) {
            // System.out.println("REST API CALL FAILED: " + userServiceUrl);
            throw new RuntimeException("User not found with ID: " + order.getUserId());
        }
        // System.out.println("USER FETCHED SUCCESSFULLY: " + user);

        // Fetch Product Details
        String productServiceUrl = "http://product-service:8082/api/products/" + order.getProductId();
        // System.out.println("ORDER-SERVICE: Making API Call to -> " + productServiceUrl);

        ProductDTO product = restTemplate.getForObject(productServiceUrl, ProductDTO.class);

        if (product == null) {
            // System.out.println("REST API CALL FAILED: " + productServiceUrl);
            throw new RuntimeException("Product not found with ID: " + order.getProductId());
        }

        // Calculate Total Price
        double totalPrice = order.getQuantity() * product.getPrice();
        order.setTotalPrice(totalPrice);
        // Save Order
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}
