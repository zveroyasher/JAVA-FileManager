package sample;
/**
 * Sample Skeleton for 'sample.fxml' Controller Class
 */

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import static sample.ListFiles.getStaticPath;
import static sample.ListFiles.removeStaticPath;

public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="fileMenu1"
    private MenuButton fileMenu1; // Value injected by FXMLLoader

    @FXML // fx:id="aboutMenu1"
    private MenuButton aboutMenu1; // Value injected by FXMLLoader

    @FXML // fx:id="viewMenu1"
    private MenuButton viewMenu1; // Value injected by FXMLLoader

    @FXML // fx:id="fileMenu2"
    private MenuButton fileMenu2; // Value injected by FXMLLoader

    @FXML // fx:id="viewMenu2"
    private MenuButton viewMenu2; // Value injected by FXMLLoader

    @FXML // fx:id="aboutMenu2"
    private MenuButton aboutMenu2; // Value injected by FXMLLoader

    @FXML // fx:id="listView1"
    private ListView<String> listView1; // Value injected by FXMLLoader

    @FXML // fx:id="listView2"
    private ListView<String> listView2; // Value injected by FXMLLoader
    private ListFiles ListFiles;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        ListFiles newListFile = new ListFiles();
        List ListfileString = newListFile.getLst();
        ObservableList<String> listPanelOne = FXCollections.observableArrayList(ListfileString);

        listView1.setItems(listPanelOne);
        addListView();
    //////////////////////////////////////////////////////////////////////////
        listView2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
                    System.out.println("DOUBLE CLICK");
                addListView2();
                }
            }
        });
////////////////////////////////////////Press enter////////////////////////////////////
        listView2.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    System.out.println("ENTER PRESS");
                        addListView2();
                    }

                }

        });


    }
public void addListView(){
    ListFiles newListFile = new ListFiles();
    String newListFile3 = newListFile.getRootLst();
    String[] subStr = newListFile3.split("/"); // Разделения строки str с помощью мето
    System.out.println("Substr 0 := "+subStr[0]+"END");
    for (int x=1; x<= (subStr.length-1) ; x++) {
        String trimItem = subStr[x].replaceAll(",","");
        String trimItem2 = trimItem.replaceAll("]","");
        listView2.getItems().add(trimItem2);
    }
}

public void addListView2(){
    String selectedItem = listView2.getSelectionModel().getSelectedItem();
    int index = listView2.getSelectionModel().getSelectedIndex();
    System.out.println("index: " + index+ " item: " + selectedItem );
    if(index == 0){
       removeStaticPath();
       selectedItem = "";
        //selectedItem = selectedItem.replace("/","");

    }
    /////////////////////////////////code for update listviwer ///////////////////////////
    System.out.println("StaticPath"+ selectedItem);

    listView2.getItems().clear();
    ListFiles newListFile = new ListFiles();

    ArrayList<String> newListFile3 = newListFile.getRootLstADD(selectedItem);
   // String[] subStr = newListFile3 .split("/"); // Разделения строки str с помощью мето
   // System.out.println("Substr 0 := "+subStr[0]+"END");

    for (int x=0; x<= (newListFile3.size()-1) ; x++) {
        if(x==0){ listView2.getItems().add("...up");

        continue;
        }
        String trimItem = newListFile3.get(x).replaceAll(",","");
        String trimItem2 = trimItem.replaceAll("]","");
        listView2.getItems().add(trimItem2);
        }
    }
}
