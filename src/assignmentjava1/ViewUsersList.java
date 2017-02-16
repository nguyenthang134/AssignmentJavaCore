/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignmentjava1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thang
 */
public class ViewUsersList extends JFrame {

    private static JTable jTable = new JTable();
    private static DefaultTableModel tableModel = new DefaultTableModel();

    public ViewUsersList() throws SQLException {
        String colsName[] = {"ID", "Name", "Username"};
        tableModel.setColumnIdentifiers(colsName);
        jTable.setModel(tableModel);

        AssignmentJavaCore.connect();
        initComponent();
        updateData(view());
    }

    public void updateData(ResultSet result) {
        String colsName[] = {"ID", "Name", "Username"};
        tableModel.setColumnIdentifiers(colsName);
        try {
            while (result.next()) {
                String rows[] = new String[3];
                rows[0] = result.getString(1);
                rows[1] = result.getString(2);
                rows[2] = result.getString(3);
                tableModel.addRow(rows);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initComponent() {
        this.setSize(400, 200);
        JScrollPane scroll = new JScrollPane(jTable);
        this.add(scroll);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public ResultSet view() {
        ResultSet rs = null;
        String sql = "SELECT * FROM users";
        try {
            Statement statement = AssignmentJavaCore.createStm();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
