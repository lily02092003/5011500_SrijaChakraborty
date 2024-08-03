/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class Task {
    String taskId;
    String taskName;
    String status;

    public Task(String taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId + ", Task Name: " + taskName + ", Status: " + status;
    }
}

class Node {
    Task task;
    Node next;

    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

class SinglyLinkedList {
    private Node head;

    public SinglyLinkedList() {
        this.head = null;
    }

    // Add a task to the end of the list
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Search for a task by taskId
    public Task searchTask(String taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.taskId.equals(taskId)) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    // Traverse and print all tasks
    public void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    // Delete a task by taskId
    public boolean deleteTask(String taskId) {
        if (head == null) {
            return false;
        }

        // If the task to be deleted is the head
        if (head.task.taskId.equals(taskId)) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.task.taskId.equals(taskId)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public static void main(String[] args) {
        SinglyLinkedList taskList = new SinglyLinkedList();

        // Add tasks
        taskList.addTask(new Task("T001", "Complete Project Report", "In Progress"));
        taskList.addTask(new Task("T002", "Send Email Updates", "Pending"));
        taskList.addTask(new Task("T003", "Prepare Presentation", "Completed"));

        // Traverse tasks
        System.out.println("Tasks in the list:");
        taskList.traverseTasks();

        // Search for a task
        String searchId = "T002";
        Task foundTask = taskList.searchTask(searchId);
        if (foundTask != null) {
            System.out.println("\nFound Task: " + foundTask);
        } else {
            System.out.println("\nTask with ID " + searchId + " not found.");
        }

        // Delete a task
        String deleteId = "T001";
        boolean deleted = taskList.deleteTask(deleteId);
        if (deleted) {
            System.out.println("\nTask with ID " + deleteId + " deleted.");
        } else {
            System.out.println("\nTask with ID " + deleteId + " not found.");
        }

        // Traverse tasks after deletion
        System.out.println("\nTasks in the list after deletion:");
        taskList.traverseTasks();
    }
}
