package com.trap_music.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.trap_music.entities.Users;
import com.trap_music.services.UsersService;

import jakarta.servlet.http.HttpSession;


@RestController
public class PaymentController {

    @PostMapping("/createOrder")
    @ResponseBody
    public String createOrder() {
        Order order = null;
        try {
            RazorpayClient razorpay = new RazorpayClient("rzp_test_bFLRfwc71TUtp7", "Qbl3K7HL7JVB4VRSrJFlacyo");

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", 500);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "receipt#1");
            JSONObject notes = new JSONObject();
            notes.put("notes_key_1", "Tea, Earl Grey, Hot");
            orderRequest.put("notes", notes);

            order = razorpay.orders.create(orderRequest);
        } catch (Exception e) {
            System.out.println("Exception while creating order");
        }
        return order.toString();
    }

    @PostMapping("/verify")
    @ResponseBody
    public boolean verifyPayment(@RequestParam String orderId, @RequestParam String paymentId,
            @RequestParam String signature) {
        try {
            // Initialize Razorpay client with your API key and secret
            RazorpayClient razorpayClient = new RazorpayClient("rzp_test_bFLRfwc71TUtp7", "Qbl3K7HL7JVB4VRSrJFlacyo");

            // Create a signature verification data string
            String verificationData = orderId + "|" + paymentId;

            // Use Razorpay's utility function to verify the signature
            boolean isValidSignature = Utils.verifySignature(verificationData, signature, "Qbl3K7HL7JVB4VRSrJFlacyo");

            return isValidSignature;
        } catch (RazorpayException e) {
            e.printStackTrace();
            return false;
        }
    }

    // payment-success -> update to premium user
    // Assuming you have a UsersService to handle user-related operations
    @GetMapping("/payment-success")
    public String paymentSuccess(HttpSession session, UsersService userService) {
        String email = (String) session.getAttribute("email");
        Users user = userService.getUser(email);
        user.setPremiumAccount(true);
        userService.updateUser(user);
        return "login"; // Redirect to login page or any other page as needed
    }

    // payment-failure -> redirect to login
    @GetMapping("/payment-failure")
    public String paymentFailure() {
        // Redirect to login page or any other page as needed
        return "login";
    }
}
