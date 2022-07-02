package org.example.dao;

import org.example.baseUtils.BaseUtils;
import org.example.config.HibernateConfig;
import org.example.domains.project.ProjectEntity;
import org.example.dto.project.ProjectColumnDTO;
import org.example.dto.project.ProjectCreateDTO;
import org.example.exceptions.DaoException;
import org.hibernate.Session;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class ProjectDAO extends GenericDAO<ProjectEntity> {
    private static ProjectDAO projectDAO;

    private ProjectDAO() {
    }

    public static ProjectDAO getInstance() {
        if (Objects.isNull(projectDAO))
            projectDAO = new ProjectDAO();
        return projectDAO;
    }

    public String getProjectList(Long id) throws DaoException {

        String result;
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        try {
            CallableStatement callableStatement = session.doReturningWork(connection -> {
                CallableStatement function = connection.prepareCall(
                        "{ ? = call project.project_list(?)}"
                );
                function.registerOutParameter(1, Types.VARCHAR);
                function.setLong(2, id);
                function.execute();
                return function;
            });
            try {
                result = callableStatement.getString(1);
            } catch (SQLException e) {

                throw new DaoException(e.getMessage());
            }

            return result;

        } catch (Exception e) {
            throw new DaoException(e.getCause().getLocalizedMessage());
        } finally {
            session.getTransaction().commit();
            session.close();
        }


    }

    public Long addProject(ProjectCreateDTO projectCreateDTO) throws DaoException {
        Long result = null;
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            CallableStatement callableStatement = session.doReturningWork(connection -> {
                CallableStatement function = connection.prepareCall(
                        "{? = call project.project_create(?)}");
                function.registerOutParameter(1, Types.BIGINT);
                function.setString(2, BaseUtils.gson.toJson(projectCreateDTO));
                function.execute();
                return function;
            });
            try {
                result = callableStatement.getLong(1);
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
            return result;
        } catch (Exception e) {
            throw new DaoException(e.getCause().getLocalizedMessage());
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public Long addProjectColumn(ProjectColumnDTO projectColumnDTO, Long userId) throws DaoException {
        Long result = null;
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            CallableStatement callableStatement = session.doReturningWork(connection -> {
                CallableStatement function = connection.prepareCall(
                        "{? = call project.project_column_create(?,?)}");
                function.registerOutParameter(1, Types.BIGINT);
                function.setString(2, BaseUtils.gson.toJson(projectColumnDTO));
                function.setLong(3,userId);
                function.execute();
                return function;
            });
            try {
                result = callableStatement.getLong(1);
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
            return result;
        } catch (Exception e) {
            throw new DaoException(e.getCause().getLocalizedMessage());
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public String getProjectInfo(Long projectId, Long userId) throws DaoException {
        String result;
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        try {
            CallableStatement callableStatement = session.doReturningWork(connection -> {
                CallableStatement function = connection.prepareCall(
                        "{ ? = call project.project_details(?,?)}"
                );
                function.registerOutParameter(1, Types.VARCHAR);
                function.setLong(2, projectId);
                function.setLong(3, userId);
                function.execute();
                return function;
            });
            try {
                result = callableStatement.getString(1);
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }


        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        } finally {
            session.getTransaction().commit();
            session.close();
        }
        return result;
    }

    public String editProjectColumn(ProjectColumnDTO projectColumnDTO) throws DaoException {

        String result;
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        try {
            CallableStatement callableStatement = session.doReturningWork(connection -> {
                CallableStatement function = connection.prepareCall(
                        "{ ? = call project.project_column_update(?)}"
                );
                function.registerOutParameter(1, Types.VARCHAR);
                function.execute();
                return function;
            });
            try {
                result = callableStatement.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException(e.getMessage());
            }


        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException(e.getCause().getLocalizedMessage());
        } finally {
            session.getTransaction().commit();
            session.close();
        }
        return result;
    }
}
