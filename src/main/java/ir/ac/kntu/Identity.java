package ir.ac.kntu;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Identity {
    private String name;
    private String lastName;
    private String nationalId;
    private String birthCertId;
    private Date birthDate;
    private Date hireDate;

    public Identity(){
        this("", "", "", "", new Date(0, 1, 1),
                new Date(0, 1, 1));
    }

    private Identity(String name, String lastName, String nationalId, String birthCertId,
                    Date birthDate, Date hireDate){
        setName(name);
        setLastName(lastName);
        setNationalId(nationalId);
        setBirthCertId(birthCertId);
        setBirthDate(birthDate);
        setHireDate(hireDate);
    }

    public Identity(@NotNull Identity identity){
        this(identity.getName(),
                identity.getLastName(),
                identity.getNationalId(),
                identity.getBirthCertId(),
                identity.getBirthDate(),
                identity.getHireDate());
    }

    public void setName(String name) {
        this.name = Objects.requireNonNullElse(name, "");
    }

    public void setLastName(String lastName){
        this.lastName = Objects.requireNonNullElse(lastName, "");
    }

    public void setNationalId(@NotNull String nationalId) {
        this.nationalId = nationalId.matches("[0-9]+") ? nationalId: "";
    }

    public void setBirthCertId(@NotNull String birthCertId){
        this.birthCertId = birthCertId.matches("[0-9]+") ? birthCertId : "";
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = Objects.requireNonNullElseGet(birthDate, () -> new Date(0, 1, 1));
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = Objects.requireNonNullElseGet(hireDate, () -> new Date(0, 1, 1));
    }

    public String getName() {
        return this.name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getBirthCertId() {
        return birthCertId;
    }

    public Date getBirthDate() {
        return new Date(birthDate);
    }

    public Date getHireDate() {
        return new Date(hireDate);
    }

    @Override
    public String toString() {
        return "name: " + getName() + '\n' +
                "lastName: " + getLastName() + '\n' +
                "nationalId: " + getNationalId() + '\n' +
                "birthCertId: " + getBirthCertId() + '\n' +
                "birthDate: " + getBirthDate() + "\n" +
                "hireDate: " + getHireDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourLeader that = (TourLeader) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getNationalId(), that.getNationalId()) &&
                Objects.equals(getBirthCertId(), that.getBirthCertId()) &&
                Objects.equals(getBirthDate(), that.getBirthDate()) &&
                Objects.equals(getHireDate(), that.getHireDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLastName(), getNationalId(), getBirthCertId(), getBirthDate(), getHireDate());
    }

}