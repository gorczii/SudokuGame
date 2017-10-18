package logic;

import java.sql.*;


/**
 * Created by jusia on 02.06.2017.
 */
public class JdbcSudokuBoardDao implements Dao<SudokuBoard> { // not autocloseable because streams are autocloseable -try with resources


    private static Connection connection = null;
    private static Statement stmt = null;
    private static String dbURL = "jdbc:derby:TestBaza;create=true";

    public JdbcSudokuBoardDao() {
       try {
            //Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //ew org.apache.derby.jdbc.EmbeddedDriver();
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:BazaTest;create=true");
            Statement stm = connection.createStatement();
            stm.execute("CREATE TABLE Boards(" +
                  "board_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
                    "boardName VARCHAR(256))");
            System.out.println("jusia");
            stm.close();

            stm = connection.createStatement();
            stm.execute("CREATE TABLE BoardFields(" +
                    "field_id INTEGER GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
                    "board_id INTEGER," +
                    "x INTEGER," +
                    "y INTEGER," +
                    "fieldValue INTEGER," +
                    "CONSTRAINT board_fk FOREIGN KEY (board_id) REFERENCES Boards(board_id))");

            stm.close();

           /* stm = connection.createStatement();
            stm.execute("INSERT INTO customers(name, surname, email, phone_number) VALUES('Mike', 'Normans', 'm.normans@gmail.com', '654-456-546')"); */
           // stm.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SudokuBoard read() {

        String query = "SELECT fieldValue from BoardFields where x = ? and y = ?";


        try {

            //Statement stmt = connection.createStatement();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {

                    PreparedStatement stmt = connection.prepareStatement(query);
                    stmt.setInt(1, i);
                    stmt.setInt(2, j);
                    ResultSet rs = stmt.executeQuery();
                    System.out.println(rs);
                    rs.close();
                    stmt.close();
                }

            }
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }

        return new SudokuBoard();

    }

   /* private void createConnection() {
        try {
            Class.forName(" org.apache.derby.jdbc.EmbeddedDriver");
            //Get a connection
            connection = DriverManager.getConnection(dbURL);
        } catch (Exception except) {
            except.printStackTrace();
        }

        try {
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE Boards(" +
                    "board_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
                            "boardName VARCHAR(256))";
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }

        try {
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE BoardFields(" +
                    "field_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
                    "board_id INTEGER NOT NULL FOREIGN KEY REFERENCES Boards(board_id)," +
                    "x INTEGER NOT NULL," +
                    "y INTEGER NOT NULL," +
                    "fieldValue INTEGER NOT NULL)";
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        } */


    @Override
    public void write(SudokuBoard board) {
        try {
            //Statement st = connection.createStatement();
            //int m = st.executeUpdate(
            String query = "INSERT INTO Boards(boardName) VALUES(?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, board.getName());
            stmt.executeUpdate();


            String queryTwo = "INSERT INTO BoardFields(board_id, x, y, fieldValue) VALUES (1, ?, ?, ?)";
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    stmt = connection.prepareStatement(queryTwo);
                    stmt.setInt(1, i);
                    stmt.setInt(2, j);
                    stmt.setInt(3, board.getBoardElement(i, j).getFieldValue());

                    stmt.executeUpdate();

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();


        }
    }



    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }


    public static void main(String[] args) {
        JdbcSudokuBoardDao test = new JdbcSudokuBoardDao();
      //  test.createConnection();
        test.write(new SudokuBoard());
        test.read();

    }




}

