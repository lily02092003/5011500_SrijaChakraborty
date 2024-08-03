/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class MVCPatternTest {
    public static void main(String[] args) {
        // Create the model
        Student student = new Student("John Doe", 123, "A");

        // Create the view
        StudentView view = new StudentView();

        // Create the controller
        StudentController controller = new StudentController(student, view);

        // Update the view with initial data
        controller.updateView();

        // Update the student details
        controller.setStudentName("Jane Doe");
        controller.setStudentGrade("B");

        // Update the view with new data
        controller.updateView();
    }
}