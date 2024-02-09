package com.acelerazg.app;

public class Task {
    String name;
    String desc;
    String endDate; // Date as string
    int priority; // Integer from 1 to 5;
    String tag; // The task's category
    int status; // 0 = to_do; 1 = doing; 2 = done;

    public Task(String name, String desc, String endDate, int priority, String tag, int status) {
        this.name = name;
        this.desc = desc;
        this.endDate = endDate;
        this.priority = priority;
        this.tag = tag;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String toCsv(String sep) {
        return  this.name + sep +
                this.desc + sep +
                this.endDate + sep +
                Integer.toString(this.priority) + sep +
                this.tag + sep +
                Integer.toString(this.status) + "\n";
    }
    @Override
    public String toString() {
        String check = "";
        String strStatus = "";

        if ((this.status == 0) || (this.status == 1)) check = "☐";
        else check = "☑";

        switch (this.status) {
            case 0:
                strStatus = "TO-DO";
                break;
            case 1:
                strStatus = "DOING";
                break;
            case 2:
                strStatus = "DONE";
                break;
        }
        return  check + " " + name + "\n    " +strStatus + "     [ " + tag + " ]" +
                "\n    " + desc +
                "\n    End-date: " + endDate +
                "   Priority Level: " + priority;
    }


}