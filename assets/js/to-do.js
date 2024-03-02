class TaskHandler {
  constructor() {
    this.tasks = [];
  }

  addTask(name, desc, endDate, status, tag, priority) {
    let repeated_task = (tasks.findIndex(function(task){return (task['name'] == name);}) != -1)
    if (repeated_task) {
        return false;
    }
    var newTask = {
      'name': name,
      'desc': desc,
      'end-date': endDate,
      'uid': new Date().toString(),
      'status': status,
      'tag': tag,
      'priority': priority
    };
    this.tasks.push(newTask);
    this.saveTasks();
    return newItem;
  }

  removeTaskByName(name) {
    let index = this.tasks.findIndex(function(task) {return (task['name'] == name);});
    if (index !== -1) {
      this.tasks.splice(index, 1);
      this.saveTasks()
      return true;
    }
    return false;
  }


  saveTasks() {
    localStorage.setItem("LogTaskList", JSON.stringify(this.tasks));
  }

  loadItems() {
    let tasksAsString = localStorage.getItem("itemList");
    if (itemsString) {
      this.tasks = JSON.parse(itemsString);
      return true;
    }
    return false;
  }

  TasksAsListItems() {

    var li_list = [];
    for (task in this.tasks) {
        
        let check_status = (this.tasks[task].status == 2) ? " checked " : "";
        let status_string = "to-do";
        if (this.tasks[task].status == 1) {
            status_string = "doing";
        } else if (this.task[task].status == 2) {
            status_string = "done";
        }

        let uid = Date.parse(this.tasks[task].uid);
        let task_id = uid.toString();

        let task_full_id = "'full-task-" + task_id + "'";
        let task_edit_id = "'edit-task-" + task_id + "'";

        var full_li_elem = '<li' +
                        'class=\'list-group-item d-flex flex-column justify-content-between align-items-center\'>' +
                        '<div class=\'task\'>' +
                            '<input type=\'checkbox\' class=\'status\''+ check_status +'/>' +
                            '<a data-toggle=\'collapse\'' +
                                'href=' + task_full_id +
                                ' aria-expanded=\'false\'' +
                                'aria-controls=' + task_full_id + '>' +
                                '<span class=\'title\'>' + this.tasks[task].name + '</span>' +
                            '</a>' +
                            '<button class=\'category btn btn-primary\'>'+ this.tasks[tag] +'</button>' +
                            '<div class=\'task-button\'>' +
                                '<a data-toggle=\'collapse\'' +
                                    'href=' + task_edit_id +
                                    'aria-expanded=\'false\'' +
                                    'aria-controls=' + task_edit_id + '>' +
                                    '<i class=\'fa fa-pencil edit\'' +
                                        'aria-hidden=\'true\'></i>' +
                                '</a>' +
                                '<i class=\'fa-solid fa-trash-can delete\'></i>' +
                            '</div>' +
                        '</div>' +
                        '<div class=\'collapse\' id=' +  task_full_id + '>' +
                            '<div class=\'info-display\'>' +
                                '<p class=\'mb-3\'><strong>Name:</strong>' + this.tasks[task].name +
                                    '</p>' +
                                '<p class=\'mb-3\'><strong>End-Date:</strong>'+ this.tasks[task].endDate +
                                    '</p>' +
                                '<p class=\'mb-3\'><strong>Tag:</strong>'+ this.tasks[task].tag +
                                    '</p>' +
                                '<p class=\'mb-3\'><strong>Status:</strong>'+ status_string +
                                    '</p>' +
                                '<p class=\'mb-3\'><strong>Priority:</strong>'+ this.tasks[task].priority +
                                    '</p>' +
                                '<p class=\'mb-3\'><strong>Description:</strong>' + this.tasks[task].desc +
                                    '</p>' +
                            '</div>' +
                        '</div>' +
                        '<div class=\'collapse\' id=' + task_edit_id + '>' +
                            '<hr>' +
                            '<h5>Editing task</p>' +
                            '<hr>' +
                            '<form>' +
                                '<div class=\'mb-3\'>' +
                                    '<p class=\'field-name\'>Task\'s name</p>' +
                                    '<input type=\'text\' class=\'form-control\'' +
                                        'name=\'name\' placeholder=\'Name\'' +
                                        'value=\''+ this.tasks[task].name +'\'required>' +
                                '</div>' +
                                '<div class=\'mb-3\'><p class=\'field-name\'>Task\'s ' +
                                        'end-date</p>' +
                                    '<input type=\'text\' class=\'form-control\'' +
                                        'name=\'date\' placeholder=\'Date (text format)\'' +
                                        'value=\''+ this.tasks[task].endDate +'\'required>' +
                                '</div>' +
                                '<div class=\'mb-3\'><p class=\'field-name\'>Task\'s ' +
                                        'tag (optional)</p>' +
                                    '<input type=\'text\' class=\'form-control\'' +
                                        'name=\'tag\' placeholder=\'Tag\'>' +
                                        'value=\''+ this.tasks[task].name +'\'required>' +
                                '</div>' +
                                '<div class=\'mb-3\'><p class=\'field-name\'>Task\'s ' +
                                        'priority (1-5)</p>' +
                                    '<input type=\'number\' class=\'form-control\'' +
                                        'name=\'priority\' min=\'1\' max=\'5\' value=\'' + this.tasks[task].priority + '\'' +
                                        'required>' +
                                '</div>' +
                                '<div class=\'mb-3\'><p class=\'field-name\'>Task\'s ' +
                                        'status (0: todo, 1: doing, 2: done)</p>' +
                                    '<input type=\'number\' class=\'form-control\'' +
                                        'name=\'priority\' min=\'0\' max=\'2\' value=\''+ this.tasks[task].status +'\'' +
                                        'required>' +
                                '</div>' +
                                '<div class=\'mb-3\'><p class=\'field-name\'>Task\'s ' +
                                        'description</p>' +
                                    '<textarea class=\'form-control\'' +
                                        'name=\'description\' placeholder=\'Description\'' +
                                        'required>'+ this.tasks[task].desc + '</textarea>' +
                                '</div>' +
                                '<button class=\'btn save-edit\'>Save changes</button>' +
                            '</form>' +
                        '</div>' +
                    '</li>';
        li_list.push(full_li_elem);
        }
    return li_list;
    }
}