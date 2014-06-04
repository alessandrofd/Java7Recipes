package chapter07.recipe7_09;

import java.io.*;

public class Team implements TeamType, Cloneable, Serializable {
    private String name;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getFullName() {
        return this.name + " - " + this.city;
    }

    // Overrides Object's clone() method to create a deep copy.
    public Team clone()  {
        Team obj = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = (Team) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // Overrides Object's clone method to create a shallow copy.
    public Team shallowCopyClone() {
        try {
            return (Team) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj instanceof Team) {
            Team other = (Team) obj;
            return other.getName().equals(this.getName()) && other.getCity().equals(this.getCity());
        } else {
            return false;
        }



    }
}

