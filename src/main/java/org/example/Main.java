import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
class Student {
    public String fullname;
    public String ID;
    public String email;

    public Student(String fullname, String ID, String email) {
        this.fullname = fullname;
        this.ID = ID;
        this.email = email;
    }

    public void print() {
        System.out.println("  SV: " + fullname + " (ID: " + ID + ", Email: " + email + ")");
    }
}

abstract class Instructor {
    public String fullname;
    public String email;

    public Instructor(String fullname, String email) {
        this.fullname = fullname;
        this.email = email;
    }

    public abstract void print();
}

class Teacher extends Instructor {
    public String ID;
    public String title;

    public Teacher(String fullname, String ID, String email, String title) {
        super(fullname, email);
        this.ID = ID;
        this.title = title;
    }

    @Override
    public void print() {
        System.out.println("  GVHD: " + title + " " + fullname + " (ID: " + ID + ", Email: " + email + ")");
    }
}

class Advisor extends Instructor {
    public Advisor(String fullname, String email) {
        super(fullname, email);
    }

    @Override
    public void print() {
        System.out.println("  HDN: " + fullname + " (Email: " + email + ")");
    }
}

interface IKhoaLuan {
    void print();
    String getTenKhoaLuan();
}

class KLTN<T extends Student, V extends Instructor> implements IKhoaLuan {
    public String tenKhoaLuan;
    public T sinhVien;
    public V nguoiHuongDan;

    public KLTN(String ten, T sv, V hd) {
        this.tenKhoaLuan = ten;
        this.sinhVien = sv;
        this.nguoiHuongDan = hd;
    }

    @Override
    public void print() {
        System.out.println("KLTN: " + tenKhoaLuan);
        sinhVien.print();
        nguoiHuongDan.print();
    }

    @Override
    public String getTenKhoaLuan() {
        return tenKhoaLuan;
    }
}

class ListSortable<E extends IKhoaLuan> {
    private List<E> danhSach;
    public ListSortable() {
        this.danhSach = new ArrayList<>();
    }
    public void add(E item) {
        danhSach.add(item);
    }
    public void print() {

        Collections.sort(danhSach, new Comparator<E>() {
            @Override
            public int compare(E kltn1, E kltn2) {
                String ten1 = kltn1.getTenKhoaLuan();
                String ten2 = kltn2.getTenKhoaLuan();

                if (ten1 == null || ten1.isEmpty()) return -1;
                if (ten2 == null || ten2.isEmpty()) return 1;

                return Character.compare(ten1.charAt(0), ten2.charAt(0));
            }
        });

        System.out.println(System.lineSeparator());
        for (E item : danhSach) {
            item.print();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Student sv1 = new Student("NA", "SV1", "a@gmail.com");
        Student sv2 = new Student("B", "SV2", "b@gmail.com");
        Student sv3 = new Student("C", "SV3", "c@gmail.com");
        Student sv4 = new Student("D", "SV4", "d@gmail.com");

        Teacher gv1 = new Teacher("Gv1", "GV001", "mail@gmail.com", "PGS.TS");
        Advisor hd1 = new Advisor("AD", "mmmm@gmail.com");
        Teacher gv2 = new Teacher("GV2", "GV002", "amo@gmail.com", "ThS");

        IKhoaLuan kltn1 = new KLTN<>(
                "Khóa luận 1", sv1, gv1
        );

        IKhoaLuan kltn2 = new KLTN<>(
                "Đây là khóa luận", sv2, hd1
        );

        IKhoaLuan kltn3 = new KLTN<>(
                "Xin chào khóa luận", sv3, gv2
        );
        IKhoaLuan kltn4 = new KLTN<>(
                "Vẫn là khóa luận", sv4, gv1
        );
        ListSortable<IKhoaLuan> danhSachKLTN = new ListSortable<>();
        danhSachKLTN.add(kltn1);
        danhSachKLTN.add(kltn2);
        danhSachKLTN.add(kltn3);
        danhSachKLTN.add(kltn4);
        danhSachKLTN.print();
    }
}