-- Drop existing tables and views
DROP TABLE IF EXISTS holidays;
DROP TABLE IF EXISTS "login";
DROP TABLE IF EXISTS employers;
DROP VIEW IF EXISTS employer_holidays;

-- Create employers table
CREATE TABLE IF NOT EXISTS employers (
    id SERIAL PRIMARY KEY,                  -- Employer ID
    first_name VARCHAR(100) NOT NULL,        -- Employer's first name
    last_name VARCHAR(100) NOT NULL,         -- Employer's last name
    email VARCHAR(50) NOT NULL UNIQUE,       -- Employer's email (unique)
    phone VARCHAR(15),                       -- Employer's phone number
    salary DECIMAL(10, 2),                   -- Employer's salary
    role VARCHAR(50),                        -- Employer's role
    poste VARCHAR(50),                       -- Employer's position
    holiday_number INTEGER DEFAULT 25        -- Number of holidays (default 25)
);

-- Create login table
CREATE TABLE IF NOT EXISTS "login" (
    id SERIAL PRIMARY KEY,                   -- Login ID
    username VARCHAR(50) NOT NULL UNIQUE,    -- Username (unique)
    password VARCHAR NOT NULL,               -- Hashed password
    employer_id INTEGER UNIQUE,              -- Associated employer ID (unique)
    CONSTRAINT fk_employee_id FOREIGN KEY (employer_id) REFERENCES employers(id) ON DELETE CASCADE
);

-- Create holidays table
CREATE TABLE holidays (
    id SERIAL PRIMARY KEY,                   -- Holiday ID
    holiday_type VARCHAR(100) NOT NULL,       -- Type of holiday (e.g., Paid Leave)
    start_date DATE NOT NULL,                 -- Start date of the holiday
    end_date DATE NOT NULL,                   -- End date of the holiday
    employer_id INTEGER NOT NULL,             -- Employer ID
    CONSTRAINT fk_employer_id FOREIGN KEY (employer_id) REFERENCES employers(id) ON DELETE CASCADE
);

-- Create a view to show employer details with their holiday information
CREATE OR REPLACE VIEW employer_holidays AS
SELECT 
    e.id AS employer_id, 
    e.first_name, 
    e.last_name, 
    e.email, 
    e.phone, 
    e.salary, 
    e.role, 
    e.poste, 
    e.holiday_number, 
    h.id AS holiday_id, 
    h.holiday_type, 
    h.start_date, 
    h.end_date
FROM 
    employers e
JOIN 
    holidays h ON e.id = h.employer_id;

-- Insert a test employer
INSERT INTO employers (first_name, last_name, email, phone, salary, role, poste, holiday_number)
VALUES ('John_Doe', 'Smith', 'johndoe@email.com', '0909090909', 200.00, 'MANAGER', 'DEVELOPEUR', 25);

-- Insert a test login entry
INSERT INTO "login" (username, password, employer_id)
VALUES ('user', 'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1', 1);

CREATE OR REPLACE FUNCTION update_holiday_number()
RETURNS TRIGGER AS $$
DECLARE
    holiday_duration integer;
BEGIN
    -- Handle the INSERT case
    IF TG_OP = 'INSERT' THEN
        -- Subtract the holiday days from the employer's holiday number
        UPDATE employers
        SET holiday_number = holiday_number - (NEW.end_date - NEW.start_date)
        WHERE id = NEW.employer_id;

    -- Handle the UPDATE case
    ELSIF TG_OP = 'UPDATE' THEN
        -- Adjust the holiday number by calculating the difference between old and new holiday duration
        UPDATE employers
        SET holiday_number = holiday_number + (OLD.end_date - OLD.start_date) - (NEW.end_date - NEW.start_date)
        WHERE id = NEW.employer_id;

    -- Handle the DELETE case
    ELSIF TG_OP = 'DELETE' THEN
        -- Recover the holiday days to the employer's holiday number
        holiday_duration := OLD.end_date - OLD.start_date;
        UPDATE employers
        SET holiday_number = holiday_number + holiday_duration
        WHERE id = OLD.employer_id;

    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create a single trigger that handles INSERT, UPDATE, and DELETE operations
CREATE TRIGGER update_holiday_number_trigger
AFTER INSERT OR UPDATE OR DELETE ON holidays
FOR EACH ROW
EXECUTE FUNCTION update_holiday_number();


SELECT * FROM employers;