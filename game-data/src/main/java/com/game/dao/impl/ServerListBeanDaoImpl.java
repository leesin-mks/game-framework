package com.game.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.game.dao.IServerListBeanDao;
import com.game.database.DBHelper;
import com.game.database.DBParamWrapper;
import com.game.database.DataExecutor;
import com.game.database.DataOption;
import com.game.database.dao.BaseDao;
import com.game.entity.bean.ServerListBean;

public class ServerListBeanDaoImpl extends BaseDao<ServerListBean> implements IServerListBeanDao
{
    public ServerListBeanDaoImpl(DBHelper helper)
    {
        super(helper);
    }

    @Override
    public boolean add(ServerListBean ServerListBean)
    {
        int id = -1;
        String sql = "insert into t_s_server_list(`id`, `server_type`, `ip`, `inner_ip`, `port`, `inner_port`, `web_port`, `inner_webport`, `key_min`, `key_max`, `server_state`, `online_num`, `max_online`, `remark`) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        DBParamWrapper params = new DBParamWrapper();
        params.put(Types.INTEGER, ServerListBean.getId());
        params.put(Types.SMALLINT, ServerListBean.getServerType());
        params.put(Types.VARCHAR, ServerListBean.getIp());
        params.put(Types.VARCHAR, ServerListBean.getInnerIp());
        params.put(Types.VARCHAR, ServerListBean.getPort());
        params.put(Types.VARCHAR, ServerListBean.getInnerPort());
        params.put(Types.VARCHAR, ServerListBean.getWebPort());
        params.put(Types.VARCHAR, ServerListBean.getInnerWebport());
        params.put(Types.INTEGER, ServerListBean.getKeyMin());
        params.put(Types.INTEGER, ServerListBean.getKeyMax());
        params.put(Types.SMALLINT, ServerListBean.getServerState());
        params.put(Types.INTEGER, ServerListBean.getOnlineNum());
        params.put(Types.INTEGER, ServerListBean.getMaxOnline());
        params.put(Types.VARCHAR, ServerListBean.getRemark());
        id = add(sql, params);
        ServerListBean.setId(id);
        return id > 0;
    }

    @Override
    public boolean update(ServerListBean ServerListBean)
    {
        String sql = "update t_s_server_list set `server_type`=?, `ip`=?, `inner_ip`=?, `port`=?, `inner_port`=?, `web_port`=?, `inner_webport`=?, `key_min`=?, `key_max`=?, `server_state`=?, `online_num`=?, `max_online`=?, `remark`=? where `id`=?;";
        DBParamWrapper params = new DBParamWrapper();
        params.put(Types.SMALLINT, ServerListBean.getServerType());
        params.put(Types.VARCHAR, ServerListBean.getIp());
        params.put(Types.VARCHAR, ServerListBean.getInnerIp());
        params.put(Types.VARCHAR, ServerListBean.getPort());
        params.put(Types.VARCHAR, ServerListBean.getInnerPort());
        params.put(Types.VARCHAR, ServerListBean.getWebPort());
        params.put(Types.VARCHAR, ServerListBean.getInnerWebport());
        params.put(Types.INTEGER, ServerListBean.getKeyMin());
        params.put(Types.INTEGER, ServerListBean.getKeyMax());
        params.put(Types.SMALLINT, ServerListBean.getServerState());
        params.put(Types.INTEGER, ServerListBean.getOnlineNum());
        params.put(Types.INTEGER, ServerListBean.getMaxOnline());
        params.put(Types.VARCHAR, ServerListBean.getRemark());
        params.put(Types.INTEGER, ServerListBean.getId());
        return update(sql, params);
    }

    @Override
    public boolean delete(ServerListBean ServerListBean)
    {
        String sql = "delete from t_s_server_list where `id`=?;";
        DBParamWrapper params = new DBParamWrapper();
        params.put(Types.INTEGER, ServerListBean.getId());
        return update(sql, params);
    }

    @Override
    public boolean addOrUpdate(ServerListBean ServerListBean)
    {
        String sql = "insert into t_s_server_list(`id`, `server_type`, `ip`, `inner_ip`, `port`, `inner_port`, `web_port`, `inner_webport`, `key_min`, `key_max`, `server_state`, `online_num`, `max_online`, `remark`) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) on DUPLICATE KEY update `server_type`=?,`ip`=?,`inner_ip`=?,`port`=?,`inner_port`=?,`web_port`=?,`inner_webport`=?,`key_min`=?,`key_max`=?,`server_state`=?,`online_num`=?,`max_online`=?,`remark`=?;";
        DBParamWrapper params = new DBParamWrapper();
        params.put(Types.INTEGER, ServerListBean.getId());
        params.put(Types.SMALLINT, ServerListBean.getServerType());
        params.put(Types.VARCHAR, ServerListBean.getIp());
        params.put(Types.VARCHAR, ServerListBean.getInnerIp());
        params.put(Types.VARCHAR, ServerListBean.getPort());
        params.put(Types.VARCHAR, ServerListBean.getInnerPort());
        params.put(Types.VARCHAR, ServerListBean.getWebPort());
        params.put(Types.VARCHAR, ServerListBean.getInnerWebport());
        params.put(Types.INTEGER, ServerListBean.getKeyMin());
        params.put(Types.INTEGER, ServerListBean.getKeyMax());
        params.put(Types.SMALLINT, ServerListBean.getServerState());
        params.put(Types.INTEGER, ServerListBean.getOnlineNum());
        params.put(Types.INTEGER, ServerListBean.getMaxOnline());
        params.put(Types.VARCHAR, ServerListBean.getRemark());
        params.put(Types.SMALLINT, ServerListBean.getServerType());
        params.put(Types.VARCHAR, ServerListBean.getIp());
        params.put(Types.VARCHAR, ServerListBean.getInnerIp());
        params.put(Types.VARCHAR, ServerListBean.getPort());
        params.put(Types.VARCHAR, ServerListBean.getInnerPort());
        params.put(Types.VARCHAR, ServerListBean.getWebPort());
        params.put(Types.VARCHAR, ServerListBean.getInnerWebport());
        params.put(Types.INTEGER, ServerListBean.getKeyMin());
        params.put(Types.INTEGER, ServerListBean.getKeyMax());
        params.put(Types.SMALLINT, ServerListBean.getServerState());
        params.put(Types.INTEGER, ServerListBean.getOnlineNum());
        params.put(Types.INTEGER, ServerListBean.getMaxOnline());
        params.put(Types.VARCHAR, ServerListBean.getRemark());
        return update(sql, params);
    }

    @Override
    public boolean deleteByKey(Object... ids)
    {
        String sql = "delete from t_s_server_list where `id`=?;";
        DBParamWrapper params = new DBParamWrapper();
        params.put(Types.INTEGER, ids[0]);
        return update(sql, params);
    }

    @Override
    public ServerListBean getByKey(Object... ids)
    {
        String sql = "select * from t_s_server_list where `id`=?;";
        DBParamWrapper params = new DBParamWrapper();
        params.put(Types.INTEGER, ids[0]);
        ServerListBean ServerListBean = query(sql, params);
        return ServerListBean;
    }

    @Override
    public List<ServerListBean> listAll()
    {
        String sql = "select * from t_s_server_list;";
        List<ServerListBean> ServerListBeans = queryList(sql);
        return ServerListBeans;
    }

    @Override
    public int[] addOrUpdateBatch(List<ServerListBean> ServerListBeans)
    {
        String sql = "insert into t_s_server_list(`id`, `server_type`, `ip`, `inner_ip`, `port`, `inner_port`, `web_port`, `inner_webport`, `key_min`, `key_max`, `server_state`, `online_num`, `max_online`, `remark`) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) on DUPLICATE KEY update `server_type`=?,`ip`=?,`inner_ip`=?,`port`=?,`inner_port`=?,`web_port`=?,`inner_webport`=?,`key_min`=?,`key_max`=?,`server_state`=?,`online_num`=?,`max_online`=?,`remark`=?;";
        int[] effectedRows = getDBHelper().sqlBatch(sql, ServerListBeans, new DataExecutor<int[]>()
        {
            @Override
            public int[] execute(PreparedStatement statement, Object... objects) throws Exception
            {
                @SuppressWarnings("unchecked")
                List<ServerListBean> ServerListBeans = (List<ServerListBean>) objects[0];
                for (ServerListBean ServerListBean : ServerListBeans)
                {
                    DBParamWrapper params = new DBParamWrapper();
                    params.put(Types.INTEGER, ServerListBean.getId());
                    params.put(Types.SMALLINT, ServerListBean.getServerType());
                    params.put(Types.VARCHAR, ServerListBean.getIp());
                    params.put(Types.VARCHAR, ServerListBean.getInnerIp());
                    params.put(Types.VARCHAR, ServerListBean.getPort());
                    params.put(Types.VARCHAR, ServerListBean.getInnerPort());
                    params.put(Types.VARCHAR, ServerListBean.getWebPort());
                    params.put(Types.VARCHAR, ServerListBean.getInnerWebport());
                    params.put(Types.INTEGER, ServerListBean.getKeyMin());
                    params.put(Types.INTEGER, ServerListBean.getKeyMax());
                    params.put(Types.SMALLINT, ServerListBean.getServerState());
                    params.put(Types.INTEGER, ServerListBean.getOnlineNum());
                    params.put(Types.INTEGER, ServerListBean.getMaxOnline());
                    params.put(Types.VARCHAR, ServerListBean.getRemark());
                    params.put(Types.SMALLINT, ServerListBean.getServerType());
                    params.put(Types.VARCHAR, ServerListBean.getIp());
                    params.put(Types.VARCHAR, ServerListBean.getInnerIp());
                    params.put(Types.VARCHAR, ServerListBean.getPort());
                    params.put(Types.VARCHAR, ServerListBean.getInnerPort());
                    params.put(Types.VARCHAR, ServerListBean.getWebPort());
                    params.put(Types.VARCHAR, ServerListBean.getInnerWebport());
                    params.put(Types.INTEGER, ServerListBean.getKeyMin());
                    params.put(Types.INTEGER, ServerListBean.getKeyMax());
                    params.put(Types.SMALLINT, ServerListBean.getServerState());
                    params.put(Types.INTEGER, ServerListBean.getOnlineNum());
                    params.put(Types.INTEGER, ServerListBean.getMaxOnline());
                    params.put(Types.VARCHAR, ServerListBean.getRemark());
                    statement = getDBHelper().prepareCommand(statement, params.getParams());
                    statement.addBatch();
                }
                return statement.executeBatch();
            }
        });
        return effectedRows;
    }

    @Override
    public void addBatch(List<ServerListBean> ServerListBeans, Connection conn) throws SQLException
    {
        String sql = "insert into t_s_server_list(`id`, `server_type`, `ip`, `inner_ip`, `port`, `inner_port`, `web_port`, `inner_webport`, `key_min`, `key_max`, `server_state`, `online_num`, `max_online`, `remark`) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        for (ServerListBean ServerListBean : ServerListBeans)
        {
            DBParamWrapper params = new DBParamWrapper();
            params.put(Types.INTEGER, ServerListBean.getId());
            params.put(Types.SMALLINT, ServerListBean.getServerType());
            params.put(Types.VARCHAR, ServerListBean.getIp());
            params.put(Types.VARCHAR, ServerListBean.getInnerIp());
            params.put(Types.VARCHAR, ServerListBean.getPort());
            params.put(Types.VARCHAR, ServerListBean.getInnerPort());
            params.put(Types.VARCHAR, ServerListBean.getWebPort());
            params.put(Types.VARCHAR, ServerListBean.getInnerWebport());
            params.put(Types.INTEGER, ServerListBean.getKeyMin());
            params.put(Types.INTEGER, ServerListBean.getKeyMax());
            params.put(Types.SMALLINT, ServerListBean.getServerState());
            params.put(Types.INTEGER, ServerListBean.getOnlineNum());
            params.put(Types.INTEGER, ServerListBean.getMaxOnline());
            params.put(Types.VARCHAR, ServerListBean.getRemark());
            statement = getDBHelper().prepareCommand(statement, params.getParams());
            statement.addBatch();
        }
        statement.executeBatch();
        super.close(statement);
    }

    @Override
    public void updateBatch(List<ServerListBean> ServerListBeans, Connection conn) throws SQLException
    {
        String sql = "update t_s_server_list set `server_type`=?, `ip`=?, `inner_ip`=?, `port`=?, `inner_port`=?, `web_port`=?, `inner_webport`=?, `key_min`=?, `key_max`=?, `server_state`=?, `online_num`=?, `max_online`=?, `remark`=? where `id`=?";
        PreparedStatement statement = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        for (ServerListBean ServerListBean : ServerListBeans)
        {
            DBParamWrapper params = new DBParamWrapper();
            params.put(Types.SMALLINT, ServerListBean.getServerType());
            params.put(Types.VARCHAR, ServerListBean.getIp());
            params.put(Types.VARCHAR, ServerListBean.getInnerIp());
            params.put(Types.VARCHAR, ServerListBean.getPort());
            params.put(Types.VARCHAR, ServerListBean.getInnerPort());
            params.put(Types.VARCHAR, ServerListBean.getWebPort());
            params.put(Types.VARCHAR, ServerListBean.getInnerWebport());
            params.put(Types.INTEGER, ServerListBean.getKeyMin());
            params.put(Types.INTEGER, ServerListBean.getKeyMax());
            params.put(Types.SMALLINT, ServerListBean.getServerState());
            params.put(Types.INTEGER, ServerListBean.getOnlineNum());
            params.put(Types.INTEGER, ServerListBean.getMaxOnline());
            params.put(Types.VARCHAR, ServerListBean.getRemark());
            params.put(Types.INTEGER, ServerListBean.getId());
            statement = getDBHelper().prepareCommand(statement, params.getParams());
            statement.addBatch();
        }
        statement.executeBatch();
        super.close(statement);
    }

    @Override
    public int[] deleteBatch(List<ServerListBean> ServerListBeans)
    {
        String sql = "delete from t_s_server_list where `id`=?;";
        int[] effectedRows = getDBHelper().sqlBatch(sql, ServerListBeans, new DataExecutor<int[]>()
        {
            @Override
            public int[] execute(PreparedStatement statement, Object... objects) throws Exception
            {

                @SuppressWarnings("unchecked")
                List<ServerListBean> ServerListBeans = (List<ServerListBean>) objects[0];
                for (ServerListBean ServerListBean : ServerListBeans)
                {
                    DBParamWrapper params = new DBParamWrapper();
                    params.put(Types.INTEGER, ServerListBean.getId());
                    statement = getDBHelper().prepareCommand(statement, params.getParams());
                    statement.addBatch();
                }
                return statement.executeBatch();
            }
        });
        return effectedRows;
    }

    @Override
    public ServerListBean rsToEntity(ResultSet rs) throws SQLException
    {
        ServerListBean ServerListBean = new ServerListBean();
        ServerListBean.setId(rs.getInt("id"));
        ServerListBean.setServerType(rs.getInt("server_type"));
        ServerListBean.setIp(rs.getString("ip"));
        ServerListBean.setInnerIp(rs.getString("inner_ip"));
        ServerListBean.setPort(rs.getString("port"));
        ServerListBean.setInnerPort(rs.getString("inner_port"));
        ServerListBean.setWebPort(rs.getString("web_port"));
        ServerListBean.setInnerWebport(rs.getString("inner_webport"));
        ServerListBean.setKeyMin(rs.getInt("key_min"));
        ServerListBean.setKeyMax(rs.getInt("key_max"));
        ServerListBean.setServerState(rs.getInt("server_state"));
        ServerListBean.setOnlineNum(rs.getInt("online_num"));
        ServerListBean.setMaxOnline(rs.getInt("max_online"));
        ServerListBean.setRemark(rs.getString("remark"));
        ServerListBean.setOp(DataOption.NONE);
        return ServerListBean;
    }

}
