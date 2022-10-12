package edu.ucdenver.csci3920.university;

import java.util.Comparator;
import java.util.Objects;

public class StudentComparator implements Comparator<Student> {
    // Sorting Students

        @Override
        public int compare(Student student1, Student student2){
            int s1Order = getStandingOrder(student1.getStanding());
            int s2Order = getStandingOrder(student2.getStanding());
            int stuCompare = s1Order - s2Order;
            if(stuCompare != 0) return stuCompare;
            stuCompare = student1.getName().compareTo(student2.getName());
            if(stuCompare != 0) return stuCompare;
            return Integer.compare(s1Order, s2Order);

        }

        public int getStandingOrder(String standing){
            if(Objects.equals(standing, "Undergraduate")) return 0;
            else if(Objects.equals(standing, "Master")) return 1;
            else if(Objects.equals(standing, "PhD")) return 2;
            else return 3;
        }
}
