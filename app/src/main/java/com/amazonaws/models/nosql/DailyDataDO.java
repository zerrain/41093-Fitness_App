package com.amazonaws.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "fitnessapp-mobilehub-969335156-daily_data")

public class DailyDataDO {
    private String _date;
    private String _userId;
    private String _nickname;
    private Double _averageHeartRate;
    private Double _energy;
    private Double _steps;

    @DynamoDBHashKey(attributeName = "date")
    @DynamoDBAttribute(attributeName = "date")
    public String getDate() {
        return _date;
    }
    public void setDate(final String _date) {
        this._date = _date;
    }

    @DynamoDBRangeKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }
    public void setUserId(final String _userId) {
        this._userId = _userId;
    }

    @DynamoDBAttribute(attributeName = "average_heart_rate")
    public Double getAverageHeartRate() {
        return _averageHeartRate;
    }
    public void setAverageHeartRate(final Double _averageHeartRate) {
        this._averageHeartRate = _averageHeartRate;
    }

    @DynamoDBAttribute(attributeName = "energy")
    public Double getEnergy() {
        return _energy;
    }
    public void setEnergy(final Double _energy) {
        this._energy = _energy;
    }

    @DynamoDBAttribute(attributeName = "steps")
    public Double getSteps() {
        return _steps;
    }
    public void setSteps(final Double _steps) {
        this._steps = _steps;
    }

    @DynamoDBAttribute(attributeName = "nickname")
    public String getNickname() {
        return _nickname;
    }
    public void setNickname(final String _nickname) {
        this._nickname = _nickname;
    }

    @Override
    public String toString() {
        return "[" + _nickname + "] Steps: " + _steps + " Energy: " + _energy + " Heart Rate: " + _averageHeartRate;
    }
}
