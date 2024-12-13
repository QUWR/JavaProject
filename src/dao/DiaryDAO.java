package dao;

import domain.DiaryDTO;
import util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DiaryDAO {

    Database db;

    public DiaryDAO(Database db) {
        this.db = db;
    }


    public int insert(DiaryDTO diary){
        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;

        con = db.getCon();

        String sql = "insert into diary(year, month, day, icon, diary)"
                + "values (?, ?, ?, ?, ?)"
                + "on duplicate key update diary = values(diary), icon = values(icon)";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, diary.getYear());
            pstmt.setInt(2, diary.getMonth());
            pstmt.setInt(3, diary.getDay());
            pstmt.setInt(4, diary.getIcon());
            System.out.println(diary.getIcon());
            pstmt.setString(5, diary.getDiary());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public DiaryDTO select(DiaryDTO diaryDTO){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        String diary = "";
        int icon = 1;
        con = db.getCon();
        String sql = "select diary, icon from diary where year = ? and month = ? and day = ?";

        try {

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, diaryDTO.getYear());
            pstmt.setInt(2, diaryDTO.getMonth());
            pstmt.setInt(3, diaryDTO.getDay());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                diary = rs.getString("diary");
                icon = rs.getInt("icon");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        diaryDTO.setDiary(diary);
        diaryDTO.setIcon(icon);

        return diaryDTO;
    }




}
