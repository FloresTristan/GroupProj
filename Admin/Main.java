import javax.swing.*;
import github.group.login.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VehicleFilterGUI(new VehicleFilterModel());
        });
    }
}