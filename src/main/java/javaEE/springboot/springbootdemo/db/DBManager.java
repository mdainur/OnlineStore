//package javaEE.springboot.springbootdemo.db;
//
//import java.sql.*;
//import java.util.ArrayList;
//
//public class DBManager {
//    private static Connection connection;
//    static{
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/home_task_7_java_ee?useUnicode=true&serverTimezone=UTC", "root", "" );
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    public static ShopItem getItem(Long id){
//        ShopItem item = null;
//        try{
//            PreparedStatement statement = connection.prepareStatement("" +
//                    "select * from items where id = ?");
//            statement.setLong(1, id);
//            ResultSet resultSet = statement.executeQuery();
//            if(resultSet.next()) {
//                item = new ShopItem(resultSet.getLong("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("description"),
//                        resultSet.getDouble("price"),
//                        resultSet.getInt("stars"),
//                        resultSet.getString("small_picture_url"),
//                        resultSet.getString("large_picture_url"),
//                        resultSet.getDate("added_date"),
//                        resultSet.getBoolean("in_top_page")
//                );
//            }
//            statement.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return item;
//    }
//
//    public static boolean addItem(ShopItem item){
//        int rows = 0;
//        try{
//            PreparedStatement statement = connection.prepareStatement("" +
//                    "INSERT INTO items (id, name, description, price, stars, small_picture_url, large_picture_url, added_date, in_top_page) " +
//                    "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)" +
//                    "");
//            statement.setString(1, item.getName());
//            statement.setString(2, item.getDescription());
//            statement.setDouble(3, item.getPrice());
//            statement.setInt(4, item.getStars());
//            statement.setString(5, item.getSmallPictureUrl());
//            statement.setString(6, item.getLargePictureUrl());
//            statement.setDate(7, item.getAddedDate());
//            statement.setBoolean(8, item.isInTopPage());
//
//            rows = statement.executeUpdate();
//            statement.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return rows > 0;
//    }
//
//    public static boolean deleteItem(Long id){
//        int row = 0;
//        try{
//            PreparedStatement statement = connection.prepareStatement("" +
//                    "delete from items where id = ?");
//            statement.setLong(1, id);
//
//            row = statement.executeUpdate();
//            statement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return row > 0;
//    }
//
//    public static ArrayList<ShopItem> getAllItems(){
//        ArrayList<ShopItem> items = new ArrayList<>();
//        try {
//            PreparedStatement statement = connection.prepareStatement("" +
//                    "SELECT * FROM items");
//            ResultSet resultSet = statement.executeQuery();
//            while(resultSet.next()){
//                items.add(new ShopItem(
//                        resultSet.getLong("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("description"),
//                        resultSet.getDouble("price"),
//                        resultSet.getInt("stars"),
//                        resultSet.getString("small_picture_url"),
//                        resultSet.getString("large_picture_url"),
//                        resultSet.getDate("added_date"),
//                        resultSet.getBoolean("in_top_page"))
//                );
//            }
//
//            statement.close();
//        }
//        catch (SQLException e){
//            e.printStackTrace();
//        }
//        return items;
//    }
//
//    public static boolean editItem(ShopItem item){
//        int rows = 0;
//        try{
//            PreparedStatement statement = connection.prepareStatement("" +
//                    "update items set name = ?, description = ?, price = ?, stars = ?, small_picture_url = ?, large_picture_url = ?, added_date = ?, in_top_page = ? where id = ?");
//            statement.setString(1, item.getName());
//            statement.setString(2, item.getDescription());
//            statement.setDouble(3, item.getPrice());
//            statement.setInt(4, item.getStars());
//            statement.setString(5, item.getSmallPictureUrl());
//            statement.setString(6, item.getLargePictureUrl());
//            statement.setDate(7, item.getAddedDate());
//            statement.setBoolean(8, item.isInTopPage());
//            statement.setLong(9, item.getId());
//            rows = statement.executeUpdate();
//            statement.close();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return rows > 0;
//    }
//
//}
