package com.stanleypham.dao.impl;

import com.stanleypham.dao.GenericDao;
import com.stanleypham.mapper.ResultSetModelMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AbstractDao<T> implements GenericDao<T> {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    public Connection getConnection() {
        try {
            Class.forName(resourceBundle.getString("driverName"));
            String url = resourceBundle.getString("url");
            String username = resourceBundle.getString("username");
            String password = resourceBundle.getString("password");
            return DriverManager.getConnection(
                    url, username, password
            );
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    @Override
    public <T> List<T> sqlQuery(String sqlQuery,
                                ResultSetModelMapper<T> resultSetModelMapper,
                                Object... parameters
    ) {
        Connection connection = getConnection();
        List<T> returnResultsList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(sqlQuery);
                setQueryParameter(preparedStatement, parameters);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    returnResultsList.add(
                            resultSetModelMapper.mappingResultSetToModel(resultSet)
                    ); // return Model from Result Set
                }
                return returnResultsList;
            } catch (SQLException e) {
                return null;
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public Long insert(String sqlQuery, Object... parameters) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id = null;
        if (connection != null) {
            try {
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(sqlQuery,
                        Statement.RETURN_GENERATED_KEYS
                );
                setQueryParameter(preparedStatement, parameters);
                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    id = resultSet.getLong(1);
                }
                connection.commit();
                return id;
            } catch (SQLException e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException e1) {
                        return null;
                    }
                }
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e2) {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public void updateDelete(String sqlQuery, Object... parameters) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        if (connection != null) {
            try {
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(sqlQuery);
                setQueryParameter(preparedStatement, parameters);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override
    public int count(String sqlQuery, Object... parameters) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(sqlQuery);
                setQueryParameter(preparedStatement, parameters);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
                return count;
            } catch (SQLException e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException e1) {
                        return 0;
                    }
                }
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e2) {
                    return 0;
                }
            }
        }
        return 0;
    }

    private void setQueryParameter(PreparedStatement preparedStatement,
                                   Object... parameters) {
        try {
            for (int i = 0; i < parameters.length; i++) {
                int index = i + 1; //parameters within the SQL query
                if (parameters[i] instanceof Long) {
                    preparedStatement.setLong(index, (Long) parameters[i]);
                } else if (parameters[i] instanceof String) {
                    preparedStatement.setString(index, (String) parameters[i]);
                } else if (parameters[i] instanceof Integer) {
                    preparedStatement.setInt(index, (Integer) parameters[i]);
                } else if (parameters[i] instanceof Timestamp) {
                    preparedStatement.setTimestamp(index, (Timestamp) parameters[i]);
                } else if (parameters[i] == null) {
                    preparedStatement.setNull(index, Types.NULL);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
