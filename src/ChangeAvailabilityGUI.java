import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChangeAvailabilityGUI extends javax.swing.JFrame implements Runnable, Observer {
    private javax.swing.JButton btnAvailable;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSelect;
    private javax.swing.JButton btnUnavailable;
    private javax.swing.JLabel lblDates;
    private javax.swing.JLabel lblFrom;
    private javax.swing.JLabel lblNotes;
    private javax.swing.JLabel lblRoomID;
    private javax.swing.JLabel lblUntil;
    private javax.swing.JTextField tfFrom;
    private javax.swing.JTextField tfNotes;
    private javax.swing.JTextField tfRoomID;
    private javax.swing.JTextField tfUntil;

    private RoomTable roomTable;
    private BookingTable bookingTable;
    private TermTable termTable;

    @Override
    public void run() {
        this.setVisible(true);
    }

    public ChangeAvailabilityGUI(RoomTable rTable, BookingTable bTable, TermTable tTable) {
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
        btnAvailable = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnSelect = new javax.swing.JButton();
        btnUnavailable = new javax.swing.JButton();
        lblDates = new javax.swing.JLabel();
        lblFrom = new javax.swing.JLabel();
        lblNotes = new javax.swing.JLabel();
        lblRoomID = new javax.swing.JLabel();
        lblUntil = new javax.swing.JLabel();
        tfFrom = new javax.swing.JTextField();
        tfNotes = new javax.swing.JTextField();
        tfRoomID = new javax.swing.JTextField();
        tfUntil = new javax.swing.JTextField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setName("JFrame");
        setTitle("Change Room Availability");
        setPreferredSize(new java.awt.Dimension(345, 240));
        setResizable(false);

        lblDates.setText("Date Range:");
        lblFrom.setText("From:");
        lblNotes.setText("Notes:");
        lblRoomID.setText("Room ID:");
        lblUntil.setText("Until:");

        tfFrom.setEditable(false);
        tfUntil.setEditable(false);

        btnAvailable.setText("Set Available");
        btnAvailable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvailableActionPerformed(evt);
            }
        });
        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        btnSelect.setText("Select Dates");
        btnSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectActionPerformed(evt);
            }
        });
        btnUnavailable.setText("Set Unavailable");
        btnUnavailable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnavailableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblDates)
                                                        .addComponent(lblRoomID))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(btnSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                                                        .addComponent(tfRoomID)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblFrom)
                                                        .addComponent(lblUntil))
                                                .addGap(48, 48, 48)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(tfFrom, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                                                        .addComponent(tfUntil)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(lblNotes)
                                                        .addGap(44, 44, 44)
                                                        .addComponent(tfNotes, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(btnCancel)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btnAvailable)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btnUnavailable))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblRoomID)
                                        .addComponent(tfRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblDates, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSelect))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tfFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblFrom))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblUntil)
                                        .addComponent(tfUntil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNotes)
                                        .addComponent(tfNotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCancel)
                                        .addComponent(btnAvailable)
                                        .addComponent(btnUnavailable))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void btnSelectActionPerformed(java.awt.event.ActionEvent evt) {
        DateRangePanel selectDates = new DateRangePanel();
        int input = JOptionPane.showConfirmDialog(null, selectDates, "Select Dates" , JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        if (input == 0) {
            List<LocalDate> dateRange = selectDates.returnDateRange();
            if (dateRange != null && dateRange.size() > 0) {
                tfFrom.setText(dateRange.get(0).toString());
                tfUntil.setText(dateRange.get(dateRange.size() - 1).toString());
            }
        }
    }

    private void btnAvailableActionPerformed(java.awt.event.ActionEvent evt) {
        if (tfRoomID.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Error: Room ID cannot be blank. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (tfFrom.getText().equals("") || tfUntil.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Error: Dates cannot be blank. Please try again.",
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
                LocalDate ldFrom = LocalDate.parse(tfFrom.getText());
                LocalDate ldUntil = LocalDate.parse(tfUntil.getText());
                long dayDifference = ChronoUnit.DAYS.between(ldFrom, ldUntil) + 1;

                List<LocalDate> dateRange = IntStream.iterate(0, i -> i + 1)
                        .limit(dayDifference)
                        .mapToObj(i -> ldFrom.plusDays(i))
                        .collect(Collectors.toList());

                List<LocalDate> retrieved = roomTable.getRoomFromTable(roomID).getUnavailability();
                retrieved.removeAll(dateRange);
                dateRange = retrieved;

                String status = "Normal";
                int countUnavailability = dateRange.stream()
                        .filter(d -> d.compareTo(LocalDate.now()) >= 0)
                        .collect(Collectors.toList()).size();
                if (countUnavailability > 0) {
                    status = "Unavailable for "+ countUnavailability + " days between "
                            + LocalDate.now() + " and " + dateRange.get(dateRange.size() -1);
                }
                if (!tfNotes.getText().equals("")) {
                    status = status + ". Latest Note: " + tfNotes.getText();
                }

                roomTable.updateUnavailability(roomID, status, dateRange);
                this.dispose();
            }
        }
    }

    private void btnUnavailableActionPerformed(java.awt.event.ActionEvent evt) {
        if (tfRoomID.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Error: Room ID cannot be blank. Please try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (tfFrom.getText().equals("") || tfUntil.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Error: Dates cannot be blank. Please try again.",
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
                LocalDate ldFrom = LocalDate.parse(tfFrom.getText());
                LocalDate ldUntil = LocalDate.parse(tfUntil.getText());
                long dayDifference = ChronoUnit.DAYS.between(ldFrom, ldUntil) + 1;

                List<LocalDate> dateRange = IntStream.iterate(0, i -> i + 1)
                        .limit(dayDifference)
                        .mapToObj(i -> ldFrom.plusDays(i))
                        .collect(Collectors.toList());

                if (!roomTable.getRoomFromTable(roomID).unavailability.isEmpty()) {
                    dateRange.addAll(roomTable.getRoomFromTable(roomID).getUnavailability());
                    dateRange = dateRange.stream().distinct().collect(Collectors.toList());
                    Collections.sort(dateRange);
                }

                String status = "Normal";
                int countUnavailability = dateRange.stream()
                        .filter(d -> d.compareTo(LocalDate.now()) >= 0)
                        .collect(Collectors.toList()).size();
                if (countUnavailability > 0) {
                    status = "Unavailable for "+ countUnavailability + " days between "
                            + LocalDate.now() + " and " + dateRange.get(dateRange.size() -1);
                }
                if (!tfNotes.getText().equals("")) {
                    status = status + ". Latest Note: " + tfNotes.getText();
                }

                roomTable.updateUnavailability(roomID, status, dateRange);
                this.dispose();
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
