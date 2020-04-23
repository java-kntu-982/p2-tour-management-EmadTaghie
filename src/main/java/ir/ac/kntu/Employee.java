package ir.ac.kntu;

public class Employee extends User{
    private Date hireDate;
    private int baseSalary;

    public Employee(){
        super();
        setBaseSalary(1000);
        setHireDate(new Date(0, 0, 1));
        setUserState(UserState.Employee);
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "hireDate: " + hireDate + "\n" +
                "baseSalary: " + baseSalary;
    }
}
