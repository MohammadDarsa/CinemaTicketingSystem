package test;

import com.mysql.jdbc.Connection;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class demo extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

        connectToDB();
    }

    private void connectToDB() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= (Connection) DriverManager.getConnection(
                    "jdbc:mysql://bw8vg030tuezxbpr6mj2-mysql.services.clever-cloud.com/bw8vg030tuezxbpr6mj2",
                    "u8iiuo74brq3xzku","h9UlTaTbwKiTf3fY5rEQ");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from movie");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
    public static void main(String[] args) {
        launch(args);
    }
}
