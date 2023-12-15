package com.mirs.view;

import com.mirs.model.business.manager.InstrumentRentalManager;
import com.mirs.model.domain.*;
import com.mirs.model.domain.Composite;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

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

        // Image Icons - source credit goes to: https://icons8.com -> Windows 11 Color Style
        ImageIcon customersIcon = new ImageIcon(Objects.requireNonNull(
                this.getClass().getResource("icons8-people-48.png")));

        ImageIcon branchesIcon = new ImageIcon(Objects.requireNonNull(
                this.getClass().getResource("icons8-related-companies-48.png")));

        // https://icons8.com/icon/u7vefpUu46Xk/drums
        ImageIcon instrumentsIcon = new ImageIcon(Objects.requireNonNull(
                this.getClass().getResource("icons8-drums-48.png")));

        ImageIcon reservationsIcon = new ImageIcon(Objects.requireNonNull(
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
        tabbedPane.addTab("Reservations", reservationsIcon, new ReservationPanel(this.manager, this));


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
    private EmployeeTableModel tableModel2;
    private JFrame parent;

    public EmployeePanel(InstrumentRentalManager manager, JFrame parent) {
        this.parent = parent;
        setLayout(new BorderLayout());


        // Column names
        String[] columns = {"Email", "Password", "First Name", "Last Name", "Phone", "Address", "Role"};

        // Table model
        // tableModel = new DefaultTableModel(columns, 0);
        tableModel2 = new EmployeeTableModel();

        Composite composite = new Composite();
        boolean isSuccess = manager.performAction("LIST_EMPLOYEES", composite);
        if (isSuccess) {
            tableModel2.setEmployeeList(composite.getUserList());
        } else {
            System.out.println("\nFAIL:  EmployeePanel:: - Employees not listed. ");
        }

        contentTable = new JTable(tableModel2);
        contentTable.setFillsViewportHeight(true);
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(5);

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
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
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

        // Add components to the form panel
        formPanel.add(idLabel);
        formPanel.add(idField);
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
                        idField.getText(),
                        emailField.getText(),
                       // passwordField.getText(),
                        firstNameField.getText(),
                        lastNameField.getText(),
                        phoneField.getText(),
                        addressField.getText()
                };
                // tableModel2.addRow(userData);
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
    private CustomerTableModel tableModel2;
    private JFrame parent;

    public CustomersPanel(InstrumentRentalManager manager, JFrame parent) {
        this.parent = parent;
        setLayout(new BorderLayout());


        // Column names
        String[] columns = {"Email", "Password", "First Name", "Last Name", "Phone", "Address", "Role"};

        // Table model
       // tableModel = new DefaultTableModel(columns, 0);
        tableModel2 = new CustomerTableModel();

        Composite composite = new Composite();
        boolean isSuccess = manager.performAction("LIST_CUSTOMERS", composite);
        if (isSuccess) {
            tableModel2.setCustomerList(composite.getUserList());
        } else {
            System.out.println("\nFAIL:  CustomerPanel:: - Customers not listed. ");
        }

        contentTable = new JTable(tableModel2);
        contentTable.setFillsViewportHeight(true);
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(5);

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
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
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

        // Add components to the form panel
        formPanel.add(idLabel);
        formPanel.add(idField);
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
                        idField.getText(),
                        emailField.getText(),
                     //   passwordField.getText(),
                        firstNameField.getText(),
                        lastNameField.getText(),
                        phoneField.getText(),
                        addressField.getText()
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
    private DefaultTableCellRenderer cellRenderer;

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
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(contentTable);
        // contentTable.setPreferredScrollableViewportSize(new Dimension(1000, 800));
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(5);
        contentTable.getColumnModel().getColumn(1).setPreferredWidth(120);
//        contentTable.getColumnModel().getColumn(2).setPreferredWidth(10);
//        contentTable.getColumnModel().getColumn(3).setPreferredWidth(50);
        contentTable.getColumnModel().getColumn(3).setPreferredWidth(500);

        // JScrollPane scrollPane = new JScrollPane(contentTable);

        cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);

        contentTable.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);


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

                Branch branch = new Branch(Integer.valueOf(idField.getText()), nameField.getText(), phoneField.getText(), new Address(addressField.getText()), BranchStatus.valueOf((String)statusComboBox.getSelectedItem()), new ArrayList<Instrument>());

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
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(5);

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
        String[] types = {"BRASS", "KEYBOARD", "PERCUSSION", "STRING", "FREE_REED_WIND", "WOODWIND"};
        JComboBox<String> typesComboBox = new JComboBox<>(types);
        JLabel modelNumLabel = new JLabel("ModelNum:");
        JTextField modelNumField = new JTextField();
        JLabel serialNumLabel = new JLabel("Serial Num:");
        JTextField serialNumField = new JTextField();
        JLabel statusLabel = new JLabel("Status:");
        String[] statusList = {"AVAILABLE", "RESERVED", "BROKEN", "LOST", "STOLEN"};
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

class ReservationPanel extends JPanel {
    private JTable contentTable;
    private DefaultTableModel tableModel;
    private ReservationTableModel tableModel2;
    private InstrumentRentalManager manager;
    private JFrame parent;

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    public ReservationPanel(InstrumentRentalManager manager, JFrame parent) {
        this.parent = parent;
        this.manager = manager;
        setLayout(new BorderLayout());

        // Column names
        String[] columns = {"ID", "Customer", "Employee", "Branch", "Instrument", "Start Date", "End Date"};

        // Table model
        tableModel = new DefaultTableModel(columns, 0);
        tableModel2 = new ReservationTableModel();

        Composite composite = new Composite();
        boolean isSuccess = manager.performAction("LIST_RESERVATIONS", composite);
        if (isSuccess) {
            tableModel2.setReservationList(composite.getReservationList());
        } else {
            System.out.println("\nFAIL:  ReservationPanel:: - Reservations not listed. ");
        }

        contentTable = new JTable(tableModel2);
        // contentTable.setFillsViewportHeight(true);
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(5);

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
                openAddReservationDialog();
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

    private void openAddReservationDialog() {
        JDialog addReservationDialog = new JDialog((Frame)null, "Add Reservation", true);
        addReservationDialog.setSize(400, 300);
        Composite composite = new Composite();
        boolean isSuccess = false;

        // Create form components
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        formPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();

        JLabel customerLabel = new JLabel("Customer:");
        isSuccess = manager.performAction("LIST_CUSTOMERS", composite);
        List<User> customerList = composite.getUserList();
        Vector<Item> customerListModel = new Vector<Item>();
        if (isSuccess) {
            for (User u : customerList) {
                customerListModel.add(new Item(u.getId(), u.getFirstName() + " " + u.getLastName()));
            }
        }
        JComboBox customerListComboBox = new JComboBox(customerListModel);

        JLabel employeeLabel = new JLabel("Employee:");
        isSuccess = manager.performAction("LIST_EMPLOYEES", composite);
        List<User> employeeList = composite.getUserList();
        Vector<Item> employeeListModel = new Vector<Item>();
        if (isSuccess) {
            for (User u : employeeList) {
                employeeListModel.add(new Item(u.getId(), u.getFirstName() + " " + u.getLastName()));
            }
        }
        JComboBox employeeListComboBox = new JComboBox(employeeListModel);


        isSuccess = manager.performAction("LIST_BRANCHES", composite);
        Vector<Item> branchListModel = new Vector<Item>();
        List<Branch> branchList = composite.getBranchList();
        if (isSuccess) {
            for (Branch b : branchList) {
                if (b.getStatus() == BranchStatus.ACTIVE) {
                    branchListModel.add(new Item(b.getId(), b.getName()));
                }
            }
        }
        JLabel branchLabel = new JLabel("Branch:");
        JComboBox<String> branchListComboBox = new JComboBox(branchListModel);


        isSuccess = manager.performAction("LIST_INSTRUMENTS", composite);
        Vector<Item> instrumentListModel = new Vector<Item>();
        List<Instrument> instrumentList = composite.getInstrumentList();
        if (isSuccess) {
            for (Instrument i : instrumentList) {
                if (i.getStatus() == InstrumentStatus.AVAILABLE) {
                    instrumentListModel.add(new Item(i.getId(), i.getName()));
                }
            }
        }
        JLabel instrumentLabel = new JLabel("Instrument:");
        JComboBox instrumentListComboBox = new JComboBox(instrumentListModel);

        JLabel startDateLabel = new JLabel("Start Date:");
        JTextField startDateField = new JTextField();

        JLabel endDateLabel = new JLabel("End Date:");
        JTextField endDateField = new JTextField();


        // Add components to the form panel
        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(customerLabel);
        formPanel.add(customerListComboBox);
        formPanel.add(employeeLabel);
        formPanel.add(employeeListComboBox);
        formPanel.add(branchLabel);
        formPanel.add(branchListComboBox);
        formPanel.add(instrumentLabel);
        formPanel.add(instrumentListComboBox);

        formPanel.add(startDateLabel);
        formPanel.add(startDateField);
        formPanel.add(endDateLabel);
        formPanel.add(endDateField);

        // Create buttons
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        // Button actions
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle create user action

                Reservation reservation = null;

                String selectedBranchName = "";
                for (Branch b : branchList) {
                    if (b.getId() == ((Item)branchListComboBox.getSelectedItem()).getId()) {
                        selectedBranchName = b.getName();
                        break;
                    }
                }

                String selectedInstrumentName = "";
                Instrument selectedInstrument = null;
                for (Instrument i : instrumentList) {
                    if (i.getId() == ((Item)instrumentListComboBox.getSelectedItem()).getId()) {
                        selectedInstrument = i;
                        selectedInstrumentName = i.getName();
                        break;
                    }
                }

                String selectedCustomerName = "";
                for (User u : customerList) {
                    if (u.getId() == ((Item)customerListComboBox.getSelectedItem()).getId()) {
                        selectedCustomerName = u.getFirstName() + " " + u.getLastName();
                        break;
                    }
                }

                String selectedEmployeeName = "";
                for (User u : employeeList) {
                    if (u.getId() == ((Item)employeeListComboBox.getSelectedItem()).getId()) {
                        selectedEmployeeName = u.getFirstName() + " " + u.getLastName();
                        break;
                    }
                }

                Date currentDate = new Date();
                Date startDate = null;
                Date endDate = null;

                try {
                    startDate = dateFormatter.parse(startDateField.getText());
                    endDate = dateFormatter.parse(endDateField.getText());

                     reservation = new Reservation(
                            Integer.valueOf(idField.getText()),
                             ((Item)customerListComboBox.getSelectedItem()).getId(),
                             selectedCustomerName,
                             ((Item)employeeListComboBox.getSelectedItem()).getId(),
                             selectedEmployeeName,
                             ((Item)branchListComboBox.getSelectedItem()).getId(),
                             selectedBranchName,
                            ((Item)instrumentListComboBox.getSelectedItem()).getId(),
                             selectedInstrumentName,
                             startDate,
                             endDate
                    );
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }


                com.mirs.model.domain.Composite composite = new Composite();
                composite.setReservation(reservation);

                boolean isAdded = manager.performAction("ADD_RESERVATION", composite);
                if (isAdded) {
                    System.out.println("\nSUCCESS:  ReservationPanel:: - Reservation was added. ");

                    if (currentDate.after(startDate) && currentDate.before(endDate)) {
                        selectedInstrument.setStatus(InstrumentStatus.RESERVED);
                    }

                    composite.setInstrument(selectedInstrument);
                    boolean isUpdated = manager.performAction("UPDATE_INSTRUMENT", composite);
                    if (isUpdated) {
                        System.out.println("\nSUCCESS:  ReservationPanel:: - Instrument was updated. ");
                    } else {
                        System.out.println("\nFAIL:  ReservationPanel:: - Instrument was not updated. ");
                    }

                } else {
                    System.out.println("\nFAIL:  ReservationPanel:: - Reservation was not added. ");
                }

                boolean isListed = manager.performAction("LIST_RESERVATIONS", composite);
                if (isListed) {
                    System.out.println("\nSUCCESS:  ReservationPanel:: - Reservations were listed. ");
                    tableModel2.setReservationList(composite.getReservationList());
                    tableModel2.fireTableDataChanged();
                } else {
                    System.out.println("\nFAIL:  ReservationPanel:: - Reservations were not listed. ");
                }

                addReservationDialog.dispose(); // Close the dialog
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the dialog without creating a new user
                addReservationDialog.dispose();
            }
        });

        // Add buttons to a panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        // Add form panel and button panel to the dialog
        addReservationDialog.add(formPanel, BorderLayout.CENTER);
        addReservationDialog.add(buttonPanel, BorderLayout.SOUTH);

        ViewUtil.centerDialogOverFrame(addReservationDialog, parent);
        // Set dialog visibility
        addReservationDialog.setVisible(true);
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

class CustomerTableModel extends AbstractTableModel {
    java.util.List<User> customerList = new ArrayList<User>();

    String[] columns = {"ID", "Email", "First Name", "Last Name", "Phone", "Address"};

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public int getRowCount() {
        return customerList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User customer = customerList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> customer.getId();
            case 1 -> customer.getEmail();
            case 2 -> customer.getFirstName();
            case 3 -> customer.getLastName();
            case 4 -> customer.getPhone();
            case 5 -> customer.getAddress().getAddress1();
            default -> null;
        };
    }

    public java.util.List<User> getCustomerList() {
        return this.customerList;
    }

    public void setCustomerList(java.util.List<User> customerList) {
        this.customerList = customerList;
    }
}

class EmployeeTableModel extends AbstractTableModel {
    java.util.List<User> employeeList = new ArrayList<User>();

    String[] columns = {"ID", "Email", "First Name", "Last Name", "Phone", "Address"};

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public int getRowCount() {
        return employeeList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User employee = employeeList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> employee.getId();
            case 1 -> employee.getEmail();
            case 2 -> employee.getFirstName();
            case 3 -> employee.getLastName();
            case 4 -> employee.getPhone();
            case 5 -> employee.getAddress().getAddress1();
            default -> null;
        };
    }

    public java.util.List<User> getEmployeeList() {
        return this.employeeList;
    }

    public void setEmployeeList(java.util.List<User> employeeList) {
        this.employeeList = employeeList;
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

class ReservationTableModel extends AbstractTableModel {
    java.util.List<Reservation> reservationList = new ArrayList<Reservation>();
    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    String[] columns = {"ID", "Customer", "Employee", "Branch", "Instrument", "Start Date", "End Date"};


    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public int getRowCount() {
        return reservationList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Reservation reservation = reservationList.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> reservation.getId();
            case 1 -> reservation.getCustomerName();
            case 2 -> reservation.getEmployeeName();
            case 3 -> reservation.getBranchName();
            case 4 -> reservation.getInstrumentName();
            case 5 -> dateFormatter.format(reservation.getStartDate());
            case 6 -> dateFormatter.format(reservation.getEndDate());
            default -> null;
        };
    }

    public java.util.List<Reservation> getReservationList() {
        return this.reservationList;
    }

    public void setReservationList(java.util.List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}

class Item {
    private int id;
    private String description;

    public Item(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return description;
    }
}