package hiber.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Cars")
public class Car {

    @Column(name = "model")
    private String model;
    @Id
    @Column(name = "series")
    private int series;

    @OneToOne(mappedBy = "car")
    private User vladelec;

    public Car() {}

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Car car = (Car) object;
        return series == car.series && Objects.equals(model, car.model) && Objects.equals(vladelec, car.vladelec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, series, vladelec);
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", series=" + series +
                '}';
    }
}
