package com.seeratsharma.bfhl.controller;

import com.seeratsharma.bfhl.model.BfhlRequest;
import com.seeratsharma.bfhl.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    private static final String EMAIL = "seerat4811.be23@chitkara.edu.in";

    @Autowired
    private AiService aiService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> handleRequest(@RequestBody BfhlRequest request) {

        Map<String, Object> response = new HashMap<>();
        response.put("official_email", EMAIL);

        try {
            int keysUsed = countKeys(request);
            if (keysUsed != 1) {
                throw new IllegalArgumentException("Invalid input");
            }

            if (request.getFibonacci() != null) {
                if (request.getFibonacci() <= 0) {
                    throw new IllegalArgumentException("Invalid input");
                }
                response.put("data", fibonacci(request.getFibonacci()));

            } else if (request.getPrime() != null) {
                validateList(request.getPrime());
                response.put("data", getPrimes(request.getPrime()));

            } else if (request.getLcm() != null) {
                validateList(request.getLcm());
                response.put("data", findLcm(request.getLcm()));

            } else if (request.getHcf() != null) {
                validateList(request.getHcf());
                response.put("data", findHcf(request.getHcf()));

            } else if (request.getAi() != null) {
                String answer = aiService.askAI(request.getAi());
                response.put("data", answer);
            }

            response.put("is_success", true);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("is_success", false);
            response.put("data", "Invalid input");
            return ResponseEntity.badRequest().body(response);
        }
    }

    private int countKeys(BfhlRequest request) {
        int count = 0;
        if (request.getFibonacci() != null) count++;
        if (request.getPrime() != null) count++;
        if (request.getLcm() != null) count++;
        if (request.getHcf() != null) count++;
        if (request.getAi() != null) count++;
        return count;
    }

    private void validateList(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    // Fibonacci
    private List<Integer> fibonacci(int n) {
        List<Integer> result = new ArrayList<>();
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            result.add(a);
            int temp = a + b;
            a = b;
            b = temp;
        }
        return result;
    }

    // Prime filter
    private List<Integer> getPrimes(List<Integer> nums) {
        List<Integer> primes = new ArrayList<>();
        for (int num : nums) {
            if (isPrime(num)) {
                primes.add(num);
            }
        }
        return primes;
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // HCF
    private int findHcf(List<Integer> nums) {
        int result = nums.get(0);
        for (int num : nums) {
            result = gcd(result, num);
        }
        return result;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // LCM
    private int findLcm(List<Integer> nums) {
        int result = nums.get(0);
        for (int num : nums) {
            result = lcm(result, num);
        }
        return result;
    }

    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
}
