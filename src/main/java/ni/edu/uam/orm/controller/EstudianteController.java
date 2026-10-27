package ni.edu.uam.orm.controller;

import ni.edu.uam.orm.model.Estudiante;
import ni.edu.uam.orm.repository.EstudianteRepository;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class EstudianteController {

    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtCorreo;
    @FXML private ComboBox<String> cmbCarrera;
    @FXML private TableView<Estudiante> tablaEstudiantes;
    @FXML private TableColumn<Estudiante, Long> colId;
    @FXML private TableColumn<Estudiante, String> colNombres;
    @FXML private TableColumn<Estudiante, String> colApellidos;
    @FXML private TableColumn<Estudiante, String> colCorreo;
    @FXML private TableColumn<Estudiante, String> colCarrera;

    private final EstudianteRepository repository = new EstudianteRepository();

    @FXML
    public void initialize() {
        cmbCarrera.setItems(FXCollections.observableArrayList(
                "Ingeniería de Sistemas", "Ingeniería Industrial", "Administración"
        ));

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));

        consultarEstudiantes();
    }

    @FXML
    private void guardarEstudiante() {
        if (txtNombres.getText().isEmpty() || txtApellidos.getText().isEmpty() ||
                txtCorreo.getText().isEmpty() || cmbCarrera.getValue() == null) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return;
        }

        Estudiante nuevo = new Estudiante(
                txtNombres.getText(), txtApellidos.getText(),
                txtCorreo.getText(), cmbCarrera.getValue()
        );

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                repository.guardar(nuevo);
                return null;
            }
        };

        task.setOnSucceeded(e -> {
            txtNombres.clear(); txtApellidos.clear(); txtCorreo.clear();
            cmbCarrera.getSelectionModel().clearSelection();
            consultarEstudiantes();
        });

        task.setOnFailed(e -> mostrarAlerta("Error", "No se pudo guardar el registro (¿Correo duplicado?)."));

        new Thread(task).start();
    }

    @FXML
    private void consultarEstudiantes() {
        Task<List<Estudiante>> task = new Task<>() {
            @Override
            protected List<Estudiante> call() {
                return repository.listar();
            }
        };

        task.setOnSucceeded(e -> tablaEstudiantes.setItems(FXCollections.observableArrayList(task.getValue())));
        new Thread(task).start();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}