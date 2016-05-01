package Model;

/**
 * Created by J2FX on 01/05/2016.
 */
public class DriverImpl {
    public String name;
    public String id;
    public String address;
    public VehicleImpl vehicle;
    public String pinCode;
    public String nationalInsurance;
    public String pcoExpiryDate;
    public String pcoNumber;

    boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public VehicleImpl getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleImpl vehicle) {
        this.vehicle = vehicle;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getNationalInsurance() {
        return nationalInsurance;
    }

    public void setNationalInsurance(String nationalInsurance) {
        this.nationalInsurance = nationalInsurance;
    }

    public String getPcoExpiryDate() {
        return pcoExpiryDate;
    }

    public void setPcoExpiryDate(String pcoExpiryDate) {
        this.pcoExpiryDate = pcoExpiryDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
