package com.revature.models;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@javax.persistence.Entity(name="locations")
@Table(name="locations")
public class Location {

    @Id
    @Column(name="location_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationId;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="country")
    private String country;

    @Column(name="location_zip_code")
    private String locationZipCode;

//    @ManyToMany(mappedBy = "locations")
//    private Set<User> users;
//
//    @OneToMany(mappedBy = "home")
//    private Set<User> homeUsers;

    public Location() {
    }

    public Location(String locationZipCode) {
        this.locationZipCode = locationZipCode;
    }

    public Location(String city, String state, String country, String locationZipCode) {
        this(locationZipCode);
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public Location(int locationId, String city, String state, String country, String locationZipCode) {
        this(city, state, country, locationZipCode);
        this.locationId = locationId;
    }

    public Location(Location copy){
        this(copy.locationId, copy.city, copy.state, copy.country, copy.locationZipCode);
    }

//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//
//    public Set<User> getHomeUsers() {
//        return homeUsers;
//    }
//
//    public void setHomeUsers(Set<User> homeUsers) {
//        this.homeUsers = homeUsers;
//    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocationZipCode() {
        return locationZipCode;
    }

    public void setLocationZipCode(String locationZipCode) {
        this.locationZipCode = locationZipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return locationId == location.locationId &&
                locationZipCode == location.locationZipCode &&
                Objects.equals(city, location.city) &&
                Objects.equals(state, location.state) &&
                Objects.equals(country, location.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, city, state, country, locationZipCode);
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", locationZipCode=" + locationZipCode +
                '}';
    }
}
