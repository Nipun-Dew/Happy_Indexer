package org.api.model;

import com.opencsv.bean.CsvBindByName;

public class HappyIndexEntry {
    @CsvBindByName(column = "Overall_rank", required = true)
    private int rank;

    @CsvBindByName(column = "Country", required = true)
    private String country;

    @CsvBindByName(column = "Score")
    private Float score;

    @CsvBindByName(column = "GDP_per_capita")
    private Float gdpPerCapita;

    @CsvBindByName(column = "Social_support")
    private Float socialSupport;

    @CsvBindByName(column = "Life_expectancy")
    private Float lifeExpectancy;

    @CsvBindByName(column = "Freedom_of_life_choices")
    private Float freedom;

    @CsvBindByName(column = "Generosity")
    private Float generosity;

    @CsvBindByName(column = "Perceptions_of_corruption")
    private Float corruption;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Float getGdpPerCapita() {
        return gdpPerCapita;
    }

    public void setGdpPerCapita(Float gdpPerCapita) {
        this.gdpPerCapita = gdpPerCapita;
    }

    public Float getSocialSupport() {
        return socialSupport;
    }

    public void setSocialSupport(Float socialSupport) {
        this.socialSupport = socialSupport;
    }

    public Float getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(Float lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public Float getFreedom() {
        return freedom;
    }

    public void setFreedom(Float freedom) {
        this.freedom = freedom;
    }

    public Float getGenerosity() {
        return generosity;
    }

    public void setGenerosity(Float generosity) {
        this.generosity = generosity;
    }

    public Float getCorruption() {
        return corruption;
    }

    public void setCorruption(Float corruption) {
        this.corruption = corruption;
    }
}
