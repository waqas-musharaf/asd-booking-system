package Interfaces;

import Data_Structures.BookingTable;
import Data_Structures.Room;
import Data_Structures.RoomTable;
import Data_Structures.TermTable;

import javax.swing.*;
import java.time.LocalDate;
import java.util.*;

public class ViewAvailabilityGUI extends javax.swing.JFrame implements Runnable, Observer {
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel lblAvailability;
    private javax.swing.JLabel lblRoomID;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblUnavailability;
    private javax.swing.JScrollPane spAvailability;
    private javax.swing.JScrollPane spStatus;
    private javax.swing.JScrollPane spUnavailability;
    private javax.swing.JTextArea taUnavailability;
    private javax.swing.JTextField tfAvailability;
    private javax.swing.JTextField tfRoomID;
    private javax.swing.JTextField tfStatus;

    private RoomTable roomTable;
    private BookingTable bookingTable;
    private TermTable termTable;

    @Override
    public void run() {
        this.setVisible(true);
    }

    public ViewAvailabilityGUI(RoomTable rTable, BookingTable bTable, TermTable tTable) {
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
        btnClose = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        lblAvailability = new javax.swing.JLabel();
        lblRoomID = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblUnavailability = new javax.swing.JLabel();
        spAvailability = new javax.swing.JScrollPane();
        spStatus = new javax.swing.JScrollPane();
        spUnavailability = new javax.swing.JScrollPane();
        taUnavailability = new javax.swing.JTextArea();
        tfAvailability = new javax.swing.JTextField();
        tfRoomID = new javax.swing.JTextField();
        tfStatus = new javax.swing.JTextField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setName("JFrame");
        setTitle("View Room Availability");
        setPreferredSize(new java.awt.Dimension(315, 435));
        setResizable(false);

        lblAvailability.setText("Availability:");
        lblRoomID.setText("Room ID:");
        lblStatus.setText("Status:");
        lblUnavailability.setText("Unavailability:");

        tfAvailability.setEditable(false);
        spAvailability.setViewportView(tfAvailability);
        tfStatus.setEditable(false);
        spStatus.setViewportView(tfStatus);
        taUnavailability.setLineWrap(true);
        taUnavailability.setEditable(false);
        spUnavailability.setViewportView(taUnavailability);

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblUnavailability)
                                        .addComponent(lblAvailability)
                                        .addComponent(lblStatus)
                                        .addComponent(lblRoomID)
                                        .addComponent(btnClose))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(tfRoomID)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btnSearch))
                                                .addComponent(spUnavailability, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                                        .addComponent(spStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblRoomID)
                                        .addComponent(tfRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSearch))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(spAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblUnavailability)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnClose))
                                        .addComponent(spUnavailability, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
        if (tfRoomID.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Error: Room ID cannot be blank. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            int roomID = intTryParse(tfRoomID.getText(), Integer.MIN_VALUE);
            if (roomTable.getRoomFromTable(roomID) == null) {
                JOptionPane.showMessageDialog(null,
                        "Error: Room ID does not exist. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                Room room = roomTable.getRoomFromTable(roomID);
                tfStatus.setText(room.getStatus());
                tfAvailability.setText(room.getAvailability());
                taUnavailability.setText("");
                for (LocalDate d : room.getUnavailability()) {
                    taUnavailability.append(d + "\n");
                }
                if (taUnavailability.getText().equals("")) {
                    taUnavailability.setText("None");
                }
            }
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        updateRoomTable();
        updateBookingTable();
        updateTermTable();
    }

    private void updateRoomTable() {
        roomTable.equals(roomTable.getTable());
    }

    private void updateBookingTable() {
        bookingTable.equals(bookingTable.getTable());
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
