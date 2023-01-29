package com.example.aclcontrol;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.nio.file.attribute.AclEntry;
import java.nio.file.attribute.AclEntryPermission;
import java.nio.file.attribute.UserPrincipal;
import java.util.Set;

public class ACLObject {
    AclEntry aclEntry;
    SimpleObjectProperty<UserPrincipal> principal;
    SimpleBooleanProperty delete = new SimpleBooleanProperty(false);
    SimpleBooleanProperty execute = new SimpleBooleanProperty(false);
    SimpleBooleanProperty read_acl = new SimpleBooleanProperty(false);
    SimpleBooleanProperty read_data = new SimpleBooleanProperty(false);
    SimpleBooleanProperty write_acl = new SimpleBooleanProperty(false);
    SimpleBooleanProperty write_data = new SimpleBooleanProperty(false);
    SimpleBooleanProperty append_data = new SimpleBooleanProperty(false);
    SimpleBooleanProperty sync = new SimpleBooleanProperty(false);
    SimpleBooleanProperty write_owner = new SimpleBooleanProperty(false);
    SimpleBooleanProperty delete_child = new SimpleBooleanProperty(false);
    SimpleBooleanProperty read_attrs = new SimpleBooleanProperty(false);
    SimpleBooleanProperty read_named_attrs = new SimpleBooleanProperty(false);
    SimpleBooleanProperty write_attrs = new SimpleBooleanProperty(false);
    SimpleBooleanProperty write_named_attrs = new SimpleBooleanProperty(false);
    int index;
    public ACLObject(AclEntry aclEntry,int index)
    {
        this.index = index;
        this.aclEntry =aclEntry;
        assert false;
        this.principal = new SimpleObjectProperty<>(aclEntry.principal());
        for (AclEntryPermission aclEntryPermission:aclEntry.permissions()) {
            switch (aclEntryPermission){
                case DELETE -> delete = new SimpleBooleanProperty(true);
                case EXECUTE -> execute = new SimpleBooleanProperty(true);
                case READ_ACL -> read_acl = new SimpleBooleanProperty(true);
                case READ_DATA -> read_data = new SimpleBooleanProperty(true);
                case WRITE_ACL -> write_acl = new SimpleBooleanProperty(true);
                case WRITE_DATA -> write_data = new SimpleBooleanProperty(true);
                case APPEND_DATA -> append_data = new SimpleBooleanProperty(true);
                case SYNCHRONIZE -> sync = new SimpleBooleanProperty(true);
                case WRITE_OWNER -> write_owner = new SimpleBooleanProperty(true);
                case DELETE_CHILD -> delete_child = new SimpleBooleanProperty(true);
                case READ_ATTRIBUTES -> read_attrs = new SimpleBooleanProperty(true);
                case READ_NAMED_ATTRS -> read_named_attrs = new SimpleBooleanProperty(true);
                case WRITE_ATTRIBUTES -> write_attrs = new SimpleBooleanProperty(true);
                case WRITE_NAMED_ATTRS -> write_named_attrs = new SimpleBooleanProperty(true);
            }
        }
    }

    public void setDelete(boolean delete) {
        this.delete.set(delete);
    }

    public void setExecute(boolean execute) {
        this.execute.set(execute);
    }

    public void setPrincipal(UserPrincipal principal) {
        this.principal.set(principal);
    }

    public void setRead_acl(boolean read_acl) {
        this.read_acl.set(read_acl);
    }

    public void setAppend_data(boolean append_data) {
        this.append_data.set(append_data);
    }

    public void setDelete_child(boolean delete_child) {
        this.delete_child.set(delete_child);
    }

    public void setRead_attrs(boolean read_attrs) {
        this.read_attrs.set(read_attrs);
    }

    public void setSync(boolean sync) {
        this.sync.set(sync);
    }

    public void setRead_data(boolean read_data) {
        this.read_data.set(read_data);
    }

    public void setRead_named_attrs(boolean read_named_attrs) {
        this.read_named_attrs.set(read_named_attrs);
    }

    public void setWrite_acl(boolean write_acl) {
        this.write_acl.set(write_acl);
    }

    public void setWrite_attrs(boolean write_attrs) {
        this.write_attrs.set(write_attrs);
    }

    public void setWrite_data(boolean write_data) {
        this.write_data.set(write_data);
    }

    public void setWrite_named_attrs(boolean write_named_attrs) {
        this.write_named_attrs.set(write_named_attrs);
    }

    public void setWrite_owner(boolean write_owner) {
        this.write_owner.set(write_owner);
    }

    public boolean getAppend_data() {
        return append_data.get();
    }

    public boolean getDelete() {
        return delete.get();
    }

    public boolean getDelete_child() {
        return delete_child.get();
    }

    public boolean getExecute() {
        return execute.get();
    }

    public boolean getRead_acl() {
        return read_acl.get();
    }

    public boolean getRead_attrs() {
        return read_attrs.get();
    }

    public boolean getRead_data() {
        return read_data.get();
    }

    public boolean getSync() {
        return sync.get();
    }

    public boolean getRead_named_attrs() {
        return read_named_attrs.get();
    }

    public boolean getWrite_acl() {
        return write_acl.get();
    }

    public boolean getWrite_attrs() {
        return write_attrs.get();
    }

    public UserPrincipal getPrincipal() {
        return principal.get();
    }

    public boolean getWrite_named_attrs() {
        return write_named_attrs.get();
    }

    public boolean getWrite_data() {
        return write_data.get();
    }

    public boolean getWrite_owner() {
        return write_owner.get();
    }
}
