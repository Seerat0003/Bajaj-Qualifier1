package com.seeratsharma.bfhl.model;

import java.util.List;

public class BfhlRequest {

    private Integer fibonacci;
    private List<Integer> prime;
    private List<Integer> lcm;
    private List<Integer> hcf;
    private String ai;

    public Integer getFibonacci() {
        return fibonacci;
    }

    public void setFibonacci(Integer fibonacci) {
        this.fibonacci = fibonacci;
    }

    public List<Integer> getPrime() {
        return prime;
    }

    public void setPrime(List<Integer> prime) {
        this.prime = prime;
    }

    public List<Integer> getLcm() {
        return lcm;
    }

    public void setLcm(List<Integer> lcm) {
        this.lcm = lcm;
    }

    public List<Integer> getHcf() {
        return hcf;
    }

    public void setHcf(List<Integer> hcf) {
        this.hcf = hcf;
    }

    public String getAi() {
        return ai;
    }

    public void setAi(String ai) {
        this.ai = ai;
    }
}
