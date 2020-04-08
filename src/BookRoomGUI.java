import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class BookRoomGUI extends javax.swing.JFrame implements Runnable, Observer {
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JComboBox<String> cbTime;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNotes;
    private javax.swing.JLabel lblRoomID;
    private javax.swing.JLabel lblTime;
    private javax.swing.JTextField tfContact;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfNotes;
    private javax.swing.JTextField tfRoomID;
    private JDatePickerImpl dpDate;

    private BookingTable bookingTable;

    @Override
    public void run() {
        this.setVisible(true);
    }

    public BookRoomGUI(BookingTable tableState) {
        super();

        bookingTable = tableState;
        bookingTable.addObserver(this);

        initGUI();
        updateSharedTable();
    }

    private void initGUI() {
        btnCancel = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        cbTime = new javax.swing.JComboBox<>();
        lblContact = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblNotes = new javax.swing.JLabel();
        lblRoomID = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        tfContact = new javax.swing.JTextField();
        tfName = new javax.swing.JTextField();
        tfNotes = new javax.swing.JTextField();
        tfRoomID = new javax.swing.JTextField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setName("JFrame");
        setTitle("Book Room");
        setPreferredSize(new java.awt.Dimension(300, 255));
        setResizable(false);

        lblContact.setText("Contact:");
        lblDate.setText("Date:");
        lblName.setText("Name:");
        lblNotes.setText("Notes:");
        lblRoomID.setText("Room ID:");
        lblTime.setText("Time:");

        cbTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"AM", "PM"}));
        cbTime.setSelectedIndex(-1);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        btnConfirm.setText("Confirm");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        dpDate = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnCancel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                                                .addComponent(btnConfirm))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))
                                                .addGap(35, 35, 35)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblRoomID)
                                                        .addComponent(lblDate)
                                                        .addComponent(lblTime)
                                                        .addComponent(lblName)
                                                        .addComponent(lblContact)
                                                        .addComponent(lblNotes))
                                                .addGap(29, 29, 29)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(tfRoomID)
                                                        .addComponent(dpDate)
                                                        .addComponent(cbTime, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(tfName)
                                                        .addComponent(tfContact)
                                                        .addComponent(tfNotes))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblRoomID)
                                        .addComponent(tfRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblDate)
                                        .addComponent(dpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTime)
                                        .addComponent(cbTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblName)
                                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblContact)
                                        .addComponent(tfContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNotes)
                                        .addComponent(tfNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCancel)
                                        .addComponent(btnConfirm))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {
        if (tfRoomID.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Error: Room ID cannot be blank. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (dpDate.getModel().getValue() == null) {
            JOptionPane.showMessageDialog(null,
                    "Error: Date cannot be blank. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (cbTime.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(null,
                    "Error: Time cannot be blank. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (tfName.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Error: Name cannot be blank. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (tfContact.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Error: Contact cannot be blank. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            int roomID = intTryParse(tfRoomID.getText(), Integer.MIN_VALUE);

            //date.getMonth() returns a value between 0 and 11, not 1-12; +1 as a workaround
            //date.getDay() however, returns a value between 1 and 31. Very weird...
            LocalDate lDate = LocalDate.of(dpDate.getModel().getYear(),
                    dpDate.getModel().getMonth() + 1,
                    dpDate.getModel().getDay());

            LocalTime lTime = LocalTime.MIN;

            if (cbTime.getSelectedIndex() == 0) {
                lTime = lTime.plusHours(9);
            } else if (cbTime.getSelectedIndex()== 1) {
                lTime = lTime.plusHours(16);
            }

            LocalDateTime dateTime = LocalDateTime.of(lDate, lTime);

            if (roomID == Integer.MIN_VALUE) {
                JOptionPane.showMessageDialog(null,
                        "Error: Room ID is invalid. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (dateTime.compareTo(LocalDateTime.now()) < 0) {
                JOptionPane.showMessageDialog(null,
                        "Error: Date is invalid. Booking date cannot be before current date. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int id = 1;
                Booking maxId;

                try {
                    maxId = Collections.max(bookingTable.getTable(), Comparator.comparing(Booking::getBookingId));
                } catch (NoSuchElementException e) {
                    maxId = null;
                }

                if (maxId != null) {
                    id = maxId.bookingId + 1;
                }

                Booking booking = new Booking(
                        id,
                        roomID,
                        dateTime,
                        tfName.getText(),
                        tfContact.getText(),
                        tfNotes.getText());

                bookingTable.addBookingToTable(booking);
                this.dispose();
            }
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // the method called when the shared table changes - updates the GUI if required
        updateSharedTable();
    }

    private void updateSharedTable() {
        bookingTable.equals(bookingTable.getTable());
    }

    public int intTryParse(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
