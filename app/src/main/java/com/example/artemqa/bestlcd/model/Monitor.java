package com.example.artemqa.bestlcd.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by artemqa on 14.01.2018.
 */

public class Monitor extends RealmObject {

   @PrimaryKey
   @Required private String url; //ссылка на маркет
    @Required private String urlPicture; // ссылка на картинку
    @Required private Double cost; //цена
    @Required private String resolution; //разрешение матрицы
    @Required private Double diagonal; // диагональ
    @Required private Integer maxFRR; //maximum frame refresh rate
    @Required private String typeMatrix; //диагональ
    @Required private Double scoreMarket; //оценка на Яндекс Маркете
    @Required private Double scoreAlgorithm;//итоговая оценка товара
    @Required private String name; //название модели
    public Monitor(String url, String urlPicture, double cost, String resolution, double diagonal, int maxFRR, String typeMatrix, double scoreMarket, String name,double scoreAlgorithm) {
        this.url = url;
        this.urlPicture = urlPicture;
        this.cost = cost;
        this.resolution = resolution;
        this.diagonal = diagonal;
        this.maxFRR = maxFRR;
        this.typeMatrix = typeMatrix;
        this.scoreMarket = scoreMarket;
        this.name = name;
        this.scoreAlgorithm = scoreAlgorithm;
    }

    public Monitor(){

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

    public int getMaxFRR() {
        return maxFRR;
    }

    public void setMaxFRR(int maxFRR) {
        this.maxFRR = maxFRR;
    }

    public String getTypeMatrix() {
        return typeMatrix;
    }

    public void setTypeMatrix(String typeMatrix) {
        this.typeMatrix = typeMatrix;
    }

    public double getScoreMarket() {
        return scoreMarket;
    }

    public void setScoreMarket(double scoreMarket) {
        this.scoreMarket = scoreMarket;
    }

    public double getScoreAlgorithm() {
        return scoreAlgorithm;
    }

    public void setScoreAlgorithm(double scoreAlgorithm) {
        this.scoreAlgorithm = scoreAlgorithm;
    }


}
