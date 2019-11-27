package entities;

import javax.persistence.*;

@Entity
public class Car {
    private long id;
    private String model;
    private long horsepower;
    private Person personByPersonId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MODEL", nullable = true, length = 45)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "HORSEPOWER", nullable = false, precision = 0)
    public long getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(long horsepower) {
        this.horsepower = horsepower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (horsepower != car.horsepower) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (int) (horsepower ^ (horsepower >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "OWNER_ID",referencedColumnName = "ID",nullable = false)
    public Person getPersonByPersonId() {
        return personByPersonId;
    }

    public void setPersonByPersonId(Person personByPersonId) {
        this.personByPersonId = personByPersonId;
    }

    public String obtainVendor() {
        String vendor = this.model.substring(0,this.model.indexOf("-"));
        return vendor;
    }

}
