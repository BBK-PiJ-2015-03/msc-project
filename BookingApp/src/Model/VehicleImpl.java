package Model;

import Model.Interfaces.Driver;
import Model.Interfaces.Vehicle;

/**
 * Implementation of vehicle.
 */
public class VehicleImpl implements Vehicle {
    private String registration;
    private Driver owner;
    private String colour;
    private String type;
    private String motExpiryDate;
    private String insuranceExpiryDate;
    private String pcoExpiryDate;

    public VehicleImpl() {
        Archive.allVehicles.add(this);
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Driver getOwner() {
        return owner;
    }

    public void setOwner(Driver owner) {
        this.owner = owner;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMotExpiryDate() {
        return motExpiryDate;
    }

    public void setMotExpiryDate(String motExpiryDate) {
        this.motExpiryDate = motExpiryDate;
    }

    public String getInsuranceExpiryDate() {
        return insuranceExpiryDate;
    }

    public void setInsuranceExpiryDate(String insuranceExpiryDate) {
        this.insuranceExpiryDate = insuranceExpiryDate;
    }

    public String getPcoExpiryDate() {
        return pcoExpiryDate;
    }

    public void setPcoExpiryDate(String pcoExpiryDate) {
        this.pcoExpiryDate = pcoExpiryDate;
    }
}
