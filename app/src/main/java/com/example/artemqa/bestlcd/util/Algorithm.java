package com.example.artemqa.bestlcd.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.artemqa.bestlcd.model.Monitor;

import java.math.BigDecimal;
import java.math.RoundingMode;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by artemqa on 14.01.2018.
 */

public class Algorithm {
    private Realm realm;
    private Monitor monitor;
    private SharedPreferences sharedPreferences;
    private RealmResults<Monitor> monitors;
    private static final int BONUS_COST = 1000;

    public Algorithm(Monitor monitor, SharedPreferences sharedPreferences) {
        this.monitor = monitor;
        this.sharedPreferences = sharedPreferences;
    }

    public void start() {
        realm = Realm.getDefaultInstance();
        monitors = realm.where(Monitor.class).findAll();
        double totalScore = getCostScore(sharedPreferences.getInt(Helper.PREF_COST_INDEX, 0)) +
                getResolutionScore(sharedPreferences.getInt(Helper.PREF_RESOLUTION_INDEX, 0)) +
                getDiagonalScore(sharedPreferences.getInt(Helper.PREF_DIAGONAL_INDEX, 0)) +
                getMaxFrrScore(sharedPreferences.getInt(Helper.PREF_MAX_FRR_INDEX, 0)) +
                getMarketScoreScore(sharedPreferences.getInt(Helper.PREF_SCORE_MARKET_INDEX, 0)) +
                getMatrixTypeScore(sharedPreferences.getInt(Helper.PREF_TYPE_MATRIX_INDEX, 0));
        final double totalScoreRound = new BigDecimal(totalScore).setScale(2, RoundingMode.UP).doubleValue();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                monitor.setScoreAlgorithm(totalScoreRound);
                realm.copyToRealmOrUpdate(monitor);
            }
        });
        realm.close();
    }

    private double getMatrixTypeScore(int sharedMatrixTypeIndex) {
        String matrixTypeString = monitor.getTypeMatrix();
        int matrixTypeInt = getSwitchedMatrixType(matrixTypeString);
        int totalTypeMatrixAllMonitors = 0;
        for (int i = 0; i < monitors.size(); i++) {
            totalTypeMatrixAllMonitors += getSwitchedMatrixType(monitors.get(i).getTypeMatrix());
        }

        return (matrixTypeInt/totalTypeMatrixAllMonitors)*sharedMatrixTypeIndex;
    }

    private int getSwitchedMatrixType(String matrixTypeString) {

        int matrixTypeInt;

        switch (matrixTypeString) {
            case "TN":
                matrixTypeInt = 1;
                break;
            case "IPS":
                matrixTypeInt = 2;
                break;
            default:
                matrixTypeInt = 1;
                break;
        }
        return matrixTypeInt;
    }

    private double getMarketScoreScore(int sharedMarketScoreIndex) {
        int totalMarketScoreAllMonitors = 0;
        for (int i = 0; i < monitors.size(); i++) {
            totalMarketScoreAllMonitors += monitors.get(i).getScoreMarket();
        }
        return (monitor.getDiagonal() / totalMarketScoreAllMonitors) * sharedMarketScoreIndex;
    }

    private double getMaxFrrScore(int sharedMaxFrrIndex) {
        int totalMaxFrrAllMonitors = 0;
        for (int i = 0; i < monitors.size(); i++) {
            totalMaxFrrAllMonitors += monitors.get(i).getMaxFRR();
        }
        return (monitor.getDiagonal() / totalMaxFrrAllMonitors) * sharedMaxFrrIndex;
    }

    private double getDiagonalScore(int sharedDiagonalIndex) {
        double totalDiagonalAllMonitors = 0;
        for (int i = 0; i < monitors.size(); i++) {
            totalDiagonalAllMonitors += monitors.get(i).getDiagonal();
        }
        return (monitor.getDiagonal() / totalDiagonalAllMonitors) * sharedDiagonalIndex;
    }

    private double getResolutionScore(int sharedResolutionIndex) {
        String resolutionString = monitor.getResolution();
        int resolutionThisMonitor = getSwitchResolution(resolutionString);
        int totalAllMonitorResolutions = 0;
        for (int i = 0; i < monitors.size(); i++) {
            int resolutionCurrentMonitor = getSwitchResolution(monitors.get(i).getResolution());
            totalAllMonitorResolutions += resolutionCurrentMonitor;
        }
        return (resolutionThisMonitor / totalAllMonitorResolutions) * sharedResolutionIndex;
    }

    private int getSwitchResolution(String resolutionScore) {
        int resolutionThisMonitor;

        switch (resolutionScore) {
            case "1920x1080":
                resolutionThisMonitor = 1920 * 1080;
                break;
            case "2560x1080":
                resolutionThisMonitor = 2560 * 1080;
                break;
            case "3840x2160":
                resolutionThisMonitor = 3840 * 2160;
                break;
            case "1920x1200":
                resolutionThisMonitor = 1920 * 1200;
                break;
            case "1366x768":
                resolutionThisMonitor = 1366 * 768;
                break;
            default:
                resolutionThisMonitor = 1920 * 1080;
                break;
        }
        return resolutionThisMonitor;
    }

    private double getCostScore(int sharedCostIndex) {
        double maxCost = (double) realm.where(Monitor.class).max("cost");
        double maxCostWithBonus = maxCost + BONUS_COST;
        double invertCost = maxCostWithBonus - this.monitor.getCost();
        double totalCostAllMonitors = 0;
        for (int i = 0; i < monitors.size(); i++) {
            double currentMonitorCost = monitors.get(i).getCost();
            double invertCurrentMonitorCost = maxCostWithBonus - currentMonitorCost;
            totalCostAllMonitors += invertCurrentMonitorCost;
        }
        return (invertCost / totalCostAllMonitors) * sharedCostIndex;
    }
}
