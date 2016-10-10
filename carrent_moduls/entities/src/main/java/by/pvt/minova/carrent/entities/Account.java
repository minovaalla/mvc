package by.pvt.minova.carrent.entities;

public class Account extends Entity{
    private static final long serialVersionUID = 1L;
    private int status;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + status;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Account)) {
            return false;
        }
        Account other = (Account) obj;

        if (status != other.status) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Account{" +
                "status=" + status +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
