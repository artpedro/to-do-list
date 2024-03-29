class TaskHandler {
    constructor() {
        let tasksAsString = localStorage.getItem("LogTaskList");
        if (tasksAsString) {
            this.tasks = JSON.parse(tasksAsString);
        } else {
            this.tasks = [];
        }
    }

    addTask(name, desc, endDate, status, tag, priority) {
        let repeated_task = (this.tasks.findIndex((task) => { return (task['name'] == name); }) != -1)
        if (repeated_task) {
            return false;
        }
        var newTask = {
            'name': name,
            'desc': desc,
            'endDate': endDate,
            'uid': new Date().getTime(),
            'status': status,
            'tag': tag,
            'priority': priority
        };

        this.tasks.push(newTask);
        this.saveTasks();
        return newTask;
    }
    getTaskById(id) {
        let index = this.tasks.findIndex(function (task) { return (task['uid'] == id); });
        if (index !== -1) {
            return this.tasks[index];
        }
        return false;    
    }

    removeTaskById(id) {
        let index = this.tasks.findIndex(function (task) { return (task['uid'] == id); });
        if (index !== -1) {
            this.tasks.splice(index, 1);
            this.saveTasks();
            return true;
        }
        return false;
    }

    showTasks() {
        for (let i = 0; i < this.tasks.length; i++) {
            console.log(this.tasks[i]);
        }
    }

    saveTasks() {
        localStorage.setItem("LogTaskList", JSON.stringify(this.tasks));
    }
    
    tasksAsListItems() {
    
        var li_list = [];
        for (let i = 0; i < this.tasks.length; i++) {
            
            let check_status = (this.tasks[i].status == 2) ? " checked " : "";
            let status_string = "To-do";
            if (this.tasks[i].status == 1) {
                status_string = "Doing";
            } else if (this.tasks[i].status == 2) {
                status_string = "Done";
            }
    
            let task_id = this.tasks[i].uid.toString();
    
            let task_full_id = "full-task-" + task_id;
            let task_edit_id = "edit-task-" + task_id;
            let task_delete_button = "delete-task-" + task_id;
            let task_edit_form = "edit-form-" + task_id;
            let task_status_id = "status-" + task_id;

            var full_li_elem = '<li ' +
                            'class=\'list-group-item d-flex flex-column justify-content-between align-items-center\'>' +
                            '<div class=\'task\'>' +
                                '<input type=\'checkbox\' id=\''+ task_status_id + '\'class=\'status\''+ check_status +'/>' +
                                '<a data-toggle=\'collapse\'' +
                                    'href=\'#' + task_full_id + '\'' +
                                    ' aria-expanded=\'false\'' +
                                    'aria-controls=\'' + task_full_id  + '\'>' +
                                    '<span class=\'title\'>' + this.tasks[i].name + '</span>' +
                                '</a>' +
                                '<button class=\'category btn btn-primary\'>'+ this.tasks[i].tag +'</button>' +
                                '<div class=\'task-button\'>' +
                                    '<a data-toggle=\'collapse\' ' +
                                        'href=\'#' + task_edit_id + '\'' +
                                        'aria-expanded=\'false\'' +
                                        'aria-controls=' + task_edit_id + '\'>' +
                                        '<i class=\'fa fa-pencil edit\'' +
                                            'aria-hidden=\'true\'></i>' +
                                    '</a>' +
                                    '<a id=\'' + task_delete_button + '\' class=\'delete\'>' +
                                    '<i class=\'fa-solid fa-trash-can\'></i>' +
                                    '</a>' +
                                '</div>' +
                            '</div>' +
                            '<div class=\'collapse\' id=\'' +  task_full_id + '\'>' +
                            '<hr>' +
                                '<div class=\'info-display\'>' +
                                    '<p class=\'mb-3\'><strong>End-Date: </strong>'+ this.tasks[i].endDate +
                                        '</p>' +
                                    '<p class=\'mb-3\'><strong>Tag: </strong>'+ this.tasks[i].tag +
                                        '</p>' +
                                    '<p class=\'mb-3\'><strong>Status: </strong>'+ status_string +
                                        '</p>' +
                                    '<p class=\'mb-3\'><strong>Priority: </strong>'+ this.tasks[i].priority +
                                        '</p>' +
                                    '<p class=\'mb-3\'><strong>Description: </strong>' + this.tasks[i].desc +
                                        '</p>' +
                                '</div>' +
                            '</div>' +
                            '<div class=\'collapse\' id=\'' + task_edit_id + '\'>' +
                                '<hr>' +
                                '<h5>Editing task</h5>' +
                                '<hr>' +
                                '<form class=\'task-edit\' id=\''+ task_edit_form +'\'>' +
                                    '<div class=\'mb-3\'>' +
                                        '<p class=\'field-name\'>Task\'s name </p>' +
                                        '<input type=\'text\' class=\'form-control\'' +
                                            'name=\'name\' placeholder=\'Name\'' +
                                            'value=\''+ this.tasks[i].name +'\' required>' +
                                    '</div>' +
                                    '<div class=\'mb-3\'><p class=\'field-name\'>Task\'s ' +
                                            'end-date </p>' +
                                        '<input type=\'text\' class=\'form-control\'' +
                                            'name=\'date\' placeholder=\'Date (text format)\'' +
                                            'value=\''+ this.tasks[i].endDate +'\' required>' +
                                    '</div>' +
                                    '<div class=\'mb-3\'><p class=\'field-name\'>Task\'s ' +
                                            'tag </p>' +
                                        '<input type=\'text\' class=\'form-control\'' +
                                            'name=\'tag\' placeholder=\'Tag\'' +
                                            'value=\'' + this.tasks[i].tag +'\' required>' +
                                    '</div>' +
                                    '<div class=\'mb-3\'><p class=\'field-name\'>Task\'s ' +
                                            'priority (1-5) </p>' +
                                        '<input type=\'number\' class=\'form-control\'' +
                                            'name=\'priority\' min=\'1\' max=\'5\' value=\'' + this.tasks[i].priority + '\' ' +
                                            'required>' +
                                    '</div>' +
                                    '<div class=\'mb-3\'><p class=\'field-name\'>Task\'s ' +
                                            'status (0: todo, 1: doing, 2: done) </p>' +
                                        '<input type=\'number\' class=\'form-control\'' +
                                            'name=\'status\' min=\'0\' max=\'2\' value=\''+ this.tasks[i].status +'\' ' +
                                            'required>' +
                                    '</div>' +
                                    '<div class=\'mb-3\'><p class=\'field-name\'>Task\'s ' +
                                            'description </p>' +
                                        '<textarea class=\'form-control\'' +
                                            'name=\'description\' placeholder=\'Description\' ' +
                                            'required>'+ this.tasks[i].desc + '</textarea>' +
                                    '</div>' +
                                    '<button type=\'submit\' class=\'btn save-edit\'>Save changes</button>' +
                                '</form>' +
                            '</div>' +
                        '</li>';
            li_list.push(full_li_elem);
            }
        return li_list;
    }
}

function refreshTasksUl(taskHandler) {
    let all_li = taskHandler.tasksAsListItems();
    let tasks_ul = document.getElementById("all-tasks");

    tasks_ul.innerHTML = "";

    for (let i = 0; i < all_li.length; i++) {
        tasks_ul.innerHTML += all_li[i];
    }

    // DELETE BUTTON

    var delete_buttons = document.getElementsByClassName("delete");

    for (let del_btn = 0; del_btn < delete_buttons.length; del_btn++) {

        delete_buttons[del_btn].onclick = function () {
            array_uid = this.id.split('-');
            uid = array_uid[array_uid.length - 1];

            taskHandler.removeTaskById(uid);

            refreshTasksUl(taskHandler);
        };
    }

    // EDIT BUTTON

    var edit_forms = document.getElementsByClassName('task-edit');

    for (let edit_f = 0; edit_f < edit_forms.length; edit_f++) {

        edit_forms[edit_f].addEventListener('submit', function (event) {
            event.preventDefault();

            let formData = new FormData(this);

            edit_id_array = edit_forms[edit_f].id.split('-');
            edit_id = edit_id_array[edit_id_array.length - 1];

            taskHandler.removeTaskById(edit_id);

            if (taskHandler.addTask(formData.get('name'), formData.get('description'), formData.get('date'), formData.get('status'), formData.get('tag'), formData.get('priority'))) {
                refreshTasksUl(taskHandler);
            } else {
                console.log("Unexpected error");
            }
        });

    }

    // CHANGE STATUS BUTTON 


    var status_check_box = document.getElementsByClassName('status');

    for (let chk_box = 0; chk_box < status_check_box.length; chk_box++) {

        status_check_box[chk_box].addEventListener('change', function () {
            status_id_array = this.id.split('-');
            status_id = status_id_array[status_id_array.length - 1];

            current_task = taskHandler.getTaskById(status_id);

            if (this.checked) {
                taskHandler.removeTaskById(status_id);

                taskHandler.addTask(current_task.name, current_task.desc, current_task.endDate, 2, current_task.tag, current_task.priority);
                
                refreshTasksUl(taskHandler);
            } else {
                taskHandler.removeTaskById(status_id);
                
                taskHandler.addTask(current_task.name, current_task.desc, current_task.endDate, 0, current_task.tag, current_task.priority);
                
                refreshTasksUl(taskHandler);
            }
        });
    }
}

addEventListener('load', function () {

    var taskHandler = new TaskHandler();
    refreshTasksUl(taskHandler);

    // ADD FORMS

    document.getElementById('new-task').addEventListener('submit', function (event) {
        event.preventDefault();

        let formData = new FormData(this);

        if (taskHandler.addTask(formData.get('name'), formData.get('description'), formData.get('date'), formData.get('status'), formData.get('tag'), formData.get('priority'))) {
            refreshTasksUl(taskHandler);
        } else {
            console.log("This task already exists!");
        }
    });
});