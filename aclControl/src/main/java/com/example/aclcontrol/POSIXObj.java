package com.example.aclcontrol;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.nio.file.attribute.PosixFilePermission;

public class POSIXObj {
    SimpleStringProperty name;
    SimpleBooleanProperty execute;
    SimpleBooleanProperty write;
    SimpleBooleanProperty read;
    public POSIXObj(String name)
    {
        this.name =new SimpleStringProperty(name);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setRead(boolean read) {
        this.read.set(read);
    }

    public void setExecute(boolean execute) {
        this.execute.set(execute);
    }

    public void setWrite(boolean write) {
        this.write.set(write);
    }

    public boolean isExecute() {
        return execute.get();
    }

    public boolean isRead() {
        return read.get();
    }

    public boolean isWrite() {
        return write.get();
    }
}
