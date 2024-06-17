/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Leonardo
 */
@Getter
@RequiredArgsConstructor
public class ApiError {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String message;
    private final String debugMessage;
    private final List<String> debugDetail;
}
