
# TODO App (Java + SQLite)

Šis ir vienkāršs TODO saraksta piemērs, kas uzrakstīts Java valodā un izmanto SQLite datubāzi.

## ✅ Funkcionalitāte
- Pievienot uzdevumu
- Apskatīt visus uzdevumus
- Dzēst uzdevumu
- Dati tiek saglabāti datubāzē (`tasks.db`)

## 📦 Fails
- `TodoApp.java`

## 🔧 Nepieciešams
1. Java (JDK 8+)
2. SQLite JDBC draiveris:  
   Lejupielādēt šeit: [https://github.com/xerial/sqlite-jdbc/releases](https://github.com/xerial/sqlite-jdbc/releases)  
   Piemēram: `sqlite-jdbc-3.36.0.3.jar`

## 🚀 Kompilācija un palaišana

### 1. Kompilēšana
```bash
javac TodoApp.java
```

### 2. Palaišana
```bash
# Windows
java -cp ".;sqlite-jdbc-3.36.0.3.jar" TodoApp

# Mac/Linux
java -cp ".:sqlite-jdbc-3.36.0.3.jar" TodoApp
```

## 💡 Idejas paplašināšanai
- Pievienot izpildes statusu (izpildīts/neizpildīts)
- Pievienot datumu vai termiņu
- Izmantot GUI ar JavaFX vai Swing

---

Veiksmi programmēšanā!
