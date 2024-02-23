# Janet's User Guide
Janet is a desktop app for **managing tasks, optimized for use via a Command Line Interface** (CLI).

- [Quick start](#quick-start)
- [Features](#features)
    - [Adding a todo task: `todo`](#adding-a-todo-task-todo)
    - [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
    - [Listing all tasks: `list`](#listing-all-tasks-list)
    - [Marking a task as done: `mark`](#marking-a-task-as-completed-mark)
    - [Marking a task as 'uncompleted': `unmark`](#marking-a-task-as-uncompleted-unmark)
    - [Locating tasks by name: `find`](#locating-tasks-by-name-find)
    - [Deleting a task: `delete`](#deleting-a-task-delete)
    - [Exiting the program: `bye`](#exiting-the-program-bye)
    - [Saving the data](#saving-the-data)
- FAQ
- Command summary

### Quick start

Quick start

### Features

Features

### Adding a todo task: `todo`
Adds a todo task to the task list. Todo tasks are tasks with no additional parameters.

Format: `todo TASK`

Examples:
- todo walk the dog
- todo read a book

### Adding a deadline task: `deadline`
Adds a deadline task to the task list with a deadline to be completed by.

Format: `deadline TASK /by DEADLINE`

Examples:
- deadline walk the cat /by 4 November
- deadline finish assignment /by 2359
- deadline buy groceries for dinner /by 5pm

### Adding a event task: `event`
Adds an event task to the task list with a start and an end time.

Format: `event TASK /from START /to END`

Examples:
- event project meeting /from 2pm /to 4pm
- event birthday party /from 5pm /to 11pm
- event meeting conference at MBS /from 3pm /to 6pm

### Listing all tasks: `list`
Shows a list of all tasks in the task list.

Format: `list`

Usage:

If the task list is empty, this message will be printed.
```
No task in your list, yay!
```

Otherwise, it will print all the tasks in it.
```
The tasks in your list are: 
1.[T][ ] walk the dog
2.[D][ ] WALK the cat (by: 4 November)
3.[E][ ] Walk the turtle (from: 6pm to: 8pm)
4.[E][ ] project meeting (from: 9pm to: 11pm)
```

### Marking a task as 'completed': `mark`
Marks a task as done.

Format: `mark TASKINDEX`

### Marking a task as 'uncompleted': `unmark`
Marks a task as incomplete. 

Format: `unmark TASKINDEX`

### Locating tasks by name: `find`
Finds tasks whose names contain any of the keyword.

Format: `find KEYWORD`
- The search is case-insensitive. e.g. `abcd` will match with `ABCD`
- Only the task name is searched.
- Task names that contain partial keyword will be matched e.g. `AB` will match with `ABCD`

Usage:

- `find walk` returns tasks containing the keywords of `WALK`, `Walk` and `walk`.
- `find wa` will still achieve the same results.
```
Here are the matching task(s) in your list: 
---------------
1. [T][ ] walk the dog
2. [D][ ] WALK the cat (by: 4 November)
3. [E][ ] Walk the turtle (from: 6pm to: 8pm)
```

### Deleting a task: `delete`
Deletes the specified task from the task list.

Format: `delete TASKINDEX`
- Deletes the task at the specified `TASKINDEX`
- The index refers to the index number shown in the displayed task list.
- The index **must be within the list**
- The index **must be a positive integer** 1, 2, 3, ...

Description

### Exiting the program: `bye`
Exits the program.

Format: `bye`

### Saving the data
The task list data are saved in the local hard disk automatically after any command that changes the data.
There is no need to save manually.

