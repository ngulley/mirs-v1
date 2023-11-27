package com.mirs.view;

import com.mirs.model.business.manager.InstrumentRentalManager;
import com.mirs.model.domain.Composite;
import com.mirs.model.domain.Instrument;
import com.mirs.model.domain.InstrumentStatus;
import com.mirs.model.domain.InstrumentType;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainJFrame extends JFrame {
    private InstrumentRentalManager manager;

    public MainJFrame() {
        setTitle("Musical Instrument Rental System v0.1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setLayout(new BorderLayout());

        this.manager = InstrumentRentalManager.getInstance();

        // Vertical navigation pane
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.addTab("Users", new UserPanel(this.manager));
        tabbedPane.addTab("Branches", new BranchPanel(this.manager));
        tabbedPane.addTab("Instruments", new InstrumentPanel(this.manager));
        tabbedPane.addTab("Rentals", new RentalPanel(this.manager));

        // Adding components to the frame
        add(tabbedPane, BorderLayout.WEST);



        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainJFrame());
    }
}

class UserPanel extends JPanel {
    private JTable contentTable;
    private DefaultTableModel tableModel;

    public UserPanel(InstrumentRentalManager manager) {
        setLayout(new BorderLayout());

        // Column names
        String[] columns = {"Email", "Password", "First Name", "Last Name", "Phone", "Address", "Role"};

        // Table model
        tableModel = new DefaultTableModel(columns, 0);

        // Prepopulate the table with test data
        //prepopulateTable();

        contentTable = new JTable(tableModel);
        contentTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(contentTable);

        contentTable.setPreferredScrollableViewportSize(new Dimension(1000, 600));

        // Buttons
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle create action
                // Implement your logic here
//                int i = 11;
//                tableModel.addRow(new Object[]{"user" + i + "@example.com", "********", "FirstName" + i, "LastName" + i, "Phone" + i, "Address" + i, "Role" + i});
//                System.out.println("create! " + tableModel.getRowCount());

                // Open the create user form
                openCreateUserDialog();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle update action
                // Implement your logic here
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle delete action
                // Implement your logic here
            }
        });

        // Adding components to the panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


    }

    private void prepopulateTable() {
        // Placeholder test data
        for (int i = 0; i < 10; i++) {
            tableModel.addRow(new Object[]{"user" + i + "@example.com", "********", "FirstName" + i, "LastName" + i, "Phone" + i, "Address" + i, "Role" + i});
        }
    }

    private void openCreateUserDialog() {
        // JDialog createUserDialog = new JDialog(MainJFrame.this, "Create User", true);
        JDialog createUserDialog = new JDialog();
        createUserDialog.setSize(400, 300);

        // Create form components
        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordField = new JTextField();
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();
        JLabel roleLabel = new JLabel("Role:");
        JTextField roleField = new JTextField();

        // Add components to the form panel
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(roleLabel);
        formPanel.add(roleField);

        // Create buttons
        JButton createButton = new JButton("Create");
        JButton cancelButton = new JButton("Cancel");

        // Button actions
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle create user action
                // Implement your logic here, e.g., add a new row to the table
                String[] userData = {
                        emailField.getText(),
                        passwordField.getText(),
                        firstNameField.getText(),
                        lastNameField.getText(),
                        phoneField.getText(),
                        addressField.getText(),
                        roleField.getText()
                };
                tableModel.addRow(userData);
                createUserDialog.dispose(); // Close the dialog
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the dialog without creating a new user
                createUserDialog.dispose();
            }
        });

        // Add buttons to a panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(cancelButton);

        // Add form panel and button panel to the dialog
        createUserDialog.add(formPanel, BorderLayout.CENTER);
        createUserDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Set dialog visibility
        createUserDialog.setVisible(true);
    }
}

// Similar panel classes for Branch, Instrument, and Rental
class BranchPanel extends JPanel {
    private JTable contentTable;
    private DefaultTableModel tableModel;
    private InstrumentRentalManager manager;

    public BranchPanel(InstrumentRentalManager manager) {
        // Implementation similar to UserPanel
        this.manager = manager;
    }
}

class InstrumentPanel extends JPanel {
    private JTable contentTable;
    private DefaultTableModel tableModel;
    private InstrumentTableModel tableModel2;
    private InstrumentRentalManager manager;

    public InstrumentPanel(InstrumentRentalManager manager) {
        this.manager = manager;
        setLayout(new BorderLayout());

        // Instrument instrument1 = new Instrument(1, "Acoustic Guitar", InstrumentType.STRING, "ABC456", "100000", InstrumentStatus.AVAILABLE);
        //
        // Column names
        String[] columns = {"ID", "Name", "Type", "Model Num", "Serial Num", "Status"};

        // Table model
        tableModel = new DefaultTableModel(columns, 0);
        tableModel2 = new InstrumentTableModel();

        Composite composite = new Composite();
        boolean isSuccess = manager.performAction("LIST_INSTRUMENTS", composite);
        if (isSuccess) {
            tableModel2.setInstrumentList(composite.getInstrumentList());
        } else {
           System.out.println("\nFAIL:  InstrumentPanel:: - Instrument not listed. ");
        }


        // Prepopulate the table with test data
        // prepopulateTable();

        contentTable = new JTable(tableModel2);
        // contentTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(contentTable);

        contentTable.setPreferredScrollableViewportSize(new Dimension(1000, 800));

        // Buttons
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle create action
                // Implement your logic here
//                int i = 11;
//                tableModel.addRow(new Object[]{"user" + i + "@example.com", "********", "FirstName" + i, "LastName" + i, "Phone" + i, "Address" + i, "Role" + i});
//                System.out.println("create! " + tableModel.getRowCount());

                // Open the create user form
                openAddInstrumentDialog();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle update action
                // Implement your logic here
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle delete action
                // Implement your logic here
            }
        });

        // Adding components to the panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


    }

    private void prepopulateTable() {
        // Placeholder test data
        for (int i = 0; i < 10; i++) {
            tableModel.addRow(new Object[]{"user" + i + "@example.com", "********", "FirstName" + i, "LastName" + i, "Phone" + i, "Address" + i, "Role" + i});
        }
    }

    private void openAddInstrumentDialog() {
        // JDialog createUserDialog = new JDialog(MainJFrame.this, "Create User", true);
        JDialog addInstrumentDialog = new JDialog();
        addInstrumentDialog.setTitle("Add Instrument");
        addInstrumentDialog.setSize(400, 300);
// String[] columns = {"ID", "Name", "Type", "Model Num", "Serial Num", "Status"};
        // Create form components
        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel typeLabel = new JLabel("Type:");
        JTextField typeField = new JTextField();
        JLabel modelNumLabel = new JLabel("ModelNum:");
        JTextField modelNumField = new JTextField();
        JLabel serialNumLabel = new JLabel("Serial Num:");
        JTextField serialNumField = new JTextField();
        JLabel statusLabel = new JLabel("Status:");
        JTextField statusField = new JTextField();

        // Add components to the form panel
        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(typeLabel);
        formPanel.add(typeField);
        formPanel.add(modelNumLabel);
        formPanel.add(modelNumField);
        formPanel.add(serialNumLabel);
        formPanel.add(serialNumField);
        formPanel.add(statusLabel);
        formPanel.add(statusField);

        // Create buttons
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle create user action
                // Implement your logic here, e.g., add a new row to the table
                String[] instrumentData = {
                        idField.getText(),
                        nameField.getText(),
                        typeField.getText(),
                        modelNumField.getText(),
                        serialNumField.getText(),
                        statusField.getText()
                };
                // tableModel.addRow(instrumentData);

                Instrument instrument = new Instrument(Integer.valueOf(idField.getText()), nameField.getText(), InstrumentType.valueOf(typeField.getText()), modelNumField.getText(), serialNumField.getText(), InstrumentStatus.valueOf(statusField.getText()));

                com.mirs.model.domain.Composite composite = new Composite();
                composite.setInstrument(instrument);

                boolean isAdded = manager.performAction("ADD_INSTRUMENT", composite);
                if (isAdded) {
                    System.out.println("\nSUCCESS:  InstrumentPanel:: - Instrument was added. ");
                    tableModel2.getInstrumentList().add(instrument);
                    tableModel2.fireTableDataChanged();
                } else {
                    System.out.println("\nFAIL:  InstrumentPanel:: - Instrument was not added. ");
                }

                boolean isListed = manager.performAction("LIST_INSTRUMENTS", composite);
                if (isListed) {
                    System.out.println("\nSUCCESS:  InstrumentPanel:: - Instruments were listed. ");
                    List<Instrument> list = composite.getInstrumentList();
                    for (Instrument i : list) {
                        System.out.println("Instrument: " + i.toString());
                    }

//                    tableModel2.setInstrumentList(composite.getInstrumentList());
//                    tableModel2.fireTableDataChanged();
                } else {
                    System.out.println("\nFAIL:  InstrumentPanel:: - Instruments were not listed. ");
                }

                addInstrumentDialog.dispose(); // Close the dialog
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the dialog without creating a new user
                addInstrumentDialog.dispose();
            }
        });

        // Add buttons to a panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        // Add form panel and button panel to the dialog
        addInstrumentDialog.add(formPanel, BorderLayout.CENTER);
        addInstrumentDialog.add(buttonPanel, BorderLayout.SOUTH);

        // Set dialog visibility
        addInstrumentDialog.setVisible(true);
    }
}

class RentalPanel extends JPanel {
    private JTable contentTable;
    private DefaultTableModel tableModel;
    private InstrumentRentalManager manager;

    public RentalPanel(InstrumentRentalManager manager) {
        // Implementation similar to UserPanel
        this.manager = manager;
    }
}

class InstrumentTableModel extends AbstractTableModel {
    java.util.List<Instrument> instrumentList = new ArrayList<Instrument>();

    String[] columns = {"ID", "Name", "Type", "Model Num", "Serial Num", "Status"};

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public int getRowCount() {
        return instrumentList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Instrument instrument = instrumentList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> instrument.getId();
            case 1 -> instrument.getName();
            case 2 -> instrument.getType();
            case 3 -> instrument.getModelNum();
            case 4 -> instrument.getSerialNum();
            case 5 -> instrument.getStatus();
            default -> null;
        };
    }

    public java.util.List<Instrument> getInstrumentList() {
        return this.instrumentList;
    }

    public void setInstrumentList(java.util.List<Instrument> instrumentList) {
        this.instrumentList = instrumentList;
    }
}
