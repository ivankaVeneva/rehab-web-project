package com.Rehab_App.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.Rehab_App.model.enums.ModelCategoryEnum;

@Entity
@Table(name="models")
public class ModelEntity extends BaseEntity {

  private String name;

  @Enumerated(EnumType.STRING)
  private ModelCategoryEnum category;

  @ManyToOne
  private ResearchEntity research;

  public String getName() {
    return name;
  }

  public ModelEntity setName(String name) {
    this.name = name;
    return this;
  }

  public ModelCategoryEnum getCategory() {
    return category;
  }

  public ModelEntity setCategory(ModelCategoryEnum category) {
    this.category = category;
    return this;
  }

  public ResearchEntity getResearch() {
    return research;
  }

  public ModelEntity setResearch(ResearchEntity research) {
    this.research = research;
    return this;
  }

  @Override
  public String toString() {
    return "ModelEntity{" +
        "name='" + name + '\'' +
        ", category=" + category +
        ", research=" + research +
        '}';
  }
}
