# 💸 SplitSmart — Smart Expense Splitter

A full-stack expense splitting application with debt optimization algorithm.

## Features
- Multiple groups (trips, flat, office)
- Add / Edit / Delete expenses
- Custom split between members
- Debt optimization algorithm (minimizes transactions)
- Who Owes Whom graph
- Mark settlements as paid
- Expense notes
- Pie chart & monthly spending charts
- Members persist in database

## Tech Stack
- **Backend:** Spring Boot, Java 25, Spring Data JPA, MySQL
- **Frontend:** React.js, Axios
- **Database:** MySQL 9.6

## How to Run

### Backend
```bash
# Create MySQL database
mysql -u root -p
CREATE DATABASE expense_db;

# Run Spring Boot
./mvnw spring-boot:run
```

### Frontend
```bash
cd expense-frontend
npm install
npm start
```

Open http://localhost:3000
