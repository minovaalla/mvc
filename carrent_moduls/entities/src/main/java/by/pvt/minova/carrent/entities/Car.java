package by.pvt.minova.carrent.entities;

public class Car extends Entity {
    private static final long serialVersionUID = 1L;

    private String number;
    private String model;
    private String year;
    private String price;


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Car)) {
            return false;
        }
        Car other = (Car) obj;

        if (number != other.number) {
            return false;
        }
        if (model != other.model) {
            return false;
        }
        if (year != other.year) {
            return false;
        }
        if (price != other.price) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + number.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + year.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    public String getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getPrice() {
        return price;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "number='" + number + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
