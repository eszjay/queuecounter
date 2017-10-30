/*
 * CC BY-SA github.com/eszjay
 */

package com.github.eszjay.queuecounter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigInteger;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-22T23:47:33.197Z")

public class CurrentResults {
    @JsonProperty("totalCount")
    private Long totalCount = null;

    @JsonProperty("aboveThresholdCount")
    private Long aboveThresholdCount = null;

    @JsonProperty("max")
    private BigInteger max = null;

    @JsonProperty("min")
    private BigInteger min = null;

    @JsonProperty("threshold")
    private BigInteger threshold = null;

    public CurrentResults totalCount(Long totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    /**
     * Get totalCount
     *
     * @return totalCount
     **/
    @ApiModelProperty(value = "")


    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public CurrentResults aboveThresholdCount(Long aboveThresholdCount) {
        this.aboveThresholdCount = aboveThresholdCount;
        return this;
    }

    /**
     * Get aboveThresholdCount
     *
     * @return aboveThresholdCount
     **/
    @ApiModelProperty(value = "")


    public Long getAboveThresholdCount() {
        return aboveThresholdCount;
    }

    public void setAboveThresholdCount(Long aboveThresholdCount) {
        this.aboveThresholdCount = aboveThresholdCount;
    }

    public CurrentResults max(BigInteger max) {
        this.max = max;
        return this;
    }

    /**
     * Get max
     *
     * @return max
     **/
    @ApiModelProperty(value = "")


    public BigInteger getMax() {
        return max;
    }

    public void setMax(BigInteger max) {
        this.max = max;
    }

    public CurrentResults min(BigInteger min) {
        this.min = min;
        return this;
    }

    /**
     * Get min
     *
     * @return min
     **/
    @ApiModelProperty(value = "")


    public BigInteger getMin() {
        return min;
    }

    public void setMin(BigInteger min) {
        this.min = min;
    }

    public CurrentResults threshold(BigInteger threshold) {
        this.threshold = threshold;
        return this;
    }

    /**
     * Get threshold
     *
     * @return threshold
     **/
    @ApiModelProperty(value = "")


    public BigInteger getThreshold() {
        return threshold;
    }

    public void setThreshold(BigInteger threshold) {
        this.threshold = threshold;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CurrentResults currentResults = (CurrentResults) o;
        return Objects.equals(this.totalCount, currentResults.totalCount) &&
                Objects.equals(this.aboveThresholdCount, currentResults.aboveThresholdCount) &&
                Objects.equals(this.max, currentResults.max) &&
                Objects.equals(this.min, currentResults.min) &&
                Objects.equals(this.threshold, currentResults.threshold);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCount, aboveThresholdCount, max, min, threshold);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CurrentResults {\n");

        sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
        sb.append("    aboveThresholdCount: ").append(toIndentedString(aboveThresholdCount)).append("\n");
        sb.append("    max: ").append(toIndentedString(max)).append("\n");
        sb.append("    min: ").append(toIndentedString(min)).append("\n");
        sb.append("    threshold: ").append(toIndentedString(threshold)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

