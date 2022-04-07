package smProject4;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController implements Initializable {
	StoreOrders database;
	Order currentOrder;

	Stage donutsStage;
	Stage coffeeStage;
	Stage basketStage;
	Stage ordersStage;

	@FXML private Pane pane;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		donutsStage = new Stage();
		donutsStage.setTitle("Order Donuts");

		coffeeStage = new Stage();
		coffeeStage.setTitle("Order Coffee");

		basketStage = new Stage();
		basketStage.setTitle("Current Order");

		ordersStage = new Stage();
		ordersStage.setTitle("View all Store Orders");

		currentOrder = new Order();
		database = new StoreOrders();
	}

	public void orderDonuts() throws IOException {
		FXMLLoader donutLoader = new FXMLLoader(getClass().getResource("OrderDonutsView.fxml"));
		Parent donutRoot = donutLoader.load();
		donutsStage.setScene(new Scene(donutRoot, 600, 400));
		donutsStage.show();
	}

	public void orderCoffee() throws IOException {
		FXMLLoader coffeeLoader = new FXMLLoader(getClass().getResource("OrderCoffeeView.fxml"));
		Parent coffeeRoot = coffeeLoader.load();
		OrderCoffeeController occ = coffeeLoader.getController();
		coffeeStage.setScene(new Scene(coffeeRoot, 600, 400));
		coffeeStage.show();
		occ.setMain(this);
	}

	public void currentOrder() throws IOException {
		FXMLLoader basketLoader = new FXMLLoader(getClass().getResource("BasketView.fxml"));
		Parent basketRoot = basketLoader.load();
		BasketController bc = basketLoader.getController();
		basketStage.setScene(new Scene(basketRoot, 600, 400));
		basketStage.show();
		bc.populateList(currentOrder, this);
	}

	public void storeOrders() throws IOException {
		FXMLLoader storeLoader = new FXMLLoader(getClass().getResource("OrdersView.fxml"));
		Parent ordersRoot = storeLoader.load();
		OrdersController oc = storeLoader.getController();
		ordersStage.setScene(new Scene(ordersRoot, 600, 400));
		ordersStage.show();
		oc.setup(database);
	}

	public void addCoffee(ArrayList<Coffee> coffees) {
		for(Coffee coffee : coffees) {
			currentOrder.add(coffee);
		}
	}
	
	public void addDonuts(ArrayList<Donut> donuts) {
		for(Donut donut : donuts) {
			currentOrder.add(donut);
		}
	}

	public void addOrder() {
		database.add(currentOrder);
		currentOrder = new Order();
	}

	public void closeListener(Stage stage) {
		stage.setOnCloseRequest(e -> {
			donutsStage.close();
			coffeeStage.close();
			basketStage.close();
			ordersStage.close();
		});
	}
}