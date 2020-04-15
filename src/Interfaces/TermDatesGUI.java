package Interfaces;

import Objects.BookingTable;
import Objects.RoomTable;
import Objects.TermTable;

import javax.swing.*;
import java.time.LocalDate;
import java.util.*;

public class TermDatesGUI extends javax.swing.JFrame implements Runnable, Observer {
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnRemove;
    private javax.swing.JLabel lblTermDates;
    private javax.swing.JScrollPane spTermDates;
    private javax.swing.JTextArea taTermDates;

    private RoomTable roomTable;
    private BookingTable bookingTable;
    private TermTable termTable;

    @Override
    public void run() {
        this.setVisible(true);
    }

    public TermDatesGUI(RoomTable rTable, BookingTable bTable, TermTable tTable) {
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
        btnClose = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        lblTermDates = new javax.swing.JLabel();
        spTermDates = new javax.swing.JScrollPane();
        taTermDates = new javax.swing.JTextArea();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setName("JFrame");
        setTitle("Configure Term Dates");
        setPreferredSize(new java.awt.Dimension(380, 300));
        setResizable(false);

        lblTermDates.setText("Current Term Dates:");

        taTermDates.setLineWrap(true);
        taTermDates.setEditable(false);
        spTermDates.setViewportView(taTermDates);

        btnAdd.setText("Add Term Dates");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        btnRemove.setText("Remove Term Dates");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTermDates)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(spTermDates, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btnRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTermDates)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnAdd)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnRemove)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnClose))
                                        .addComponent(spTermDates, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        DateRangePanel addDates = new DateRangePanel();
        int input = JOptionPane.showConfirmDialog(null, addDates, "Add Term Dates" , JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        if (input == 0) {
            List<LocalDate> dateRange = addDates.returnDateRange();
            for (LocalDate d : dateRange) {
                if (!termTable.hasDate(d)) {
                    if (d.getDayOfWeek().getValue() != 6 && d.getDayOfWeek().getValue() != 7) {
                        termTable.addDate(d);
                    }
                }
            }
        }
    }

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {
        DateRangePanel removeDates = new DateRangePanel();
        int input = JOptionPane.showConfirmDialog(null, removeDates, "Remove Term Dates" , JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        if (input == 0) {
            List<LocalDate> dateRange = removeDates.returnDateRange();
            for (LocalDate d : dateRange) {
                if (termTable.hasDate(d)) {
                    termTable.removeDate(d);
                }
            }
        }
    }

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
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
        taTermDates.setText("");
        ArrayList<LocalDate> table = termTable.getTable();
        Collections.sort(table);

        for (LocalDate d : table) {
            taTermDates.append(d + "\n");
        }
    }
}
