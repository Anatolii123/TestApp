package entities;

import javax.persistence.*;

@Entity
public class Car {
    private Long id;
    private String model;
    private Long horsepower;
    private Long ownerId;
    private Person personByPersonId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, precision = 0)
    public Long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "MODEL", nullable = true, length = 45)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "HORSEPOWER", nullable = false, precision = 0)
    public Long getHorsepower() {
        return this.horsepower;
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

    @Column(name = "OWNER_ID", nullable = false, precision = 0)
    public Long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", insertable = false, updatable = false, referencedColumnName = "ID",nullable = false)
    public Person getPersonByPersonId() {
        return this.personByPersonId;
    }

    public void setPersonByPersonId(Person personByPersonId) {
        this.personByPersonId = personByPersonId;
    }

    @Transient
    public String getVendorModel() {
        String vendor = this.model.substring(0,this.model.indexOf("-"));
        return vendor;
    }

    @Transient
    public String getModelModel() {
        String model = this.model.substring(this.model.indexOf("-") + 1, this.model.length());
        return model;
    }

}
