package dao;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import enums.HolidayType;
import model.*;
import enums.*;
import model.EmployerModel.Employer;
import model.Holiday;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HolidayDAO implements InterfaceDAO<Holiday> ,DataImportExport<Holiday>{

    private Connection connection;

    public HolidayDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException connectionException) {
            connectionException.printStackTrace();
        }
    }

    @Override
    public boolean add(Holiday Holiday) {
        try (PreparedStatement addStatement = connection.prepareStatement(
            "INSERT INTO Holidays (holiday_type, start_date, end_date, employer_id) VALUES (?, ?, ?, ?)")) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            addStatement.setString(1, Holiday.getHolidayType().name());
            addStatement.setDate(2, java.sql.Date.valueOf(Holiday.getStartDate()));
            addStatement.setDate(3, java.sql.Date.valueOf(Holiday.getEndDate()));
            addStatement.setInt(4, Holiday.getEmployerId());
            

            return addStatement.executeUpdate() > 0;

        } catch (SQLException addException) {
            addException.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Holiday Holiday) {
        try (PreparedStatement updateStatement = connection.prepareStatement(
            "UPDATE Holidays SET holiday_type = ?, start_date = ?, end_date = ? WHERE id = ?")) {

            updateStatement.setString(1, Holiday.getHolidayType().name());
            updateStatement.setDate(2, java.sql.Date.valueOf(Holiday.getStartDate()));
            updateStatement.setDate(3, java.sql.Date.valueOf(Holiday.getEndDate()));
            updateStatement.setInt(4, Holiday.getId());

            return updateStatement.executeUpdate() > 0;

        } catch (SQLException updateException) {
            updateException.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Holidays WHERE id = ?")) {

            deleteStatement.setInt(1, id);
            return deleteStatement.executeUpdate() > 0;

        } catch (SQLException deleteException) {
            deleteException.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Holiday> getAll() {
        List<Holiday> Holidays = new ArrayList<>();
        try (ResultSet getResult = connection.prepareStatement("SELECT * FROM employer_holidays").executeQuery()) {

            while (getResult.next()) {
                Holidays.add(new Holiday(
                    getResult.getInt("holiday_id"), 
                    getResult.getInt("employer_id"), 
                    getResult.getString("first_name") + " " + getResult.getString("last_name"), 
                    LocalDate.parse(getResult.getString("start_date")), 
                    LocalDate.parse(getResult.getString("end_date")), 
                    HolidayType.valueOf(getResult.getString("holiday_type"))
                ));
            }

        } catch (SQLException getException) {
            getException.printStackTrace();
        }
        return Holidays;
    }

    @Override
    public int getHolidayDays(int id) {
        try (PreparedStatement searchStatement = connection.prepareStatement("SELECT holiday_number FROM employers WHERE id = ?")) {

            searchStatement.setInt(1, id);
            ResultSet getResult = searchStatement.executeQuery();

            if (getResult.next()) {
                return getResult.getInt("holiday_number");
            }
            return -1;

        } catch (SQLException deleteException) {
            deleteException.printStackTrace();
            return -1;
        }
    }

    @Override
    public String getPassword(String username) {
        throw new RuntimeException("Access denied");
    }

    @Override
    public String getRole(String username) {
        throw new RuntimeException("Access denied");
    }

    @Override
    public boolean createLogin(int employee_id, String username, String password) {
        throw new RuntimeException("Access denied");
    }

    @Override
    public void importData(String filePath) {
        String query = "INSERT INTO Holidays (holiday_type, start_date, end_date, employer_id) VALUES (?, ?, ?, ?)";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");


                if (data.length == 4) {
                    pstmt.setString(1, data[0].trim());
                    pstmt.setDate(2, Date.valueOf(data[1].trim()));
                    pstmt.setDate(3, Date.valueOf(data[2].trim()));
                    pstmt.setInt(4, Integer.parseInt(data[3].trim()));
                    pstmt.addBatch();
                } else {
                    System.out.println("Row ignored due to missing data: " + line);
                }
            }

            pstmt.executeBatch();
            System.out.println("returen hliday with succes");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void exportData(String fileName,List<Holiday> data) {
        try (BufferedWriter writer =new BufferedWriter(new FileWriter(fileName))) {
            writer.write("holiday_type, start_date, end_date, employer_id");
            writer.newLine();
            for (Holiday holiday : data ){
                String line =String.format("%s,%s,%s,%d",
                        holiday.getHolidayType(),
                        holiday.getStartDate(),
                        holiday.getEndDate(),
                        holiday.getEmployerId()

                );
                writer.write(line);
                writer.newLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
