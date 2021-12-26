package cinematicketingsystem.utils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBManager {
    private static DBManager instance;
    private Connection connection;
    private final String url = "jdbc:mysql://bw8vg030tuezxbpr6mj2-mysql.services.clever-cloud.com/bw8vg030tuezxbpr6mj2";
    private final String user = "u8iiuo74brq3xzku";
    private final String password = "h9UlTaTbwKiTf3fY5rEQ";

    private DBManager() {}

    public static DBManager getInstance() {
        if(instance == null) instance = new DBManager();
        return instance;
    }

    private void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, password);
    }

    public <T> List<T> executeQuery(String query, Class<T> clazz) {
        ResultSet resultSet = null;
        List<T> list = null;
        try {
            createConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            list = map2dto(resultSet, clazz);
            connection.close();
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public <T> List<T> executeQueryFromFile(String path, Class<T> clazz) {
        String query = null;
        try {
            InputStream is = getClass().getResourceAsStream(path);
            query = readFromInputStream(is);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
        return executeQuery(query, clazz);
    }

    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    private <T> List<T> map2dto(ResultSet resultSet, Class<T> clazz) {
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        for(Field field: fields) {
            field.setAccessible(true);
        }
        List<T> list = new ArrayList<>();
        while(true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            T dto = null;
            try {
                dto = clazz.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            for(Field field: fields) {
                String name = field.getName();
                try {
                    String value = resultSet.getString(name);
                    field.set(dto, field.getType().getConstructor(String.class).newInstance(value));
                } catch (Exception e) { }
            }
            list.add(dto);
        }
        return list;
    }
}
