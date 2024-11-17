package com.example.miguelmoya_retoconjuntodiad_2parte.controller;

import com.example.miguelmoya_retoconjuntodiad_2parte.HelloApplication;
import com.example.miguelmoya_retoconjuntodiad_2parte.HibernateUtil;
import com.example.miguelmoya_retoconjuntodiad_2parte.dao.CopiaDAO;
import com.example.miguelmoya_retoconjuntodiad_2parte.model.Copia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ListaPeliculasController {

    @FXML
    private Label mensajeBienvenidaLabel;

    @FXML
    private Button agregarNuevaPeliculaButton;

    @FXML
    private Button agregarNuevaCopiaButton;

    @FXML
    private TableView<Copia> peliculaTableView;

    @FXML
    private TableColumn<Copia, String> tituloTableColumn;
    @FXML
    private TableColumn<Copia, String> directorTableColumn;
    @FXML
    private TableColumn<Copia, String> duracionTableColumn;
    @FXML
    private TableColumn<Copia, Integer> añoTableColumn;
    @FXML
    private TableColumn<Copia, String> generoTableColumn;
    @FXML
    private TableColumn<Copia, String> estadoTableColumn;

    public ListaPeliculasController() {
    }

    public void initialize() {
        iniciarSesion();

        CopiaDAO copiaDAO = new CopiaDAO(HibernateUtil.getSessionFactory());

        List<Copia> listaCopias = copiaDAO.findByUserId(HelloApplication.usuarioIniciado.getId());
        ObservableList<Copia> copias = FXCollections.observableArrayList(listaCopias);
        peliculaTableView.setItems(copias);

        tituloTableColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPelicula().getTitulo() != null ? cellData.getValue().getPelicula().getTitulo() : "Sin título"));

        directorTableColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPelicula().getDirector() != null ? cellData.getValue().getPelicula().getDirector() : "Sin director"));

        duracionTableColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPelicula().getDuracion() != null ? cellData.getValue().getPelicula().getDuracion().toString() : "0"));

        añoTableColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getPelicula().getAnio() != null ? cellData.getValue().getPelicula().getAnio() : 0));

        generoTableColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPelicula().getGenero() != null ? cellData.getValue().getPelicula().getGenero() : "Sin género"));

        estadoTableColumn.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEstado() != null ? cellData.getValue().getEstado() : "Estado desconocido"));
    }

    public void iniciarSesion() {
        mensajeBienvenidaLabel.setText("Hola, " + HelloApplication.usuarioIniciado.getNombre());

        if (HelloApplication.usuarioIniciado.getAdmin()) {
            agregarNuevaPeliculaButton.setVisible(true);
        } else {
            agregarNuevaCopiaButton.setLayoutX(755f);
        }
    }

    public void cerrarSesionOnActionButton() throws IOException {
        System.out.println("Cerrando sesion...");
        HelloApplication.usuarioIniciado = null;
        Stage ventanaActual = (Stage) mensajeBienvenidaLabel.getScene().getWindow();
        HelloApplication.abrirVentana("Login.fxml", "Login", ventanaActual, 600, 400);
        System.out.println("Sesion cerrada con exito");
    }

    public void agregarNuevaPeliculaOnActionButton() throws IOException {
        Stage ventanaActual = (Stage) peliculaTableView.getScene().getWindow();
        HelloApplication.abrirVentana("AgregarPelicula.fxml", "Agregar Pelicula", ventanaActual, 600, 400);
    }

    public void agregarNuevaCopiaOnActionButton() throws IOException {
        Stage ventanaActual = (Stage) peliculaTableView.getScene().getWindow();
        HelloApplication.abrirVentana("AgregarCopia.fxml", "Agregar Copia", ventanaActual, 600, 400);
    }
}
