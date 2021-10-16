package javaEE.springboot.springbootdemo.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class dDBManager1 {
    private static Connection connection;
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/spring_boot_first_project?useUnicode=true&serverTimezone=UTC", "root", "" );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Tasks getTask(Long id){
        Tasks task = null;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "select * from tasks where task_id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                task = new Tasks(resultSet.getLong("task_id"),
                        resultSet.getString("task_name"),
                        resultSet.getString("task_description"),
                        resultSet.getDate("deadline_date"),
                        resultSet.getBoolean("task_completed")
                );
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return task;
    }
    public static boolean addTask(Tasks task){
        int rows = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO tasks (task_id, task_name, task_description, deadline_date)" +
                    "VALUES (NULL, ?, ?, ?)" +
                    "");
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());

            statement.setDate(3, (java.sql.Date) task.getDeadlineDate());
            rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static boolean deleteTask(Long id){
        int row = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "delete from tasks where task_id = ?");
            statement.setLong(1, id);

            row = statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row > 0;
    }

    public static ArrayList<Tasks> getAllTasks(){
        ArrayList<Tasks> tasks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                            "SELECT * FROM tasks");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                tasks.add(new Tasks(
                        resultSet.getLong("task_id"),
                        resultSet.getString("task_name"),
                        resultSet.getString("task_description"),
                        resultSet.getDate("deadline_date"),
                        resultSet.getBoolean("task_completed"))
                );
            }

            statement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return tasks;
    }

    public static boolean updateTask(Tasks task){
        int rows = 0;
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "update tasks set task_name = ?, task_description = ?, deadline_date = ?, task_completed = ? where task_id = ?");
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setDate(3, (java.sql.Date) task.getDeadlineDate());
            statement.setBoolean(4, task.isCompleted());
            statement.setLong(5, task.getId());
            rows = statement.executeUpdate();
            statement.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rows > 0;
    }

    public static ArrayList<Tasks> filteredTasks(String task_name, java.sql.Date deadline_to, boolean is_completed){
        ArrayList<Tasks> filteredTasks = new ArrayList<>();
/*
* name
* completed
* date to
* name + date
* */
        try{
            PreparedStatement statement = null;
            //only is completed zapolnen
            if(task_name.equals("No Name") && deadline_to.equals(Date.valueOf("2020-01-01")) && is_completed){
                statement = connection.prepareStatement("" +
                        "select * from tasks where task_completed = ?");
                statement.setBoolean(1, is_completed);
            }
            //only deadline date zapolnen
            else if(task_name.equals("No Name") && is_completed && !deadline_to.equals(Date.valueOf("2020-01-01"))){
                statement = connection.prepareStatement("" +
                        "select * from tasks where deadline_date = ? ");
                statement.setDate(1, (java.sql.Date)deadline_to);
            }
            //only task name zapolnen
            else if(is_completed && deadline_to.equals(Date.valueOf("2020-01-01")) && !task_name.equals("No name")){
                statement = connection.prepareStatement("" +
                        "select * from tasks where task_name = ? ");
                statement.setString(1, task_name);
            }
            //deadline date + is completed
            else if(task_name.equals("No Name") && !deadline_to.equals(Date.valueOf("2020-01-01")) && is_completed){
                statement = connection.prepareStatement("" +
                        "select * from tasks where task_completed = ? && deadline_date = ?");
                statement.setBoolean(1, is_completed);
                statement.setDate(2, deadline_to);
            }
            // task name + deadline date
            else if(!task_name.equals("No Name") && is_completed && !deadline_to.equals(Date.valueOf("2020-01-01"))){
                statement = connection.prepareStatement("" +
                        "select * from tasks where deadline_date = ? && task_name = ?");
                statement.setDate(1, deadline_to);
                statement.setString(2, task_name);
            }
            //task name + is copmleted
            else if(is_completed && deadline_to.equals(Date.valueOf("2020-01-01")) && !task_name.equals("No name")){
                statement = connection.prepareStatement("" +
                        "select * from tasks where task_name = ? && task_completed = ?");
                statement.setString(1, task_name);
                statement.setBoolean(2, is_completed);
            }
            // task name + is completed + deadline date
            else if(!is_completed && !deadline_to.equals(Date.valueOf("2020-01-01")) && !task_name.equals("No name")){
                statement = connection.prepareStatement("" +
                        "select * from tasks where task_name = ? && task _completed = ? && deadline_date = ?");
                statement.setString(1, task_name);
                statement.setBoolean(2, is_completed);
                statement.setDate(3, deadline_to);
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Tasks task = new Tasks();
                task.setId(resultSet.getLong(1));
                task.setName(resultSet.getString(2));
                task.setDescription(resultSet.getString(3));
                task.setDeadlineDate(resultSet.getDate(4));
                task.setCompleted(resultSet.getBoolean(5));
                filteredTasks.add(task);
            }

            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return filteredTasks;
    }


}
