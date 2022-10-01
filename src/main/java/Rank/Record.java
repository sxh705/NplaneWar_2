package Rank;

import java.sql.*;

public class Record {
    public static int pscore;
    public static String pname;
    static String[] wrank = new String[100];
    static String[] record = new String[10];
    static String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=PlayerRecord";
    static String userName = "plane_war";
    static String userPwd = "wuqiz2005";

    static Connection con;
    static Statement st;
    static ResultSet rs;

    public static void update() {
        //int num;
        try {
            con = DriverManager.getConnection(URL, userName, userPwd);
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String cnt = "select count(distinct score) cnt from All_Player where player_name='" + pname + "'";
            rs = st.executeQuery(cnt);
            while (rs.next()) {
                if (rs.getInt("cnt") == 10) {
                    int m = 0;
                    String min = "select min(score) m from All_Player where player_name='" + pname + "'";
                    rs = st.executeQuery(min);
                    while (rs.first())
                        m = rs.getInt("m");
                    if (pscore > m) {
                        String del = "delete from All_Player where player_name='" + pname + "' and score=" + m;
                        st.executeUpdate(del);
                    }
                }
            }
            String ins = "insert into All_Player values('" + pname + "'," + pscore + ")";
            st.executeUpdate(ins);

            /*String sql="select * from All_Player where player_name='"+pname+"'";
            if(!st.executeQuery(sql).first()){
                String ins = "insert into All_Player values ('"+pname+"',"+pscore+")";
                st.executeUpdate(ins);
            }
            else{
                String cmp="select score from All_Player where player_name='"+pname+"'";
                rs=st.executeQuery(cmp);
                while(rs.next()){
                    if(pscore>rs.getInt("score")){
                        String up="update All_Player set score="+pscore+"where player_name='"+pname+"'";
                        st.executeUpdate(up);
                    }
                }
            }*/
        } catch (SQLException e) {
            System.out.println(e);
        }
        //num = st.executeUpdate(sql);
            /*while (rs.next()){
                System.out.println(rs.getString("score"));
            }*/
    }

    public static void showrank() {
        try {
            int i = 0;
            con = DriverManager.getConnection(URL, userName, userPwd);
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String rank = "select player_name,max(score) ms from All_Player group by player_name order by ms desc";
            rs = st.executeQuery(rank);

            while (rs.next()) {

                if (i < 100)
                    wrank[i++] = rs.getString("player_name") + "     " + rs.getInt("ms");
                else
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void showrec() {
        record = new String[10];
        System.out.println("名字" + pname);
        try {
            int i = 0;
            con = DriverManager.getConnection(URL, userName, userPwd);
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String rec = "select score from All_player where player_name='" + pname + "' order by score desc ";
            rs = st.executeQuery(rec);
            while (rs.next()) {
                if (i < 10)
                    record[i++] = Integer.toString(rs.getInt("score"));
                else
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

