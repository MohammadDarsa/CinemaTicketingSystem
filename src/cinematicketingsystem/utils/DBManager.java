package cinematicketingsystem.utils;

import cinematicketingsystem.annotations.*;
import cinematicketingsystem.exceptions.sqlexceptions.EntityNotFoundException;
import lombok.SneakyThrows;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DBManager {
    private static DBManager instance;
    private Connection connection = null;
    private final String url = "jdbc:mysql://bw3uu6emf0jk4qraonoh-mysql.services.clever-cloud.com:3306/bw3uu6emf0jk4qraonoh?characterEncoding=latin1&useConfigs=maxPerformance";
    private final String user = "udlz31um2uy8mvj1";
    private final String password = "NPEAg9OPyT8t3bK8Lup3";

    private final String url2 = "jdbc:mysql://localhost:3306/mydb?characterEncoding=latin1&useConfigs=maxPerformance";
    private final String user2 = "root";
    private final String password2 = "kouti123";

    @SneakyThrows
    private DBManager() {
        createConnection();
    }

    public static DBManager getInstance() {
        if(instance == null) instance = new DBManager();
        return instance;
    }

    private void createConnection() throws ClassNotFoundException, SQLException {
        if(connection != null) return;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url2, user2, password2);
    }

    public void executeUpdate(String query) {
        try {
            createConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
//            connection.close();
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
//            connection.close();
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

    public <T> void insertAll(List<T> items, Class<T> entity) {
        items.forEach(item -> insertEntity(item, entity));
    }

    public <T> void insertEntity(T item, Class<T> entity) {
        Field[] fields = entity.getDeclaredFields();
        List<Field> fieldList = new ArrayList<>();
        List<Field> oneToOneFields = new ArrayList<>();
        List<Field> manyToOneFields = new ArrayList<>();
        List<Field> oneToManyFields = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.isAnnotationPresent(ManyToOne.class)) manyToOneFields.add(field);
            if(field.isAnnotationPresent(OneToOne.class)) oneToOneFields.add(field);
            if(field.isAnnotationPresent(OneToMany.class)) oneToManyFields.add(field);
            Col col = null;
            if(field.isAnnotationPresent(Col.class)) col = field.getAnnotation(Col.class);
            if(field.isAnnotationPresent(ID.class) || col == null || col.insertIgnore() == true) continue;
            fieldList.add(field);
        }



        StringBuilder query = new StringBuilder("insert into " + entity.getAnnotation(Table.class).name() + " (");
        for(Field field:fieldList) {
            Col col = field.getAnnotation(Col.class);
            query.append(col.name()).append(", ");
        }

        for (Field field:manyToOneFields) {
            ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
            query.append(manyToOne.key()).append(", ");
        }

        for (Field field:oneToOneFields) {
            OneToOne oneToOne = field.getAnnotation(OneToOne.class);
            query.append(oneToOne.key()).append(", ");
        }

        query = new StringBuilder(query.substring(0, query.length() - 2) + ") values (");
        for(Field field:fieldList) {
            try {
                query.append("'").append(field.get(item).toString()).append("', ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        for(Field field:manyToOneFields) {
            try {
                query.append("'").append(getIdFromType(field.get(item), field.getClass())).append("', ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        for(Field field:oneToOneFields) {
            try {
                query.append("'").append(getIdFromType(field.get(item), field.getClass())).append("', ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        query = new StringBuilder(query.substring(0, query.length() - 2) + ")");
        executeUpdate(query.toString());
    }

    private <T> String getIdFromType(Object item, Class<T> type) {
        return Arrays.stream(item.getClass().getDeclaredFields()).peek(field -> field.setAccessible(true)).filter(field -> field.isAnnotationPresent(ID.class)).map(field -> {
            try {
                return field.get(item).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }).findFirst().get();
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
//                    connection.close();
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

                if(field.isAnnotationPresent(OneToOne.class) || field.isAnnotationPresent(ManyToOne.class)) {
                    OneToOne oneToOne = field.getAnnotation(OneToOne.class);
                    ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
                    try {
                        Attributes attributes = new Attributes();
                        String fkid = null;
                        if(oneToOne != null)
                            fkid = resultSet.getString(oneToOne.key());
//                        if(manyToOne!= null)
//                            fkid = resultSet.getString(manyToOne.key());
                        String colid = getIdNameFromType(field.getType());
                        attributes.addAttribute(colid, fkid);
                        field.set(dto, selectAll(field.getType(), attributes).get(0));
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                }

                if(field.isAnnotationPresent(OneToMany.class)) {
                    OneToMany oneToMany = field.getAnnotation(OneToMany.class);
                    try {
                        Attributes attributes = new Attributes();
                        String fkid = resultSet.getString(1);
                        String colid = oneToMany.key();
                        attributes.addAttribute(colid, fkid);
                        field.set(dto, selectAll(getGenericType(field), attributes));
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                }

                if (field.isAnnotationPresent(Col.class)) {
                    Col col = field.getAnnotation(Col.class);
                    String name = col.name();
                    try {
                        Object value = resultSet.getObject(name);
                        field.set(dto, value);
                    } catch (Exception e) {
//                        e.printStackTrace();
                    }
                }
            }
            list.add(dto);
        }
        return list;
    }

    private <T> String getIdFromType(Class<T> type, T dto) {
        try {
            return Arrays.stream(type.getDeclaredFields()).filter(field -> field.isAnnotationPresent(ID.class)).findAny().get().get(dto).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
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
//            connection.close();
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public int countEntities(String tableName, Attributes attributes, String joint, String operator) {
        String check = "select count(*) from " + tableName + " where " + attributes.getString(joint, operator);
        return executeCount(check);
    }

    public <T> String getIdNameFromType(Class<T> type) {
        return Arrays.stream(type.getDeclaredFields()).filter(field -> field.isAnnotationPresent(ID.class)).findAny().get().getAnnotation(Col.class).name();
    }

    public Class<?> getGenericType(Field field) {
        Type genericFieldType = field.getGenericType();
        if(genericFieldType instanceof ParameterizedType){
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            Type[] fieldArgTypes = aType.getActualTypeArguments();
            return (Class)fieldArgTypes[0];
        }
        return null;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("hello");
        connection.close();
        super.finalize();
    }
}
