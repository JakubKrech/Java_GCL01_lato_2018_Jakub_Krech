/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analogclock;

import java.awt.Font;
import javafx.scene.image.Image ;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import static java.lang.Math.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 *
 * @author Kuba
 */

class ResizableClock extends Canvas {
 
    public ResizableClock() {
      // Redraw canvas when size changes
      widthProperty().addListener(evt -> draw());
      heightProperty().addListener(evt -> draw()); 
    }
    
    Calendar calendar = GregorianCalendar.getInstance();
    double s  = calendar.get(Calendar.SECOND);
    double m  = calendar.get(Calendar.MINUTE);
    double h    = calendar.get(Calendar.HOUR); 
    
    void timertick()
    {
        s++; if(s == 60)
        {
            s = 0;
            m++;
        }
        if (m == 60) 
        {
            m = 0;
            h++;
        }
        if (h == 12) h = 0;
         
        System.out.printf(String.valueOf(h) + " " + String.valueOf(m) + " " + String.valueOf(s) + "\n");
    }
    void draw() {
        double height = getHeight() - 25;
        double width = getWidth() - 225;
        double centerX = width/2;
        double centerY = width/2;
        
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width + 225, height);
        // TŁO
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,width, width);
        // TARCZA ZEGARA
        gc.setFill(Color.WHITE);
        gc.fillOval(width/20, width/20, width - 2*width/20, width - 2*width/20);
        // KRESKI GODZINOWE
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(15*width/500);
        
        gc.strokeLine(width/2, 0+width/20, width/2, width/10+width/20);
        gc.strokeLine(width/2, width-width/20, width/2 , 9*width/10-width/20);
        gc.strokeLine(0+width/20, width/2, width/10+width/20, width/2);
        gc.strokeLine(width-width/20, width/2, 9*width/10-width/20, width/2);
        
        
        
        // ------- WSKAZOWKI --------
        
        // HOUR HAND
        Line hourHand = new Line(width/2, width/2, width/2, 150*(width/575));      
        Line minuteHand = new Line(width/2, width/2, width/2, 100*(width/575));  
        Line secondHand = new Line(width/2, width/2, width/2, 50*(width/575));  
        
        
        
        double secondRotation = s*6 * PI/180 - PI/2;
        double minuteRotation = m*6 * PI/180 - PI/2;
        double h2 = h + (m/60);
        double hourRotation =   (h2*30 * PI/180 - PI/2);
        
        
        
        double secondR = sqrt((secondHand.getEndX() - secondHand.getStartX())*(secondHand.getEndX() - secondHand.getStartX()) + 
                (secondHand.getEndY() - secondHand.getStartY())*(secondHand.getEndY() - secondHand.getStartY()));
        double minuteR = sqrt((minuteHand.getEndX() - minuteHand.getStartX())*(minuteHand.getEndX() - minuteHand.getStartX()) + 
                (minuteHand.getEndY() - minuteHand.getStartY())*(minuteHand.getEndY() - minuteHand.getStartY()));
        double hourR = sqrt((hourHand.getEndX() - hourHand.getStartX())*(hourHand.getEndX() - hourHand.getStartX()) + 
                (hourHand.getEndY() - hourHand.getStartY())*(hourHand.getEndY() - hourHand.getStartY()));
   
        System.out.printf(String.valueOf(hourRotation) + " asdasddsa\n");
        
        hourHand.setEndX(centerX + hourR*cos(hourRotation));
        hourHand.setEndY(centerY + hourR*sin(hourRotation));
        
        minuteHand.setEndX(centerX + minuteR*cos(minuteRotation));
        minuteHand.setEndY(centerY + minuteR*sin(minuteRotation));
        
        secondHand.setEndX(centerX + secondR*cos(secondRotation));
        secondHand.setEndY(centerY + secondR*sin(secondRotation));

        
        gc.setStroke(Color.CORNFLOWERBLUE);
        gc.setLineWidth(15*width/1000);
        gc.strokeLine(centerX, centerY, hourHand.getEndX(), hourHand.getEndY());
        
        gc.setStroke(Color.DEEPPINK);
        gc.setLineWidth(10*width/1000);
        gc.strokeLine(centerX, centerY, minuteHand.getEndX(), minuteHand.getEndY());
        
        gc.setStroke(Color.BROWN);
        gc.setLineWidth(5*width/1000);
        gc.strokeLine(centerX, centerY, secondHand.getEndX(), secondHand.getEndY());
        
        // KROPKA NA ŚRODKU       
        gc.setFill(Color.BLACK);
        gc.fillOval(width/2 - 50*width/2000, width/2 - 50*width/2000, 50*width/1000, 50*width/1000);
    }
 
    @Override
    public boolean isResizable() {
      return true;
    }
 
    @Override
    public double prefWidth(double height) {
      return getHeight() - 25;  // zeby zachowalo ratio, bo obszar zegaru to heightxheight, wczesniej wypychalo tablice w prawo
    }
 
    @Override
    public double prefHeight(double width) {
      return getHeight();
    }
  }

public class AnalogClock extends Application {
    
    TableView table = new TableView();
    TableColumn hour = new TableColumn("Godz");
    TableColumn minute = new TableColumn("Min");
    Timer timer = new Timer();
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Analog Clock - Jakub Krech");
        
        BorderPane root = new BorderPane(); 
        Scene scene = new Scene(root, 800, 600);
        
        
        // -------------------- MENU --------------------
        
        MenuBar menu = new MenuBar();
        
            Menu menuProgram = new Menu("Program");                             // PIERWSZE MENU
            
                MenuItem zamknij = new MenuItem("Zamknij");
                    zamknij.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent t) {
                            timer.purge();
                            System.exit(0);
                        }
                    });  
                menuProgram.getItems().add(zamknij);
            
            Menu menuBudzik = new Menu("Budzik");                               // DRUGIE MENU
            
                MenuItem dodajAlarm = new MenuItem("Dodaj alarm");
                    dodajAlarm.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent t) {
                            Stage stage = new Stage();  
                            stage.setTitle("Dodaj alarm");
                            BorderPane root = new BorderPane(); 
                            Scene scene = new Scene(root, 260, 70);
                            
                            
                            VBox vbox1 = new VBox();
                            
                                Label label1 = new Label("Nazwa alarmu");
                                TextField textField1 = new TextField ();
                            
                            
                                vbox1.getChildren().addAll(label1, textField1);
                            
                            VBox vbox2 = new VBox();

                                Label label2 = new Label("Godzina |  Minuta");
                                
                                HBox hbox2 = new HBox();
                                    TextField textField2 = new TextField ();
                                        textField2.setMaxWidth(50);
                                    TextField textField3 = new TextField ();
                                        textField3.setMaxWidth(50);
                                hbox2.getChildren().addAll(textField2, textField3);
                            
                                vbox2.getChildren().addAll(label2, hbox2);
                            
                            HBox hbox1 = new HBox();
                                Label label3 = new Label("    Niepoprawne dane!");
                                    label3.setVisible(false);
                                    label3.setTextFill(Color.RED);
                                Button button = new Button("Dodaj alarm");
                                 button.setOnAction(new EventHandler<ActionEvent>() {
                                    public void handle(ActionEvent t) {      
                                        try{
                                            if(textField1.getText().isEmpty() || textField2.getText().isEmpty() || textField3.getText().isEmpty())
                                            {
                                                label3.setVisible(true);
                                            }
                                            else
                                            {
                                                int godz = Integer.parseInt(textField2.getText());
                                                int min = Integer.parseInt(textField3.getText()); 
                                                if(godz < 1 || godz > 24 || min < 0 || min > 60) label3.setVisible(true);
                                                else
                                                {
                                                    Alarm alarm = new Alarm(textField1.getText(), textField2.getText(), textField3.getText());
                                                    table.getItems().add(alarm);
                                                    label3.setVisible(false);
                                                    hour.setSortType(TableColumn.SortType.ASCENDING);
                                                    minute.setSortType(TableColumn.SortType.ASCENDING);
                                                    table.getSortOrder().addAll(hour, minute);
                                                    hour.setSortable(true);
                                                    minute.setSortable(true);
                                                    table.sort();
                                                }
                                            }
                                        }catch(NumberFormatException e)
                                        {
                                            label3.setVisible(true);
                                        }
                                    }
                                      
                                }); 
                                
                           
                                hbox1.getChildren().addAll(button, label3);
                            
                            root.setBottom(hbox1);
                            root.setLeft(vbox1);
                            root.setRight(vbox2);
                            
                            stage.setScene(scene);
                            stage.show();
                        }
                    });
                    
                MenuItem wyczyscAlarmy = new MenuItem("Wyczyść alarmy");
                    wyczyscAlarmy.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent t) {
                            table.getItems().clear();
                        }
                    }); 
                menuBudzik.getItems().addAll(dodajAlarm, wyczyscAlarmy);
            
            Menu menuAutor = new Menu("O autorze");                             // TRZECIE MENU
                MenuItem dummy_menuItem = new MenuItem("O");
               
                dummy_menuItem.setOnAction(new EventHandler<ActionEvent>() {    // trzeba bylo zrobic taki dummyItem, zeby jego akcja wywolywala sie
                        public void handle(ActionEvent t) {                     // przy kliknieciu na button z menu, bo domyslnie takiego czegos nie ma
                            
                            // ---- O AUTORZE ----
                            Stage stage = new Stage();  
                            stage.setTitle("O autorze - Jakub Krech");
                            BorderPane root = new BorderPane(); 
                            Scene scene = new Scene(root, 450, 256);
                            
                            Image image = new Image("photo.png");
                            ImageView view = new ImageView();
                            view.setImage(image);
                            root.setLeft(view);
                            
                            Label label = new Label("DANE AUTORA \n\n"
                                    + "Imie: Jakub\n"
                                    + "Nazwisko: Krech\n"
                                    + "Wydział: WIMiIP\n"
                                    + "Kierunek: Informatyka Stosowana\n"
                                    + "Rok akademicki: 2017/2018\n"
                                    + "Grupa laboratoryjna: 4");
                            
                            root.setCenter(label);
                            
                            stage.setScene(scene);
                            stage.show();
                        }
                    }); 
                menuAutor.getItems().add(dummy_menuItem);
            
            if (menuAutor.getItems().size() == 1) {                             // przy kliknieciu w 'O Autorze' jego menuItem (dummy) jest usuwany oraz jest
                menuAutor.showingProperty().addListener(                        // wywolywana jego akcja
                    (observableValue, oldValue, newValue) -> {
                        if (newValue) {
                            // the first menuItem is triggered
                            menuAutor.getItems().get(0).fire();
                        }
                    }
                );
            }

            
            menu.getMenus().addAll(menuProgram, menuBudzik, menuAutor);
        root.setTop(menu);
                
                
         // -------------------- TABLE --------------------
                
           
        table.setPrefWidth(225);
        table.setEditable(true);
        table.getSelectionModel().setCellSelectionEnabled(false);
        
        TableColumn name = new TableColumn("Nazwa");
        
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        
        hour.setCellValueFactory(new PropertyValueFactory<>("hour"));
        
        
        minute.setCellValueFactory(new PropertyValueFactory<>("minute"));
        
        name.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
        name.setResizable(false);
        hour.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        hour.setResizable(false);
        minute.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        minute.setResizable(false);
        hour.setEditable(true);
        minute.setEditable(true);
        name.setEditable(true);

        
        table.getColumns().addAll(name, hour, minute);
        
        final ContextMenu contextMenu = new ContextMenu();  
        final MenuItem removeMenuItem = new MenuItem("Remove");
        
        contextMenu.getItems().add(removeMenuItem);
        
        table.setContextMenu(contextMenu);
        
        
        root.setRight(table);
        
        
        
        // -------------------- CANVAS --------------------
        
        ResizableClock canvas = new ResizableClock();       
        canvas.widthProperty().bind(root.widthProperty());
        canvas.heightProperty().bind(root.heightProperty());
                
        root.setLeft(canvas);
        
        
        // -------------------- TIMER --------------------
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                canvas.timertick();
                canvas.draw();
                alarmcheck();
            }
        },0, 1000);
        
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Platform.setImplicitExit(true);
        primaryStage.setOnCloseRequest((ae) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public class Alarm {
        private String name;
        private String hour;
        private String minute;

        private Alarm(String n, String h, String m) {
            this.name = n;
            this.hour = h;
            this.minute = m;
        }

        public String getName() {
            return name;
        }   
        public String getHour() {
            return hour;
        }    
        public String getMinute() {
            return minute;
        }     
    }
    
    public void alarmcheck()
    {
        if(!table.getItems().isEmpty()) 
        {
            Alarm selectedRecord = (Alarm)table.getItems().get(0);
            
            Calendar calendar = GregorianCalendar.getInstance();
            double s  = calendar.get(Calendar.SECOND);
            double m  = calendar.get(Calendar.MINUTE);
            double h    = calendar.get(Calendar.HOUR);
            
            if(Double.parseDouble(selectedRecord.hour) == h && Double.parseDouble(selectedRecord.minute) == m)
            {
                String musicFile = "music.mp3";     // For example
                    
                Media sound = new Media(new File(musicFile).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
                
                table.getItems().remove(0);
            }
        }  
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    } 
}

