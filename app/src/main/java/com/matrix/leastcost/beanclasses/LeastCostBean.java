package com.matrix.leastcost.beanclasses;

/**
 * Created by sampath_k on 26/08/17.
 */
public class LeastCostBean {
    private String isValidInput;
    private int cost;
    private String leastPath;

    public LeastCostBean(String isValidInput, int cost, String leastPath) {
        this.isValidInput = isValidInput;
        this.cost = cost;
        this.leastPath = leastPath;
    }

    public LeastCostBean() {

    }

    public String getIsValidInput() {
        return isValidInput;
    }

    public void setIsValidInput(String isValidInput) {
        this.isValidInput = isValidInput;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getLeastPath() {
        return leastPath;
    }

    public void setLeastPath(String leastPath) {
        this.leastPath = leastPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LeastCostBean that = (LeastCostBean) o;

        if (cost != that.cost) return false;
        if (isValidInput != null ? !isValidInput.equals(that.isValidInput) : that.isValidInput != null)
            return false;
        return leastPath != null ? leastPath.equals(that.leastPath) : that.leastPath == null;

    }

    @Override
    public int hashCode() {
        int result = isValidInput != null ? isValidInput.hashCode() : 0;
        result = 31 * result + cost;
        result = 31 * result + (leastPath != null ? leastPath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LeastCostBean{" +
                "isValidInput='" + isValidInput + '\'' +
                ", cost=" + cost +
                ", leastPath='" + leastPath + '\'' +
                '}';
    }
}
