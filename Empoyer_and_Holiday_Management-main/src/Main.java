import controller.EmployerController;
import controller.HolidayController;
import dao.EmployerDAO;
import dao.HolidayDAO;
import model.EmployerLogic;
import model.HolidayLogic;
import view.*;
import dao.*;
import model.*;
import enums.*;
import controller.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        FormFrame frame = new FormFrame();
        new EmployerController(frame, new EmployerLogic(new EmployerDAO()));
        new HolidayController(frame, new HolidayLogic(new HolidayDAO()));
    }
}
