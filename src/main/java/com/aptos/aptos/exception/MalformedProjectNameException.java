/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.exception;

/**
 *
 * @author Leonardo
 */
public class MalformedProjectNameException extends RuntimeException{
    public MalformedProjectNameException(String message){
        super(message);
    }
}
