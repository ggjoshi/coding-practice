import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

/**
 * Example of implementing comparable and comparator.
 */
public class Employee implements Comparable<Employee>, Serializable {
    private final String name;
    private final int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /*
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null) {
            return false;
        }

        if(this.getClass() != obj.getClass()) {
            return false;
        }

        Employee other = (Employee)obj;

        if(name == null) {
            if(other.name != null)
                return false;
        } else {
            if(!name.equals(other.name)) {
                return false;
            }
        }

        if(age != other.age) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {

    }
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(age, employee.age) &&
            Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public int compareTo(Employee other) {
        if(other == null) {
            return 1;
        }
        int compareResult = 0;
        if((name != null) != (other.name != null)) {
            return -1;
        } else if(name != other.name) {
            compareResult = name.compareTo(other.name);
            if(compareResult != 0) {
                return compareResult;
            }
        }

        return age - other.age;
    }

    public static void main(String [] args) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Employee employee = new Employee("Girish", 32);
        ObjectOutputStream objectOutputStream =
            new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(employee);
        System.out.println(byteArrayOutputStream.toString());

        String fileName = "/Users/gjoshi/Foo.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        String outString = "Hello World from Java.";
        System.out.println("Length of string - " + outString.length());
        System.out.println("Length of string bytes array - " + outString.getBytes().length);
        fileOutputStream.write(outString.getBytes());
        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(fileName);
        int data = fileInputStream.read();
        while(data != -1) {
            System.out.print((char) data);
            data = fileInputStream.read();
        }
        fileInputStream.close();
    }
}
