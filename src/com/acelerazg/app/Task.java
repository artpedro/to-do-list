package com.acelerazg.app;

import com.acelerazg.printer.TextColors;
import java.util.Objects;

public class Task implements Cloneable{
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(getName(), task.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
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

    public String getStrStatus() {
        switch (this.status) {
            case 0: return "Todo";
            case 1: return "Doing";
            case 2: return "Done";
        }
        return "";
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String toCsv(String sep) {
        return  this.name + sep +
                this.desc + sep +
                this.endDate + sep +
                this.priority + sep +
                this.tag + sep +
                this.status + "\n";
    }
    @Override
    public String toString() {
        String check = "☑";
        String strStatus = "";
        String strPriority = "";
        if ((this.status == 0) || (this.status == 1)) {
            check = "☐";
        }

        switch (this.priority) {
            case 1: strPriority = "    " + TextColors.ANSI_GREEN_BACKGROUND + TextColors.ANSI_BLACK + "Priority Level " + "1" + TextColors.ANSI_RESET;
                break;
            case 2: strPriority = "    " + TextColors.ANSI_CYAN_BACKGROUND + TextColors.ANSI_BLACK + "Priority Level " + "2" + TextColors.ANSI_RESET;
                break;
            case 3: strPriority = "    " + TextColors.ANSI_BLUE_BACKGROUND + TextColors.ANSI_BLACK + "Priority Level " + "3" + TextColors.ANSI_RESET;
                break;
            case 4: strPriority = "    " + TextColors.ANSI_YELLOW_BACKGROUND + TextColors.ANSI_BLACK + "Priority Level " + "4" + TextColors.ANSI_RESET;
                break;
            case 5: strPriority = "    " + TextColors.ANSI_RED_BACKGROUND + TextColors.ANSI_BLACK + "Priority Level " + "5" + TextColors.ANSI_RESET;
        }
        switch (this.status) {
            case 0:
                strStatus = TextColors.ANSI_RED_BACKGROUND + TextColors.ANSI_BLACK +"TO-DO" + TextColors.ANSI_RESET;
                break;
            case 1:
                strStatus = TextColors.ANSI_YELLOW_BACKGROUND + TextColors.ANSI_BLACK +"DOING" + TextColors.ANSI_RESET;
                break;
            case 2:
                strStatus = TextColors.ANSI_GREEN_BACKGROUND + TextColors.ANSI_BLACK + "DONE" + TextColors.ANSI_RESET;
                break;
        }
        return  check + " " + name + "\n    " +strStatus + "     [ " + tag + " ]" +
                "\n    " + desc +
                "\n    End-date: " + endDate +
                 strPriority;
    }
    @Override
    public Task clone() {
        return new Task(this.name,this.desc,this.endDate,this.priority,this.tag,this.status);
    }

}