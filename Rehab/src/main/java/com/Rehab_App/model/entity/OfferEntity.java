package com.Rehab_App.model.entity;

import com.Rehab_App.model.enums.DeviceTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {

  @NotEmpty
  private String description;

  @Positive
  private Integer mileage;

  @Positive
  private int price;

  @Enumerated(EnumType.STRING)
  private DeviceTypeEnum device;

  public Integer getMileage() {
    return mileage;
  }

  public OfferEntity setMileage(Integer mileage) {
    this.mileage = mileage;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public OfferEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  public DeviceTypeEnum getDevice() {
    return device;
  }

  public OfferEntity setDevice(DeviceTypeEnum device) {
    this.device = device;
    return this;
  }

  public int getPrice() {
    return price;
  }

  public OfferEntity setPrice(int price) {
    this.price = price;
    return this;
  }
}
