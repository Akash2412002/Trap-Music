package com.trap_music.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.trap_music.entity.User;
import com.trap_music.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
public class SubscriptionController {

    @Autowired
    private UserService userService;

    @PostMapping("/createOrder")
    @ResponseBody
    public String createOrder() {
        Order order = null;
        try {
            RazorpayClient razorpay = new RazorpayClient("rzp_test_bFLRfwc71TUtp7", "Qbl3K7HL7JVB4VRSrJFlacyo");

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", 500); // Example amount, modify as needed
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "receipt#1");
            JSONObject notes = new JSONObject();
            notes.put("notes_key_1", "Tea, Earl Grey, Hot");
            orderRequest.put("notes", notes);

            order = razorpay.orders.create(orderRequest);
        } catch (Exception e) {
            System.out.println("Exception while creating order");
        }
        return order != null ? order.toString() : "";
    }

    @PostMapping("/verify")
    @ResponseBody
    public boolean verifyPayment(@RequestParam String orderId, @RequestParam String paymentId,
            @RequestParam String signature) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient("rzp_test_bFLRfwc71TUtp7", "Qbl3K7HL7JVB4VRSrJFlacyo");

            String verificationData = orderId + "|" + paymentId;
            boolean isValidSignature = Utils.verifySignature(verificationData, signature, "Qbl3K7HL7JVB4VRSrJFlacyo");

            return isValidSignature;
        } catch (RazorpayException e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/payment-success")
    public String paymentSuccess(HttpSession session) {
        String email = (String) session.getAttribute("email");
        User user = userService.getUser(email);
        if (user != null) {
            user.setPremiumAccount(true);
            userService.updateUser(user);
            return "redirect:/customerhomepage"; // Redirect to customer homepage or any other page as needed
        } else {
            return "redirect:/login"; // Redirect to login page if user not found
        }
    }

    @GetMapping("/payment-failure")
    public String paymentFailure() {
        return "redirect:/login"; // Redirect to login page on payment failure
    }
}
