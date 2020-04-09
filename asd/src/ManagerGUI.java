import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class ManagerGUI extends javax.swing.JFrame implements Runnable, Observer {
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAvailability;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnTermDates;
    private javax.swing.JLabel lblBookings;
    private javax.swing.JLabel lblRooms;
    private javax.swing.JScrollPane spBookings;
    private javax.swing.JScrollPane spRooms;
    private javax.swing.JTable tblBookings;
    private javax.swing.JTable tblRooms;

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
        btnAvailability = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnTermDates = new javax.swing.JButton();
        lblBookings = new javax.swing.JLabel();
        lblRooms = new javax.swing.JLabel();
        spBookings = new javax.swing.JScrollPane();
        spRooms = new javax.swing.JScrollPane();
        tblBookings = new javax.swing.JTable() {
            @Override
            public String getToolTipText (MouseEvent e) {
                int r = rowAtPoint(e.getPoint());
                int c = columnAtPoint(e.getPoint());
                Object value = getValueAt(r,c);
                return value == null ? null : value.toString();
            }
        };
        tblRooms = new javax.swing.JTable() {
            @Override
            public String getToolTipText (MouseEvent e) {
                int r = rowAtPoint(e.getPoint());
                int c = columnAtPoint(e.getPoint());
                Object value = getValueAt(r,c);
                return value == null ? null : value.toString();
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("JFrame");
        setTitle("Room Manager GUI");
        setPreferredSize(new java.awt.Dimension(650, 875));
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

        btnAvailability.setText("Change Room Availability");

        btnSearch.setText("Search Rooms");
        btnAdd.setText("Add Room");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        btnRemove.setText("Remove Room");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        btnTermDates.setText("Configure Term Dates");
        btnTermDates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTermDatesActionPerformed(evt);
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
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnAdd)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnRemove)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnSearch))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblRooms)
                                                        .addComponent(lblBookings)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(btnAvailability)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnTermDates)))
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
                                        .addComponent(btnRemove)
                                        .addComponent(btnSearch))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnAvailability)
                                        .addComponent(btnTermDates))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {
        new AddRoomGUI(roomTable, bookingTable, termTable).setVisible(true);
    }

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {
        new RemoveRoomGUI(roomTable, bookingTable, termTable).setVisible(true);
    }

    private void btnTermDatesActionPerformed(java.awt.event.ActionEvent evt) {
        new TermDatesGUI(roomTable, bookingTable, termTable).setVisible(true);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        updateRoomTable();
        updateBookingTable();
        updateTermTable();
    }

    private void updateRoomTable() {
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
}
