package model;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import enums.*;
import dao.EmployerDAO;
import enums.Poste;
import enums.Role;
import model.EmployerModel.Employer;

import java.util.List;

public class EmployerLogic {

    private EmployerDAO dao;

    public EmployerLogic (EmployerDAO dao) {
        this.dao = dao;
    }

    public boolean addEmployer(int id, String firstName, String lastName, String email, int phoneNumber, double salary, Role role, Poste poste) {


        return dao.add( new Employer(
                id,
                firstName,
                lastName,
                email,
                phoneNumber,
                salary,
                role,
                poste
        ));
    }

    public boolean updateEmployer(int id, String firstName, String lastName, String email, int phoneNumber, double salary, Role role, Poste poste) {


        if ( isValidEmail(email) ) {

            return dao.update(new Employer(
                    id,
                    firstName,
                    lastName,
                    email,
                    phoneNumber,
                    salary,
                    role,
                    poste
            ));
        }

        return false;
    }

    public boolean handleLogin(String username, String password) {
        return (null == dao.getPassword(username) || !dao.getPassword(username).equals(password)) ? false : true;
    }

    public boolean handleAccess(String username) {
        return (null == dao.getRole(username) || !dao.getRole(username).equals("MANAGER")) ? false : true ;
    }

    public boolean createLogin(int id, String username, String password, String confirmPassword) {
        if( !password.equals(confirmPassword) || username.length() == 0 || password.length() == 0 || confirmPassword.length() == 0 ) return false;
        return dao.createLogin(id, username, password);
    }

    private boolean isValidEmail(String email) {
        return email.contains("@gmail.com");
    }

    private boolean isValidPhoneNumber(int phoneNumber) {
        return String.valueOf(phoneNumber).length() == 10;
    }

    private boolean isValidName(String firstName, String lastName) {
        return firstName.equals("") || lastName.equals("");
    }

    private boolean isValidSalary (double salary) {
        return salary > 0;
    }

    public boolean deleteEmployer(int id) {
        return dao.delete(id);
    }

    public List<Employer> getAllEmployers() {
        return dao.getAll();
    }

    private boolean checkFileExists(File file){
        if(!file.exists()){
            throw new IllegalArgumentException("le fichier n'existe pas :" + file.getPath());
        }
        return true;
    }

    private boolean checkIsFile(File file){
        if(!file.isFile()){
            throw new IllegalArgumentException("le chemin specifie n'est pas un fichier :" + file.getPath());
        }
        return true;
    }

    private boolean checkIsReadable(File file){
        if(!file.canRead()){
            throw new IllegalArgumentException("le fichier n'est pas lisible :" + file.getPath());
        }
        return true;
    }

    public void importData(String FileName) {
        File file = new File(FileName);
        checkFileExists(file);
        checkIsFile(file);
        checkIsReadable(file);
        dao.importData(FileName);
    }

    public void exportData(String FileName , List<Employer> data) throws IOException {
        File file = new File(FileName);
        checkFileExists(file);
        checkIsFile(file);
        dao.exportData(FileName, data);
    }
}

