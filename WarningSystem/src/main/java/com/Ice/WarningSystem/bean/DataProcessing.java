package com.Ice.WarningSystem.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;

@ApiModel(description = "数据库中data_processing表对应的实体类")
@TableName("data_processing")
public class DataProcessing {
    /**
     * 位置id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "id",type= IdType.AUTO)
    private Long id;
    /**
     * 起始温度
     */
    @TableField(value = "temp")
    private Double temp;
    /**
     * 起始风速
     */
    @TableField(value = "wind_speed")
    private Double windSpeed;
    /**
     * 起始湿度
     */
    @TableField(value = "humidity")
    private Double humidity;
    /**
     * 类型
     */
    @TableField(value = "type")
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DataProcessing{" +
                "id=" + id +
                ", temp=" + temp +
                ", windSpeed=" + windSpeed +
                ", humidity=" + humidity +
                ", type='" + type + '\'' +
                '}';
    }
}
