# TaskCli (EN)
TaskCli is a command-line tool for managing tasks.

## Basic Usage
TaskCli has 5 main commands: `add`, `update`, `mark`, `list`, and `help`.

### `add`: Add a new task
- **Usage:**  
  `java TaskCli add "[task description]"`

  Example:  
  `java TaskCli add "Buy milk"`

### `update`: Update the description of a task
- **Usage:**  
  `java TaskCli update [ID] "[new description]"`

  Example:  
  `java TaskCli update 1 "Buy milk and bread"`

### `mark`: Change the status of a task
- **Usage:**  
  `java TaskCli mark [ID] [new status]`

  **Valid statuses:** `done`, `in-progress` (`unstarted` is the default status for new tasks, but it cannot be set manually.).

  Example:  
  `java TaskCli mark 1 done`

### `list`: List filtered/sorted tasks
- **Usage:**  
  `java TaskCli list [status|all] [-sortcd|-sortud]`

  **Statuses:** `unstarted`, `in-progress`, `done`, `all` (mandatory).

  **Sorting options:**
    - `-sortcd`: Sorts by creation date.
    - `-sortud`: Sorts by last update date.  
      *By default, tasks are sorted by ID.*

  Examples:
    - List all tasks sorted by creation date:  
      `java TaskCli list all -sortcd`

    - List tasks in progress:  
      `java TaskCli list in-progress`

### `help`: Displays help
- **Usage:**  
  `java TaskCli help`


## Additional Notes
- **Build:** This program was built using JDK version 24.
- **Execution:** Designed for command-line interface (CLI).



# TaskCli (ES)
TaskCli es una herramienta de línea de comandos para gestionar tareas.

## Uso Básico
TaskCli tiene 5 comandos principales: `add`, `update`, `mark`, `list` y `help`.

### `add`: Agrega una nueva tarea
- **Uso:**  
  `java TaskCli add "[descripción de la tarea]"`

  Ejemplo:  
  `java TaskCli add "Comprar leche"`

### `update`: Actualiza la descripción de una tarea
- **Uso:**  
  `java TaskCli update [ID] "[nueva descripción]"`

  Ejemplo:  
  `java TaskCli update 1 "Comprar leche y pan"`

### `mark`: Cambia el estado de una tarea
- **Uso:**  
  `java TaskCli mark [ID] [nuevo estado]`

  **Estados válidos:** `done`, `in-progress` (`unstarted` es el estado por defecto de nuevas tareas, pero no se puede asignar manualmente).

  Ejemplo:  
  `java TaskCli mark 1 done`

### `list`: Lista tareas filtradas/ordenadas
- **Uso:**  
  `java TaskCli list [estado|all] [-sortcd|-sortud]`

  **Estados:** `unstarted`, `in-progress`, `done`, `all` (obligatorio).

  **Opciones de orden:**
    - `-sortcd`: Ordena por fecha de creación.
    - `-sortud`: Ordena por fecha de actualización.  
      *Por defecto, se ordena por ID*.

  Ejemplos:
    - Listar todas ordenadas por creación:  
      `java TaskCli list all -sortcd`

    - Listar tareas en progreso:  
      `java TaskCli list in-progress`

### `help`: Muestra la ayuda
- **Uso:**  
  `java TaskCli help`


## Notas adicionales
- **Construcción:** Este programa fue construido con el JDK versión 24.
- **Ejecución:** Diseñado para línea de comandos (CLI).
