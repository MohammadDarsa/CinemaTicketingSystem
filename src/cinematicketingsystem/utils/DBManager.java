package cinematicketingsystem.utils;

import cinematicketingsystem.annotations.Col;
import cinematicketingsystem.annotations.ID;
import cinematicketingsystem.annotations.Table;
import cinematicketingsystem.exceptions.sqlExceptions.EntityNotFoundException;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DBManager {
    private static DBManager instance;
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/mydb?characterEncoding=latin1&useConfigs=maxPerformance";
    private final String user = "root";
    private final String password = "kouti123";

    private DBManager() {}

    public static DBManager getInstance() {
        if(instance == null) instance = new DBManager();
        return instance;
    }

    private void createConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, password);
    }

    public void executeUpdate(String query) {
        try {
            createConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public <T> List<T> executeQuery(String query, Class<T> entity) {
        ResultSet resultSet = null;
        List<T> list = null;
        try {
            createConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            list = map2dto(resultSet, entity);
            connection.close();
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public <T> List<T> executeQueryFromFile(String path, Class<T> entity) {
        String query = null;
        try {
            query = file2String(path);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
        return executeQuery(query, entity);
    }

    public String file2String(String path) throws IOException {
        InputStream is = getClass().getResourceAsStream(path);
        return  readFromInputStream(is);
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

    public <T> void insertEntity(T item, Class<T> entity) {
        Field[] fields = entity.getDeclaredFields();
        List<Field> fieldList = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Col col = field.getAnnotation(Col.class);
            ID id = field.getAnnotation(ID.class);
            if (col == null || col.insertIgnore()) continue;
            if (id != null) continue;
            fieldList.add(field);
        }
        StringBuilder query = new StringBuilder("insert into " + entity.getAnnotation(Table.class).name() + " (");
        for(Field field:fieldList) {
            Col col = field.getAnnotation(Col.class);
            query.append(col.name()).append(", ");
        }
        query = new StringBuilder(query.substring(0, query.length() - 2) + ") values (");
        for(Field field:fieldList) {
            try {
//                if(field.getType().equals(Timestamp.class)) query.append("STR_TO_DATE(");
                query.append("'").append(field.get(item).toString()).append("', ");
//                if(field.getType().equals(Timestamp.class)) query.append("'%Y-%m-%d %H:%i:%s.%f'), ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        query = new StringBuilder(query.substring(0, query.length() - 2) + ")");
        executeUpdate(query.toString());
    }

    public <T> void updateEntity(T item, Class<T> entity) throws EntityNotFoundException {
        String tableName = entity.getAnnotation(Table.class).name();
        StringBuilder query = new StringBuilder("update " + tableName + " set ");
        Field [] fields = entity.getDeclaredFields();
        List<Field> fieldList = new ArrayList<>();
        Field idField = null;
        int isPresent = -1;
        for (Field field : fields) {
            field.setAccessible(true);
            Col col = field.getAnnotation(Col.class);
            if(field.isAnnotationPresent(ID.class)) {
                idField = field;
                String q = null;
                try {
                    q = "select count("+ col.name() +") from " + tableName + " where " + col.name() + " = " + (String)field.get(item) + ";";
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    createConnection();
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(q);
                    rs.next();
                    isPresent = rs.getInt(1);
                    connection.close();
                }catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                continue;
            }
            if (col == null || col.updateIgnore()) continue;
            fieldList.add(field);
        }
        if(isPresent == -1) {
            throw new EntityNotFoundException("Query Error..");
        } else if (isPresent == 0) {
            throw new EntityNotFoundException("Entity not found in database");
        } else {
            for(Field field:fieldList) {
                Col col = field.getAnnotation(Col.class);
                try {
                    query.append(col.name()).append(" = '").append(field.get(item).toString()).append("', ");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            query = new StringBuilder(query.substring(0, query.length() - 2));
            try {
                query.append(" where ").append(idField.getAnnotation(Col.class).name()).append(" = ").append(idField.get(item).toString()).append(";");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            executeUpdate(query.toString());
        }
    }

    private <T> List<T> map2dto(ResultSet resultSet, Class<T> entity) {
        Field[] fields = entity.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }
        List<T> list = new ArrayList<>();

        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            T dto = null;
            try {
                dto = entity.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            for (Field field : fields) {
                Col col = field.getAnnotation(Col.class);
                if (col != null) {
                    String name = col.name();
                    try {
                        String value = resultSet.getString(name);
//                        System.out.println(resultSet.getObject(name).getClass());
                        Class<?> type = field.getType();
//                        System.out.println(type);
//                        field.set(dto, type.getConstructor(String.class).newInstance(value));
                        changeToObj(field, type, value, dto);
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                }
            }
            list.add(dto);
        }
        return list;
    }

    private <T> void changeToObj(Field field, Class<?> type, String value, T dto) throws Exception {
        if(type.equals(Timestamp.class)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("hh:mm:ss");
            Date parsedDate = null;
            if(value.length() == 8)
            parsedDate = dateFormat1.parse(value);
            else parsedDate = dateFormat.parse(value);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            field.set(dto, timestamp);
        } else {
            field.set(dto, type.getConstructor(String.class).newInstance(value));
        }

    }

    public <T> void deleteEntity(T item, Class<T> entity) {
        String query = "delete from " + entity.getAnnotation(Table.class).name() + " where ";
        Field idField = Arrays.stream(entity.getDeclaredFields()).peek(field -> field.setAccessible(true)).filter(field -> field.isAnnotationPresent(ID.class)).findFirst().get();
        try {
            query = query + idField.getAnnotation(Col.class).name() + " = " + (String)idField.get(item);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        executeUpdate(query);
    }

    public void deleteEntity(String tableName, Attributes attributes) {
        String query = "delete from " + tableName + " where " + attributes.getString("and", "=");
        executeUpdate(query);
    }

    public void deleteEntity(Attributes attributes) {
        String query = "delete from " + attributes.getTableName() + " where " + attributes.getString("and", "=");
        executeUpdate(query);
    }

    public void deleteEntity(String tableName, Attributes attributes, String joint, String operator) {
        String query = "delete from " + tableName + " where " + attributes.getString(joint, operator);
        executeUpdate(query);
    }

    public void deleteEntity(Attributes attributes, String joint, String operator) {
        String query = "delete from " + attributes.getTableName() + " where " + attributes.getString(joint, operator);
        executeUpdate(query);
    }

    public <T> List<T> selectAll(Class<T> entity) {
        Table table = entity.getAnnotation(Table.class);
        String query = "select * from " + table.name() + ";";
        return executeQuery(query, entity);
    }

    public <T> List<T> selectAll(Class<T> entity, Attributes attributes) {
        Table table = entity.getAnnotation(Table.class);
        String query = "select * from " + table.name() + " where " + attributes.getString("and", "=");
        return executeQuery(query, entity);
    }

    public <T> List<T> selectAll(Class<T> entity, Attributes attributes, String joint, String operator) {
        Table table = entity.getAnnotation(Table.class);
        String query = "select * from " + table.name() + " where " + attributes.getString(joint, operator);
        return executeQuery(query, entity);
    }

    public void updateEntity(Attributes setValues, Attributes attributes, String joint, String operator) throws EntityNotFoundException {
        if(countEntities(setValues.getTableName(), attributes, joint, operator) <= 0) throw new EntityNotFoundException("Entity not found");
        String s = "update " + setValues.getTableName() + " set " + setValues.getString(",", "=") + " where " + attributes.getString(joint, operator);
        executeUpdate(s);
    }
    public void updateEntity(Attributes setValues, Attributes attributes) throws EntityNotFoundException {
        updateEntity(setValues, attributes, "and", "=");
    }

    public void insertEntity(Attributes attributes) {
        String query = "insert into " + attributes.getTableName() + " (" + attributes.getColumns(",") + " ) values ( " + attributes.getValues(",") + " )";
        executeUpdate(query);
    }

    public int executeCount(String query) {
        int num = 0;
        try {
            createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            num = resultSet.getInt(1);
            connection.close();
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public int countEntities(String tableName, Attributes attributes, String joint, String operator) {
        String check = "select count(*) from " + tableName + " where " + attributes.getString(joint, operator);
        return executeCount(check);
    }
}
