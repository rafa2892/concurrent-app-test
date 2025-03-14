# Ariadna Event Manager

## Requirements
1. **Postman**: To create and test API requests.
2. **Java**: Must be installed on the system.
3. **Environment variable `JAVA_HOME`**: Must be correctly configured.

## Instructions
1. Unzip the file containing the `jar` file.
2. Open the console in the directory where the `.jar` file is located.
3. Run the following command:
   ```sh
   java -jar AriadnaEventManager-1.0.0.jar
   ```
4. If everything is correct, informational logs will indicate that the application has started. By default, it will run on port **8080**.
5. Open **Postman** to test the API requests.

## Tests
### 1. Search events by source
- **HTTP Method:** `GET`
- **URL:**
  ```
  http://localhost:8080/api/eventos/buscarEventosPorFuente?nombreFuente=Fuente60
  ```
- **Description:** Returns all events that contain the specified source.
- **Example response:**
  ```json
  {
    "message": "14 events found for source Fuente60",
    "data": [
      { "id": 1, "source": "Fuente60", "timestamp": "2024-08-05T10:15:30", "value": 123.45 },
      ...
    ]
  }
  ```

### 2. Search events by `timeStamp`
- **HTTP Method:** `GET`
- **URL:**
  ```
  http://localhost:8080/api/eventos/buscarEntreFechas?fechaInicio=05-08-2024 10:00:23&fechaFin=11-08-2024 15:00:50
  ```
- **Required date format:** `dd-MM-yyyy HH:mm:ss`
- **Description:** Returns all events recorded within the specified date range.
- **Example response:**
  ```json
  {
    "message": "639 events found between 05-08-2024 10:00:23 and 11-08-2024 15:00:50",
    "data": [
      { "id": 25, "source": "Fuente10", "timestamp": "2024-08-06T14:20:50", "value": 210.75 },
      ...
    ]
  }
  ```

### 3. Search events by value range
- **HTTP Method:** `GET`
- **URL:**
  ```
  http://localhost:8080/api/eventos/buscarPorRangoDeValores?valorMin=111.45&valorMax=376.76
  ```
- **Description:** Returns all events whose value is within the specified range.
- **Example response:**
  ```json
  {
    "message": "339 events found with values between 111.45 and 376.76",
    "data": [
      { "id": 100, "source": "Fuente30", "timestamp": "2024-08-07T09:12:45", "value": 150.33 },
      ...
    ]
  }
  ```

## Data
The application comes preloaded with over **1000 events**, each linked to one of the **100 available sources**.

The events are structured in test files:
- **6 files** `eventosN.txt`, containing the events.
- **3 files** `fuentesN.txt`, containing the source information.

These files allow for efficient and quick testing.

