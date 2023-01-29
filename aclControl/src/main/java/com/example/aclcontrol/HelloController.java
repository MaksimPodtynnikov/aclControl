package com.example.aclcontrol;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.attribute.*;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class HelloController implements Initializable {

    public TextField filePath;
    public TableView<POSIXObj> table;
    File selectedFile;
    Set<PosixFilePermission> perms = new HashSet<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<POSIXObj, String> objectColumn = new TableColumn<>("Объект");
        objectColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().add(objectColumn);
        TableColumn<ACLObject, Boolean> wrAtColumn = new TableColumn<>("WRITE");
        wrAtColumn.setCellValueFactory(new PropertyValueFactory<>("write"));
        wrAtColumn.setCellFactory(col -> new CheckBoxTableCell<>(index -> {
            BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).isWrite());
            active.addListener((obs, wasActive, isNowActive) -> {
                POSIXObj item = table.getItems().get(index);
                item.setWrite(isNowActive);
                if(isNowActive)
                    perms.add(
                else perms.remove();
            });
            return active;
        }));
        /*TableColumn<ACLObject, UserPrincipal> objectColumn = new TableColumn<>("Объект");
        objectColumn.setCellValueFactory(new PropertyValueFactory<>("principal"));
        table.getColumns().add(objectColumn);
        TableColumn<ACLObject, Boolean> wrAtColumn = new TableColumn<>("WRITE_ATTRIBUTES");
        wrAtColumn.setCellValueFactory(new PropertyValueFactory<>("write_attrs"));
        wrAtColumn.setCellFactory(col -> new CheckBoxTableCell<>(index -> {
            BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).getWrite_attrs());
            active.addListener((obs, wasActive, isNowActive) -> {
                ACLObject item = table.getItems().get(index);
                item.setWrite_attrs(isNowActive);
                if(isNowActive)
                    item.aclEntry.permissions().add(AclEntryPermission.WRITE_ATTRIBUTES);
                else item.aclEntry.permissions().remove(AclEntryPermission.WRITE_ATTRIBUTES);
            });
            return active;
        }));
        table.getColumns().add(wrAtColumn);
        TableColumn<ACLObject, Boolean> wrOwColumn = new TableColumn<>("WRITE_OWNER");
        wrOwColumn.setCellValueFactory(new PropertyValueFactory<>("write_owner"));
        wrOwColumn.setCellFactory(col -> new CheckBoxTableCell<>(index -> {
            BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).getWrite_owner());
            active.addListener((obs, wasActive, isNowActive) -> {
                ACLObject item = table.getItems().get(index);
                item.setWrite_owner(isNowActive);
                if(isNowActive)
                    item.aclEntry.permissions().add(AclEntryPermission.WRITE_OWNER);
                else item.aclEntry.permissions().remove(AclEntryPermission.WRITE_OWNER);
                try {
                    aclFileAttributes.getAcl().set(item.index, item.aclEntry);
                    Files.setAttribute(selectedFile.toPath(),"acl",aclFileAttributes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            return active;
        }));
        table.getColumns().add(wrOwColumn);
        TableColumn<ACLObject, Boolean> rdAtColumn = new TableColumn<>("READ_ATTRIBUTES");
        rdAtColumn.setCellValueFactory(new PropertyValueFactory<>("read_attrs"));
        rdAtColumn.setCellFactory(col -> new CheckBoxTableCell<>(index -> {
            BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).getRead_attrs());
            active.addListener((obs, wasActive, isNowActive) -> {
                ACLObject item = table.getItems().get(index);
                item.setRead_attrs(isNowActive);
                if(isNowActive)
                    item.aclEntry.permissions().add(AclEntryPermission.READ_ATTRIBUTES);
                else item.aclEntry.permissions().remove(AclEntryPermission.READ_ATTRIBUTES);
            });
            return active;
        }));
        table.getColumns().add(rdAtColumn);
        TableColumn<ACLObject, Boolean> deleteColumn = new TableColumn<>("DELETE");
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("delete"));
        deleteColumn.setCellFactory(col -> new CheckBoxTableCell<>(index -> {
            BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).getDelete());
            active.addListener((obs, wasActive, isNowActive) -> {
                ACLObject item = table.getItems().get(index);
                item.setDelete(isNowActive);
                if(isNowActive)
                    item.aclEntry.permissions().add(AclEntryPermission.DELETE);
                else item.aclEntry.permissions().remove(AclEntryPermission.DELETE);
            });
            return active;
        }));
        table.getColumns().add(deleteColumn);
        TableColumn<ACLObject, Boolean> rdDtColumn = new TableColumn<>("READ_DATA");
        rdDtColumn.setCellValueFactory(new PropertyValueFactory<>("read_data"));
        rdDtColumn.setCellFactory(col -> new CheckBoxTableCell<>(index -> {
            BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).getRead_data());
            active.addListener((obs, wasActive, isNowActive) -> {
                ACLObject item = table.getItems().get(index);
                item.setRead_data(isNowActive);
                if(isNowActive)
                    item.aclEntry.permissions().add(AclEntryPermission.READ_DATA);
                else item.aclEntry.permissions().remove(AclEntryPermission.READ_DATA);
            });
            return active;
        }));
        table.getColumns().add(rdDtColumn);
        TableColumn<ACLObject, Boolean> delChColumn = new TableColumn<>("DELETE_CHILD");
        delChColumn.setCellValueFactory(new PropertyValueFactory<>("delete_child"));
        delChColumn.setCellFactory(col -> new CheckBoxTableCell<>(index -> {
            BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).getDelete_child());
            active.addListener((obs, wasActive, isNowActive) -> {
                ACLObject item = table.getItems().get(index);
                item.setDelete_child(isNowActive);
                if(isNowActive)
                    item.aclEntry.permissions().add(AclEntryPermission.DELETE_CHILD);
                else item.aclEntry.permissions().remove(AclEntryPermission.DELETE_CHILD);

            });
            return active;
        }));
        table.getColumns().add(delChColumn);
        TableColumn<ACLObject, Boolean> wrDtColumn = new TableColumn<>("WRITE_DATA");
        wrDtColumn.setCellValueFactory(new PropertyValueFactory<>("write_data"));
        wrDtColumn.setCellFactory(col -> new CheckBoxTableCell<>(index -> {
            BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).getWrite_data());
            active.addListener((obs, wasActive, isNowActive) -> {
                ACLObject item = table.getItems().get(index);
                item.setWrite_data(isNowActive);
                if(isNowActive)
                    item.aclEntry.permissions().add(AclEntryPermission.WRITE_DATA);
                else item.aclEntry.permissions().remove(AclEntryPermission.WRITE_DATA);
            });
            return active;
        }));
        table.getColumns().add(wrDtColumn);
        TableColumn<ACLObject, Boolean> apDtColumn = new TableColumn<>("APPEND_DATA");
        apDtColumn.setCellValueFactory(new PropertyValueFactory<>("append_data"));
        apDtColumn.setCellFactory(col -> new CheckBoxTableCell<>(index -> {
            BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).getAppend_data());
            active.addListener((obs, wasActive, isNowActive) -> {
                ACLObject item = table.getItems().get(index);
                item.setAppend_data(isNowActive);
                if(isNowActive)
                    item.aclEntry.permissions().add(AclEntryPermission.APPEND_DATA);
                else item.aclEntry.permissions().remove(AclEntryPermission.APPEND_DATA);
            });
            return active;
        }));
        table.getColumns().add(apDtColumn);
        TableColumn<ACLObject, Boolean> wrNmAtColumn = new TableColumn<>("WRITE_NAMED_ATTRS");
        wrNmAtColumn.setCellValueFactory(new PropertyValueFactory<>("write_named_attrs"));
        wrNmAtColumn.setCellFactory(col -> new CheckBoxTableCell<>(index -> {
            BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).getWrite_named_attrs());
            active.addListener((obs, wasActive, isNowActive) -> {
                ACLObject item = table.getItems().get(index);
                item.setWrite_named_attrs(isNowActive);
                if(isNowActive)
                    item.aclEntry.permissions().add(AclEntryPermission.WRITE_NAMED_ATTRS);
                else item.aclEntry.permissions().remove(AclEntryPermission.WRITE_NAMED_ATTRS);
            });
            return active;
        }));
        table.getColumns().add(wrNmAtColumn);
        TableColumn<ACLObject, Boolean> execColumn = new TableColumn<>("EXECUTE");
        execColumn.setCellValueFactory(new PropertyValueFactory<>("execute"));
        execColumn.setCellFactory(col -> new CheckBoxTableCell<>(index -> {
            BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).getExecute());
            active.addListener((obs, wasActive, isNowActive) -> {
                ACLObject item = table.getItems().get(index);
                item.setExecute(isNowActive);
                if(isNowActive)
                    item.aclEntry.permissions().add(AclEntryPermission.EXECUTE);
                else item.aclEntry.permissions().remove(AclEntryPermission.EXECUTE);
            });
            return active;
        }));
        table.getColumns().add(execColumn);
        TableColumn<ACLObject, Boolean> rdACLColumn = new TableColumn<>("READ_ACL");
        rdACLColumn.setCellValueFactory(new PropertyValueFactory<>("read_acl"));
        rdACLColumn.setCellFactory(col -> new CheckBoxTableCell<>(index -> {
            BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).getRead_acl());
            active.addListener((obs, wasActive, isNowActive) -> {
                ACLObject item = table.getItems().get(index);
                item.setRead_acl(isNowActive);
                if(isNowActive)
                    item.aclEntry.permissions().add(AclEntryPermission.READ_ACL);
                else item.aclEntry.permissions().remove(AclEntryPermission.READ_ACL);
            });
            return active;
        }));
        table.getColumns().add(rdACLColumn);
        TableColumn<ACLObject, Boolean> rdNmAtrColumn = new TableColumn<>("READ_NAMED_ATTRS");
        rdNmAtrColumn.setCellValueFactory(new PropertyValueFactory<>("read_named_attrs"));
        rdNmAtrColumn.setCellFactory(col -> new CheckBoxTableCell<>(index -> {
            BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).getRead_named_attrs());
            active.addListener((obs, wasActive, isNowActive) -> {
                ACLObject item = table.getItems().get(index);
                item.setRead_named_attrs(isNowActive);
                if(isNowActive)
                    item.aclEntry.permissions().add(AclEntryPermission.READ_NAMED_ATTRS);
                else item.aclEntry.permissions().remove(AclEntryPermission.READ_NAMED_ATTRS);
            });
            return active;
        }));
        table.getColumns().add(rdNmAtrColumn);
        TableColumn<ACLObject, Boolean> wrACLColumn = new TableColumn<>("WRITE_ACL");
        wrACLColumn.setCellValueFactory(new PropertyValueFactory<>("write_acl"));
        wrACLColumn.setCellFactory(col -> new CheckBoxTableCell<>(index -> {
            BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).getWrite_acl());
            active.addListener((obs, wasActive, isNowActive) -> {
                ACLObject item = table.getItems().get(index);
                item.setWrite_acl(isNowActive);
                if(isNowActive)
                    item.aclEntry.permissions().add(AclEntryPermission.WRITE_ACL);
                else item.aclEntry.permissions().remove(AclEntryPermission.WRITE_ACL);
            });
            return active;
        }));
        table.getColumns().add(wrACLColumn);
        TableColumn<ACLObject, Boolean> syncColumn = new TableColumn<>("SYNCHRONIZE");
        syncColumn.setCellValueFactory(new PropertyValueFactory<>("sync"));
        syncColumn.setCellFactory(col -> new CheckBoxTableCell<>(index -> {
            BooleanProperty active = new SimpleBooleanProperty(table.getItems().get(index).getSync());
            active.addListener((obs, wasActive, isNowActive) -> {
                ACLObject item = table.getItems().get(index);
                item.setSync(isNowActive);
                if(isNowActive)
                    item.aclEntry.permissions().add(AclEntryPermission.SYNCHRONIZE);
                else item.aclEntry.permissions().remove(AclEntryPermission.SYNCHRONIZE);
            });
            return active;
        }));
        table.getColumns().add(syncColumn);*/
    }
    @FXML
    private void openFile() throws IOException {
        ObservableList<POSIXObj> objects = FXCollections.observableArrayList();
        POSIXObj owner = new POSIXObj("owner");
        POSIXObj group = new POSIXObj("group");
        POSIXObj other = new POSIXObj("other");
        objects.addAll(other,owner,group);
        table.setItems(objects);
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            selectedFile = file;
            filePath.setText(file.getPath());
            perms = Files.getPosixFilePermissions(selectedFile.toPath());

            for (PosixFilePermission permission: perms)
                if(permission.equals(PosixFilePermission.GROUP_EXECUTE))
                    group.setExecute(true);
                else if(permission.equals(PosixFilePermission.GROUP_WRITE))
                    group.setWrite(true);
                else if(permission.equals(PosixFilePermission.GROUP_READ))
                    group.setRead(true);
                else if(permission.equals(PosixFilePermission.OWNER_WRITE))
                    owner.setWrite(true);
                else if(permission.equals(PosixFilePermission.OWNER_READ))
                    owner.setRead(true);
                else if(permission.equals(PosixFilePermission.OWNER_EXECUTE))
                    owner.setExecute(true);
                else if(permission.equals(PosixFilePermission.OTHERS_EXECUTE))
                    other.setExecute(true);
                else if(permission.equals(PosixFilePermission.OTHERS_WRITE))
                    other.setRead(true);
                else if(permission.equals(PosixFilePermission.OTHERS_READ))
                    other.setRead(true);

            /*aclFileAttributes = Files.getFileAttributeView(
                    file.toPath(), AclFileAttributeView.class);
            int index = 0;
            for (AclEntry aclEntry : aclFileAttributes.getAcl()) {
                ACLObject object=new ACLObject(aclEntry,index);
                objects.add(object);
                System.out.println(aclEntry.principal() + ":");
                System.out.println(aclEntry.permissions() + "\n");
                index++;
            }*/

        }
    }
}