package Interfaces;

import Data_Structures.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

public class ManagerGUI extends javax.swing.JFrame implements Runnable, Observer {
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChangeAvailability;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnTermDates;
    private javax.swing.JButton btnViewAvailability;
    private javax.swing.JLabel lblBookings;
    private javax.swing.JLabel lblRooms;
    private javax.swing.JScrollPane spBookings;
    private javax.swing.JScrollPane spRooms;
    private javax.swing.JTable tblBookings;
    private javax.swing.JTable tblRooms;
    private boolean searchActive;

    private RoomTable roomTable;
    private BookingTable bookingTable;
    private TermTable termTable;

    @Override
    public void run() {
        this.setVisible(true);
    }

    public ManagerGUI(RoomTable rTable, BookingTable bTable, TermTable tTable) {
        super();

        roomTable = rTable;
        roomTable.addObserver(this);

        bookingTable = bTable;
        bookingTable.addObserver(this);

        termTable = tTable;
        termTable.addObserver(this);

        initGUI();
        updateRoomTable();
        updateBookingTable();
        updateTermTable();
    }

    private void initGUI() {
        btnAdd = new javax.swing.JButton();
        btnChangeAvailability = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnTermDates = new javax.swing.JButton();
        btnViewAvailability = new javax.swing.JButton();
        lblBookings = new javax.swing.JLabel();
        lblRooms = new javax.swing.JLabel();
        spBookings = new javax.swing.JScrollPane();
        spRooms = new javax.swing.JScrollPane();
        tblBookings = new javax.swing.JTable() {
            @Override
            public String getToolTipText(MouseEvent e) {
                int r = rowAtPoint(e.getPoint());
                int c = columnAtPoint(e.getPoint());
                Object value = getValueAt(r, c);
                return value == null ? null : value.toString();
            }
        };
        tblRooms = new javax.swing.JTable() {
            @Override
            public String getToolTipText(MouseEvent e) {
                int r = rowAtPoint(e.getPoint());
                int c = columnAtPoint(e.getPoint());
                Object value = getValueAt(r, c);
                return value == null ? null : value.toString();
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("JFrame");
        setTitle("Room Manager GUI");
        setPreferredSize(new java.awt.Dimension(650, 895));
        setResizable(false);

        tblRooms.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Room ID", "Room Name", "Size", "Type", "Availability", "Status"
                }
        ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tblRooms.setAutoCreateRowSorter(true);
        spRooms.setViewportView(tblRooms);

        tblBookings.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Booking ID", "Room ID", "Date/Time", "Name", "Contact", "Notes"
                }
        ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tblBookings.setAutoCreateRowSorter(true);
        spBookings.setViewportView(tblBookings);

        lblRooms.setText("Rooms:");
        lblBookings.setText("Bookings:");

        btnAdd.setText("Add Room");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        btnChangeAvailability.setText("Change Room Availability");
        btnChangeAvailability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeAvailabilityActionPerformed(evt);
            }
        });
        btnRemove.setText("Remove Room");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        btnSearch.setText("Search Rooms");
        searchActive = false;
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        btnTermDates.setText("Configure Term Dates");
        btnTermDates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTermDatesActionPerformed(evt);
            }
        });
        btnViewAvailability.setText("View Room Availability");
        btnViewAvailability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAvailabilityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(spRooms, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                                        .addComponent(spBookings, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btnChangeAvailability, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnTermDates, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(btnViewAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblRooms)
                                                        .addComponent(lblBookings)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblRooms)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spRooms, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblBookings)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spBookings, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAdd)
                                        .addComponent(btnRemove))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnChangeAvailability)
                                        .addComponent(btnViewAvailability))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnTermDates)
                                        .addComponent(btnSearch))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        new AddRoomGUI(roomTable, bookingTable, termTable).setVisible(true);
    }

    private void btnChangeAvailabilityActionPerformed(java.awt.event.ActionEvent evt) {
        new ChangeAvailabilityGUI(roomTable, bookingTable, termTable).setVisible(true);
    }

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {
        new RemoveRoomGUI(roomTable, bookingTable, termTable).setVisible(true);
    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
        if (searchActive) {
            searchActive = false;
            btnSearch.setText("Search Rooms");
            updateRoomTable();
        } else {
            SearchPanel search = new SearchPanel();
            int input = JOptionPane.showConfirmDialog(null, search, "Search Rooms", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
            if (input == 0) {
                if (!searchActive) {
                    searchActive = true;
                    btnSearch.setText("Clear Search");
                }
                ArrayList<Room> table = roomTable.getTable();
                Search snapshot = search.getSnapshot();
                ArrayList<String> warnings = new ArrayList<>();

                if (!snapshot.getId().equals("")) {
                    int id = intTryParse(snapshot.getId(), Integer.MIN_VALUE);
                    if (id != Integer.MIN_VALUE) {
                        table = (ArrayList<Room>) table.stream().filter(r -> r.getRoomId() == id).collect(Collectors.toList());
                    } else {
                        warnings.add("Provided ID value was invalid. ID filter was not applied.");
                    }
                }

                if (!snapshot.getName().equals("")) {
                    table = (ArrayList<Room>) table.stream().filter(r -> r.getRoomName().toLowerCase().contains(snapshot.getName().toLowerCase())).collect(Collectors.toList());
                }

                if (!(snapshot.getSize().getKey() == 0)) {
                    if (!snapshot.getSize().getValue().equals("")) {
                        int size = intTryParse(snapshot.getSize().getValue(), Integer.MIN_VALUE);
                        if (size != Integer.MIN_VALUE) {
                            if (snapshot.getSize().getKey() == 1) {
                                table = (ArrayList<Room>) table.stream().filter(r -> r.getSize() == size).collect(Collectors.toList());
                            } else if (snapshot.getSize().getKey() == 2) {
                                table = (ArrayList<Room>) table.stream().filter(r -> r.getSize() < size).collect(Collectors.toList());
                            } else if (snapshot.getSize().getKey() == 3) {
                                table = (ArrayList<Room>) table.stream().filter(r -> r.getSize() > size).collect(Collectors.toList());
                            }
                        } else {
                            warnings.add("Provided size value was invalid. Size filter was not applied.");
                        }
                    } else {
                        warnings.add("No size value provided. Size filter was not applied.");
                    }
                }

                if (!(snapshot.getType() == 0)) {
                    if (snapshot.getType() == 1) {
                        table = (ArrayList<Room>) table.stream().filter(r -> r.getType().equals("Computer Lab")).collect(Collectors.toList());
                    } else if (snapshot.getType() == 2) {
                        table = (ArrayList<Room>) table.stream().filter(r -> r.getType().equals("Tutorial Room")).collect(Collectors.toList());
                    } else if (snapshot.getType() == 3) {
                        table = (ArrayList<Room>) table.stream().filter(r -> r.getType().equals("Lecture Theatre")).collect(Collectors.toList());
                    }
                }

                if (!(snapshot.getDAvailability() == 0)) {
                    if (snapshot.getDAvailability() == 1) {
                        table = (ArrayList<Room>) table.stream().filter(r -> r.getAvailability().contains("Weekday PM")).collect(Collectors.toList());
                    } else if (snapshot.getDAvailability() == 2) {
                        table = (ArrayList<Room>) table.stream().filter(r -> r.getAvailability().contains("Weekend AM")).collect(Collectors.toList());
                    } else if (snapshot.getDAvailability() == 3) {
                        table = (ArrayList<Room>) table.stream().filter(r -> r.getAvailability().contains("Weekend PM")).collect(Collectors.toList());
                    } else if (snapshot.getDAvailability() == 4) {
                        table = (ArrayList<Room>) table.stream().filter(r -> r.getAvailability().contains("Holiday AM")).collect(Collectors.toList());
                    } else if (snapshot.getDAvailability() == 5) {
                        table = (ArrayList<Room>) table.stream().filter(r -> r.getAvailability().contains("Holiday PM")).collect(Collectors.toList());
                    }
                }

                if (!snapshot.getSAvailability().getKey().equals(LocalDate.of(1970, 01, 01))) {
                    LocalDate date = snapshot.getSAvailability().getKey();
                    LocalDateTime dateAM = LocalDateTime.of(date, LocalTime.MIN.plusHours(9));
                    LocalDateTime datePM = LocalDateTime.of(date, LocalTime.MIN.plusHours(16));

                    if (snapshot.getSAvailability().getValue() == 0) {
                        for (Room r : table) {
                            if (bookingTable.checkBookingExists(r.roomId, dateAM) && bookingTable.checkBookingExists(r.roomId, datePM)) {
                                table = (ArrayList<Room>) table.stream().filter(r1 -> r1.getRoomId() != r.roomId).collect(Collectors.toList());
                            }
                            if (((date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7) && !r.getAvailability().contains("Weekend")) ||
                                    ((termTable.hasDate(date)) && !r.getAvailability().contains("Weekday")) ||
                                    (!(termTable.hasDate(date)) && !r.getAvailability().contains("Holiday"))) {
                                table = (ArrayList<Room>) table.stream().filter(r1 -> r1.getRoomId() != r.roomId).collect(Collectors.toList());
                            }
                        }
                    } else if (snapshot.getSAvailability().getValue() == 1) {
                        for (Room r : table) {
                            if (bookingTable.checkBookingExists(r.roomId, dateAM)) {
                                table = (ArrayList<Room>) table.stream().filter(r1 -> r1.getRoomId() != r.roomId).collect(Collectors.toList());
                            }
                            if (((date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7) && !r.getAvailability().contains("Weekend AM")) ||
                                    ((termTable.hasDate(date)) && !r.getAvailability().contains("Weekday AM")) ||
                                    (!(termTable.hasDate(date)) && !r.getAvailability().contains("Holiday AM"))) {
                                table = (ArrayList<Room>) table.stream().filter(r1 -> r1.getRoomId() != r.roomId).collect(Collectors.toList());
                            }
                        }
                    } else if (snapshot.getSAvailability().getValue() == 2) {
                        for (Room r : table) {
                            if (bookingTable.checkBookingExists(r.roomId, datePM)) {
                                table = (ArrayList<Room>) table.stream().filter(r1 -> r1.getRoomId() != r.roomId).collect(Collectors.toList());
                            }
                            if (((date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7) && !r.getAvailability().contains("Weekend PM")) ||
                                    ((termTable.hasDate(date)) && !r.getAvailability().contains("Weekday PM")) ||
                                    (!(termTable.hasDate(date)) && !r.getAvailability().contains("Holiday PM"))) {
                                table = (ArrayList<Room>) table.stream().filter(r1 -> r1.getRoomId() != r.roomId).collect(Collectors.toList());
                            }
                        }
                    }
                }

                if (!snapshot.getStatus().equals("")) {
                    table = (ArrayList<Room>) table.stream().filter(r -> r.getStatus().toLowerCase().contains(snapshot.getStatus().toLowerCase())).collect(Collectors.toList());
                }

                if(warnings.size() != 0) {
                    JOptionPane.showMessageDialog(null,
                            "Warning:\n" + warnings.stream().collect(Collectors.joining("\n")),
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                }
                searchRoomTable(table);
            }
        }
    }

    private void btnTermDatesActionPerformed(java.awt.event.ActionEvent evt) {
        new TermDatesGUI(roomTable, bookingTable, termTable).setVisible(true);
    }

    private void btnViewAvailabilityActionPerformed(java.awt.event.ActionEvent evt) {
        new ViewAvailabilityGUI(roomTable, bookingTable, termTable).setVisible(true);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        updateRoomTable();
        updateBookingTable();
        updateTermTable();
    }

    private void searchRoomTable(ArrayList<Room> table) {
        DefaultTableModel dtm = (DefaultTableModel) tblRooms.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < table.size(); i++) {
            Object[] rowData = new Object[6];
            rowData[0] = table.get(i).getRoomId();
            rowData[1] = table.get(i).getRoomName();
            rowData[2] = table.get(i).getSize();
            rowData[3] = table.get(i).getType();
            rowData[4] = table.get(i).getAvailability();
            rowData[5] = table.get(i).getStatus();
            dtm.addRow(rowData);
        }
        tblRooms = new JTable(dtm);
    }

    private void updateRoomTable() {
        if (!searchActive) {
            DefaultTableModel dtm = (DefaultTableModel) tblRooms.getModel();
            dtm.setRowCount(0);
            for (int i = 0; i < roomTable.getTable().size(); i++) {
                Object[] rowData = new Object[6];
                rowData[0] = roomTable.getTable().get(i).getRoomId();
                rowData[1] = roomTable.getTable().get(i).getRoomName();
                rowData[2] = roomTable.getTable().get(i).getSize();
                rowData[3] = roomTable.getTable().get(i).getType();
                rowData[4] = roomTable.getTable().get(i).getAvailability();
                rowData[5] = roomTable.getTable().get(i).getStatus();
                dtm.addRow(rowData);
            }
            tblRooms = new JTable(dtm);
        }
    }

    private void updateBookingTable() {
        DefaultTableModel dtm = (DefaultTableModel) tblBookings.getModel();
        dtm.setRowCount(0);
        for (int i = 0; i < bookingTable.getTable().size(); i++) {
            Object[] rowData = new Object[6];
            rowData[0] = bookingTable.getTable().get(i).getBookingId();
            rowData[1] = bookingTable.getTable().get(i).getRoomId();
            rowData[2] = bookingTable.getTable().get(i).getDateTime();
            rowData[3] = bookingTable.getTable().get(i).getName();
            rowData[4] = bookingTable.getTable().get(i).getContact();
            rowData[5] = bookingTable.getTable().get(i).getNotes();
            dtm.addRow(rowData);
        }
        tblBookings = new JTable(dtm);
    }

    private void updateTermTable() {
        termTable.equals(termTable.getTable());
    }

    public int intTryParse(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
