# Student Course Registration App (JavaFX Educational GUI)

This JavaFX application implements an interactive **Educational Student Course Registration System** fulfilling all assignment requirements.

---

## 📋 Required Components Implemented

| UI Component | JavaFX Control | Educational Theme Usage |
| :--- | :--- | :--- |
| **1. Dropdown Bar** | `ComboBox<String>` | Select Academic Department / Stream (e.g., Computer Science, Mathematics, Physics, etc.) |
| **2. Checkbox** | `CheckBox` | Choose Supplementary Educational Add-ons (e.g., Digital Library, 1-on-1 Mentorship, Virtual Lab Kit, Certificate) |
| **3. Radio Buttons** | `RadioButton` + `ToggleGroup` | Select Preferred Study Mode (`In-Person`, `Hybrid`, `100% Online`) |
| **4. List** | `ListView<String>` | Dynamically displays available elective courses corresponding to the selected department |

---

## 🗂️ Project Structure

```text
OOPS-assignment/
├── pom.xml                                                      # Maven configuration with JavaFX dependencies & plugins
├── README.md                                                    # Documentation and instructions
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    └── educational/
                        └── StudentCourseRegistrationApp.java   # Complete JavaFX Application
```

---

## 🚀 How to Run the Application

### Prerequisites
- **JDK 17 or later** installed ([Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [Eclipse Temurin](https://adoptium.net/))
- **Apache Maven 3.8+** installed

### Option 1: Run with Maven (Recommended)
From the root directory of this project, execute:

```bash
mvn clean javafx:run
```

### Option 2: Compile and Package
```bash
mvn clean package
```

### Option 3: Run directly with JavaFX SDK (CLI)
If running directly using downloaded JavaFX SDK:
```bash
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls src/main/java/com/example/educational/StudentCourseRegistrationApp.java -d out/
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -cp out com.example.educational.StudentCourseRegistrationApp
```

---

## ✨ Features & Functionality

1. **Dynamic Dependent Dropdown & List**:
   - Selecting a different Department in the dropdown dynamically refreshes the available Course List in real time.
2. **Mutual Exclusion with ToggleGroup**:
   - Study mode radio buttons belong to a single `ToggleGroup`, guaranteeing only one mode can be selected.
3. **Multi-Selection Checkboxes**:
   - Any combination of supplementary learning tools and resources can be chosen.
4. **Form Actions & Instant Summary**:
   - **Confirm Enrollment**: Validates selections and displays a formatted confirmation receipt inside a summary area.
   - **Reset Form**: Resets all form fields back to their default states.
