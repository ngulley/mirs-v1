package com.mirs.view;

import com.mirs.model.business.manager.InstrumentRentalManager;
import com.mirs.model.domain.*;
import com.mirs.model.domain.Composite;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainJFrame extends JFrame {
    private InstrumentRentalManager manager;
    private JFrame parentFrame;

    public MainJFrame() {
        parentFrame = this;
        setTitle("Musical Instrument Rental System v0.1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setLayout(new BorderLayout());

        this.manager = InstrumentRentalManager.getInstance();

        // Image Icons - Give source credit to: https://icons8.com/icon/u7vefpUu46Xk/drums
        ImageIcon customersIcon = new ImageIcon(Objects.requireNonNull(
                this.getClass().getResource("icons8-people-48.png")));

        ImageIcon branchesIcon = new ImageIcon(Objects.requireNonNull(
                this.getClass().getResource("icons8-related-companies-48.png")));

        ImageIcon instrumentsIcon = new ImageIcon(Objects.requireNonNull(
                this.getClass().getResource("icons8-drums-48.png")));

        ImageIcon rentalsIcon = new ImageIcon(Objects.requireNonNull(
                this.getClass().getResource("icons8-exchange-48.png")));

        ImageIcon adminsIcon = new ImageIcon(Objects.requireNonNull(
                this.getClass().getResource("icons8-decision-making-48.png")));

        // Vertical navigation pane
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        tabbedPane.addTab("Employees", adminsIcon, new EmployeePanel(this.manager, this));
        tabbedPane.addTab("Customers", customersIcon, new CustomersPanel(this.manager, this));
        tabbedPane.addTab("Branches", branchesIcon, new BranchPanel(this.manager, this));
        tabbedPane.addTab("Instruments",  instrumentsIcon, new InstrumentPanel(this.manager, this));
        tabbedPane.addTab("Rentals", rentalsIcon, new RentalPanel(this.manager, this));


        // Increase the vertical space between tabs
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(20, 0, 20, 0));


        // Add a ChangeListener to the tabbedPane
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Get the index of the selected tab
                int selectedIndex = tabbedPane.getSelectedIndex();

                // Perform actions based on the selected tab
                System.out.println("Selected Tab: " + selectedIndex);

                // You can run your code here when a tab is selected
                // For example, change the background color of the selected tab
                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                    if (i == selectedIndex) {
                        // tabbedPane.setBackgroundAt(i, Color.YELLOW);

                        // tabbedPane.setIconAt(selectedIndex, selectedIcon);

                        Font selectedFont = new Font("Arial", Font.BOLD, 12);


                    } else {
                        // tabbedPane.setBackgroundAt(i, null); // Set to default color
                        // tabbedPane.setIconAt(selectedIndex, null);

                    }
                }
                tabbedPane.repaint();
            }
        });

        // Adding components to the frame
        add(tabbedPane, BorderLayout.WEST);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainJFrame());
    }
}

class EmployeePanel extends JPanel {
    private JTable contentTable;
    private DefaultTableModel tableModel;
    private JFrame parent;

    public EmployeePanel(InstrumentRentalManager manager, JFrame parent) {
        this.parent = parent;
        setLayout(new BorderLayout());


        // Column names
        String[] columns = {"Email", "Password", "First Name", "Last Name", "Phone", "Address", "Role"};

        // Table model
        tableModel = new DefaultTableModel(columns, 0);

        contentTable = new JTable(tableModel);
        contentTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(contentTable);

        contentTable.setPreferredScrollableViewportSize(new Dimension(1000, 600));

        // Buttons
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the add user form
                openAddEmployeeDialog();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle edit action

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle delete action

            }
        });

        // Adding components to the panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    private void openAddEmployeeDialog() {
        JDialog addUserDialog = new JDialog((Frame) null, "Add Employee",true);
        addUserDialog.setSize(400, 300);

        // Create form components
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        formPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
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
        String[] roleOptions = {"ADMIN", "CUSTOMER"};
        JComboBox<String> roleComboBox = new JComboBox<>(roleOptions);

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
        formPanel.add(roleComboBox);

        // Create buttons
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        // Button actions
        submitButton.addActionListener(new ActionListener() {
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
                        (String)roleComboBox.getSelectedItem()
                };
                tableModel.addRow(userData);
                addUserDialog.dispose(); // Close the dialog
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the dialog without creating a new user
                addUserDialog.dispose();
            }
        });

        // Add buttons to a panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        // Add form panel and button panel to the dialog
        addUserDialog.add(formPanel, BorderLayout.CENTER);
        addUserDialog.add(buttonPanel, BorderLayout.SOUTH);

        ViewUtil.centerDialogOverFrame(addUserDialog, parent);

        // Set dialog visibility
        addUserDialog.setVisible(true);
    }

}

class CustomersPanel extends JPanel {
    private JTable contentTable;
    private DefaultTableModel tableModel;
    private JFrame parent;

    public CustomersPanel(InstrumentRentalManager manager, JFrame parent) {
        this.parent = parent;
        setLayout(new BorderLayout());


        // Column names
        String[] columns = {"Email", "Password", "First Name", "Last Name", "Phone", "Address", "Role"};

        // Table model
        tableModel = new DefaultTableModel(columns, 0);

        contentTable = new JTable(tableModel);
        contentTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(contentTable);

        contentTable.setPreferredScrollableViewportSize(new Dimension(1000, 600));

        // Buttons
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the add user form
                openCustomerDialog();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle edit action

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle delete action

            }
        });

        // Adding components to the panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);




    }

    private void openCustomerDialog() {
        JDialog addUserDialog = new JDialog((Frame) null, "Add Customer",true);
        addUserDialog.setSize(400, 300);

        // Create form components
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        formPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
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
        String[] roleOptions = {"ADMIN", "CUSTOMER"};
        JComboBox<String> roleComboBox = new JComboBox<>(roleOptions);

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
        formPanel.add(roleComboBox);

        // Create buttons
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        // Button actions
        submitButton.addActionListener(new ActionListener() {
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
                        (String)roleComboBox.getSelectedItem()
                };
                tableModel.addRow(userData);
                addUserDialog.dispose(); // Close the dialog
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the dialog without creating a new user
                addUserDialog.dispose();
            }
        });

        // Add buttons to a panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        // Add form panel and button panel to the dialog
        addUserDialog.add(formPanel, BorderLayout.CENTER);
        addUserDialog.add(buttonPanel, BorderLayout.SOUTH);

        ViewUtil.centerDialogOverFrame(addUserDialog, parent);

        // Set dialog visibility
        addUserDialog.setVisible(true);
    }

}

class BranchPanel extends JPanel {
    private JTable contentTable;
    private DefaultTableModel tableModel;
    private BranchTableModel tableModel2;
    private InstrumentRentalManager manager;
    private JFrame parent;

    public BranchPanel(InstrumentRentalManager manager, JFrame parent) {
        this.parent = parent;
        this.manager = manager;
        setLayout(new BorderLayout());

        // Column names
        String[] columns = {"ID", "Name", "Type", "Model Num", "Serial Num", "Status"};

        // Table model
        tableModel = new DefaultTableModel(columns, 0);
        tableModel2 = new BranchTableModel();

        Composite composite = new Composite();
        boolean isSuccess = manager.performAction("LIST_BRANCHES", composite);
        if (isSuccess) {
            tableModel2.setBranchList(composite.getBranchList());
        } else {
            System.out.println("\nFAIL:  BranchPanel:: - Branch not listed. ");
        }

        contentTable = new JTable(tableModel2);
        // contentTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(contentTable);

        contentTable.setPreferredScrollableViewportSize(new Dimension(1000, 800));

        // Buttons
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle add action
                openAddBranchDialog();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle edit action
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
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


    }

    private void openAddBranchDialog() {
        JDialog addBranchDialog = new JDialog((Frame)null, "Add Branch", true);
        addBranchDialog.setSize(400, 300);

        // Create form components
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        formPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();
        JLabel statusLabel = new JLabel("Status:");
        String[] statusList = {"ACTIVE", "INACTIVE"};
        JComboBox<String> statusComboBox = new JComboBox<>(statusList);


        // Add components to the form panel
        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        formPanel.add(statusLabel);
        formPanel.add(statusComboBox);

        // Create buttons
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        // Button actions
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle create user action
                // Implement your logic here, e.g., add a new row to the table
                String[] branchData = {
                        idField.getText(),
                        nameField.getText(),
                        phoneField.getText(),
                        addressField.getText(),
                        (String)statusComboBox.getSelectedItem()
                };
                // tableModel.addRow(branchData);

                Branch branch = new Branch(Integer.valueOf(idField.getText()), nameField.getText(), phoneField.getText(), new Address(addressField.getText()), BranchStatus.valueOf((String)statusComboBox.getSelectedItem()), null, new ArrayList<Instrument>());

                com.mirs.model.domain.Composite composite = new Composite();
                composite.setBranch(branch);

                boolean isAdded = manager.performAction("ADD_BRANCH", composite);
                if (isAdded) {
                    System.out.println("\nSUCCESS:  BranchPanel:: - Branch was added. ");
                    tableModel2.getBranchList().add(branch);
                    tableModel2.fireTableDataChanged();
                } else {
                    System.out.println("\nFAIL:  BranchPanel:: - Branch was not added. ");
                }

                boolean isListed = manager.performAction("LIST_BRANCHES", composite);
                if (isListed) {
                    System.out.println("\nSUCCESS:  BranchPanel:: - Branches were listed. ");
                    List<Branch> list = composite.getBranchList();
                    for (Branch i : list) {
                        System.out.println("Branch: " + i.toString());
                    }

//                    tableModel2.setBranchList(composite.getBranchList());
//                    tableModel2.fireTableDataChanged();
                } else {
                    System.out.println("\nFAIL:  BranchPanel:: - Branches were not listed. ");
                }

                addBranchDialog.dispose(); // Close the dialog
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the dialog without creating a new user
                addBranchDialog.dispose();
            }
        });

        // Add buttons to a panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        // Add form panel and button panel to the dialog
        addBranchDialog.add(formPanel, BorderLayout.CENTER);
        addBranchDialog.add(buttonPanel, BorderLayout.SOUTH);

        ViewUtil.centerDialogOverFrame(addBranchDialog, parent);
        // Set dialog visibility
        addBranchDialog.setVisible(true);
    }
}

class InstrumentPanel extends JPanel {
    private JTable contentTable;
    private DefaultTableModel tableModel;
    private InstrumentTableModel tableModel2;
    private InstrumentRentalManager manager;
    private JFrame parent;

    public InstrumentPanel(InstrumentRentalManager manager, JFrame parent) {
        this.parent = parent;
        this.manager = manager;
        setLayout(new BorderLayout());

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

        contentTable = new JTable(tableModel2);
        // contentTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(contentTable);

        contentTable.setPreferredScrollableViewportSize(new Dimension(1000, 800));

        // Buttons
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle add action
               openAddInstrumentDialog();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle edit action

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle delete action

            }
        });

        // Adding components to the panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


    }

    private void openAddInstrumentDialog() {
        JDialog addInstrumentDialog = new JDialog((Frame)null, "Add Instrument", true);
        addInstrumentDialog.setSize(400, 300);

        // Create form components
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        formPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel typeLabel = new JLabel("Type:");
        String[] types = {"BRASS", "KEYBOARD", "PERCUSSION", "STRING", "WOODWIND"};
        JComboBox<String> typesComboBox = new JComboBox<>(types);
        JLabel modelNumLabel = new JLabel("ModelNum:");
        JTextField modelNumField = new JTextField();
        JLabel serialNumLabel = new JLabel("Serial Num:");
        JTextField serialNumField = new JTextField();
        JLabel statusLabel = new JLabel("Status:");
        String[] statusList = {"AVAILABLE", "RENTED", "BROKEN", "LOST", "STOLEN"};
        JComboBox<String> statusComboBox = new JComboBox<>(statusList);

        // Add components to the form panel
        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(typeLabel);
        formPanel.add(typesComboBox);
        formPanel.add(modelNumLabel);
        formPanel.add(modelNumField);
        formPanel.add(serialNumLabel);
        formPanel.add(serialNumField);
        formPanel.add(statusLabel);
        formPanel.add(statusComboBox);

        // Create buttons
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        // Button actions
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle create user action
                // Implement your logic here, e.g., add a new row to the table
                String[] instrumentData = {
                        idField.getText(),
                        nameField.getText(),
                        (String)typesComboBox.getSelectedItem(),
                        modelNumField.getText(),
                        serialNumField.getText(),
                        (String)statusComboBox.getSelectedItem()
                };
                // tableModel.addRow(instrumentData);

                Instrument instrument = new Instrument(Integer.valueOf(idField.getText()), nameField.getText(), InstrumentType.valueOf((String)typesComboBox.getSelectedItem()), modelNumField.getText(), serialNumField.getText(), InstrumentStatus.valueOf((String)statusComboBox.getSelectedItem()));

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
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        // Add form panel and button panel to the dialog
        addInstrumentDialog.add(formPanel, BorderLayout.CENTER);
        addInstrumentDialog.add(buttonPanel, BorderLayout.SOUTH);

        ViewUtil.centerDialogOverFrame(addInstrumentDialog, parent);
        // Set dialog visibility
        addInstrumentDialog.setVisible(true);
    }
}

class RentalPanel extends JPanel {
    private JTable contentTable;
    private DefaultTableModel tableModel;
    private InstrumentRentalManager manager;

    public RentalPanel(InstrumentRentalManager manager, JFrame parent) {
        // Implementation similar to CustomersPanel
        this.manager = manager;
    }
}

class BranchTableModel extends AbstractTableModel {
    java.util.List<Branch> branchList = new ArrayList<Branch>();

    String[] columns = {"ID", "Name", "Phone", "Address", "Status",};

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public int getRowCount() {
        return branchList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Branch branch = branchList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> branch.getId();
            case 1 -> branch.getName();
            case 2 -> branch.getPhone();
            case 3 -> branch.getAddress();
            case 4 -> branch.getStatus();
            default -> null;
        };
    }

    public java.util.List<Branch> getBranchList() {
        return this.branchList;
    }

    public void setBranchList(java.util.List<Branch> branchList) {
        this.branchList = branchList;
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
