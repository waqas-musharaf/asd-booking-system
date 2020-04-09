import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class RemoveRoomGUI extends javax.swing.JFrame implements Runnable, Observer {
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JLabel lblRoomID;
    private javax.swing.JTextField tfRoomID;

    private RoomTable roomTable;
    private BookingTable bookingTable;
    private TermTable termTable;

    @Override
    public void run() {
        this.setVisible(true);
    }

    public RemoveRoomGUI(RoomTable rTable, BookingTable bTable, TermTable tTable) {
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
        lblRoomID = new javax.swing.JLabel();
        tfRoomID = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setName("JFrame");
        setTitle("Remove Room");
        setPreferredSize(new java.awt.Dimension(300, 112));
        setResizable(false);

        lblRoomID.setText("Room ID:");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(lblRoomID)
                                                .addGap(29, 29, 29)
                                                .addComponent(tfRoomID))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnCancel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                                                .addComponent(btnConfirm)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblRoomID)
                                        .addComponent(tfRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
        } else {
            int roomID = intTryParse(tfRoomID.getText(), Integer.MIN_VALUE);
            if (roomID == Integer.MIN_VALUE) {
                JOptionPane.showMessageDialog(null,
                        "Error: Room ID is invalid. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                if (roomTable.getRoomFromTable(roomID) == null) {
                    JOptionPane.showMessageDialog(null,
                            "Error: Room ID not found, cannot remove room. Please try again.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    tfRoomID.setText("");
                } else {
                    roomTable.removeRoomFromTable(roomTable.getRoomFromTable(roomID));
                    JOptionPane.showMessageDialog(null,
                            "Success: Room removed successfully.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
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
