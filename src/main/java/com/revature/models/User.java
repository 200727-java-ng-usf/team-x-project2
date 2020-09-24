package com.revature.models;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@javax.persistence.Entity(name= "users")
@Table(name="users")
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name="username")
    private String username;

    @Column(name="user_password")
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="zip_code")
    private String zipCode;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="role_id")
    private UserRole userRole;

    @ManyToMany(cascade = { CascadeType.REMOVE})
    @JoinTable(
            name = "user_locations",
            joinColumns = { @JoinColumn(name="user_id")},
            inverseJoinColumns = { @JoinColumn(name = "location_id")}
    )
    private Set<Location> locations;

    @ManyToOne(cascade = { CascadeType.REMOVE})
    @JoinTable(
            name = "home_locations",
            joinColumns = { @JoinColumn(name="user_id")},
            inverseJoinColumns = { @JoinColumn(name = "location_id")}
    )
    private Location home;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userRole = UserRole.USER;
    }

    public User(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userRole = UserRole.USER;
    }

    public User(String username, String password, String firstName, String lastName, String email, String zipCode) {
        this(username, password, firstName, lastName, email);
        this.zipCode = zipCode;
    }

    public User(int userId, String username, String password, String firstName, String lastName, String email, String zipCode, UserRole userRole) {
        this(username, password, firstName, lastName, email, zipCode);
        this.userId = userId;
        this.userRole = userRole;
    }

    public User(User copy){
        this(copy.userId, copy.username, copy.password, copy.firstName, copy.lastName, copy.email, copy.zipCode, copy.userRole);
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public Location getHome() {
        return home;
    }

    public void setHome(Location home) {
        this.home = home;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                zipCode == user.zipCode &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                userRole == user.userRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, firstName, lastName, email, zipCode, userRole);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", userRole=" + userRole +
                ", locations=" + locations +
                ", home=" + home +
                '}';
    }
}
