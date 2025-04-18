/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customersupport.auth;

/**
 *
 * @author yani_
 */

public class TokenGenerator {
    public static void main(String[] args) {
        String token = JwtUtil.generateToken("user123");
        System.out.println("Token JWT generated: ");
        System.out.println(token);
    }
}

