package com.workfront.internship.booklibrary.dao;

import com.workfront.internship.booklibrary.common.Book;
import com.workfront.internship.booklibrary.common.PickBook;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PickBookDAOImpl extends General implements PickBookDAO {
    public void createPickBook(PickBook pickedBook) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatementAssign = null;

        try{
            connection = DataSource.getInstance().getConnection();
            //connection.setAutoCommit(false);

            String sqlBook = "";


            String sql;
            sql = "INSERT INTO Pick_Book VALUES(?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, pickedBook.getPickId());
            preparedStatement.setInt(2, pickedBook.getBookId());
            preparedStatement.setInt(3, pickedBook.getUserId());
            preparedStatement.setTimestamp(4, new Timestamp(pickedBook.getPickingDate().getTime()));
            preparedStatement.setTimestamp(5, new Timestamp(pickedBook.getReturnDate().getTime()));

            int changeMade = preparedStatement.executeUpdate();
            if(changeMade == 1){  //transaction sarqel

            }




        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }finally {
            closeConnection(preparedStatement, connection);
        }
    }

    public PickBook getPickedBookByID(int id) {
        PickBook pickedBook = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DataSource.getInstance().getConnection();
            pickedBook = new PickBook();
            String sql;
            sql = "select * from Pick_Book where Pick_Book.pick_id =" + pickedBook.getPickId();

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                pickedBook.setPickId(resultSet.getInt("pick_id"));
                pickedBook.setBookId(resultSet.getInt("book_id"));
                pickedBook.setUserId(resultSet.getInt("user_id"));
                pickedBook.setPickingDate(resultSet.getTimestamp("picking_date"));
                pickedBook.setReturnDate(resultSet.getTimestamp("return_date"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }finally {
            closeConnection(resultSet, preparedStatement, connection);
        }
        return pickedBook;
    }

    public List<PickBook> getAllPickedBooks() {
        List<PickBook> pickedBookList = null;
        Book book = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DataSource.getInstance().getConnection();
            pickedBookList = new ArrayList<PickBook>();

            String sql;
            sql = "SELECT * FROM pick_book";

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                PickBook pickedBook = new PickBook();

                pickedBook.setPickId(resultSet.getInt("pick_id"));
                pickedBook.setBookId(resultSet.getInt("book_id"));
                pickedBook.setUserId(resultSet.getInt("user_id"));
                pickedBook.setPickingDate(resultSet.getTimestamp("picking_date"));
                pickedBook.setReturnDate(resultSet.getTimestamp("return_date"));

                pickedBookList.add(pickedBook);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }finally {
            closeConnection(resultSet, preparedStatement, connection);
        }

        return pickedBookList;
    }

    public void updatePickedBook(PickBook pickedBook) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            if(pickedBook.getPickId() != 0){
                connection = DataSource.getInstance().getConnection();
                String sql;
                sql = "UPDATE Pick_Book SET " +
                        "pick_id = ?, book_id = ?, user_id = ?, picking_date = ?, return_date = ? " +
                        "WHERE pick_id = " + pickedBook.getPickId();
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, pickedBook.getPickId());
                preparedStatement.setInt(2, pickedBook.getBookId());
                preparedStatement.setInt(3, pickedBook.getUserId());
                //preparedStatement.setDate(4, pickedBook.getPickingDate()); // TODO
                //preparedStatement.setDate(5, pickedBook.getReturnDate()); // TODO

                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            closeConnection(preparedStatement, connection);
        }
    }

    public void deletePickedBook(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DataSource.getInstance().getConnection();
            String sql;
            sql = "DELETE * FROM Pick_Book where pick_id=" + id;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            closeConnection(preparedStatement, connection);
        }
    }

    public List<PickBook> getAllPickedBooksByUserId(int userId) {
        List<PickBook> pickedBookList = null;
        //Book book = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = DataSource.getInstance().getConnection();
            pickedBookList = new ArrayList<PickBook>();

            String sql;
            sql = "SELECT * FROM pick_book" +
                    "where pick_book.user_id" + userId;

            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                PickBook pickedBook = new PickBook();

                pickedBook.setPickId(resultSet.getInt("pick_id"));
                pickedBook.setBookId(resultSet.getInt("book_id"));
                pickedBook.setUserId(resultSet.getInt("user_id"));
                pickedBook.setPickingDate(resultSet.getTimestamp("picking_date"));
                pickedBook.setReturnDate(resultSet.getTimestamp("return_date"));

                pickedBookList.add(pickedBook);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }finally {
            closeConnection(resultSet, preparedStatement, connection);
        }

        return pickedBookList;
    }
}